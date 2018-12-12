<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html>
<html>
<head>
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
>
<meta charset="UTF-8">
<meta
	name="viewport"
	content="width=device-width, initial-scale=1"
>
<title>Add A Parking Location</title>
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

<link
	rel="stylesheet"
	href="/style.css"
>
</head>
<style>
.jumbotron {
	background-color: #6C18C4; /* maroon */
	color: #DEDEDE; /*lime green */
}
</style>
<body>
	<div class="jumbotron text-center">
		<h1 align="left">GoTo</h1>

	</div>

	<h2
		align="center"
		id="ex"
	>This location will be listed for future events in the area.</h2>
	<main class="flex1">
	<div class="card1">
		<form action="/add/parkingspot">
			<p>
				Purchasing Price:
				<span style="float: right;">
					<input
						type="text"
						name="price"
						style="text-align: center"
						class="input"
						required
					>
				</span>
			</p>
			<p>
				Name:
				<span style="float: right;">
					<input
						type="text"
						name="name"
						class="input"
						style="text-align: center"
						required
					>
				</span>
			</p>
			<p>
				Address:
				<span style="float: right;">
					<input
						type="text"
						name="address"
						style="text-align: center"
						class="input"
						required
					>
				</span>
			</p>
			<p>
				City:
				<span style="float: right;">
					<input
						type="text"
						name="city"
						class="input"
						style="text-align: center"
						required
					>
				</span>
			</p>
			<p>
				Latitude:
				<span style="float: right;">
					<input
						type="text"
						name="latitude"
						placeholder="(Optional)"
						style="text-align: center"
						class="input"
					>
				</span>
			</p>
			<p>
				Longitude:
				<span style="float: right;">
					<input
						type="text"
						name="longitude"
						style="text-align: center"
						placeholder="(Optional)"
						class="input"
					>
				</span>
			</p>
			<p
				style="padding-top: 35px"
				align="center"
			>
				<span>
					<input
						class="btn btn-warning"
						role="button"
						type="submit"
						value="Submit"
						class="input"
					>
				</span>
				<span>
					<a
						href="/park"
						class="btn btn-warning"
						role="button"
					>Back</a>
				</span>
			</p>
		</form>
	</div>
	</main>

</body>
</html>
