<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.users"/></title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            //            $("#navUsers").addClass("active");
            $(function() {
                $('[data-toggle="tooltip"]').tooltip({'placement': 'top'});
                $(".userInfo").click(function(e) {
                    e.preventDefault();
                    var userId = $(this).attr("id");
                    openModal(userId);
                });
            });
            function openModal(userId) {
                var frame = $(".modal-body").children('iframe');
                frame.attr('src','${contextPath}/user/'+userId+"?internal=true");
                $("#editModal").modal("show");
                frame.load(function() {
                    frame.contents().find('.btn').hide();
                    var form = frame.contents().find('.form-horizontal');
                    $('.btn-primary').click(function () {
                        form.find('.btn').trigger('click');
                    });
                });

            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <c:set var="passInfo"><spring:message code="users.table.tooltip"/></c:set>
            <table class="table table-striped" data-toggle="tooltip" data-toggle="tooltip" data-placement="top" title="" data-original-title="${passInfo}">
                <tr>
                    <th><spring:message code="users.table.number"/></th>
                    <th><spring:message code="users.table.login"/></th>
                    <th><spring:message code="users.table.name"/></th>
                </tr>
                <c:forEach var="curUser" items="${users}" varStatus="st">
                    <%--<joda:format var="joinDate" value="${curUser.joinDate}" pattern="dd-MM-yyyy"/>--%>
                    <tr id="${curUser.id}" class="userInfo">
                        <td>${st.index+1}</td>
                        <td>${curUser.login}</td>
                        <td>${curUser.fullName}</td>
                        <td><spring:message code="${curUser.post}"/></td>
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
                            <iframe src="" frameborder="0" height="500" width="99.6%"></iframe>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="users.table.modal.close"/></button>
                            <button class="btn btn-primary"><spring:message code="users.table.modal.save"/></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
    </jsp:body>

</t:genericpage>
