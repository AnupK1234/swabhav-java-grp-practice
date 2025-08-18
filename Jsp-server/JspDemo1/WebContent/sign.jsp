<%@page import="javax.swing.plaf.metal.MetalBorders.Flush3DBorder"%>
<%@ include file="/WEB-INF/auth.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2> Welcome <%= request.getParameter("username") %></h2>
<h2>Email <%= request.getParameter("email") %></h2>
<h3> Your gender is <%= request.getParameter("radioo") %></h3>
<%
String[] subjects = request.getParameterValues("check");
if(subjects !=null){
	for(String sub : subjects ){
%>
<h3> Your subject <%= sub %></h3>
<% }
	
}
else{
%>
<h3>No subjected Selected</h3>
<%
} %>
</body>
</html>