<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Index Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>Parking</title>
</head>
<style>
.jumbotron {
background-color: #6C18C4; /* maroon */
	color: #DEDEDE; /*lime green */
	
}
</style>
<body>
	<div class="jumbotron text-center">
		<h1 align="left">GoTo</h1>

	</div>

	<h2 align="center">This location will be listed for future events
		in similar location</h2>


	<form action="/add/parkingspot">
		<p align="center">
			<br></br> <br></br> Purchasing Price:<br> <input type="text"
				name="price"> <br> <br></br> Name:<br> <input
				type="text" name="name"> <br>
			<br> <br></br> Address:<br> <input type="text"
				name="address"> <br> City:<br> <br></br> <input
				type="text" name="city"> <br> Latitude:<br> <br></br>
			<input type="text" name="latitude"> <br> Longitude:<br>
			<br></br> <input type="text" name="longitude"> <br> <input
				type="submit" value="Submit">
		</p>
	</form>
	<br></br>
	<br></br>

	<p align="center">
		<a href="/park" class="btn btn-danger" role="button">Back</a>
	</p>
</body>
</html>