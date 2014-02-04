function init(contextPath, productName) {
    var fullProperties = {labels: {}, enums:{}}
      , isPropRendered = false
      , insertObjectMetadata = {model: "", producer: "",productName: ""}
      , isEdit = false;
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
            isEdit = false;
        }
    });

    $('.product-edit').click(function(e) {
        e.preventDefault();
        var properties;
        var productId = $(this).attr('id').substr(5);
        isEdit = true;
        $.ajax({
            url: contextPath + "/auth/administration/editor/get/edit/item/" + productId + "/" + productName,
            type: 'POST',
            cashed: false,
            'success': function (result) {
                properties = result;
                var props = result.properties;
                insertObjectMetadata = {model: result.objectModel, producer: result.objectProducer,productName: result.objectProduct };
                var i;
                for (i = 0; i < props.length; ++i) {
                    var input = $('#input'+props[i].second.first);
                    input.val(props[i].second.second);
                }
                $('.thumbnails').addClass('hide');
                $('.add-new').addClass('hide');
                $('#image-upload-edit').removeClass('hide');
                $('#props-area').removeClass('hide');
                $('#image-upload').removeClass('hide');
                $('#area-title').text("Редактирование");
            }
        });
    });
    $('.product-remove').click(function(e) {
        e.preventDefault();
        var properties;
        var productId = $(this).attr('id').substr(7);
        $.ajax({
            url: contextPath + "/auth/administration/editor/remove/item/" + productId,
            type: 'POST',
            cashed: false,
            'success': function (result) {
               if (result == true) {
                   window.location.href = contextPath + "/auth/administration/editor/show/" + productName
               }
            }
        });
    });

    var selector = $('.thumbnail');
    selector.mouseover(function(e) {
        $(this).find('.offset9').css('visibility','visible');
    });
    selector.mouseout(function(e) {
        $(this).find('.offset9').css('visibility','hidden');
    });
    function getTitle() {
        var title = $('#area-title')
            , titleText = title.text();
        var index = titleText.indexOf(" ");
        if (index != -1) {
            titleText = titleText.substr(0,index);
        }
        return titleText;
    }

    var setTitle = function (str) {
        var titleTag = $('#area-title');
        var title = getTitle();

        return (function() {
            titleTag.text(title + " - " +str);
        })();
    };


    function renderProperties() {
        var totalProps = fullProperties.labels.length + count(fullProperties.enums);
        var firstCol;
        if (totalProps%2 == 0) {
            firstCol = totalProps/2;
        } else {
            firstCol = totalProps/2-totalProps%2 + 1;
        }
        var addHtml = "<div id='props-area' class='hide'><div class='control-group'>"+
                "<div class='controls'><button id='save' class='btn btn-primary add-save form-control'>Сохранить</button>" +
                "<button class='btn add-cancel'>Отмена</button><button class='btn back hide'>Назад</button></div></div><h3 id='area-title'></h3>" +

                "<form id='image-upload' class='form-inline hide'><label>Выберите&nbsp;изображение</label>"+
                "<input id='inputFile' type='file' name='file' size='50' style='position:absolute; top:-200px;'/>" +
                "<button class='btn btn-success form-control open-file'>Открыть</button>" +
                "</form>"+

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
                addHtml += "<div class='controls'><input type='text' class='form-control input-label' name='"+label.first+"' id='input"+label.first+"'></div></div>";
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
            $('#image-upload-edit').addClass('hide');
            $('.add-new').removeClass('hide');
            $('.thumbnails').removeClass('hide');

        });
        $('.back').click(function(e) {
            e.preventDefault();
            window.location.href = contextPath + "/auth/administration/editor/show/" + productName
        })
        $('.add-save').click(function(e) {
            e.preventDefault();
            sendNewItem();
        });
        $('.open-file').click(function(e) {
            e.preventDefault();
            $('#inputFile').click();
        });
        $('input:file').change(function (){
            var fileName = $(this).val();
            $(".filename").html(fileName);
            if (fileName !== "") loadPic();
        });
        $('#btn-redirect').click(function(e) {
            e.preventDefault();
            window.location.href = contextPath + "/auth/administration/editor/show/" + productName
        });


        function loadPic() {
            $('#success').addClass('hide');
            $('#error').addClass('hide');
            setTitle("Ожидание...");
            var data = new FormData();
            jQuery.each($('#inputFile')[0].files, function(i, file) {
                data.append('file-'+i, file);
            });
            $.ajax({
                url: contextPath+"/image/upload/" + insertObjectMetadata.productName + "/" + insertObjectMetadata.producer + "/" + insertObjectMetadata.model,
                type: 'POST',
                data: data,
                processData: false,
                contentType: false,
                cashed: false,
                'success': function (result) {
                    if (result == true) {
                        setTitle("Изображение загружено");
                        $('.add-cancel').addClass('hide');
                        $('.back').removeClass('hide');
                    } else {
                        setTitle("Ошибка при загрузке изорбажения");
                    }
                }
            });
        }
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
        setTitle("Ожидание...");
        for (var i = 0; i < inputs.length; ++i) {
            data.properties.push({first: "label", second: {first: $(inputs[i]).attr('name'), second: $(inputs[i]).val()}});
        }
        for (i = 0; i < enums.length; ++i) {
            data.properties.push({first: "enum", second: {first: $(enums[i]).attr('name'), second: $(enums[i]).val()}});
        }
        for (i = 0; i  < data.properties.length; ++i) {
            $('#input'+data.properties[i].second.first).popover('destroy');
        }
        data = JSON.stringify(data);
        var mode;
        $.ajax({
            url: contextPath+"/auth/administration/editor/edit/" + productName + "/" +insertObjectMetadata.model,
            data: data,
            contentType: 'application/json',
            type: 'POST',
            cashed: false,
            'success': function (properties) {
                if (properties.success) {
                    insertObjectMetadata = {model: properties.objectModel, producer: properties.objectProducer,productName: properties.objectProduct };
                    setTitle("Продукт сохранен");
                    $('#image-upload').removeClass('hide');
                    $('.add-cancel').addClass('hide');
                    $('.back').removeClass('hide');
                } else {
                    if (properties.itemInDB) {
                        setTitle("Ошибка при сохранении продукта - Такая модель уже существует");
                    } else {
                        var i = 0
                            , $input;
                        setTitle("Ошибка при сохранении продукта");
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
            }
        });
    }
}