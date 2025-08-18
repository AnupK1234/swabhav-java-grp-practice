<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
</head>
<body>
    <h3>List of Students (From Session)</h3>
    <hr>

    <c:forEach var="tempStudent" items="${student_list}">
        ${tempStudent} <br/>
    </c:forEach>

</body>
</html>
