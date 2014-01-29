function init(contextPath, productName) {
    var fullProperties = {labels: {}, enums:{}}
      , showItemsHtml
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
            $('.thumbnails').addClass('hidden');
            $('.add-new').addClass('hidden');
            $('.form-horizontal').removeClass('hidden');
            $('#propTitle').text("Добавление");
        }
    });
    $('.btn-edit').click(function(e) {
        e.preventDefault();
        var id = $(this).attr('id');
            $('.thumbnails').addClass('hidden');
            $('.add-new').addClass('hidden');
            $('.form-horizontal').removeClass('hidden');
            $('#propTitle').text("Добавление");
    });


    function renderProperties() {
        var addHtml = "<div class='form-horizontal offset2 hidden'><h3 id='propTitle'></h3>"
            , key
            , enumeration
            , label;
        for(key in fullProperties.labels) {
            if (fullProperties.labels.hasOwnProperty(key)) {
                label = fullProperties.labels[key];
                addHtml += "<div class='control-group'><label for='input"+label.first+"' class='control-label'>";
                addHtml += label.second + "</label>";
                addHtml += "<div class='controls'><input type='text' class='form-control input-label' name='"+label.first+"' id='input"+label.first+"'></div></div>"
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
                addHtml += "</select></div></div>"
            }
        }
        addHtml += "<div class='control-group'>"+
            "<div class='controls'><button id='save' class='btn btn-primary add-save form-control'>Сохранить</button><button class='btn add-cancel'>Отмена</button></div></div>"+
            "</div>";
        isPropRendered = true;

        $('.add-new').after(addHtml);
        $('.add-cancel').click(function(e) {
            e.preventDefault();
            $('.form-horizontal').addClass('hidden');
            $('.add-new').removeClass('hidden');
            $('.thumbnails').removeClass('hidden');
        });
        $('.add-save').click(function(e) {
            e.preventDefault();
            sendNewItem();
        });
    }
    function sendNewItem() {
        var inputs = $('.input-label')
            , enums = $('.input-enum')
            , data = {properties: []}
            , property = {first:{}, second: {}}
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
        data = JSON.stringify(data);
        $.ajax({
            url: contextPath+"/auth/administration/editor/" + productName + "/new",
            data: data,
            contentType: 'application/json',
            type: 'POST',
            cashed: false,
            'success': function (properties) {
                console.log(properties);
                if (properties.success) {

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
}  /*<select name="carlist" form="carform">
 <option value="volvo">Volvo</option>
 <option value="saab">Saab</option>
 <option value="opel">Opel</option>
 <option value="audi">Audi</option>
 </select>*/
/*
 <form class='form-horizontal'>
 <div class='control-group'>
 <label for='inputFirstName' class='control-label'>${firstNamePH}</label>

 <div class='controls'>
 <input type='text' class='form-control' id='inputFirstName'
 placeholder='${firstNamePH}' name='newFirstName' required='true'>
 </div>
 </div>
 <div class='control-group'>
 <label for='inputLastName' class='control-label'>${lastNamePH}</label>

 <div class='controls'>
 <input type='text' class='form-control' id='inputLastName' placeholder='${lastNamePH}' name='newLastName' required='true'>
 </div>
 </div>
 <div class='control-group'>
 <label for='inputLogin' class='control-label'>${loginPH}</label>

 <div class='controls'>
 <input type='text' class='form-control' id='inputLogin' placeholder='${loginPH}' name='newLogin' required='true'>
 </div>
 </div>

 <div class='control-group'>
 <label for='inputPassword3' class='control-label'>${passwordPH}</label>

 <div class='controls'>
 <c:set var='passInfo'><spring:message code='edit.passInfo'/></c:set>
 <input type='password' class='form-control' id='inputPassword3' placeholder='${passwordPH}' name='newPassword'
 data-toggle='tooltip' data-placement='top' title='${passInfo}'>
 </div>
 </div>
 <div style='margin-left: 100px;margin-right: 130px;'>
 <div class='alert alert-danger hidden' id='error'>
 <strong><spring:message code='edit.submit.fail.title'/></strong> <spring:message code='edit.error.labels'/>
 </div>
 <div class='alert alert-danger hidden' id='error-add'>
 <strong><spring:message code='edit.submit.fail.title'/></strong> <spring:message code='edit.error.labels.add'/>
 </div>
 <div class='alert alert-danger hidden' id='loginIsExist'>
 <strong><spring:message code='edit.submit.fail.title'/></strong> <spring:message code='edit.error.login.exist'/>
 </div>
 <div class='alert alert-success hidden' id='success'>
 <spring:message code='edit.submit.success'/>
 </div>
 <div class='alert alert-success hidden' id='add-success'>
 <spring:message code='edit.submit.success.add'/>
 </div>
 </div>
 </form>*/
/*
 <button class="btn btn-primary edit-save"><spring:message code="users.table.modal.save"/></button>
 <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="users.table.modal.close"/></button>*/