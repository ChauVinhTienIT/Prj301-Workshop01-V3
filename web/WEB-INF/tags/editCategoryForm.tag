<%-- 
    Document   : editProductForm
    Created on : Jul 8, 2024, 2:36:03 PM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="model.Categorie" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="existCategory" required="yes" type="Categorie"%>

<%-- any content can be specified here e.g.: --%>
<div class="row">
    <h3>Update Category</h3>
    <div class="col-md-8">
        <form action="category-manager?action=update" method="POST" accept-charset="UTF-8">


            <input type="hidden" class="form-control" name="typeId" value = "${existCategory.getTypeId()}">

            <div class="form-group">
                <label for="categoryName">Category Name:</label>
                <input type="text" class="form-control" name="categoryName" value = "${existCategory.getCategoryName()}">
            </div>
            <div class="form-group">
                <label for="memo">Memo:</label>
                <input type="text" class="form-control" name="memo" value = "${existCategory.getMemo()}">
            </div>

            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>