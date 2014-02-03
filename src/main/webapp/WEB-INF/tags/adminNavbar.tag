<%@ tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">
    $(function() {
//        $('#editProfile').popover({placement: "bottom"});
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
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="navProduction"><spring:message code="navbar.production"/>&nbsp;<i class="caret"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="${contextPath}/auth/administration/editor/show/wheels"><spring:message code="navbar.wheels"/></a></li>
                            <li><a href="${contextPath}/auth/administration/editor/show/accumulators"><spring:message code="navbar.accumulators"/></a></li>
                            <li><a href="${contextPath}/auth/administration/editor/show/radiators"><spring:message code="navbar.radiators"/></a></li>
                        </ul>
                    </li>
                    <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
                        <li id="navUsers"><a href="${contextPath}/auth/administration/users"><spring:message code="navbar.users"/></a></li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole({'ROLE_ADMINISTRATOR','ROLE_SUPERVISOR'})">
                    <c:set var="login"><sec:authentication property="principal.user.login"/></c:set>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="navFullName"><sec:authentication property="principal.user.fullName"/>&nbsp;<i class="caret"></i></a>
                        <ul class="dropdown-menu">
                            <%--<li class="nav-header">Выберите действие</li>--%>
                            <li><a href="${contextPath}/auth/administration/users/edit/${login}"><i class="icon-pencil"></i>&nbsp;<spring:message code="navbar.edit.profile"/></a></li>
                            <li><a href="${contextPath}/j_spring_security_logout"><i class="icon-remove"></i>&nbsp;Выход из администрирования</a></li>
                        </ul>
                    </li>
                </ul>
                    </sec:authorize>
            </div>
        </div>
    </div>
</div>
