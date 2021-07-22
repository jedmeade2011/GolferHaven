<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Profile</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark">
	  <a class="navbar-brand" href="/home"><img src="/pageImage/home_.png" alt="home" style="width:50px;height:50px;"></a>
	  <div class="searchForm">
	  <form action="/course/search">
		<input type="text" name="golfCourse" name="golfers">
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
		<hr>
		<div class="row">
			<div class="col">
				<h3 class="text-center">${golfer.userName} rounds played</h3>
				<div class="tableTwo">
					<div class="table-wrapper-scroll-y my-custom-scrollbar2">
						<table class="table table-striped">
								<thead>
									<tr>
										<th>Course</th>
										<th>State</th>
										<th>Tees</th>
										<th>Holes Played</th>
										<th>Score</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${golfer.rounds}" var="oneRound">
										<tr>
											<td>${oneRound.golfCourse}</td>
											<td>${oneRound.state}</td>
											<td>${oneRound.tees }</td>
											<td>${oneRound.holesPlayed}</td>
											<td>${oneRound.score}</td>
											<c:choose>
												<c:when test="${golf.id == golfer.id}">
													<td><a href="/round/${oneRound.id}/edit"><button class=" btn btn-light">Edit</button></a></td>
												</c:when>
											</c:choose>
										</tr>
									</c:forEach>
								</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col text-center">
				<h3 class="text-center">Player Information</h3>
				<div class="card"><br>
						<c:choose>
						  <c:when test="${empty golfer.profilePhotos.profilePic}">
	       						 <p>No profile picture available </p>
	    					</c:when>
	    					<c:otherwise>
	    						<p class="text-center">
	        						<img src="${golfer.profilePhotos.profilePic}" height="160px" width="120px">
	        					</p>
	   						 </c:otherwise>
						</c:choose>
					<div class="card-body">
						<p>Golfer: ${golfer.fname}</p>
						<p>Home Course: ${golfer.homeCourse}</p>
						<p>Number of rounds played: ${golfer.rounds.size()}</p>
						<p>Handicap: ${golfer.handicap}</p>
					 </div>
				</div><br>
				<c:choose>
					<c:when test="${golf.id == golfer.id}">
						<h4>Upload photo</h4>
						<form method="POST" action="/profile/${golfer.id}/profileP" enctype="multipart/form-data">
							<div class="form-data">
								<p class="text-center">
									<input type="file" name="profile">
								</p>
							</div>
							<p class="text-center">
								<button type="submit" class="btn btn-secondary center">Submit</button>
							</p>
						</form>
						<p class="text-center">
							<a href="/delete/profPic/${prof.id}"><button class=" btn btn-secondary">Delete</button></a>
						</p>
					</c:when>
				</c:choose>
			</div>
		</div>
		<hr>
		<h3 class="text-center">Photo Wall</h3>
		<c:forEach items="${golfer.pictures}" var="onePic">
			<img src="${onePic.image_url}" height="150px" width="200px" class="img-thumbnail m-2 pb-2">
				<c:choose>
					<c:when test="${golf.id == golfer.id}">
						<a href="/delete/picture/${onePic.id}"><button class=" btn btn-secondary">Delete</button></a>
					</c:when>
				</c:choose>
		</c:forEach>
		<hr>
		<c:choose>
			<c:when test="${golf.id == golfer.id}">
				<form method="POST" action="/profile/${golfer.id}/upload" enctype="multipart/form-data">
					<div class="form-data">
						<p>
							<label>Select File:</label>
							<input type="file" name="image">
						</p>
					</div>
					<p>
						<button type="submit" class="btn btn-secondary center">Submit</button>
					</p>
				</form>
			</c:when>
		</c:choose>
	</div>
</body>
</html>
