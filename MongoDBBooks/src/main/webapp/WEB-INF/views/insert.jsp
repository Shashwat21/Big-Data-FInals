<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Bitter' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bitter' rel='stylesheet' type='text/css'>
<style type="text/css">
.form-style-10 textarea,
table {
    border-collapse: collapse;
    width: 100%;
}
th{
	padding: 8px;
    text-align: centre;
    border-bottom: 1px solid #ddd;
    font: normal 25px 'Bitter', serif;
}

tr:hover{background-color:#f5f5f5}
h3{
    background: #2A88AD;
    padding: 20px 30px 15px 30px;
    margin: -30px -30px 30px -30px;
    border-radius: 10px 10px 0 0;
    -webkit-border-radius: 10px 10px 0 0;
    -moz-border-radius: 10px 10px 0 0;
    color: #fff;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.12);
    font: normal 30px 'Bitter', serif;
    -moz-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
    -webkit-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
    box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
    border: 1px solid #257C9E;
}
 input[type = "text"]{
    display: block;
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    width: 100%;
    padding: 8px;
    border-radius: 6px;
    -webkit-border-radius:6px;
    -moz-border-radius:6px;
    border: 2px solid #fff;
    box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.33);
    -moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.33);
    -webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.33);
}
.form-style-10 td{
    display: block;
    font: 13px Arial, Helvetica, sans-serif;
    color: #888;
    margin-bottom: 15px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recommendation for Users</title>
</head>
<body>
	<body>
<h3>Books Recommendations!</h3>
<form action = "updateMovie.htm" method = "POST">
<table>
	<tr>
		<th>User ID</th>
		<th>Book Recommendations</th>
	</tr>
	<c:forEach var = "rec" items="${RecomPOJO}">
		<tr>
			<td><input type = text id = "userID"    name = "userID"    value = "${rec.userID}" readonly/></td>
			<td><input type = text id = "recommendation" name = "recommendation" value = "${rec.recommendations}"  height="100"/></td>
		</tr>	
	</c:forEach>
</table>
</form>
	<a href = "http://localhost:8080/book/">Home Page</a>
</body>
</html>