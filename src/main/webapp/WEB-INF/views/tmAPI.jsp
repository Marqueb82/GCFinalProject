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

</head>
<style>
.jumbotron {
	background-color: #EE6032; /* orange*/
	color: #FfFfFF /* white */
}
</style>

<body>
	<div class="jumbotron text-center">
		<h1 align="left">GoTo</h1>

	</div>
	<form action="/" method="post">

		<p align="center">
			<font size="3">I want to see</font> <input name="Search"
				class="input" /><font size="3"> in this city</font> <input
				name="City" class="input" />
			<button class="btn btn-danger">Submit</button>
		</p>

	</form>

	<table class="table table-hover">
		<thead>

			<tr bgcolor="#F4CABC">

				<th><font size="4">Item Name</font></th>
				<th><font size="4">Item Venue</font></th>

				<th><font size="4">Date</font></th>
				<th><font size="4">time</font></th>
				<th><font size="4"></font></th>

			</tr>

		</thead>

		<tbody>
			<c:forEach var="item" items="${Events}">

				<tr bgcolor="#FFFAF8  ">
					<td>${item.name}</td>
					<td>${ item._embedded.venues[0].name }</td>

					<td>${ item.dates.start.localDate }</td>
					<td>${ item.dates.start.localTime }</td>
					<td><a href="/howFar/${ item.id }">
							<button class="btn btn-danger">Select Event</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%-- 	<c:forEach var="event" items="${ pr }">
		<div>
			<p>${ event }</p>
			<p><img src="${ event.image }" /></p>
		</div>
	</c:forEach> --%>


	<%-- ${ pagedresponse } response of entire object--%>
</body>
</html>