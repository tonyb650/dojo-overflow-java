<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dojo Overflow - Add Question</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>What is your question?</h1>
			<p><a href="/dashboard">Back to dashboard</a></p>
			<form:form action="/questions/new" modelAttribute="question" method="post">
				<form:label path="text" class="form-label">Question</form:label>
				<form:errors path="text" class="text-danger"/>
				<form:textarea path="text" rows="3" class="form-control"></form:textarea>
				<form:label path="tagEntry" class="form-label">Tags:</form:label>
				<form:errors path="tagEntry" class="text-danger"/>
				<form:input type="text" path="tagEntry" class="form-control"/>
				<input type="submit" class="btn btn-secondary" />
			</form:form>
	</div>
</body>
</html>