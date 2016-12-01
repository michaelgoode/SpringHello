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
    <link href="${themeCSS}" rel="stylesheet" />
    <title>Add Burberry shoe reference</title>
</head>
<body>
<h1 align="center">Add new shoe</h1>

<form:form action="saveshoe" method="get">
    <table>
            <tr><td>Code</td> <td><form:input path="imagecode"/></td></tr>
            <tr><td>Image</td><td><form:input path="imagename"/></td></tr>



    <tr><td><input type="submit" value="Save"/></td></tr>
</table>
</form:form>
</body>
</html>
