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

	<table>
	<c:forEach var = "park" items = "${Parks }" >
		<tr>
        	<td>${park.locationId}</td>
        	<td>${park.purchaseoption}</td>
        	<td>${park.distance.straightline.getDescription()}</td>
        	<td>${park.embedded.location.address1 }</td>
        	<td>${park.embedded.location.name }</td>
        	<td>${park.embedded.location.city }</td>
        	
        </tr>
      </c:forEach>
	</table>
</body>
</html>