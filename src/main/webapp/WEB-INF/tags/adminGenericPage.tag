<%@tag description="The generic page template. Uses bootstrap, jquery and navbar"
       pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@attribute name="scripts" fragment="true"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="content" fragment="true"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html>
    <head>
        <t:defaulthead />
        <jsp:invoke fragment="head"/>
        <script type="text/javascript">
            $(document).ready(getModels("${contextPath}"));
        </script>
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
                <div class="span8">
                    <div id="search-row" class="navbar">
                        <div class="navbar-inner">
                            <a class="brand"><spring:message code="search.row.brand"/></a>
                            <form class="navbar-search" action="${contextPath}/search/light" method="get">
                                <input id="typeahead-models" type="text" class="search-query" name="searchText" placeholder="Поиск по продукции" tabindex="1">
                            </form>
                        </div>
                    </div>
                    <div class="content">
                        <jsp:invoke fragment="content"/>
                    </div>
                </div>
                <div class="span2">

                </div>
            </div>
        </div>
        <t:defaultscripts />
        <jsp:invoke fragment="scripts"/>
    </body>
</html>
