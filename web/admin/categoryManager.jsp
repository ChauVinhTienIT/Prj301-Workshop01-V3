

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <myLib:categoryTable categoryList="${category}"></myLib:categoryTable>
            </c:when>
            
            <c:when test="${action == 'edit'}">
                <myLib:editCategoryForm existCategory="${existCategory}"></myLib:editCategoryForm>
            </c:when>
            
            <c:when test="${action == 'new'}">
                <myLib:insertCategoryForm></myLib:insertCategoryForm>
            </c:when>
            
            <c:otherwise>
                <myLib:categoryTable categoryList="${category}"></myLib:categoryTable>
            </c:otherwise>
        </c:choose>
    </body>
</div>        
</div>

</body>
</html>
