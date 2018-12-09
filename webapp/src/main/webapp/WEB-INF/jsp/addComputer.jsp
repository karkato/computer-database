<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.header" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="label.title" /></a>
				<div class="btn-group btn-group-sm pull-right" role="group"> <a
				href="?lang=fr">
				<button type="button" class="btn btn-default">
					<spring:message code="french" />
				</button>
			</a> <a href="?lang=en">
				<button type="button" class="btn btn-default">
					<spring:message code="english" />
				</button>
			</a>
		</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="add.name" />
					</h1>
					<form:form id="addForm" action="addComputer"
						modelAttribute="computerDTO" method="POST">
						<fieldset>
							<div class="form-group">
								<form:label path="name" for="computerName">
									<spring:message code="label.cpuname" />
								</form:label>
								<form:input path="name" type="text" class="form-control"
									id="computerName" name="computerName"
									placeholder="Computer name" />
							</div>
							<div class="form-group">
								<form:label path="introduced" for="introduced">
									<spring:message code="label.intro" />
								</form:label>
								<form:input path="introduced" type="date" class="form-control"
									id="introduced" name="introDate" placeholder="Introduced date" />
							</div>
							<div class="form-group">
								<form:label path="discontinued" for="discontinued">
									<spring:message code="label.disco" />
								</form:label>
								<form:input path="discontinued" type="date" class="form-control"
									id="discontinued" name="discDate"
									placeholder="Discontinued date" />
							</div>
							<div class="form-group">
								<form:label path="companyId" for="companyId">
									<spring:message code="label.company" />
								</form:label>
								<form:select path="companyId" class="form-control"
									id="companyId" name="companyId" >

								<option value="0">--</option>
								<c:forEach items="${companies}" var="company">
									<option value="${company.getId()}">
										<c:out value="${company.getName()}" />
									</option>
								</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<spring:message code="add.button" var="Add" />
							<input type="submit" value="${Add}" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default"><spring:message
									code="add.cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="./js/jquery.min.js">
		
	</script>
	<script src="./js/jquery.validate.min.js"></script>
	<script src="./js/formValidator.js"></script>
</body>
</html>