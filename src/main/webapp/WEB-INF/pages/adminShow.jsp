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
                                <div class="offset9" style="visibility: hidden">
                                    <a href="" id="edit-${product1.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;
                                    <a href="" id="remove-${product1.id}" class="product-remove"><i class="icon-remove"></i></a>
                                </div>
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/get/${product1.imageId}">
                                <div class="caption">
                                    <h4>${product1.standardInfo['model']}</h4>
                                    <h6 class="muted">${product1.standardInfo['producer']}</h6>
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
                                <div class="offset9" style="visibility: hidden">
                                    <a href="" id="edit-${product2.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;
                                    <a href="" id="remove-${product2.id}" class="product-remove"><i class="icon-remove"></i></a>
                                </div>
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/get/${product2.imageId}">
                                <div class="caption">
                                    <h4>${product2.standardInfo['model']}</h4>
                                    <h6 class="muted">${product2.standardInfo['producer']}</h6>
                                    <table class="table table-striped">
                                        <c:forEach items="${product2.standardInfo['list']}" var="pair" >
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
                        <c:if test="${product3 != null}">
                            <div class="thumbnail">
                                <div class="offset9" style="visibility: hidden">
                                    <a href="" id="edit-${product3.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;
                                    <a href="" id="remove-${product3.id}" class="product-remove"><i class="icon-remove"></i></a>
                                </div>
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/get/${product3.imageId}">
                                <div class="caption">
                                    <h4>${product3.standardInfo['model']}</h4>
                                    <h6 class="muted">${product3.standardInfo['producer']}</h6>
                                    <table class="table table-striped">
                                        <c:forEach items="${product3.standardInfo['list']}" var="pair" >
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
                        <c:if test="${product4 != null}">
                            <div class="thumbnail">
                                <div class="offset9" style="visibility: hidden">
                                    <a href="" id="edit-${product4.id}" class="product-edit"><i class="icon-pencil"></i></a>&nbsp;
                                    <a href="" id="remove-${product1.id}" class="product-remove"><i class="icon-remove"></i></a>
                                </div>
                                <img data-src="holder.js/160x160" alt="160x160" style="width: 160px; height: 160px;" src="${contextPath}/image/get/${product4.imageId}">
                                <div class="caption">
                                    <h4>${product4.standardInfo['model']}</h4>
                                    <h6 class="muted">${product4.standardInfo['producer']}</h6>
                                    <table class="table table-striped">
                                        <c:forEach items="${product4.standardInfo['list']}" var="pair" >
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
                </ul>
            </div>
        </c:forEach>
         <div class="modal fade" id="imageloadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
             <div class="modal-dialog">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h3 id="myModalLabel"><spring:message code="users.table.edit"/>&nbsp;<img id="loading" width="16" height="16" alt="Loading" src="${contextPath}/image/gif/util/loading"/></h3>
                     </div>
                     <div class="modal-body">
                         <form id="image-upload" class="form-horizontal" action="" enctype="multipart/form-data">
                             <div class="control-group">
                                 <label for="inputFile" class="control-label">${firstNamePH}</label>

                                 <div class="controls">
                                     <input id="inputFile" type="file" name="file" size="50" />
                                 </div>
                             </div>

                             <div style="margin-left: 100px;margin-right: 130px;">
                                 <div class="alert alert-danger hidden" id="error">
                                     <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels"/>
                                 </div>
                                 <div class="alert alert-danger hidden" id="error-add">
                                     <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.labels.add"/>
                                 </div>
                                 <div class="alert alert-danger hidden" id="loginIsExist">
                                     <strong><spring:message code="edit.submit.fail.title"/></strong> <spring:message code="edit.error.login.exist"/>
                                 </div>
                                 <div class="alert alert-success hidden" id="success">
                                     <spring:message code="edit.submit.success"/>
                                 </div>
                                 <div class="alert alert-success hidden" id="add-success">
                                     <spring:message code="edit.submit.success.add"/>
                                 </div>
                             </div>
                         </form>
                     </div>
                     <div class="modal-footer">
                         <button class="btn btn-primary pic-upload"><spring:message code="users.table.modal.save"/></button>
                         <button class="btn" data-dismiss="modal" aria-hidden="true" disabled="disabled"><spring:message code="users.table.modal.close"/></button>
                     </div>
                 </div>
                 <!-- /.modal-content -->
             </div>
             <!-- /.modal-dialog -->
         </div>
    </jsp:attribute>

</t:adminGenericPage>
