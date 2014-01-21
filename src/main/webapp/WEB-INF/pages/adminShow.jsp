<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminGenericPage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.index"/></title>
        <script src="${contextPath}/assets/js/adminShow.js"></script>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $(document).ready(function() {
                init("${contextPath}","${productName}");
            })
        </script>
    </jsp:attribute>

    <jsp:attribute name="content">
         <a href="${contextPath}/auth/administration/editor/new/wheel" class="btn btn-success add-new"><i class="icon-user icon-white"></i>&nbsp;<spring:message code="users.add"/></a>
        <ul class="thumbnails">
        <c:forEach var="product" items="${products}">
            <li class="span3">
                <div class="thumbnail">
                    <img data-src="holder.js/230x230" alt="230x230" style="width: 300px; height: 200px;" src="${contextPath}/image/${productName}/33311">
                    <div class="caption">
                        <h1>Thumbnail label</h1>
                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                    </div>
                </div>
            </li>
        </c:forEach>

        </ul>
    </jsp:attribute>

</t:adminGenericPage>
