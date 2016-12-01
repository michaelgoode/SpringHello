<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="/resources/css/theme.css" var="themeCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <title>Search</title>
</head>
<body>
<h1>Shoe Search</h1>
<form:form action="findshoe" method="get">
    <table>
        <tr>
            <td><form:input path="imagecode" /></td>
        </tr>
        <tr><td><input type="radio" name="group" value="code" checked>By code</td><td><input type="radio" name="group" value="image">By image</td></tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
<c:if test="${not empty list}">
<table>
    <tr><td>Code</td><td>Image</td></tr>
    <c:forEach var="value" items="${list}" varStatus="status">
        <tr>
            <td>${shoe.imagecode}</td><td>${value}</td>
        </tr>
    </c:forEach>
</table>
</c:if>
</form:form>
</body>
</html>
