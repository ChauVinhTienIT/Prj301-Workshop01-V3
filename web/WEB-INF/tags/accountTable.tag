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

    <div class="container-lg">
        <div class="row">
            <div class="col-sm-8"><h2>Account <b>Details</b></h2></div>
        </div>
        <div class="table-responsive">
            <div class="table-responsive">
                <div class="table-title">
                        <a href="account-manager?action=new" type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i>Add New Account</a>
                </div>
                <br>
                <table class="table table-bordered">
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

                                <a href="${editUrl}" class="edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                <a href="${deleteUrl}" class="delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                <a href="${changeUrl}">${account.getIsUse()?"Deactivate":"Activate"}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</c:if>