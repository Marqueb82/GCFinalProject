<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>GoTo: Event Details</title>
</head>
<body>

<div class="jumbotron">
	<h2>GoTo: <font size="8">${event.name }</font></h2>
</div>

<form action="/park">
	How far are you willing to walk from your parking spot? <br>
	<input type="number" min=0 step=.1 name="howFar"> miles<br> <br>
	How far are you driving to your event? <br>
	<input type="number" min=0 max=300 name="DrivingDistance"> miles<br><br>
	How many people can you fit in your car? <br>
	<input type="number" min=2 max=8 name="vSize">people <br>
	<input type="submit" value="Submit">
</form>

</body>
</html>