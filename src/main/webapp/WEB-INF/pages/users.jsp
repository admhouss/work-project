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
            var userForEdit = {login:"", firstName:"",lastName: ""};
            var userLogin;
            $(function() {
                $('#inputPassword3').tooltip({trigger: "focus"});
                var editClick = function(e,$this) {
                    e.preventDefault();
                    userLogin = $this.attr("id");
                    userLogin = userLogin.substr(5);
                    openModal(userLogin);
                };
                var repoClick = function(e,$this) {
                    e.preventDefault();
                    var $href = $this;
                    var $i = $($($href.children('i'))[0]);
                    userLogin = $href.attr("id");
                    userLogin = userLogin.substr(5);
                    $.ajax({
                        url: "${contextPath}/auth/administration/users/edit/repo/"+userLogin,
                        type: 'POST',
                        cashed: false,
                        'success': function(repo) {
                            if (repo == "star") {
                                $i.removeClass('icon-star-empty')
                                $i.addClass('icon-star');
                            } else {
                                $i.removeClass('icon-star')
                                $i.addClass('icon-star-empty');
                            }
                        }
                    });
                }
                var removeClick = function(e, $this) {
                    e.preventDefault();
                    userLogin = $this.attr("id");
                    var $row = $this;
                    userLogin = userLogin.substr(7);
                    $.ajax({
                        url: "${contextPath}/auth/administration/users/edit/remove/",
                        type: 'POST',
                        cashed: false,
                        data: {login:userLogin},
                        'success': function(repo) {
                            $('#user-row-'+userLogin).hide(1000, function () {
                                $('#user-row-'+userLogin).remove();
                            });
                        }
                    });
                }
                $('.add-user').click(function(e) {
                    e.preventDefault();
                    userLogin = "add-new-user";
                    openModal(userLogin);
                });
                $(".user-edit").click(function(e) {
                    editClick(e, $(this));
                });
                $(".user-repo").click(function(e) {
                    repoClick(e, $(this));
                });
                $('.user-remove').click(function(e) {
                    removeClick(e, $(this));
                });
                $('.edit-save').click(function(e) {
                    e.preventDefault();
                    doRegistration = true;
                    cleanMessages()
                    nameCheck(!!((userLogin == "add-new-user")));
                    if (doRegistration) {
                        var dataJSON = JSON.stringify({
                            login: userForEdit.login,
                            firstName: userForEdit.firstName,
                            lastName: userForEdit.lastName,
                            newFirstName:$('#inputFirstName').val(),
                            newLastName:$('#inputLastName').val(),
                            newPassword:$('#inputPassword3').val(),
                            newLogin:$('#inputLogin').val(),
                            role: "1"});
                        $.ajax({
                            url: "${contextPath}/auth/administration/users/do/edit/",
                            type: 'POST',
                            contentType: 'application/json',
                            cashed: false,
                            data: dataJSON,
                            beforeSend: function() {
                                $('#loading').removeClass('hidden');
                            },
                            success: function(data) {
                                $('#loading').addClass('hidden');
                                if (data.loginIsFree == false && data.success == false) {
                                    $('#loginIsExist').removeClass("hidden");
                                    $('#add-success').addClass('hidden');
                                    $('#success').addClass("hidden");
                                }
                                if (data.success == true) {
                                    console.log(data);
//                                        alert(data);
                                    if (userLogin == "add-new-user") {
                                        $('#add-success').removeClass('hidden');
                                    } else {
                                        $('#success').removeClass("hidden");
                                    }
                                    refreshRow(userLogin, data);
                                    $('#error').addClass("hidden");
                                    $('#loginIsExist').addClass("hidden");
//                                        $('#navFullName').html(data.newLastName + " " + data.newFirstName + "&nbsp;<i class='caret'></i>");
                                }
                            }
                        });
                    }
                });
                function nameCheck(isNew) {
                    var firstNameStr = $('#inputFirstName').val();
                    var lastNameStr = $('#inputLastName').val();
                    var loginStr = $('#inputLogin').val();
                    var passStr = $('#inputPassword3').val();
                    if (firstNameStr.length == 0 || lastNameStr.length == 0 || loginStr.length == 0 ) {
                        if (isNew && passStr.length == 0) {
                            $('#error-add').removeClass("hidden");
                        } else {
                            $('#error').removeClass("hidden");
                        }
                        doRegistration &= false;
                    } else {
                        cleanMessages()
                        doRegistration &= true;
                    }
                }
                function resetModal(obj) {
                    $('#inputFirstName').val(obj.firstName);
                    $('#inputLastName').val(obj.lastName);
                    $('#inputLogin').val(obj.login);
                    $('#inputPassword3').val("");
                }
                function cleanMessages() {
                    $('#success').addClass("hidden");
                    $('#error').addClass("hidden");
                    $('#error-add').addClass("hidden");
                    $('#loginIsExist').addClass("hidden");
                    $('#add-success').addClass("hidden");
                }
                function openModal(userLogin) {
                    userForEdit = {login:"", firstName:"",lastName: ""};
                    cleanMessages();
                    if (userLogin != "add-new-user") {
                        $.ajax({
                            url: "${contextPath}/auth/administration/users/edit/get/"+userLogin,
                            type: 'POST',
                            cashed: false,
                            'success': function(editUser) {
                                resetModal(editUser)
                                $('#myModalLabel').html("<spring:message code='users.table.edit'/>");
                                $('#inputPassword3').tooltip('enable');
                                $("#editModal").modal("show");
                                userForEdit.login = editUser.login;
                                userForEdit.firstName = editUser.firstName;
                                userForEdit.lastName = editUser.lastName;
                            }
                        });
                    } else {
                        $('#myModalLabel').html("<spring:message code='users.table.modal.add'/>");
                        userForEdit = {login:"", firstName:"",lastName: ""}
                        resetModal(userForEdit);
                        $('#inputPassword3').tooltip('disable');
                        $("#editModal").modal("show");
                    }


                };
                function refreshRow(login, data) {
                    if (login == "add-new-user") {
                        var lastRow = $('.table tr:last')
                        var index = parseInt(lastRow.find('.index').text()) + 1;
                        var star = "<i class='icon-star-empty'></i>";
                        lastRow.after("<tr id='"+'user-row-' + data.newLogin+"' class='userInfo'>" +
                                "<td class='index'>"+index+"</td>" +
                                "<td>"+data.newLogin+"</td>" +
                                "<td>"+data.newLastName + " " + data.newFirstName+"</td>" +
                                "<td><a href='' id='edit-"+data.newLogin+"' class='user-edit'><i class='icon-pencil'></i></a>&nbsp;\n"+
                                "<a href='' id='repo-"+data.newLogin+"' class='user-repo'>"+star+"</a>&nbsp;\n"+
                                "<a href='' id='remove-"+data.newLogin+"'><i class='icon-remove'></i></a></td></tr>")
                        var editHref = $('#edit-'+data.newLogin);
                        var repoHref = $('#repo-'+data.newLogin);
                        var removeHref = $('#remove-'+data.newLogin);
                        editHref.click(function(e) {
                            editClick(e, editHref);
                        });
                        repoHref.click(function(e) {
                            repoClick(e, repoHref);
                        });
                        removeHref.click(function(e) {
                            removeClick(e,removeHref);
                        });

                        $('.table tr:last').show(1000);
                    }
                    var $row = $('#user-row-'+login);
                    $row.find('.login').text(data.newLogin);
                    $row.find('.fullName').text(data.newLastName + " " + data.newFirstName);
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
            <button class="btn btn-success add-user"><i class="icon-user icon-white"></i>&nbsp;<spring:message code="users.add"/></button>
            <table class="table table-hover" data-toggle="tooltip" data-toggle="tooltip" data-placement="top" title="" data-original-title="${passInfo}">
                <tr>
                    <th><spring:message code="users.table.number"/></th>
                    <th><spring:message code="users.table.login"/></th>
                    <th><spring:message code="users.table.name"/></th>
                    <th colspan="3"><spring:message code="users.table.action"/></th>
                </tr>
                <c:forEach var="curUser" items="${users}" varStatus="st">
                    <tr id="user-row-${curUser.login}" class="userInfo">
                        <td class="index">${st.index+1}</td>
                        <td class="login">${curUser.login}</td>
                        <td class="fullName">${curUser.fullName}</td>
                        <td><a href="" id="edit-${curUser.login}" class="user-edit"><i class="icon-pencil"></i></a>&nbsp;
                            <c:if test="${curUser.id != principalId}">
                            <a href="" id="repo-${curUser.login}" class="user-repo"><c:choose><c:when test="${curUser.role == 'ROLE_ADMINISTRATOR'}"><i class="icon-star-empty"></i></c:when><c:when test="${curUser.role == 'ROLE_SUPERVISOR'}"><i class="icon-star"></i></c:when></c:choose></a>&nbsp;
                            <a href="" id="remove-${curUser.login}" class="user-remove"><i class="icon-remove"></i></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!-- Modal -->
            <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h3 id="myModalLabel"><spring:message code="users.table.edit"/>&nbsp;<img id="loading" width="16" height="16" alt="Loading" src="${contextPath}/image/gif/util/loading"/></h3>
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
                                <div style="margin-left: 100px;margin-right: 130px;">
                                    <div class="alert alert-danger hidden" id="error">
                                        <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels"/>
                                    </div>
                                    <div class="alert alert-danger hidden" id="error-add">
                                        <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels.add"/>
                                    </div>
                                    <div class="alert alert-danger hidden" id="loginIsExist">
                                        <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.login.exist"/>
                                    </div>
                                    <div class="alert alert-success hidden" id="success">
                                        <spring:message code="edit.submit.success"/>
                                    </div>
                                    <div class="alert alert-success hidden" id="add-success">
                                        <spring:message code="edit.submit.success.add"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
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