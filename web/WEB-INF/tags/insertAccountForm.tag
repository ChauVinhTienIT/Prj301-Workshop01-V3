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
    <h2>Add New Account</h2>
    <div class="col-md-8">
        <form action="account-manager?action=insert" method="POST" accept-charset="UTF-8">

            <div class="form-group">
                <label for="accountName">Account Name:</label>
                <input type="text" class="form-control" name="accountName">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" name="password">
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" class="form-control" name="lastName">
            </div>
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" class="form-control" name="firstName">
            </div>

            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" class="form-control" name="phone">
            </div>

            <div class="form-group">
                <label for="birthDay">Birth Day:</label>
                <input type="date" class="form-control" name="birthDay">
            </div>

            <div class="form-group">
                <label for="gender">Gender:</label></br>
                <input type="radio" name="gender" value="1" checked>Male
                <input type="radio" name="gender" value="0" >Female
            </div>

            <div class="form-group">
                <label for="roleInSystem">Role in system:</label>
                <select class="form-control" name="roleInSystem">
                    <option value="1">Administrator</option>
                    <option value="2">Staff</option>
                    <option value="3">Customer</option>
                </select>
            </div>

            <div class="checkbox">
                <label><input type="checkbox" name="isUse" value="1">Is Active</label>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>