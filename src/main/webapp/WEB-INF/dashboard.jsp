<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dojo Overflow</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Questions Dashboard</h1>
			<table class="table">
				<thead>
					<tr>
						<th>Question</th>
						<th>Tags</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="question" items="${ questions }">
						<tr>
							<td><a href="/questions/${question.id}/show"> <c:out value="${ question.text }"/> </a></td>
							<td>
								<c:forEach var="tag" items="${ question.tags }">
									<c:out value="${ tag.subject }"/>, 
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		
		<p><a href="questions/new">New Question</a></p>
	</div>
</body>
</html>