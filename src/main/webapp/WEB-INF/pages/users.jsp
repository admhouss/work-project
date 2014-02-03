<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:adminGenericPage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.users"/></title>
        <script src="${contextPath}/assets/js/users.js"></script>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $(document).ready(function() {
                usersControls("${contextPath}");
            })
        </script>
    </jsp:attribute>

    <jsp:attribute name="content">
        <c:set var="principalId"><sec:authentication property="principal.user.id"/></c:set>
        <c:set var="loginPH"><spring:message code="edit.login"/></c:set>
        <c:set var="firstNamePH"><spring:message code="edit.firstName"/></c:set>
        <c:set var="lastNamePH"><spring:message code="edit.lastName"/></c:set>
        <c:set var="passwordPH"><spring:message code="edit.password"/></c:set>
        <button class="btn btn-success add-new"><i class="icon-user icon-white"></i>&nbsp;<spring:message code="users.add"/></button>
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
                        <h3 id="myModalLabel"><spring:message code="users.table.edit"/>&nbsp;<img id="loading" width="16" height="16" alt="Loading" class="hidden" src="${contextPath}/assets/img/util/loading.gif"/></h3>
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
    </jsp:attribute>

</t:adminGenericPage>