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
		<div class="row">
			<div class="col">
				<h3 class="text-center ">All Courses</h3>
		<div class="tableOne">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Course</th>
						<th>Player profile</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="coursess">
						<tr>
							<td><a href="/course/${coursess.id}">${coursess.courseName}</a></td>
							<td><a href="/profile/${coursess.courses.id}">${coursess.courses.userName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
			</div>
			<div class="col">
			<h1 class="text-right">Input Course</h1>
			<form:form method="post" action="create/course" modelAttribute="course">
				<div class="form-group">
					<form:label path="courseName">Course Name: </form:label>
					<form:errors path="courseName"/>
					<form:input path="courseName" type="text" class="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="courseCity">Course City: </form:label>
					<form:errors path="courseCity"/>
					<form:input path="courseCity" type="text" class="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="courseState"> Course State: </form:label>
					<form:errors path="courseState"/>
					<form:select path="courseState">
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
					
						<option value="AR">Arkansas</option>
					
						<option value="CA">California</option>
					
						<option value="CO">Colorado</option>
					
						<option value="CT">Connecticut</option>
					
						<option value="DE">Delaware</option>
					
						<option value="DC">District Of Columbia</option>
					
						<option value="FL">Florida</option>
					
						<option value="GA">Georgia</option>
					
						<option value="HI">Hawaii</option>
					
						<option value="ID">Idaho</option>
					
						<option value="IL">Illinois</option>
					
						<option value="IN">Indiana</option>
					
						<option value="IA">Iowa</option>
					
						<option value="KS">Kansas</option>
					
						<option value="KY">Kentucky</option>
					
						<option value="LA">Louisiana</option>
					
						<option value="ME">Maine</option>
					
						<option value="MD">Maryland</option>
					
						<option value="MA">Massachusetts</option>
					
						<option value="MI">Michigan</option>
					
						<option value="MN">Minnesota</option>
					
						<option value="MS">Mississippi</option>
					
						<option value="MO">Missouri</option>
					
						<option value="MT">Montana</option>
					
						<option value="NE">Nebraska</option>
					
						<option value="NV">Nevada</option>
					
						<option value="NH">New Hampshire</option>
					
						<option value="NJ">New Jersey</option>
					
						<option value="NM">New Mexico</option>
					
						<option value="NY">New York</option>
					
						<option value="NC">North Carolina</option>
					
						<option value="ND">North Dakota</option>
					
						<option value="OH">Ohio</option>
					
						<option value="OK">Oklahoma</option>
					
						<option value="OR">Oregon</option>
					
						<option value="PA">Pennsylvania</option>
					
						<option value="RI">Rhode Island</option>
					
						<option value="SC">South Carolina</option>
					
						<option value="SD">South Dakota</option>
					
						<option value="TN">Tennessee</option>
					
						<option value="TX">Texas</option>
					
						<option value="UT">Utah</option>
					
						<option value="VT">Vermont</option>
					
						<option value="VA">Virginia</option>
						
						<option value="USVI">Virgin Islands</option>
					
						<option value="WA">Washington</option>
					
						<option value="WV">West Virginia</option>
					
						<option value="WI">Wisconsin</option>
					
						<option value="WY">Wyoming</option>
					</form:select>
				</div>
				<div class="form-group">
					<form:label path="holeCount">Course Type: </form:label>
					<form:errors path="holeCount"/>
					<form:select path="holeCount">
						<option value="9 Hole">9 Hole</option>
						<option value="18 Holes">18 Holes</option>
					</form:select>
				</div>
				<div class="form-group">
					<form:label path="coursePar">Course Par: </form:label>
					<form:errors path="coursePar"/>
					<form:input type="number" path="coursePar" class="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="courseLength">Course Length: </form:label>
					<form:input path="courseLength"  type="number" class="form-control"/>
				</div>	
				<p class="text-center">
					<button type="submit" class="btn btn-outline-secondary center">Submit</button>
				</p>					
			</form:form>
			</div>
		</div>
	</div>
</body>