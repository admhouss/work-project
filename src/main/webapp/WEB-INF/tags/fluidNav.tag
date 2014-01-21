<%@ tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<div class="accordion" id="accordion2">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                <spring:message code="navbar.production"/>
            </a>
        </div>
        <div id="collapseOne" class="accordion-body collapse">
            <div class="accordion-inner">
                <ul class="nav nav-list">
                    <li><a href="${contextPath}/auth/administration/editor/show/wheels"><spring:message code="navbar.wheels"/></a></li>
                    <li><a href="${contextPath}/auth/administration/editor/show/accumulators"><spring:message code="navbar.accumulators"/></a></li>
                    <li><a href="${contextPath}/auth/administration/editor/show/radiators"><spring:message code="navbar.radiators"/></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                Collapsible Group Item #2
            </a>
        </div>
        <div id="collapseTwo" class="accordion-body collapse">
            <div class="accordion-inner">
                Anim pariatur cliche...
            </div>
        </div>
    </div>
</div>