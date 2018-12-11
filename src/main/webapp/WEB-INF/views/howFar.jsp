<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css">

<title>Index Page</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>GoTo: Event Details</title>
</head>
<style>
.jumbotron {
	background-color: #6C18C4; /* maroon */
	color: #DEDEDE; /*lime green */
}
/* .howfarr {
    text-align: right;
} */
</style>
<body>
	<div class="jumbotron">
		<h1>
			GoTo
		</h1>
		<p><font size="8">${event.name}</font></p>
	</div>

	<form action="/park">
		<div>

			<p class = "howfarr">How far are you willing to walk from your parking spot?
			<br> <input type="number" min=0 step=.1 name="howFar">
			miles<br> <br>How far are you driving to your event? <br>
			<input type="number" min=0 max=300 name="DrivingDistance">
			miles<br> <br>How many people can you fit in your car?<br>
			<input type="number" min=2 max=8 name="vSize">people <br>
			</p>
			<button class="btn btn-danger">Submit</button>
		</div>
<!-- <input type="submit" value="Submit"> -->
			</form>




</body>
</html>