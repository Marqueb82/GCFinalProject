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
<title>Park</title>
<meta charset="utf-8">
<meta
	name="viewport"
	content="width=device-width, initial-scale=1"
>
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
<style>
.jumbotron {
	background-color: #3E1869; /* maroon */
	color: #ccffcc; /*lime green */
}

#myTable td {
	border: 1px solid black;
	padding: 12px;
	border-collapse: collapse;
}

/
</style>
</head>
<body>
	<div class="jumbotron text-center">
		<h1 align="left">GoTo</h1>

	</div>
	<h1>

		<font size="6">${event.name }</font>

	</h1>

	<div>
			<table>
				<tr>
					<th>Distance</th>
					<th>Name</th>
					<th>Address</th>
					<th>City</th>
					<th>Price</th>

					<tr>
				<c:forEach
						var="park"
						items="${allParking }"
					>
						<tr>
						<td>${park.distanceDescription}</td>
						<td>${park.name}</td>
						<td>${park.address}</td>
						<td>${park.city }</td>
						<td>${park.price }</td>
						<form
							id="test"
							method="post"
							action="/park/choose/"
						>
							<td>
								<input
									name="Name"
									type="hidden"
									value="${park.name}"
								>
								<button
									class="btn btn-success"
									name="Price"
									type="submit"
									value="${park.price}"
								>Select</button>
							</td>
						</form>
					</tr>
					</c:forEach>
			</table>
		<a
			href="/parkingspot"
			class="btn btn-info"
			role="button"
		>Add my own parking</a>
	</div>


	<a href="#myModal" role="button" class="btn" data-toggle="modal">See modal</a>
	
	<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
     <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Possible Cost</h4>
                  </div>
        <div class="modal-body">
	<table id="myTable">
		<tr>
			<td>Expenses</td>
			<td>Solo</td>
			<td>Two Passengers</td>
			<td>Three Passengers</td>
			<td>Four Passengers</td>
		</tr>
		<tr>
			<td>Gas Cost</td>
			<td>${GasCost }</td>
			<td>${GasCost }</td>
			<td>${GasCost }</td>
			<td>${GasCost }</td>
		</tr>
		<tr>
			<td>Parking Cost</td>
			<td>${ParkPrice }</td>
			<td>${ParkPrice }</td>
			<td>${ParkPrice }</td>
			<td>${ParkPrice }</td>
		</tr>		<tr>
			<td>Ticket Price</td>
			<td>${TicketRange }</td>
			<td>-</td>
			<td>-</td>
			<td>-</td>
		</tr>
		<tr>
			<td>Total Cost</td>
			<td>${TotalCost }</td>
			<td>${twoTotalCost }</td>
			<td>${threeTotalCost }</td>
			<td>${fourTotalCost }</td>
		</tr>
	</table>
	</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
	
	<a href="/back">back to start</a>
</body>
</html>