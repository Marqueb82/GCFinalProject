<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Park</title>
</head>
<body>

	<div>
		<p>${event.name }</p>
	</div>

	<div>
	<table>
	<c:forEach var = "park" items = "${Parks }" >
		<tr>
        	<td>${park.locationId}</td>
        	<td>${park.getCost()}</td>
        	<td>${park.distance.straightline.getDescription()}</td>
        	<td>${park.embedded.location.address1 }</td>
        	<td>${park.embedded.location.name }</td>
        	<td>${park.embedded.location.city }</td>
        	
        </tr>
      </c:forEach>
	</table>
	<br>
	<h2>User found parking locations</h2>
	<table>
	<c:forEach var = "userpark" items = "${userparking }" >
		<tr>
        	<td>${userpark.purchasingprice}</td>
        	<td>${userpark.address }</td>
        	<td>${userpark.name }</td>
        	<td>${userpark.city }</td>
        </tr>
      </c:forEach>
	</table>
	 <a href="/parkingspot" class="btn btn-info" role="button">Add my own parking</a>
	</div>
</body>
</html>