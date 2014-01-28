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
        <a class="btn btn-success add-new"><i class="icon-user icon-white"></i>&nbsp;<spring:message code="users.add"/></a>
        <ul class="thumbnails">
        <c:forEach var="product" items="${products}" varStatus="st">
            <c:set var="info" value="${product.standardInfo}"/>
            <c:if test="${st.index%3 == 0}">
                <div
            </c:if>
            <li class="span4">
                <div class="thumbnail">
                    <img data-src="holder.js/230x230" alt="230x230" style="width: 230px; height: 230px;" src="${contextPath}/image/${productName}/33311">
                    <div class="caption">
                        <h1>${info['name']}</h1>
                        <table class="table table-striped">
                            <c:forEach items="${info['list']}" var="pair" >
                                <tr>
                                    <td>${pair.first}</td>
                                    <td>${pair.second}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                    </div>
                </div>
            </li>
        </c:forEach>

        </ul>
    </jsp:attribute>

</t:adminGenericPage>
