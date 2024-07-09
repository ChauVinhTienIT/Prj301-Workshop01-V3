<%-- 
    Document   : accountList
    Created on : Jun 18, 2024, 3:39:34 PM
    Author     : Lenovo
--%>

<%@page import="blo.CategoryBLO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <c:set var="proList" value="${productList}"></c:set>      
                <myLib:productTable proList="${proList}"></myLib:productTable>
            </c:when>
            
            <c:when test="${action == 'edit'}">
                <myLib:editProductForm existProduct="${existProduct}"></myLib:editProductForm>
            </c:when>
            
            <c:when test="${action == 'new'}">
                <myLib:insertProductForm></myLib:insertProductForm>
            </c:when>
            
            <c:otherwise>
                <c:set var="proList" value="${productList}"></c:set>      
                <myLib:productTable proList="${proList}"></myLib:productTable>
            </c:otherwise>
        </c:choose>
    </body>
</html>
