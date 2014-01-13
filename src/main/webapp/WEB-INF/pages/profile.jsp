<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminGenericPage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.user.edit"/></title>
    </jsp:attribute>

   <jsp:attribute name="scripts">
        <style type="text/css">
            .form-edit {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 60px auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }
            .form-signin input[type="text"],
            .form-signin input[type="password"] {
                font-size: 16px;
                height: auto;
                margin-bottom: 15px;
                padding: 7px 9px;
            }
        </style>
        <script type="text/javascript">
            var doRegistration = true;
            function nameCheck() {
                var firstNameStr = $('#inputFirstName').val();
                var lastNameStr = $('#inputLastName').val();
                var loginStr = $('#inputLogin').val();
                if (firstNameStr.length == 0 ||
                    lastNameStr.length == 0 ||
                    loginStr.length == 0) {
                    $('#error').removeClass("hidden");
                    doRegistration &= false;
                } else {
                    $('#error').addClass("hidden");
                    doRegistration &= true;
                }
            }
            function validate() {
                doRegistration = true;
                nameCheck();
                if (doRegistration) {
                    var form = $('.form-edit');
                    var formData = form.serializeArray();
                    var firstNameStr = $('#inputFirstName').val();
                    var lastNameStr = $('#inputLastName').val();
                    var loginStr = $('#inputLogin').val();
                    formData.push({name: "login", value: "${user.login}"});
                    formData.push({name: "firstName", value: "${user.firstName}"});
                    formData.push({name: "lastName", value: "${user.lastName}"});
                    var dataJSON = JSON.stringify({ login:"${user.login}",
                                                    firstName:"${user.firstName}",
                                                    lastName:"${user.lastName}",
                                                    newFirstName:$('#inputFirstName').val(),
                                                    newLastName:$('#inputLastName').val(),
                                                    newPassword:$('#inputPassword3').val()});
                    $.ajax({
                        url: form.attr('action'),
                        type: 'POST',
                        contentType: 'application/json; charset=utf-8',
                        data: dataJSON,
                        'success': function(data) {
                            if (data.loginIsFree == false) {
                                $('#loginIsExist').removeClass("hidden");
                            }
                            if (data.success == true) {
                                $('#success').removeClass("hidden");
                                $('#error').addClass("hidden");
                                $('#loginIsExist').addClass("hidden");
                            }
                        }
                    });
                }
            }
            $(function() {
                $('[data-toggle="tooltip"]').tooltip({'placement': 'right', trigger: 'focus'});
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty param.nonav}">
            <%--<div class="offset3 span6">--%>
            <div class="container">
        </c:if>

        <form class="form-edit" role="form" action="${contextPath}/auth/administration/users/do/edit/" method="post">
            <c:set var="loginPH"><spring:message code="edit.login"/></c:set>
            <c:set var="firstNamePH"><spring:message code="edit.firstName"/></c:set>
            <c:set var="lastNamePH"><spring:message code="edit.lastName"/></c:set>
            <c:set var="passwordPH"><spring:message code="edit.password"/></c:set>

            <div class="control-group">
                <label for="inputFirstName" class="control-label">${firstNamePH}</label>

                <div class="controls">
                    <input type="text" value="${user.firstName}" class="form-control" id="inputFirstName"
                           placeholder="${firstNamePH}" name="newFirstName" required="true">
                </div>
            </div>
            <div class="control-group">
                <label for="inputLastName" class="control-label">${lastNamePH}</label>

                <div class="controls">
                    <input type="text" value="${user.lastName}" class="form-control" id="inputLastName" placeholder="${lastNamePH}" name="newLastName" required="true">
                </div>
            </div>
            <div class="control-group">
                <label for="inputLogin" class="control-label">${loginPH}</label>

                <div class="controls">
                    <input type="text" value="${user.login}" class="form-control" id="inputLogin" placeholder="${loginPH}" name="newLogin" required="true">
                </div>
            </div>

            <div class="control-group">
                <label for="inputPassword3" class="control-label">${passwordPH}</label>

                <div class="controls">
                    <c:set var="passInfo"><spring:message code="edit.passInfo"/></c:set>
                    <input type="password" class="form-control" id="inputPassword3" placeholder="${passwordPH}" name="newPassword"
                           data-toggle="tooltip" data-placement="top" title="${passInfo}">
                </div>
            </div>
            <div class="control-group">
                <div class=" controls">
                    <button type="submit" class="btn btn-default" onclick="validate()"><spring:message code="edit.submit"/></button>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                        <div class="alert alert-danger hidden" id="error">
                            <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels"/>
                        </div>
                    </div>
                        <div class="alert alert-danger hidden" id="loginIsExist">
                            <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.login.exist"/>
                        </div>
                    </div>
                        <div class="alert alert-success hidden" id="success">
                            <spring:message code="edit.submit.success"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <c:if test="${empty param.nonav}">
            </div>
        </c:if>
    </jsp:body>

</t:adminGenericPage>
