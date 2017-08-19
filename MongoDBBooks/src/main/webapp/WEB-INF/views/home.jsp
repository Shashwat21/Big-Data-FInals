<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Bitter' rel='stylesheet' type='text/css'>
<style type="text/css">
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
td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
    font: normal 15px 'Bitter', serif;
    contenteditable: true;
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
.form-style-10 input[type="button"], 
.form-style-10 input[type="submit"]{
    background: #2A88AD;
    padding: 8px 20px 8px 20px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    color: #fff;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.12);
    font: normal 30px 'Bitter', serif;
    -moz-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
    -webkit-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
    box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
    border: 1px solid #257C9E;
    font-size: 15px;
}
.form-style-10 input[type="button"]:hover, 
.form-style-10 input[type="submit"]:hover{
    background: #2A6881;
    -moz-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.28);
    -webkit-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.28);
    box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.28);
}
</style>
	<title>Searched Users</title>
</head>
<body>
<h3>Searched Users!</h3>
<form action = "updateMovie.htm" method = "POST">
<table>
	<tr>
		<th>User ID</th>
		<th>Location</th>
		<th>Age</th>
	</tr>
	<c:forEach var = "user" items="${UsersPOJO}">
		<tr>
			<td><input type = text id = "userID"    name = "userID"    value = "${user.userID}" readonly/></td>
			<td><input type = text id = "location" name = "location" value = "${user.location}"  /></td>
			<td><input type = text id = "age" name = "age" value = "${user.age}"  /></td>	
			<td>
				<div class="button-section">
				<input type = submit value = "View Recommendations" name="viewRecommendations" onclick="form.action='viewRecommendations.htm';">
				</div>
			</td>			
		</tr>	
	</c:forEach>
</table>
</form>
<a href = "http://localhost:8080/book/">Home Page</a>
</body>
</html>
