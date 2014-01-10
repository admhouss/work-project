<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title><spring:message code="title.error.${error}"/></title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
    </jsp:attribute>

    <jsp:body>
        <div style="margin-top: 70px">
            <div class="container">
                <img src="${contextPath}/image/errors/${error}" alt="Ошибка ${error}" >
            </div>
        </div>

    </jsp:body>

</t:genericpage>
