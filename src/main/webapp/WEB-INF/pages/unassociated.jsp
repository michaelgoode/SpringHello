<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="/resources/css/theme2.css" var="theme2CSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${theme2CSS}" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Unassociated Report</title>
</head>
<body>
<div align="center">
    <h1>Unassociated report</h1>
    <table border="1">
        <th>Report</th>
        <th>Batch ID</th>
        <th>File name</th>
        <th>Date Sent</th>
        <th>Sent</th>
        <th>Bureau</th>
        <th>Association</th>
        <th>Contract</th>
        <th>UPC</th>
        <th>EPC</th>
        <th>Valid SML tag</th>
        <c:forEach var="value" items="${list}" varStatus="status">
            <tr>
                <td>${value.reportName}</td>
                <td>${value.batch}</td>
                <td>${value.filename}</td>
                <td>${value.sent}</td>
                <td>${value.nowSent}</td>
                <td>${value.bureau}</td>
                <td>${value.association}</td>
                <td>${value.contract}</td>
                <td>${value.upc}</td>
                <td>${value.EPC}</td>
                <td>${value.valid}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
