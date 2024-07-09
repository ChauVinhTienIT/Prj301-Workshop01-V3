<%-- 
    Document   : insertAccountForm
    Created on : Jul 8, 2024, 1:27:36 PM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<div class="row">
    <h3>New Category</h3>
    <div class="col-md-8">
        <form action="category-manager?action=insert" method="POST" accept-charset="UTF-8">

            <div class="form-group">
                <label for="categoryName">Category Name:</label>
                <input type="text" class="form-control" name="categoryName">
            </div>
            <div class="form-group">
                <label for="memo">Memo:</label>
                <input type="text" class="form-control" name="memo">
            </div>

            <button type="submit" class="btn btn-primay">Save</button>
        </form>
    </div>
</div>