<%@tag description="The generic page template. Uses bootstrap, jquery and navbar"
       pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="scripts" fragment="true"%>
<%@attribute name="head" fragment="true"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html>
    <head>
        <t:defaulthead />
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <c:if test="${empty param.nonav}">
            <t:navbar/>
            <div class="container">
                <h1>Заговок, большыми буквами</h1>
                <p>Маленькое описание</p>
            </div>
        </c:if>

        <%--<div class="jumbotron subhead">--%>
            <%--<div class="container">--%>
                <%--<h1>Шины, Аккумуляторы и Радиаторы</h1>--%>
                <%--<p>Шины, Аккумуляторы и Радиаторы</p>--%>
            <%--</div>--%>
        <%--</div>--%>
        <jsp:doBody />
        <t:defaultscripts />
        <jsp:invoke fragment="scripts"/>
    </body>
</html>
