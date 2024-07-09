<%-- 
    Document   : myDataGrid
    Created on : Jul 7, 2024, 10:26:27 PM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="java.util.List" %>
<%@tag import="model.Account" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="accList" type="List<Account>"%>
<%@attribute name="user" type="Account"%>
<%-- any content can be specified here e.g.: --%>

<c:if test="${not empty accList}">

    <div class="table-title">
        <div class="row">
            <div class="col-sm-6">
                <h2>Manage Accounts</h2>
            </div>
            <div class="col-sm-6">
                <a href="account-manager?action=new" class="btn btn-success" data-toggle="modal">Add New Account</a>
            </div>
        </div>
    </div>
    
    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>Account</th>
                <th>Pass</th>
                <th>Full Name</th>
                <th>BirthDay</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach var="account" items="${accList}">
                <c:set var="accountId" value="${account.getAccountId()}"></c:set>
                <c:choose>
                    <c:when test="${user.getAccountId() == accountId}">
                        <tr hidden="true">
                        </c:when>
                        <c:otherwise>
                        <tr>
                        </c:otherwise>
                    </c:choose>

                    <td>${accountId}</td>
                    <td>${account.getAccount()}</td>
                    <td>${account.getPass()}</td>
                    <td>${account.getFullName()}</td>
                    <td>${account.getBirthday()}</td>
                    <td>${account.getGender()?"Male":"Female"}</td>
                    <td>${account.getPhone()}</td>
                    <td>${account.getRoleId().getRoleName()}</td>

                    <td>
                        <c:url var="editUrl" value="account-manager">
                            <c:param name="action" value="edit"></c:param>
                            <c:param name="accountId" value="${accountId}"></c:param>
                        </c:url>
                        <c:url var="deleteUrl" value="account-manager">
                            <c:param name="action" value="delete"></c:param>
                            <c:param name="accountId" value="${accountId}"></c:param>
                        </c:url>
                        <c:url var="changeUrl" value="account-manager">
                            <c:param name="action" value="changeStatus"></c:param>
                            <c:param name="accountId" value="${accountId}"></c:param>
                        </c:url>

                        <a href="${editUrl}" class="edit" data-toggle="modal">Edit</a>
                        <a href="${deleteUrl}" class="delete" data-toggle="modal">Delete</a>
                        <a href="${changeUrl}">${account.getIsUse()?"Deactivate":"Activate"}</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</c:if>