<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"
%>
<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD

	<link rel="stylesheet" href="/style.css">
=======
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
>
>>>>>>> 345371cd100746220b7e41b93c8ff7469999557b
<meta charset="UTF-8">
<meta
	name="viewport"
	content="width=device-width, initial-scale=1"
>
<title>Index Page</title>
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
></script>
<link
	rel="stylesheet"
	href="styles.css"
>
</head>
<style>
.jumbotron {
	background-color:#6C18C4; /* purple */
	color: #DEDEDE; /*gray */
}
body{
background-color: #ddd;
}
input {
	transition: all .5s;
	text-align: center;
}

input:hover {
	transform: scale(1.1);
}
</style>


<body>
	<div class="jumbotron text-center">
		<h1 align="left">GoTo</h1>
	</div>
	<p align="center">
		<font
			color="red"
			size="2"
		>${ CityMessage } ${EventMessage }</font>
	</p>
	<form
		action="/"
		method="post"
	>
		<p align="center">
			<font size="3">I want to see</font>
			<input
				name="Search"
				id="input"
				
			/>
			<font size="3"> in this city</font>
			<input

<<<<<<< HEAD
		<p align="center">
			<font size="3">I want to see</font> <input name="Search"
				class="input" /><font size="3"> in this city</font> <input
				name="City" class="input" />
			<button class="btn btn-warning">Submit</button>
=======
				name="City"
				class="input"
				placeholder="(Optional)"
				/>
			<button class="btn btn-danger">Submit</button>
>>>>>>> 345371cd100746220b7e41b93c8ff7469999557b
		</p>

	</form>

<c:if test="${ not Searched }">
	<table class="table table-hover">
		<thead>

			<tr bgcolor="#F4CABC">

<<<<<<< HEAD
				<th><font size="4">Item Name</font></th>
				<th><font size="4">Item Venue</font></th>

				<th><font size="4">Date</font></th>
				<th><font size="4">time</font></th>
				<th><font size="4"></font></th>
=======
				<th>
					<font size="4">Item Name</font>
				</th>
				<th>
					<font size="4">Item Venue</font>
				</th>
				<th>
					<font size="4">Item ID</font>
				</th>
				<th>
					<font size="4">Date</font>
				</th>
				<th>
					<font size="4">time</font>
				</th>
				<th>
					<font size="4"></font>
				</th>
>>>>>>> 345371cd100746220b7e41b93c8ff7469999557b

			</tr>

		</thead>

		<tbody>
			<c:forEach
				var="item"
				items="${Events}"
			>

<<<<<<< HEAD
				<tr bgcolor="#FFFAF8  ">
=======
				<tr bgcolor="#E1E2F7">
>>>>>>> 345371cd100746220b7e41b93c8ff7469999557b
					<td>${item.name}</td>
					<td>${ item._embedded.venues[0].name }</td>

					<td>${ item.dates.start.localDate }</td>
					<td>${ item.dates.start.localTime }</td>
<<<<<<< HEAD
					<td><a href="/howFar/${ item.id }">
							<button class="btn btn-warning">Select Event</button>
					</a></td>
=======
					<td>
						<a href="/howFar/${ item.id }">
							<button class="btn btn-danger">Select Event</button>
						</a>
					</td>
>>>>>>> 345371cd100746220b7e41b93c8ff7469999557b
				</tr>
			</c:forEach>
		</tbody>
	</table>
<<<<<<< HEAD

=======
</c:if>
>>>>>>> 345371cd100746220b7e41b93c8ff7469999557b
</body>
</html>