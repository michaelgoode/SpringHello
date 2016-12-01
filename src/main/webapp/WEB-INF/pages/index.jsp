<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: michaelgoode
  Date: 05/09/2016
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/css/theme.css" var="themeCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${themeCSS}" rel="stylesheet" />
    <title>M&S EPC WEB</title>
</head>
<body>
<div align="center">
    <table>
        <tr><td><a href="searchepc">Search EPC</a></td></tr>
        <tr><td><a href="list">List contracts</a></td></tr>
        <tr><td><a href="batch">List batches</a></td></tr>
        <tr><td><a href="searchcontract">Search contract</a></td></tr>
        <tr><td><a href="checkepc">Check EPC</a></td></tr>
        <tr><td><a href="unassociated">Show unassociated report</a></td></tr>
        <tr><td><a href="uploadreport">Load Unassociated Report</a></td></tr>
        <tr><td><a href="getshoe">Get shoe</a></td></tr>
        <tr><td><a href="addshoe">Add shoe</a></td></tr>
    </table>
</div>
</body>
</html>
