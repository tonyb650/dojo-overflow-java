<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dojo Overflow - Question Detail</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1><c:out value="${ question.text }"/> </h1>
		<p><a href="/dashboard">Back to dashboard</a></p>
		<div class="row">
			<div class="col">
				<h4>Tags:</h4>
				<c:forEach var="tag" items="${ question.tags }">
					<button class="btn btn-secondary"><c:out value="${tag.subject}" /></button>
				</c:forEach>
			</div>
		</div>
		<div class="row">

			<div class="col border-top border-bottom">
				<h4>Answers:</h4>
				<ul>
					<c:forEach var="answer" items="${ question.answers }">
						<li><c:out value="${ answer.text }"/></li>
					</c:forEach>
				</ul>
			</div>
			
		</div>	
			<form:form action="/answers/new" modelAttribute="answer" method="post">
				<input type="hidden" name="question_id" value="${ question.id }"></input>
				<form:label path="text" class="form-label">Answer</form:label>
				<form:errors path="text" class="text-danger"/>
				<form:textarea path="text" rows="3" class="form-control"></form:textarea>
				<input type="submit" class="btn btn-secondary" />
			</form:form>
	</div>
</body>
</html>