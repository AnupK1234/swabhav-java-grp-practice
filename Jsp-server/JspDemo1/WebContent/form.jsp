<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="sign.jsp" method="post">
<h2>Welcome to the page </h2>
<br>
<label name="Username" id="username" >Enter your name </label>
<br>
<input type="text" name="username" required>
<br>
<label name = "email" id="email"> Enter your email</label>
<br>
<input type="email" name="email" required>
<br>
<br>
<label name="radioo">Enter your Gender</label>
<br>
<input type="radio" name="radioo" value="Male" >Male
<br>
<input type="radio" name="radioo" value ="Female">Female
<br>
<br>
<label name="check" >Check the box to select box </label>
<br>
<input type="checkbox" name="check" value="Math">Math
<br>
<input type="checkbox" name="check" value="Science">Science
<br>
<input type="checkbox" name="check" value="English">English
<br>
<input type="checkbox" name="check" value="Marathi">Marathi
<br>
<br>
<button type="submit" >Submit</button>

</form>

</body>
</html>