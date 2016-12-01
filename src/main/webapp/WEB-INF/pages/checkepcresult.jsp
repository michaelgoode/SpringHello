<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <spring:url value="/resources/css/theme.css" var="themeCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${themeCSS}" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EPC check result</title>
</head>
<body>
<div align="center">
    <h1>EPC ${epcNo}</h1>
    <table border="0">
        <tr>

            <table border="1">
                <c:forEach var="valuesMap" items="${valuesMap}"  varStatus="status">
                <tr>
                    <td>${valuesMap.key}</td><td>${valuesMap.value}</td>
                </tr>
                </c:forEach>
            </table>
        </tr>
        <tr>
            <table border="1">
                <c:forEach var="messages" items="${messages}"  varStatus="status">
                <tr>
                    <td>${messages.key}</td><td>${messages.value}</td>
                </tr>
                </c:forEach>
            </table>
        </tr>
    </table>
</div>
</body>
</html>
