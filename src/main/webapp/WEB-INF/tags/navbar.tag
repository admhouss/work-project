<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">
    $(function() {
        $('#editProfile').tooltip({placement: "bottom"});
    });
</script>

<div id="myNav" class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li id="navAll"><a href="${contextPath}/show?c=all"><spring:message code="navbar.all"/></a></li>
                    <li id="navWheels"><a href="${contextPath}/show?c=wheels&m=all"><spring:message code="navbar.wheels"/></a></li>
                    <li id="navAccumulators"><a href="${contextPath}/show?c=accumulators&m=all"><spring:message code="navbar.accumulators"/></a></li>
                    <li id="navRadiators"><a href="${contextPath}/show?c=radiators&m=all"><spring:message code="navbar.radiators"/></a></li>
                    <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
                        <li id="navUsers"><a href="${contextPath}/admin/users">
                            <spring:message code="navbar.users"/></a></li>
                    </sec:authorize>
                    <form class="navbar-search" action="${contextPath}/search/light" method="get">
                        <input type="text" class="search-query" name="searchText" placeholder="Поиск по продукции">
                    </form>
                    <sec:authorize access="isAuthenticated()">
                    <c:set var="login"><sec:authentication property="principal.user.login"/></c:set>
                    <c:set var="tooltipText"><spring:message code="navbar.edit.profile"/></c:set>
                    <li><a id="editProfile" href="${contextPath}/admin/user/edit/${login}" data-toggle="tooltip" title="first tooltip"><sec:authentication property="principal.user.fullName"/></a></li>
                    </sec:authorize>
                </ul>
                </div>
            </div>
        </div>
    </div>
</div>