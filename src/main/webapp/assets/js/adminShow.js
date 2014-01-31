function init(contextPath, productName) {
    var fullProperties = {labels: {}, enums:{}}
      , isPropRendered = false;
    $.ajax({
        url: contextPath+"/auth/administration/editor/get/full/properties/" + productName,
        type: 'POST',
        cashed: false,
        'success': function (properties) {
            console.log(properties);
            fullProperties = properties;
            isPropRendered = true;
            renderProperties();
        }
    });
    $('.add-new').click(function(e) {
        e.preventDefault();
        if (isPropRendered == true) {
            $('.thumbnails').addClass('hide');
            $('.add-new').addClass('hide');
            $('#props-area').removeClass('hide');
            $('#area-title').text("Добавление");
        }
    });
    $('.thumbnail').mouseover(function(e) {
        $(this).find('.offset9').css('visibility','visible');
    });
    $('.thumbnail').mouseout(function(e) {
        $(this).find('.offset9').css('visibility','hidden');
    });

    function renderProperties() {
        var totalProps = fullProperties.labels.length + count(fullProperties.enums);
        var firstCol;
        if (totalProps%2==0) {
            firstCol = totalProps/2;
        } else {
           firstCol = totalProps/2-totalProps%2 + 1;
        }
        var addHtml = "<div id='props-area' class='hide'><div class='control-group'>"+
                "<div class='controls'><button id='save' class='btn btn-primary add-save form-control'>Сохранить</button>" +
                "<button class='btn add-cancel'>Отмена</button></div></div><h3 id='area-title'></h3>" +
                "<div class='row-fluid'>" +
                "<div class='form-horizontal span6'>"
            , key
            , enumeration
            , label
            , i = 0;

        for(key in fullProperties.labels) {
            if (fullProperties.labels.hasOwnProperty(key)) {
                label = fullProperties.labels[key];
                addHtml += "<div class='control-group'><label for='input"+label.first+"' class='control-label'>";
                addHtml += label.second + "</label>";
                addHtml += "<div class='controls'><input type='text' class='form-control input-label' name='"+label.first+"' id='input"+label.first+"'></div></div>"
                ++i;
                if (i == firstCol) {
                    addHtml += "</div><div class='form-horizontal span6'>"
                }
            }
        }
        for(key in fullProperties.enums) {
            if (fullProperties.enums.hasOwnProperty(key)) {
                enumeration = fullProperties.enums[key];
                addHtml += "<div class='control-group'><label for='input"+key+"' class='control-label'>";
                addHtml += enumeration.first + "</label>";
                addHtml += "<div class='controls'><select name='"+key+"' class='form-control input-enum' id='input"+key+"'>";
                for (key in enumeration.second) {
                    if (enumeration.second.hasOwnProperty(key)) {
                        addHtml += "<option value='"+enumeration.second[key].first+"'>"+enumeration.second[key].second+"</option>"
                    }
                }
                addHtml += "</select></div></div>";
                ++i;
                if (i == firstCol) {
                    addHtml += "</div><div class='form-horizontal span6'>"
                }
            }
        }
        addHtml += "</div>";
        isPropRendered = true;

        $('.add-new').after(addHtml);

        $('.input-label').on('keypress', function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                sendNewItem();
            }
        });
        $('.input-enum').on('keypress', function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                sendNewItem();
            }
        });

        $('.add-cancel').click(function(e) {
            e.preventDefault();
            var input;
            for(key in fullProperties.labels) {
                if (fullProperties.labels.hasOwnProperty(key)) {
                    input = $('#input'+fullProperties.labels[key].first);
                    input.popover('destroy');
                    input.val("");
                }
            }
            for(key in fullProperties.enums) {
                if (fullProperties.enums.hasOwnProperty(key)) {
                    input = $('#input'+key);
                    input.popover('destroy');
                    input.val('NAN');
                }
            }
            $('#props-area').addClass('hide');
            $('.add-new').removeClass('hide');
            $('.thumbnails').removeClass('hide');
        });
        $('.add-save').click(function(e) {
            e.preventDefault();
            sendNewItem();
        });
    }
    function count(obj) {
        if (obj.__count__ !== undefined) { // Old FF
            return obj.__count__;
        }
        if (Object.keys) { // ES5
            return Object.keys(obj).length;
        }
        // Everything else:
        var c = 0, p;
        for (p in obj) {
            if (obj.hasOwnProperty(p)) {
                c += 1;
            }
        }
        return c;
    }

    function sendNewItem() {
        var inputs = $('.input-label')
            , enums = $('.input-enum')
            , data = {properties: []}
            , name;
        for (var i = 0; i < inputs.length; ++i) {
            data.properties.push({first: "label", second: {first: $(inputs[i]).attr('name'), second: $(inputs[i]).val()}});
        }
        for (i = 0; i < enums.length; ++i) {
//            data[name] = $(enums[i]).find('option:selected').text();
            data.properties.push({first: "enum", second: {first: $(enums[i]).attr('name'), second: $(enums[i]).val()}});
        }
        for (i = 0; i  < data.properties.length; ++i) {
            $('#input'+data.properties[i].second.first).popover('destroy');
        }
        var temp = data;
        data = JSON.stringify(data);
        $.ajax({
            url: contextPath+"/auth/administration/editor/new/" + productName,
            data: data,
            contentType: 'application/json',
            type: 'POST',
            cashed: false,
            'fail': function (data) {
                alert(data);
            },
            'success': function (properties) {
                console.log(properties);
                if (properties.success) {
                    $('#image-upload').attr('ac')
                } else {
                    var i = 0
                        , $input;
                    for (i; i < properties.notSetFields.length; ++i) {
                        $input = $('#input'+properties.notSetFields[i]);
                        $input.popover({content:"Поле должно быть заполнено", trigger: 'manual'});
                        $input.popover("show");
                    }
                    for (i = 0; i < properties.failedFields.length; ++i) {
                        $input = $('#input'+properties.failedFields[i]);
                        $input.popover({content:"Данные не верные", trigger: 'manual'});
                        $input.popover("show");
                    }
                }
            }
        });
    }
}