<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title><spring:message code="title.index"/></title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
        </script>
    </jsp:attribute>

    <jsp:body>
        <%--<div class="container">--%>
            <%--<br/>--%>
            <%--<h3 class="muted"><spring:message code="week.${currentWeekCode}"/></h3>--%>
            <%--<br/>--%>
            <%--<div class="container-fluid">--%>
                <%--<div class="span2">--%>
                    <%--<ul class="nav nav-pills nav-stacked" id="days">--%>
                        <%--<c:forEach begin="0" end="4" var="idx">--%>
                            <%--<li id="day${idx}"><a href="#"><spring:message code="home.day${idx + 1}"/></a></li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</div>--%>
                <%--<c:forEach items="${order.order}" var="menuItem" varStatus="st">--%>
                    <%--<div id="day${st.index}info" class="span7 day-info hide">--%>
                        <%--<h3><spring:message code="home.order.title"/></h3>--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${menuItem.isPaid == true}">--%>
                                <%--<span class="badge badge-success"><i class="icon-ok icon-white"></i></span>--%>
                                <%--<spring:message code="menuItem.status.paid"/>--%>
                                <%--</span>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<span class="badge badge-warning">--%>
                                    <%--<i class="icon-remove icon-white"></i><spring:message code="menuItem.status.unpaid"/>--%>
                                <%--</span>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <%--<hr/>--%>
                        <%--<table class="table table-striped">--%>
                            <%--<tr>--%>
                                <%--<th><spring:message code="menu.table.number"/></th>--%>
                                <%--<th><spring:message code="menu.table.name"/></th>--%>
                                <%--<th><spring:message code="menu.table.price"/></th>--%>
                                <%--<th><spring:message code="actions.remove"/></th>--%>
                            <%--</tr>--%>
                            <%--<c:if test="${empty menuItem.choices}">--%>
                                <%--<tr class="order-rows">--%>
                                    <%--<td colspan="4" class="centered-line">&lt;<spring:message code="message.empty"/>&gt;</td>--%>
                                <%--</tr>--%>
                            <%--</c:if>--%>
                            <%--<c:forEach items="${menuItem.choices}" var="item" varStatus="itemStatus">--%>
                                <%--<tr class="order-rows" id="${item.id}">--%>
                                    <%--<td>${itemStatus.index + 1}</td>--%>
                                    <%--<td>${item.name}</td>--%>
                                    <%--<td>${item.price}</td>--%>
                                    <%--<td>--%>
                                        <%--<a href="#" role="button" class="btn btn-danger btn-mini order-remove">--%>
                                            <%--<spring:message code="menu.order.remove"/>--%>
                                        <%--</a>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                        <%--</table>--%>
                        <%--<hr/>--%>
                        <%--<joda:format var="itemDate" value="${menuItem.date}" pattern="dd-MM-yyyy"/>--%>
                        <%--<a href="#" role="button" class="btn btn-primary order-edit" data-date="${itemDate}" style="float: right">--%>
                            <%--<spring:message code="home.order.edit"/>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</c:forEach>--%>
                <%--<!-- Edit order item modal -->--%>
                <%--<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
                    <%--<div class="modal-header">--%>
                        <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>--%>
                        <%--<h3 id="myModalLabel"><spring:message code="action.customize"/></h3>--%>
                    <%--</div>--%>
                    <%--<div class="modal-body">--%>
                        <%--<iframe src="" frameborder="0" width="99.6%" height="100%"></iframe>--%>
                    <%--</div>--%>
                    <%--<div class="modal-footer">--%>
                        <%--<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="action.close"/></button>--%>
                        <%--&lt;%&ndash;<button class="btn btn-primary"><spring:message code="actiom.save.changes"/></button>&ndash;%&gt;--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <%--<br/>--%>
            <%--<c:if test="${not empty prevWeek}">--%>
            <%--<a class="btn btn-info" style="float: left" href="${contextPath}/home/${prevWeek}">--%>
                <%--<spring:message code="week.0"/></a>--%>
            <%--</c:if>--%>
            <%--<c:if test="${not empty nextWeek}">--%>
            <%--<a class="btn btn-info" style="float: right" href="${contextPath}/home/${nextWeek}">--%>
                <%--<spring:message code="week.2"/></a>--%>
            <%--</c:if>--%>
        <%--</div>--%>
    </jsp:body>

</t:genericpage>
