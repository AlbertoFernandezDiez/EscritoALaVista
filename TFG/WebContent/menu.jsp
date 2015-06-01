<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset='utf8'>
<script src="../js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="css/style.css" /> 

</head>
<body>-->
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href='Index'>INICIO</a></li>
					<li><a href="#">CATEGORIA</a></li>
					<c:choose>
						<c:when test="${not empty requestScope.userId}">
							<li><a href="UploadBook">CREAR HISTORIA</a></li>
							<li><a
								href='VerAutor?autor=<c:out value="${requestScope.userId}"></c:out>'><c:out
										value="${requestScope.userName}"></c:out></a></li>
							<li><a href="LogOut">Logout</a></li>
						</c:when>
						<c:otherwise>
							<li><a href='login.jsp'>IDENTIFICARSE</a></li>
							<li><a href="registrarse.jsp">REGISTRARSE</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	
<!-- 
</body>
</html>
 -->