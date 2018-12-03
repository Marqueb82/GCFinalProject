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
	<h2>${ park }</h2>
	
	<table>
	<c:forEach var = "park" items = "${parks }" >
		<tr>
        	<td><h2>park.purchaseOption</h2></td>
        </tr>
      </c:forEach>
	</table>
</body>
</html>