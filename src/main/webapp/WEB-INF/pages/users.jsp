<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:adminGenericPage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.users"/></title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            //            $("#navUsers").addClass("active");
            var doRegistration = true;
            $(function() {
                $('#inputPassword3').tooltip({trigger: "focus"});
                $(".user-edit").click(function(e) {
                    e.preventDefault();
                    var userLogin = $(this).attr("id");
                    userLogin = userLogin.substr(5);
                    console.log(userLogin);
                    openModal(userLogin);
                });
                $(".user-repo").click(function(e) {
                    e.preventDefault();
                    var userLogin = $(this).attr("id");
                    userLogin = userLogin.substr(5);
                    console.log(userLogin);
                    $.ajax({
                        url: "${contextPath}/auth/administration/users/edit/repo/"+userLogin,
                        type: 'POST',
                        cashed: false,
                        'success': function(repo) {
                            if (repo == "star") {
                                $($(this).children('i')).removeClass('.icon-star-empty').addClass('.icon-star');
                            } else {
                                $($(this).children('i')).removeClass('.icon-star').addClass('.icon-star-empty');
                            }
                        }
                    });
                });
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
                function openModal(userLogin) {
                    var userForEdit;
                    $.ajax({
                        url: "${contextPath}/auth/administration/users/edit/get/"+userLogin,
                        type: 'POST',
                        cashed: false,
                        'success': function(editUser) {
                            $('#inputFirstName').val(editUser.firstName);
                            $('#inputLastName').val(editUser.lastName);
                            $('#inputLogin').val(editUser.login);
                            $("#editModal").modal("show");
                            userForEdit = editUser;
                        }
                    });

                    $('.edit-save').click(function(e) {
                        e.preventDefault();
                        doRegistration = true;
                        nameCheck();
                        if (doRegistration) {
                            var dataJSON = JSON.stringify({
                                login: userForEdit.login,
                                firstName: userForEdit.firstName,
                                lastName: userForEdit.lastName,
                                newFirstName:$('#inputFirstName').val(),
                                newLastName:$('#inputLastName').val(),
                                newPassword:$('#inputPassword3').val(),
                                newLogin:$('#inputLogin').val()});
                            $.ajax({
                                url: "${contextPath}/auth/administration/users/do/edit/",
                                type: 'POST',
                                contentType: 'application/json',
                                cashed: false,
                                data: dataJSON,
                                'success': function(data) {
                                    if (data.loginIsFree == false && data.success == false) {
                                        $('#loginIsExist').removeClass("hidden");
                                    }
                                    if (data.success == true) {
                                        console.log(data);
                                        alert(data);
                                        $('#success').removeClass("hidden");
                                        $('#error').addClass("hidden");
                                        $('#loginIsExist').addClass("hidden");
//                                        $('#navFullName').html(data.newLastName + " " + data.newFirstName + "&nbsp;<i class='caret'></i>");
                                    }
                                }
                            });
                        }
                    });
                }
            });

        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <c:set var="principalId"><sec:authentication property="principal.user.id"/></c:set>
            <c:set var="loginPH"><spring:message code="edit.login"/></c:set>
            <c:set var="firstNamePH"><spring:message code="edit.firstName"/></c:set>
            <c:set var="lastNamePH"><spring:message code="edit.lastName"/></c:set>
            <c:set var="passwordPH"><spring:message code="edit.password"/></c:set>
            <button class="btn btn-success"><i class="icon-user icon-white"></i>&nbsp;<spring:message code="users.add"/></button>
            <table class="table table-hover" data-toggle="tooltip" data-toggle="tooltip" data-placement="top" title="" data-original-title="${passInfo}">
                <tr>
                    <th><spring:message code="users.table.number"/></th>
                    <th><spring:message code="users.table.login"/></th>
                    <th><spring:message code="users.table.name"/></th>
                    <th colspan="3"><spring:message code="users.table.action"/></th>
                </tr>
                <c:forEach var="curUser" items="${users}" varStatus="st">
                    <tr id="${curUser.id}" class="userInfo">
                        <td>${st.index+1}</td>
                        <td>${curUser.login}</td>
                        <td>${curUser.fullName}</td>
                        <td><a id="edit-${curUser.login}" class="user-edit"><i class="icon-pencil"></i></a>&nbsp;</td>
                        <c:if test="${curUser.id != principalId}">
                        <td><a id="repo-${curUser.login}" class="user-repo"><sec:authorize access="hasRole('ROLE_ADMINISTRATOR')"><i class="icon-star-empty"></i></sec:authorize><sec:authorize access="hasRole('ROLE_SUPERVISOR')"><i class="icon-star"></i></sec:authorize></a>&nbsp;</td>
                        <td><a href="${contextPath}/auth/administration/users/edit/remove/${curUser.login}"><i class="icon-remove"></i></a>&nbsp;</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <!-- Modal -->
            <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h3 id="myModalLabel"><spring:message code="users.table.edit"/></h3>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal">
                                <div class="control-group">
                                    <label for="inputFirstName" class="control-label">${firstNamePH}</label>

                                    <div class="controls">
                                        <input type="text" class="form-control" id="inputFirstName"
                                               placeholder="${firstNamePH}" name="newFirstName" required="true">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="inputLastName" class="control-label">${lastNamePH}</label>

                                    <div class="controls">
                                        <input type="text" class="form-control" id="inputLastName" placeholder="${lastNamePH}" name="newLastName" required="true">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="inputLogin" class="control-label">${loginPH}</label>

                                    <div class="controls">
                                        <input type="text" class="form-control" id="inputLogin" placeholder="${loginPH}" name="newLogin" required="true">
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
                            </form>
                        </div>
                        <div class="modal-footer">
                            <div class="alert alert-danger hidden" id="error">
                                <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels"/>
                            </div>
                            <div class="alert alert-danger hidden" id="loginIsExist">
                                <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.login.exist"/>
                            </div>
                            <div class="alert alert-success hidden" id="success">
                                <spring:message code="edit.submit.success"/>
                            </div>
                            <button class="btn btn-primary edit-save"><spring:message code="users.table.modal.save"/></button>
                            <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="users.table.modal.close"/></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
    </jsp:body>

</t:adminGenericPage>

<%--<div class="control-group">--%>
    <%--<div class="controls">--%>
        <%--<div class="alert alert-danger hidden" id="error">--%>
            <%--<strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="alert alert-danger hidden" id="loginIsExist">--%>
        <%--<strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.login.exist"/>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div class="alert alert-success hidden" id="success">--%>
    <%--<spring:message code="edit.submit.success"/>--%>
<%--</div>--%>