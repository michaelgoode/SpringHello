<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="/resources/css/theme.css" var="themeCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${themeCSS}" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contract search</title>
</head>
<body>
<div align="center">
    <h1>Contract</h1>
    <table border="1">
        <th>Contract</th>
        <th>UPC</th>
        <th>Batch</th>
        <th>Date</th>
        <th>EPC</th>
        <c:forEach var="value" items="${list}" varStatus="status">
            <tr>
                <td>${value.contract}</td>
                <td>${value.upc}</td>
                <td>${value.transmissionId}</td>
                <td>${value.associationDate}</td>
                <td>${value.epc}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
