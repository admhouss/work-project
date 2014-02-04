<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminGenericPage>

    <jsp:attribute name="head">
        <title><spring:message code="title.default"/>&nbsp;<spring:message code="title.separator"/>&nbsp;<spring:message code="title.index"/></title>
        <script src="${contextPath}/assets/js/showItem.js"></script>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $(document).ready(function() {
                init("${contextPath}","${wheel.id}");
            })
        </script>
    </jsp:attribute>

    <jsp:attribute name="content">
        <%--<a class="btn btn-success add-new"><i class="icon-user icon-white"></i>&nbsp;<spring:message code="users.add"/></a>--%>
        <%--<a href="" id="edit-${product1.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;--%>
        <%--<a href="" id="remove-${product1.id}" class="product-remove"><i class="icon-remove"></i></a>--%>
        <div id="edit-area" class="row-fluid hide"></div>
        <div id="show-area" class="row-fluid">
            <div class="offset1 span3">
                <h4>${wheel.model}</h4>
                <div class="thumbnail ">
                    <img data-src="holder.js/200x200" style="width: 200px; height: 200px;" src="${contextPath}/image/get/${wheel.imageId}" alt="${wheel.model}" title="${wheel.model}">
                </div>
            </div>
            <div class="span7">
                <h4 id="wait">Получение информации...</h4>
                <dl class="dl-horizontal hide">
                </dl>
            </div>
            <div class="span1">
                <a href="" id="edit-${product1.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;
                <a href="" id="remove-${product1.id}" class="product-remove"><i class="icon-remove"></i></a>
            </div>
        </div>
    </jsp:attribute>

</t:adminGenericPage>
