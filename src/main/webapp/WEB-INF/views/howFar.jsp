<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet">
<title>GoTo: Event Details</title>
</head>
<h3>GoTo</h3>

<div>
	<p>${event.name }</p>
</div>

<form action="/park" >
	How Far:<br>
	<input type="number" min=0 step=.1 name="howFar"><br>
	Distance to Event:<br>
	<input type="number" min=0 max=300 name="DrivingDistance"><br>
	Vehicle Size:<br>
	<input type="number" min=2 max=8 name="vSize"><br> <br>
	<input type="submit" value="Submit">
</form>

<p>If you click the "Submit" button, the form-data will be sent to a
	page called "/action_page.php".</p>


<body>

</body>
</html>