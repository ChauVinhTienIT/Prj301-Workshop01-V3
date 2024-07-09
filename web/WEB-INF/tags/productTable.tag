<%-- 
    Document   : productTable
    Created on : Jul 8, 2024, 12:50:40 AM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="blo.CategoryBLO"%>
<%@tag import="java.util.List" %>
<%@tag import="model.Product" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="proList" type="List<Product>"%>

<%-- any content can be specified here e.g.: --%>

<c:if test="${not empty proList}">
    <div>
        <h2>Product Manager</h2>
        <a href="product-manager?action=new"><span>Add New Product</span></a>
    </div>
    <table border="1">
        <thead>
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Product Image</th>
                <th>Brief</th>
                <th>Posted Date</th>
                <th>Type Id</th>
                <th>Account</th>
                <th>Unit</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:set value="<%= new CategoryBLO()%>" var="cateBLO"></c:set>
            <c:forEach var="product" items="${proList}">
                <tr>
                    <td>${product.getProductId()}</td>
                    <td>${product.getProductName()}</td>
                    <td><img src=".${product.getProductImage()}" width="100" height="100" class="rounded mx-auto d-block"></td>
                    <td>${product.getBrief()}</td>
                    <td>${product.getPostedDate()}</td>
                    <td>${cateBLO.getObjectById(product.getTypeId().getTypeId()).getCategoryName()}</td>
                    <td>${product.getAccountId().getAccount()}</td>
                    <td>${product.getUnit()}</td>
                    <td>${product.getPrice()}</td>
                    <td>${product.getDiscount()}</td>

                    <td>
                        <c:set var="productId" value="${product.getProductId()}"></c:set>

                        <c:url var="editUrl" value="product-manager">
                            <c:param name="action" value="edit"></c:param>
                            <c:param name="productId" value="${productId}"></c:param>
                        </c:url>

                        <c:url var="deleteUrl" value="product-manager">
                            <c:param name="action" value="delete"></c:param>
                            <c:param name="productId" value="${productId}"></c:param>
                        </c:url>

                        <a href="${editUrl}" class="edit" data-toggle="modal">edit</a> 
                        <a href="${deleteUrl}" class="delete" data-toggle="modal">delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>