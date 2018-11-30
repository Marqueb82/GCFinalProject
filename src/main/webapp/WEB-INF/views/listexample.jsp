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
				items="${numbers}"
			>
				<tr>
					<td>${item}</td>
					<td>item's name could go here ${ item })</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<table class="table">
		<thead>
			<tr>
				<th>Hash Key</th>
				<th>Hash Value</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach
				items="${Dec6th}"
				var="entry"
			>
				<tr>
					<td>Key = ${entry.key}</td>
					<td>value = ${entry.value}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>