<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="<c:url value="css/ALLCSS/style.css"/>" rel="stylesheet">
<title>Login GolfersHaven</title>
</head>
<body>
<div class="container-fluid">
<h1 class="text-left">Welcome to GolfersHaven</h1>
<hr><hr>
<div class="row">
	<div class="col">
	<div id="startPage" class="carousel slide" data-ride="carousel">
	  <ul class="carousel-indicators">
	    <li data-target="#startPage" data-slide-to="0" class="active"></li>
	    <li data-target="#startPage" data-slide-to="1"></li>
	    <li data-target="#startPage" data-slide-to="2"></li>
	    <li data-target="#startPage" data-slide-to="3"></li>
	    <li data-target="#startPage" data-slide-to="4"></li>
	  </ul>
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="/pageImage/sk.JPG/" alt="South Korea" width="700" height="500">
	    </div>
	    <div class="carousel-item">
	      <img src="/pageImage/pb.jpeg/" alt="Pebble Beach" width="700" height="500">
	    </div>
	    <div class="carousel-item">
	      <img src="/pageImage/sk2.JPG/" alt="South K" width="700" height="500">
	    </div>
	    <div class="carousel-item">
	      <img src="/pageImage/ph.jpeg/" alt="Pinehurst" width="700" height="500">
	    </div>
	    <div class="carousel-item">
	      <img src="/pageImage/mR.jpeg/" alt="Mahogany Run" width="700" height="500">
	    </div>
	  </div>
	  <a class="carousel-control-prev" href="#startPage" data-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#startPage" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </a>
</div>
	<!-- <img src="/pageImage/download.jpeg/"> -->
	</div>
<div class="col">
<h3 class="text-center">Login</h3>
<p>${loginError}</p>
	<hr>
	<form method="POST" action="login/golfer">
		<div class=form-group>
			<label>Email: </label>
			<input name="loginEmail" type="email" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Password: </label>
			<input name="loginPass" type="password" class="form-control"/>
		</div>
	<button type="submit" class="btn btn-secondary  center">Login</button>
	</form>
	<br>
	<p class="text-right">
		<a href="/golfersHaven"><button class="btn btn-secondary">Create User</button></a>
	</p>
	</div>
</div>
<hr><hr>
</div>
</body>
</html>