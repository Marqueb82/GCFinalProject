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
<title>Example of a list display</title>
</head>
<body>
	<h1>tmAPI</h1>
	${ Events.size() }

	<form
		action="/ticketmasterAPI"
		method="post"
	>
		<p class="message">${ message }</p>
		<p>
			Search for an Event:
			<input
				name="Search"
				value="Justin Timberlake"
				class="input"
			/>
			<button>SUBMIT.</button>
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
					<td>					<a href="/park/${ item.id }">
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