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
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="col">Add a village</h3>
			<a  href="/dashboard" class="text-danger">back to the villages</a>
		</div>
		<div class="row">
			<form:form action="/process/village" method="post" modelAttribute="newVillage">
				<form:input type="hidden" path="villageFounder" value="${user.id}"/>
			    <div class="form-group">
			        <label>Title</label>
			        <form:input type="text" path="name" class="form-control" />
			        <form:errors path="name" class="text-danger" />
			    </div>
			    <input type="submit" value="Found village" class="btn btn-primary" />
			</form:form>
		</div>
	</div>

</body>
</html>