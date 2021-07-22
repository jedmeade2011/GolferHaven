<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 <link href="<c:url value="/css/ALLCSS/style.css"/>" rel="stylesheet">
<title>Home Page</title>
</head>
<body>
	<nav class="navbar navbar-expand">
	  <a class="navbar-brand" href="/home"><img src="/pageImage/home_.png" alt="home" style="width:50px;height:50px;"></a>
	  <div class="searchForm">
	  <form action="/course/search">
		<input type="text" name="golfCourse">
		<button type="submit" class="btn btn-outline-light center btn-sm">Search Courses</button>
	  </form> 
	 </div>
<div class="navy">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" data-toggle="tooltip" title="Create New Round" href="/newRound"><img src="/pageImage/pencil.png" alt="create" style="width:30px;height:30px;"></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" data-toggle="tooltip" title="Create New Course" href="/newCourse"><img src="/pageImage/hole.png" alt="create" style="width:35px;height:35px;"></a>
	      </li>
		  <li class="nav-item">
			<c:choose>
			  <c:when test="${empty golf.profilePhotos.profilePic}">
     				<a class="nav-link" data-toggle="tooltip" title="Your profile" href="/profile/${golf.id }"><img src="/pageImage/iconss.png" alt="profile" style="width:30px;height:30px;"></a>
  			  </c:when>
  			  <c:otherwise>
  				<p class=" para text-center">
      				<a class="nav-link" data-toggle="tooltip" title="Your profile" href="/profile/${golf.id }"><img src="${golf.profilePhotos.profilePic}" style="width:30px;height:30px;"></a>
      			</p>
 			</c:otherwise>
		   </c:choose>
	      </li>     
	      <li class="logout nav-item">
	        <a class="nav-link" data-toggle="tooltip" title="Logout" href="/logout"><img src="/pageImage/logout.png" alt="logout" style="width:55px;height:25px;"></a>
	      </li> 
	    </ul>
	   </div>
	</nav>
<div class="container-fluid bg-light">
	<h3 class="text-center">Rounds Played</h3>
	<div class="tableFour">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Golfer</th>
					<th>Score</th>
					<th>Tees</th>
					<th>Holes Played</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${course}" var="coursess">
				<tr>
					<c:choose>
					  <c:when test="${empty coursess.golfers.profilePhotos.profilePic}">
       						<td><a href="/profile/${coursess.golfers.id}">${coursess.golfers.userName}</a></td>
    					</c:when>
    					<c:otherwise>
							<td class="text-left"><img src="${coursess.golfers.profilePhotos.profilePic}" class="rounded-circle" style="width:40px;height:50px;"> <a href="/profile/${coursess.golfers.id}">${coursess.golfers.userName}</a></td>
   						 </c:otherwise>
					</c:choose>
					<td>${coursess.score}</td>
					<td>${coursess.tees}</td>
					<td>${coursess.holesPlayed}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<p>
</div>
	<hr><hr>
</body>
</html>