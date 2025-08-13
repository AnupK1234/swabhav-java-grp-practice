<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Function Example</title>
</head>
<body>
<%
    String name = "  Vivek Dhalkari  ";
    request.setAttribute("name", name);
%>

<p>Hello ${fn:length(name)}</p>
<p>Original String: "${name}"</p>

<p>Length: ${fn:length(name)}</p>

<p>Trimmed: "${fn:trim(name)}"</p>

<p>Uppercase: ${fn:toUpperCase(name)}</p>
<p>Lowercase: ${fn:toLowerCase(name)}</p>

<p>Contains 'Dhalkari'?: ${fn:contains(name, "Dhalkari")}</p>

<p>Starts with 'Vivek'?: ${fn:startsWith(fn:trim(name), "Hello")}</p>

<p>Replace 'Dhalkari' with 'JSTL': ${fn:replace(name, "Dhalkari", "JSTL")}</p>





<c:set var="csv" value="Java,Python,C++,JavaScript" />

<p>Split CSV:</p>
<ul>
    <c:forEach var="lang" items="${fn:split(csv, ',')}">
        <li>${lang}</li>
    </c:forEach>
</ul>


  <c:set var="langsArray" value="${fn:split(csv, ',')}" />
        ${fn:join(langsArray, " | ")}
        
 
 

 <c:set var="email" value="vivek@example.com" />

<p><b>Email:</b> ${email}</p>

<p><b>Username (before @):</b> ${fn:substringBefore(email, "@")}</p>
<p><b>Domain (after @):</b> ${fn:substringAfter(email, "@")}</p>
 
</body>
</html>