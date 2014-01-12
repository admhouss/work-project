<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.index"/></title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
        </script>
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            <div style="margin-top: 15%;margin-left: 20%;">
                <form class="form-horizontal" method="post" action="${contextPath}/login_check">
                    <div class="control-group">
                        <label class="control-label" for="inputLogin">Логин</label>
                        <div class="controls">
                            <input type="text" id="inputLogin" placeholder="Логин" name="login">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputPassword">Пароль</label>
                        <div class="controls">
                            <input type="password" id="inputPassword" placeholder="Пароль" name="password" >
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn">Вход</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>

</t:genericpage>
