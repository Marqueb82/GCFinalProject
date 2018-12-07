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
	How Far are you willing to walk from your parking spot? <br>
	<input type="number" min=0 step=.1 name="howFar"><br>
	How far are you driving to your event? <br>
	<input type="number" min=0 max=300 name="DrivingDistance"><br>
	How many people can you fit in your car? <br>
	<input type="number" min=2 max=8 name="vSize"><br> <br>
	<input type="submit" value="Submit">
</form>




<body>

</body>
</html>