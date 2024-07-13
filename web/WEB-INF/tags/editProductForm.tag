<%-- 
    Document   : editProductForm
    Created on : Jul 8, 2024, 2:36:03 PM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="blo.CategoryBLO" %>
<%@tag import="model.Product" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="existProduct" required="yes" type="Product"%>

<%-- any content can be specified here e.g.: --%>
<div class="row">
    <h3>Update Product</h3>
    <div class="col-md-8">
        <form action="product-manager?action=update" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">

            <div class="form-group">
                <label for="productId">Product Id:</label>
                <input type="text" class="form-control" name="productId" value="${existProduct.getProductId()}">
            </div>
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" class="form-control" name="productName" value="${existProduct.getProductName()}">
            </div>


            <div class="form-group">
                <label for="productImage">Product Image:</label><br>
                <img src="./../${existProduct.getProductImage()}" width="200" height="200">
                <input type = "file" name = "image" size = "50" /><br>
            </div>

            <div class="form-group">
                <label for="brief">Brief:</label>
                <input type="text" class="form-control" name="brief" value="${existProduct.getBrief()}">
            </div>


            <div class="form-group">
                <c:set value="<%= new CategoryBLO().listAll()%>" var="cateList"></c:set>
                <label for="typeId">Type:</label>
                <select class="form-control" name="typeId">
                    <c:forEach items="${cateList}" var="category">
                        <option value="${category.getTypeId()}" ${existProduct.getTypeId().getTypeId()==category.getTypeId()?"check":""}> ${category.getCategoryName()}</option>
                    </c:forEach>
                </select>

            </div>


            <div class="form-group">
                <label for="unit">Unit:</label>
                <select class="form-control" name="unit">
                    <option value="1" ${existProduct.getUnit().equals(existProduct.getUnit())?"check":""}>Cái</option>
                    <option value="2" ${existProduct.getUnit().equals(existProduct.getUnit())?"check":""}>Chiếc</option>
                    <option value="3" ${existProduct.getUnit().equals(existProduct.getUnit())?"check":""}>Bộ</option>
                </select>
            </div>

            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" min="0" class="form-control" name="price" value="${existProduct.getPrice()}">
            </div>

            <div class="form-group">
                <label for="discount">Discount</label>
                <input type="number" min="0" max="100" class="form-control" name="discount" value="${existProduct.getDiscount()}">
            </div>

            <button type="submit" class="btn btn-primary">Save</button>
        </form>

    </div>
</div>