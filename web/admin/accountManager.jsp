<%-- 
    Document   : listAccount.jsp
    Created on : Jun 22, 2024, 4:48:55 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="blo.AccountBLO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="com" uri="/WEB-INF/tlds/customtag_library.tld"%>
<%@taglib prefix="myLib" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Account Manager</title>
        
    </head>
    <body>
        <%@include file="../header.jspf"%>
        <c:set var="action" value="${action}"></c:set>
        <c:choose>
            <c:when test="${empty action}">
                <c:set value="<%= new AccountBLO()%>" var="accountBLO"></c:set>
                <myLib:accountTable accList="${accList}" user="${accountBLO.getObjectByAccount('admin')}"></myLib:accountTable>
            </c:when>
            
            <c:when test="${action == 'edit'}">
                <myLib:editAccountForm account="${existAccount}"></myLib:editAccountForm>
            </c:when>
            
            <c:when test="${action == 'new'}">
                <myLib:insertAccountForm></myLib:insertAccountForm>
            </c:when>
            
            <c:otherwise>
                <c:set value="<%= new AccountBLO()%>" var="accountBLO"></c:set>
                <myLib:accountTable accList="${accList}" user="${accountBLO.getObjectByAccount('admin')}"></myLib:accountTable>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
