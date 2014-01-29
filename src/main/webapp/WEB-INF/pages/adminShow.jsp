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
        <c:forEach var="product" items="${products}" varStatus="st" step="4">
            <c:set var="product1" value="${products[st.index]}"/>
            <c:set var="product2" value="${products[st.index+1]}"/>
            <c:set var="product3" value="${products[st.index+2]}"/>
            <c:set var="product4" value="${products[st.index+3]}"/>
            <div class="row-fluid">
                <ul class="thumbnails">
                    <li class="span3">
                        <c:if test="${product1 != null}">
                            <div class="thumbnail">
                                <div>
                                    <a href="" id="edit-${product1.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;
                                    <a href="" id="remove-${product1.id}" class="product-remove"><i class="icon-remove"></i></a>
                                </div>
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${product1.standardInfo['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${product1.standardInfo['list']}" var="pair" >
                                            <tr>
                                                <td>${pair.first}</td>
                                                <td>${pair.second}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                    </li>
                    <li class="span3">
                        <c:if test="${product2 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${product2.standardInfo['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${product2.standardInfo['list']}" var="pair" >
                                            <tr>
                                                <td>${pair.first}</td>
                                                <td>${pair.second}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                                </div>
                            </div>
                        </c:if>
                    </li>
                    <li class="span3">
                        <c:if test="${product3 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${product3.standardInfo['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${product3.standardInfo['list']}" var="pair" >
                                            <tr>
                                                <td>${pair.first}</td>
                                                <td>${pair.second}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                                </div>
                            </div>
                        </c:if>
                    </li>
                    <li class="span3">
                        <c:if test="${product4 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${product4.standardInfo['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${product4.standardInfo['list']}" var="pair" >
                                            <tr>
                                                <td>${pair.first}</td>
                                                <td>${pair.second}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                                </div>
                            </div>
                        </c:if>
                    </li>
                </ul>
            </div>
        </c:forEach>

        </ul>
    </jsp:attribute>

</t:adminGenericPage>
