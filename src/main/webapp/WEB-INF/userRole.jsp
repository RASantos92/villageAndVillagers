<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="col" >Add a role to ${villager.userName}</h1>
			<a href="/dashboard">back to the villages</a>
		</div>
		<div class="row">
		<form action="/process/user/role/${villager.id}" method="post">
			<input type="hidden" name="_method" value="put">
			<select name="role">
				<c:forEach items="${allRoles}" var="role">
					<option value="${role.id}">${role.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Add Role" class="btn btn-primary" />
		</form>		

		</div>
	</div>
</body>
</html>