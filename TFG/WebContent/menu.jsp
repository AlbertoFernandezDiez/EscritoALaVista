<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container theme-showcase" role="main">
		<header>
			<h1>Nombre de la web</h1>
		</header>
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