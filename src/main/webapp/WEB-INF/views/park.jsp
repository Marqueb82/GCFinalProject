<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Park</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
*
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
	<%-- <form method="post" action="/cart/add">
        <table align="center">
            <tr>
                <th>Coffee</th><th>Description</th><th>Cost</th><th>Item Select</th>
            </tr>
            <c:forEach var="coffee" items="${coffees}">
                <tr>
                    <td>${coffee.name}</td><td>${coffee.description}</td><td>$ ${coffee.price}</td>
                    <td> <button name="id" type="submit" value="${coffee.id}">Select</button></td>
                </tr>
            </c:forEach>
        </table>
        </form> --%>

	<div>
		<form method="post" action="/park/choose">
			<table>
				<c:forEach var="park" items="${Parks }">
					<tr>
						<td>${park.locationId}</td>
						<td>$${park.purchaseoption[0].price.usd}</td>
						<td>${park.distance.straightline.getDescription()}</td>
						<td>${park.embedded.location.address1 }</td>
						<td>${park.embedded.location.name }</td>
						<td>${park.embedded.location.city }</td>
						<td>
							<button name="ParkPrice" type="submit"
								value="${park.purchaseoption[0].price.usd}">Select</button>
						</td>

					</tr>
				</c:forEach>

				<tr>
					<td><h2>User found parking locations</h2></td>
				</tr>
				<tr>

					<c:forEach var="userpark" items="${userparking }">
						<tr>
							<td>${userpark.id}</td>
							<td>$${userpark.purchasingprice }</td>
							<td>${userpark.name }</td>
							<td>${userpark.address }</td>
							<td>${userpark.name }</td>
							<td>${userpark.city }</td>
							<td>
								<button name="ParkPrice" type="submit"
									value="${userpark.purchasingprice}">Select</button>
							</td>

						</tr>
					</c:forEach>
			</table>
		</form>
		<a href="/parkingspot" class="btn btn-info" role="button">Add my
			own parking</a>
	</div>


	<div>
		<table class="table table-bordered" id="myTable">
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
				<td>Ticket Cost</td>
				<td>${ParkPrice }</td>
				<td>${ParkPrice }</td>
				<td>${ParkPrice }</td>
				<td>${ParkPrice }</td>
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
</body>
</html>