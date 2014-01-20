<%@ tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">
</script>

<div id="myNav" class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <form class="navbar-search" action="${contextPath}/search/light" method="get">
                <input type="text" class="search-query collapsed" name="searchText" placeholder="Поиск по продукции" tabindex="1">
            </form>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li id="navAll"><a href="${contextPath}/show?c=all"><spring:message code="navbar.all"/></a></li>
                    <li id="navWheels"><a href="${contextPath}/show?c=wheels&m=all"><spring:message code="navbar.wheels"/></a></li>
                    <li id="navAccumulators"><a href="${contextPath}/show?c=accumulators&m=all"><spring:message code="navbar.accumulators"/></a></li>
                    <li id="navRadiators"><a href="${contextPath}/show?c=radiators&m=all"><spring:message code="navbar.radiators"/></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>