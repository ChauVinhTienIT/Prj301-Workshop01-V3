<%-- 
    Document   : myDataGrid
    Created on : Jul 7, 2024, 10:26:27 PM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="java.util.List" %>
<%@tag import="model.Categorie" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="categoryList" type="List<Categorie>"%>
<%-- any content can be specified here e.g.: --%>

<c:if test="${not empty categoryList}">

    <div class="container-lg">
        <div class="row">
            <div class="col-sm-8"><h2>Category <b>Details</b></h2></div>
        </div>
        <div class="table-responsive">
            <div class="table-responsive">
                <div class="table-title">
                    <a href="category-manager?action=new" type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i>Add New Category</a>
                </div>
                <br>
                <table class="table">
                    <thead>
                        <tr>
                        <tr>
                            <th>Type ID</th>
                            <th>Category Name</th>
                            <th>Memo</th>
                            <th>Action</th>
                        </tr>
                        </tr>
                    </thead>
                    <c:forEach items="${categoryList}" var="category">
                        <tr>
                            <td>${category.getTypeId()}</td>
                            <td>${category.getCategoryName()}</td>
                            <td>${category.getMemo()}</td>
                            <td>


                                <c:url value = "category-manager" var = "editUrl">
                                    <c:param name = "action" value = "edit"/>
                                    <c:param name = "typeId" value = "${category.getTypeId()}"/>
                                </c:url>

                                <c:url value = "category-manager" var = "deleteUrl">
                                    <c:param name = "action" value = "delete"/>
                                    <c:param name = "typeId" value = "${category.getTypeId()}"/>
                                </c:url>

                                <a href="${editUrl}" class="edit" data-toggle="modal"><i class="material-icons">&#xE254;</i></a> 
                                <a href="${deleteUrl}"class="delete" data-toggle="modal"><i class="material-icons">&#xE872;</i></a>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>

</c:if>