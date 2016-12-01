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
    <title>Search machine</title>
</head>
<body>
<h1 align="center">Contract Search</h1>
<form:form action="contractresults" method="post">
    <table align="center">
        <tr>
            <td align="center">Flowrate<form:input path="flowrate" value="" /></td>
        </tr>
        <tr>
        <td align="center">Speed<form:input path="speed" value="" /></td>
    </tr>
        <tr>
            <td align="center">Pressure In<form:input path="inpressure" value="" /></td>
        </tr>
        <tr>
            <td align="center">Pressure Out<form:input path="outpressure" value="" /></td>
        </tr>
        <tr>
            <td align="center">Inlet temperature<form:input path="inlettemp" value="" /></td>
        </tr>
        <tr>
            <td align="center">Altitude<form:input path="altitude" value="" /></td>
        </tr>
        <tr>
            <td align="center">Barometer<form:input path="barometer" value="" /></td>
        </tr>
        <tr>
            <td align="center">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>

