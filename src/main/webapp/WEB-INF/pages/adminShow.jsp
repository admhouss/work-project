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
            <c:set var="info1" value="${products[st.index].standardInfo}"/>
            <c:set var="info2" value="${products[st.index+1].standardInfo}"/>
            <c:set var="info3" value="${products[st.index+2].standardInfo}"/>
            <c:set var="info4" value="${products[st.index+3].standardInfo}"/>
            <div class="row-fluid">
                <ul class="thumbnails">
                    <li class="span3">
                        <c:if test="${info1 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${info1['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${info1['list']}" var="pair" >
                                            <tr>
                                                <td>${pair.first}</td>
                                                <td>${pair.second}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <p><a class="btn btn-primary btn-edit" id="${product.id}">Редактировать</a> <a href="#" class="btn btn-remove">Удалить</a></p>
                                </div>
                            </div>
                        </c:if>
                    </li>
                    <li class="span3">
                        <c:if test="${info2 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${info2['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${info2['list']}" var="pair" >
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
                        <c:if test="${info3 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${info3['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${info3['list']}" var="pair" >
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
                        <c:if test="${info4 != null}">
                            <div class="thumbnail">
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/${productName}/33311">
                                <div class="caption">
                                    <h4>${info4['name']}</h4>
                                    <table class="table table-striped">
                                        <c:forEach items="${info4['list']}" var="pair" >
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
