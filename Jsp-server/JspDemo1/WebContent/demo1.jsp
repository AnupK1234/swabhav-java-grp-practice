<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>
		Today's date is
		<%=new java.util.Date()%>
	</h2>

	<%
	for (int i = 0; i <= 10; i++) {
		out.println("<p>" + i + "</p>");
	}
	%>

	<%!public String StringConcatUpper(String str1 ,String str2){
			return (str1+""+str2).toUpperCase();
	}
			%>
			<h2> <%= StringConcatUpper("rohan","Maurya")%> </h2>
	<form action="">
	</form>

</body>
</html>