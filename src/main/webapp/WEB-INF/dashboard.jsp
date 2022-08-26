<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h3 class="col">welcome <c:out value="${user.userName}"/></h3>
			<a  href="/logout" class="text-danger">LOGOUT</a>
		</div>
		<div class="row">
			<p class="col" >Here are all the villages </p>
			<a href="new/village">found a village</a>||
			<a href="/new/role">Create a Role</a>
		</div>
		<div class="row">
							
			<table class="table table-dark">
			  <thead>
			    <tr>
			      <th scope="col">id</th>
			      <th scope="col">Village Name</th>
			      <th scope="col">Village Founder</th>
			      <th class="text-center" scope="col">Village Population</th>
			      <th class="text-center" scope="col">Join Village</th>
			    </tr>
			  </thead>
			  <tbody>
			      	<c:forEach items="${allVillages}" var="village">
			    		<tr>
							<td>${village.id}</td>
							<td><a href="/show/village/${village.id}">${village.name}</a></td>
							<td>${village.villageFounder.userName}</td>
							<td class="text-center">${village.villagers.size()}</td>
							<td class="text-center">
							
							<c:if test="${village.id != user.village.id}">
							
							<form action="/add/user/village" method="post">
								<input type="hidden" name="_method" value="put"/>
								<input type="hidden" value="${village.id}" name="id"/>
								<input class="btn btn-success" type="submit" value="Join village">
							</form>
							</c:if>
							<c:if test="${village.id == user.village.id}">
								Your village
							</c:if>
							</td>
			    		</tr>
				</c:forEach>
			  </tbody>
			</table>
		</div>
	</div>
</body>
</html>