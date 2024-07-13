<%-- 
    Document   : editAccountForm
    Created on : Jul 8, 2024, 12:56:04 PM
    Author     : Lenovo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="model.Account" %>
<%@tag import="blo.RoleBLO" %>
<%@tag import="tools.DateFormater" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="account" required="true" type="Account"%>

<%-- any content can be specified here e.g.: --%>
<form action="account-manager?action=update" method="POST" accept-charset="UTF-8">
    <div class="form-group">
        <label for="accountId">Account Id:</label>
        <input type="text" class="form-control" name="accountId" value="<c:out value='${account.getAccountId()}' />" readonly/>
    </div>
    <div class="form-group">
        <label for="accountName">Account Name:</label>
        <input type="text" class="form-control" name="account" value="<c:out value='${account.getAccount()}' />" readonly/>
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" name="password" value="<c:out value='${account.getPass()}' />" disabled>
    </div>
    <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" name="lastName" value="${account.getLastName()}">
    </div>
    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" name="firstName" value="${account.getFirstName()}">
    </div>

    <div class="form-group">
        <label for="phone">Phone Number:</label>
        <input type="text" class="form-control" name="phone" value="${account.getPhone()}">
    </div>

    <div class="form-group">
        <label for="birthDay">Birthday:</label>
        <input type="date" class="form-control" name="birthDay" value="${DateFormater.juDateToString(account.getBirthday())}">
    </div>

    <div class="form-group">
        <label for="gender">Gender:</label></br>
        <input type="radio" name="gender" value="1" checked>Male
        <input type="radio" name="gender" value="0" >Female
    </div>

    <div class="form-group">
        <label for="roleInSystem">Role in system:</label>
        <select class="form-control" name="roleInSystem" >
            <c:set var="roleBLO" value="<%= new RoleBLO()%>"></c:set>
            <c:forEach var="role" items="${roleBLO.listAll()}">
                <c:set var="roleId" value="${role.getRoleId()}"></c:set>
                <option value="${roleId}" <c:if test="${account.getIntRoleId() == roleId}"> selected</c:if> >${role.getRoleName()}</option>
            </c:forEach>
        </select>
    </div>

    <div class="checkbox">
        <label for="isUse">Is Active</label><input type="checkbox" name="isUse" value="1" <c:if test="${account.getIsUse()==true}">checked</c:if>>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>