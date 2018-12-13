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
	<section class="flex3">
		<span id="image2"><img
			src="/images/spot.png"
			id="spot"
		></span>
		<main>
		<div class="card1">
			<form action="/add/parkingspot">
				<p class="howfarr">
					Purchasing Price:
					<br>
					<input
						type="text"
						class="input"
						style="text-align: center"
						name="price"
						required
					>
					<br>
					<br>
					Name:
					<br>
					<input
						type="text"
						class="input"
						style="text-align: center"
						name="name"
						required
					>
					<br>
					<br>
					Address:
					<br>
					<input
						type="text"
						class="input"
						style="text-align: center"
						name="address"
						required
					>
					<br>
					<br>
					City:
					<br>
					<input
						type="text"
						class="input"
						style="text-align: center"
						name="city"
						required
					>
					<br>
					<br>
					Latitude:
					<br>
					<input
						type="text"
						class="input"
						style="text-align: center"
						name="latitude"
						placeholder="(Optional)"
					>
					<br>
					<br>
					Longitude:
					<br>
					<input
						type="text"
						class="input"
						style="text-align: center"
						name="longitude"
						placeholder="(Optional)"
					>
					<br>
					<br>
					Longitude:
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
	</section>

</body>
</html>
