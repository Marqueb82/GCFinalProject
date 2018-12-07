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
</head>
<body>

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
		<p>${event.name }</p>
	</div>

	<div>
	<form method="post" action="/park/choose">
		<table>
			<c:forEach
				var="park"
				items="${Parks }"
			>
				<tr>
					<td>${park.locationId}</td>
					<td>$${park.purchaseoption[0].price.usd}</td>
					<td>${park.distance.straightline.getDescription()}</td>
					<td>${park.embedded.location.address1 }</td>
					<td>${park.embedded.location.name }</td>
					<td>${park.embedded.location.city }</td>
					<td> <button name="ParkPrice" type="submit" value="${park.purchaseoption[0].price.usd}">Select</button></td>

				</tr>
			</c:forEach>

		<tr><td><h2>User found parking locations</h2></td></tr>		<tr>

			<c:forEach
				var="userpark"
				items="${userparking }"
			>
				<tr>
					<td>${userpark.id}</td>
					<td>$${userpark.purchasingprice }</td>
					<td>${userpark.name }</td>
					<td>${userpark.address }</td>
					<td>${userpark.name }</td>
					<td>${userpark.city }</td>
					<td> <button name="ParkPrice" type="submit" value="${userpark.purchasingprice}">Select</button></td>
					
				</tr>
			</c:forEach>
		</table>
        </form>
		<a
			href="/parkingspot"
			class="btn btn-info"
			role="button"
		>Add my own parking</a>
	</div>
</body>
</html>