<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="ISO-8859-1">
<title>Show Village</title>
</head>
<body>
	<h1> Welcome to <c:if test="${village.villageFounder.id == user.id}">your village, </c:if>${village.name}</h1>
	<h3>Here are the villagers of ${village.name}</h3>
	<a href="/dashboard">back to the villages</a>
		<table class="table table-dark">
		  <thead>
		    <tr>
		      <th scope="col">id</th>
		      <th scope="col">name</th>
		      <th scope="col">roles</th>
		      <c:if test="${village.villageFounder.id == user.id }">
		      	<th scope="col">action</th>
		      </c:if>
		    </tr>
		  </thead>
		  <tbody>
		 <c:forEach items="${village.villagers}" var="villager">
		    <tr>
		 	<c:if test="${villager.id != village.villageFounder.id }">
			<td>${villager.id}</td>
			<td>${villager.userName}</td>
			<td>
				<c:forEach items="${villager.role}" var="role">
					${role.name} ,
				</c:forEach>
			</td>
			<c:if test="${village.villageFounder.id == user.id }">
			<td>
			<form action="/remove/user/village" method="post">
				<input type="hidden" name="_method" value="put"/>
				<input type="hidden" value="${villager.id}" name="villagerId"/>
				<input type="hidden" value="${village.id}" name="villageId"/>
				<input class="btn btn-danger" type="submit" value="Kick from village">
			</form>
			<a class="btn btn-primary" href="/add/user/role/${villager.id}"> Add Role</a>

			</td>
			</c:if>
			</c:if>
		    </tr>
		</c:forEach>
		  </tbody>
		</table>
</body>
</html>