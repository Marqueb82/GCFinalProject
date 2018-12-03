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
${ pr.size() }


<table class="table">
		<thead>
			<tr>
				<th>Item Number</th>
				<th>Item Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach
				var="item"
				items="${pr}"
			>
				<tr>
					<td>${item}</td>
					<td>item's name could go here ${ item.name }</td>
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