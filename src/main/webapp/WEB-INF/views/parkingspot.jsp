<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Parking</title>
</head>
<body>

<h2>This location will be listed for future events in similar location</h2>

<form action="/add/parkingspot">
  Purchasing Price:<br>
  <input type="text" name="purchasingprice">
  <br>
  Name:<br>
  <input type="text" name="name">
  <br><br>
  Address:<br>
  <input type="text" name="address">
  <br>
  City:<br>
  <input type="text" name="city">
  <br>
  Latitude:<br>
  <input type="text" name="latitude">
  <br>
  Longitude:<br>
  <input type="text" name="longitude">
  <br>
  <input type="submit" value="Submit">
  </form> 
  
  <a href="/park" class="btn btn-info" role="button">Back</a>

</body>
</html>