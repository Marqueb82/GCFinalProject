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
<h2>GoTo: <font size="8">${event.name }</font></h2>


<form action="/park" >
	How far are you willing to walk from your parking spot? <br>
	<input type="number" min=0 step=.1 name="howFar"> miles<br> <br>
	How far are you driving to your event? <br>
	<input type="number" min=0 max=300 name="DrivingDistance"> miles<br><br>
	How many people can you fit in your car? <br>
	<input type="number" min=2 max=8 name="vSize">people <br>
	<input type="submit" value="Submit">
</form>




<body>

</body>
</html>