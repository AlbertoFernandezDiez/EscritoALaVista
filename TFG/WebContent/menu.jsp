<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container theme-showcase" role="main">
	<header>
		<h1>Escrito a la Vista</h1>
	</header>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a id='inicio' href='Index'>INICIO</a></li>
					<!-- <li><a href="#">CATEGORIA</a></li> -->
					<c:choose>
						<c:when test="${not empty requestScope.userId}">
							<li><a id='crearHistoria' href="UploadBook">CREAR HISTORIA</a></li>
							<li><a id='verAutor'
								href='VerAutor?autor=<c:out value="${requestScope.userId}"></c:out>'><c:out
										value="${requestScope.userName}"></c:out></a></li>
							<li><a id='cambiarDatos' href="CambiarDatos">MODIFICAR DATOS</a></li>
							<li><a id='logout' href="LogOut">Logout</a></li>
							<input id='user' type='hidden' value='${requestScope.userId}' />

						</c:when>
						<c:otherwise>
							<li><a id='logIn' href='login.jsp'>IDENTIFICARSE</a></li>
							<li><a id='registrarse' href="registrarse.jsp">REGISTRARSE</a></li>
						</c:otherwise>
						
					</c:choose>
				</ul>
				<ul class="nav navbar-nav navbar-right">
							<li><a id='administrador' href="administrador.jsp">Administrador</a></li>
						</ul>
			</div>
		</div>
	</nav>