<%@tag description="The generic page template. Uses bootstrap, jquery and navbar"
       pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="scripts" fragment="true"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="content" fragment="true"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html>
    <head>
        <t:defaulthead />
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <c:if test="${empty param.nonav}">
            <t:adminNavbar/>
            <t:header/>
        </c:if>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2">
                    <t:fluidNav/>
                </div>
                <div class="span10">
                    <jsp:invoke fragment="content"/>
                </div>
            </div>
        </div>
        <t:defaultscripts />
        <jsp:invoke fragment="scripts"/>
    </body>
</html>
