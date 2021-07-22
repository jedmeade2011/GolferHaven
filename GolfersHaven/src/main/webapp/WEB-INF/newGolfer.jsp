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
<link href="<c:url value="css/ALLCSS/style.css"/>" rel="stylesheet">
<title>Login GolfersHaven</title>
</head>
<body>
<div class="container-fluid">
<h1 class="text-left">Welcome to <a class="head" href="/">GolfersHaven</a></h1>
		<hr><hr>
		<div class="row">
			<div class="col">
				<h3 class="text-center">Register</h3>
				<hr>
				<form:form method="post" action="/register/create" modelAttribute="golfer">
					<div class="form-group">
						<form:label path="userName">User name: </form:label>
						<form:errors path="userName"/>
						<form:input path="userName" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="fname">First Name: </form:label>
						<form:errors path="fname"/>
						<form:input path="fname" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="lname">Last Name: </form:label>
						<form:errors path="lname"/>
						<form:input path="lname" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="email">Email: </form:label>
						<form:errors path="email"/>
						<form:input path="email" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="homeCourse">Home Course: </form:label>
						<form:errors path="homeCourse"/>
						<form:input path="homeCourse" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="handicap">Handicap: </form:label>
						<form:errors path="handicap"/>
						<form:input path="handicap"  type="number" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password: </form:label>
						<form:errors path="password"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="passwordConfirmation">Password Confirmation: </form:label>
						<form:errors path="passwordConfirmation"/>
						<form:input path="passwordConfirmation" type="password" class="form-control"/>
					</div>
					<button type="submit" class="btn btn-secondary center">Register</button>
				</form:form>
			</div>
		</div>
		<hr><hr>
</div>
</body>
</html>