<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>JSTL Functions Demo</title>
</head>
<body>

<%
    request.setAttribute("message", "  Hello World   ");
    request.setAttribute("csv", "apple,banana,orange,kiwi");
%>

<h2>Original String: "${message}"</h2>

<p>Trimmed: ${fn:trim(message)}</p>
<p>Uppercase: ${fn:toUpperCase(message)}</p>
<p>Lowercase: ${fn:toLowerCase(message)}</p>


<p>Contains "World"? ${fn:contains(message, "World")}</p>
<p>Starts with "  Hello"? ${fn:startsWith(message, "  Hello")}</p>
<p>Length: ${fn:length(message)}</p>
<p>Replace the world: ${fn:replace(message, "World","Rohan")} </p> 

<hr>

<h3>Working with CSV</h3>
<p>CSV String: ${csv}</p>
<c:set var="fruitsArray" value="${fn:split(csv, ',')}" />
<p>First Fruit: ${fruitsArray[0]}</p>
<p>All Fruits Joined with "/": ${fn:join(fruitsArray, "/")}</p>



</body>
</html>
