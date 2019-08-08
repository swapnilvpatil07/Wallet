<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Error <c:out value="${errCd}"></c:out></title>

<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Montserrat:500"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Titillium+Web:700,900"
	rel="stylesheet">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="/css/style.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>
					<c:out value="${errCd}"></c:out>
				</h1>
			</div>
			<c:if test="${errCd eq '404'}">
				<h2>Oops! This Page Could Not Be Found</h2>
				<p>Sorry but the page you are looking for does not exist, have
					been removed. name changed or is temporarily unavailable</p>
			</c:if>
			
			<c:if test="${errCd eq '500'}">
				<h2>Oops! Internal Server Error!!!</h2>
				<p>Sorry but the page you are looking for does not exist, have
					been removed. name changed or is temporarily unavailable</p>
			</c:if>
			
			<c:if test="${(not empty errMsg) and ((errCd != '404') or (errCd != '500'))}">
				<h2>Oops! Something Went Wrong!!!</h2>
				<p><c:out value="${errMsg}"></c:out> </p>
			</c:if>

			<a href="/wallet/secure/home">Go To Homepage</a>
		</div>
	</div>

</body>

</html>