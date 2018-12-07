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
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Index Page</title>
</head>
<body>
	<h1 align="center">Go To</h1>
	<form
		action="/"
		method="post"
	>
		<p>
			I want to see  
			<input
				name="Search"
				class="input"
			/> in this city 
			<input
				name="City"

				class="input"
			/>
			<button>Submit</button>
		</p>
	</form>

	<table class="table">
		<thead>
			<tr>
				<th>Item Name</th>
				<th>Item Venue</th>
				<th>Item ID</th>
				<th>Date</th>
				<th>time</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach
				var="item"
				items="${Events}"
			>
			
				<tr>
					<td>${item.name}</td>
					<td>${ item._embedded.venues[0].name }</td>
					<td>${ item.id }</td>
					<td>${ item.dates.start.localDate }</td>
					<td>${ item.dates.start.localTime }</td>
					<td>					<a href="/howFar/${ item.id }">
						<button class="botton1">Select Event</button>
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