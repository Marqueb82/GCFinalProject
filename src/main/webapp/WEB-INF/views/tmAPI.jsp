<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="styles.css">
</head>

<style>
.jumbotron {
	background-color: #6C18C4; /* purple */
	color: #DEDEDE; /*gray */
}

body {
	background-color: #ddd;
}
</style>

<body>
	<div class="jumbotron text-center">
		<h1 align="left">GoTo</h1>
	</div>
	<p align="center">
		<font color="red" size="2">${ CityMessage } ${EventMessage }</font>
	</p>
	<form action="/" method="post">
		<p align="center">
			<font size="3">I want to see</font> <input name="Search" id="input" />
			<font size="3"> in this city</font> <input name="City" class="input"
				style="text-align: center" placeholder="(Optional)" />
			<button class="btn btn-warning">Submit</button>


		</p>

	</form>

	<c:if test="${ not empty Events }">
		<table class="table table-hover">
			<thead>
				<tr bgcolor="#ccffcc">
					<th><font size="4">Event</font></th>
					<th><font size="4">Venue</font></th>
					<th><font size="4">City</font></th>
					<th><font size="4">Date</font></th>
					<th><font size="4">Time</font></th>
					<th><font size="4"></font></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${Events}">
					<tr bgcolor="#FFFAF8">
						<td>${ item.name}</td>
						<td>${ item._embedded.venues[0].name }</td>
						<td>${ item._embedded.venues[0].city.name }</td>
						<td>${ item.dates.start.localDate }</td>
						<td>${ item.dates.start.localTime }</td>
						<td><a href="/howFar/${ item.id }">
								<button class="btn btn-danger">Select Event</button>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>