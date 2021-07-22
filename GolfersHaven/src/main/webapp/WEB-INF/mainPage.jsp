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
<link href="<c:url value="css/ALLCSS/style.css"/>" rel="stylesheet">
<title>Home Page</title>
</head>
<body>
	<nav class="navbar navbar-expand-xl navbar-dark">
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
	<br>
	<h1 class=" mainHead text-left">Welcome ${golf.fname }</h1>
		<div class="row">
			<div class="col">
				<h3 class="text-center">Message Wall</h3>
				<div class="tableThree">
					<div class="table-wrapper-scroll-y my-custom-scrollbar">
						<table class="table table-borderless">
							<tbody>
							<c:forEach items="${messages}" var = "message">
								<tr>
								<c:choose>
									  <c:when test="${empty message.golferMessage.profilePhotos.profilePic}">
				       						<td class="text-left">${message.golferMessage.userName}: ${message.comment} <br> <span class="badge">likes: ${message.golferLikes.size() }</span></td>
				    					</c:when>
				    					<c:otherwise>
											<td class="text-left"><img src="${message.golferMessage.profilePhotos.profilePic}" class="rounded-circle" style="width:40px;height:50px;">  ${message.golferMessage.userName}: ${message.comment} <br> <span class="badge">likes: ${message.golferLikes.size() }</span></td>
				   						 </c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${message.golferLikes.contains(golf)}">
										 	<td><a data-toggle="tooltip" title="Unlike message" href="/unliked/${message.id }"><img src="/pageImage/unliked.png" alt="unlike" style="width:20px;height:20px;"></a></td>
										 </c:when>
										<c:otherwise>
											<td><a data-toggle="tooltip" title="Like message" href="/liked/${message.id}"><img src="/pageImage/Likes.png" alt="likes" style="width:25px;height:25px;"></a>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${golf.id == message.golferMessage.id}">
											<td><a href="/delete/message/${message.id}"><button class=" btn btn-light">Delete</button></a></td>
										</c:when>
									</c:choose>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<br>
			<form:form method="POST" action="/home/messages" modelAttribute="message">
				<p class="text-center">
					<form:errors path="comment"/>
					<form:input path="comment" type="textarea" placeholder="Add comment"/>
					<button class="btn btn-secondary">Submit</button>
				</p>
			</form:form>
			<hr>
		</div>
		<div class="col">
			<img src="/pageImage/golfquote.jpeg" alt="quote" style="width:600px;height:600px;">
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col">
		<h3 class="text-center ">Rounds on your course</h3>
		<div class="tableOne">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Course</th>
						<th>State</th>
						<th>Tees</th>
						<th>Holes Played</th>
						<th>Score</th>
						<th>Player profile</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rounds}" var="roundss">
						<tr>
							<td>${roundss.golfCourse}</td>
							<td>${roundss.state}</td>
							<td>${roundss.tees }</td>
							<td>${roundss.holesPlayed}</td>
							<td>${roundss.score}</td>
							<td><a href="/profile/${roundss.golfers.id}">${roundss.golfers.userName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<hr>
	<div class="col">
		<h3 class="text-center ">All other rounds</h3>
		<div class="tableOne">
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Course</th>
							<th>State</th>
							<th>Tees</th>
							<th>Holes Played</th>
							<th>Score</th>
							<th>Player profile</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${round}" var="otherRound">
							<tr>
								<td>${otherRound.golfCourse}</td>
								<td>${otherRound.state}</td>
								<td>${otherRound.tees }</td>
								<td>${otherRound.holesPlayed}</td>
								<td>${otherRound.score}</td>
								<td><a href="/profile/${otherRound.golfers.id}">${otherRound.golfers.userName}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</div>
	<hr>
</div>	
</body>
</html>