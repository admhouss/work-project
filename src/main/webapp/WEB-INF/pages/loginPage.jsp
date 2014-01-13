<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!DOCUMENT html>
<html>
<head>
    <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.index"/></title>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet" media="screen" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.css" rel="stylesheet" media="screen" type="text/css"/>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-2.0.3.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
        </style>
    <script type="text/javascript">
        $(function() {
            $('.back').on('click',function() {
                history.back();
            })
            $('#log').focus();
        });
    </script>
</head>
<body>
<div class="container">
    <div class="container">
        <form class="form-signin" method="post" action="<c:url value='/j_spring_security_check' />">
            <h2 class="form-signin-heading">Авторизируйтесь</h2>
            <input id="log" type="text" class="input-block-level" placeholder="Логин" name="j_username" tabindex="1">
            <input id="pass" type="password" class="input-block-level" placeholder="Пароль" name="j_password" tabindex="2">
            <%--<label class="checkbox">--%>
            <%--<input type="checkbox" value="remember-me"> Запомнить меня--%>
            <%--</label>--%>
            <button class="btn btn-large btn-primary" type="submit" tabindex="3">Вход</button>
            <a class="btn btn-large back" tabindex="4">Назад</a>
        </form>
    </div>
</div>
</body>
</html>