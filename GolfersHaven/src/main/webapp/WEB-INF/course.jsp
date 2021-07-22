<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/css/ALLCSS/style.css"/>" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Create Course</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark">
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
	<div class="container-fluid">
		<div class="card"><br>
			<div class="card-body">
				<h2>Course Name: ${oneCourse.courseName}</h2>
				<p>Course Type: ${oneCourse.holeCount}</p>
				<p>Course Length: ${oneCourse.courseLength}</p>
				<p>Par: ${oneCourse.coursePar}</p>
				<p>City Located: ${oneCourse.courseCity}</p>
				<p>State: ${oneCourse.courseState}</p>
			</div>
		</div>
	</div>
</body>
</html>