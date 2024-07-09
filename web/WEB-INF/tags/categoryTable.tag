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

    <div class="table-title">
        <div class="row">
            <h2>Category Manager</h2>
            <a href="category-manager?action=new" class="btn btn-success" data-toggle="modal"><span>Add New Category</span></a>
        </div>
    </div>

    <table border="1">
        <thead>
            <tr>
                <th>Type ID</th>
                <th>Category Name</th>
                <th>Memo</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>

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

                        <a href="${editUrl}" class="edit" data-toggle="modal">edit</a> 
                        <a href="${deleteUrl}"class="delete" data-toggle="modal">delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</c:if>