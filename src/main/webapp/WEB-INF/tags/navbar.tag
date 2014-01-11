<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="container">

    <%--<div class="btn-group" style="float: right" id="userButton">--%>
        <%--<a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">--%>
            <%--<i class="icon-user icon-white"></i>--%>
            <%--<sec:authentication property="principal.user.name" />--%>
            <%--<span class="caret"></span>--%>
        <%--</a>--%>
        <%--<ul class="dropdown-menu">--%>
            <%--<c:set var="userId">--%>
                <%--<sec:authentication property="principal.user.id"/>--%>
            <%--</c:set>--%>
            <%--<li><a href="${contextPath}/edit/user/${userId}"><spring:message code="navbar.user.edit"/></a></li>--%>
            <%--<li class="divider"></li>--%>
            <%--<li><a href="${contextPath}/j_spring_security_logout"><spring:message code="navbar.user.logout"/></a></li>--%>
        <%--</ul>--%>
    <%--</div>--%>
    <h2 class="muted">Проект</h2>
    <div id="myNav" class="navbar">
        <div class="navbar-inner">
            <ul class="nav">
                <li id="navAll"><a href="${contextPath}/show?c=all"><spring:message code="navbar.all"/></a></li>
                <li id="navWheels"><a href="${contextPath}/show?c=wheels&m=all"><spring:message code="navbar.wheels"/></a></li>
                <li id="navAccumulators"><a href="${contextPath}/show?c=accumulators&m=all"><spring:message code="navbar.accumulators"/></a></li>
                <li id="navRadiators"><a href="${contextPath}/show?c=radiators&m=all"><spring:message code="navbar.radiators"/></a></li>

                <%--<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">--%>
                    <%--<li id="navUsers"><a href="${contextPath}/users">--%>
                            <%--<spring:message code="navbar.users"/></a></li>--%>
                    <%--<li class="nav">--%>
                    <%--<li id="navReports" class="dropdown">--%>
                        <%--<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="navbar.reports"/> <span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu" role="menu">--%>
                            <%--<li role="presentation" class="left">--%>
                                <%--<a role="menuitem" class="left" tabindex="-1" href="${contextPath}/report/1"><spring:message code="navbar.report"/></a>--%>
                            <%--</li>--%>
                            <%--<li role="presentation" class="left">--%>
                                <%--<a role="menuitem" class="left" tabindex="-1" href="${contextPath}/report/summary"><spring:message code="navbar.reportSummary"/></a>--%>
                            <%--</li>--%>
                            <%--<li role="presentation" class="left">--%>
                                <%--<a role="menuitem" class="left" tabindex="-1" href="${contextPath}/orderTable"><spring:message code="navbar.reportTable"/></a>--%>
                            <%--</li>--%>
                            <%--<li role="presentation" class="left">--%>
                                <%--<a role="menuitem" class="left" tabindex="-1" href="${contextPath}/report/changes"><spring:message code="navbar.changes"/></a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</sec:authorize>--%>
            </ul>
            <form class="navbar-search pull-right" action="${contextPath}/search/light" method="get">
                <input type="text" class="search-query" name="searchText" placeholder="Поиск по продукции">
            </form>
        </div>
    </div>
</div>