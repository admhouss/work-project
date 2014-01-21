function init(contextPath, productName) {
    var fullProperties = {labels: {}, enums:{}};
    var showItemsHtml;
    $.ajax({
        url: contextPath+"/auth/administration/editor/get/full/properties/" + productName,
        type: 'POST',
        cashed: false,
        'success': function (properties) {
            console.log(properties);
            fullProperties = properties;
        }
    });
    $('.add-new').click(function(e) {
        e.preventDefault();
        showItemsHtml = $('.thumbnails').html();
        addNewItem($(this));
    });

    function addNewItem(addButton) {
        var addHtml = "<form class='form-horizontal'>";
        for(var key in fullProperties.labels) {
            if (fullProperties.labels.hasOwnProperty(key)) {
                var value = fullProperties.labels[key];
                addHtml += "<div class='control-group'><label for='inputLabel"+key+"' class='control-label'>";
                addHtml += value + "</label>";
                addHtml += "<div class='controls'><input type='text' class='form-control' id='inputLabel"+key+"'></div></div>"
            }
        }
        for(key in fullProperties.enums) {
            if (fullProperties.enums.hasOwnProperty(key)) {
                value = fullProperties.enums[key];
                addHtml += "<div class='control-group'><label for='inputLabel"+key+"' class='control-label'>";
                addHtml += key + "</label>";
                addHtml += "<div class='controls'><select name='inputEnum"+key+"' class='form-control' id='inputEnum"+key+"'>";
                for (key in value) {
                    if (value.hasOwnProperty(key)) {
                        addHtml += "<option>"+value[key]+"</option>"
                    }
                }
                addHtml += "</select></div></div>"
            }
        }
        addHtml += "</form>";
        addButton.after(addHtml);
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