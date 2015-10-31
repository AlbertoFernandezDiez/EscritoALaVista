<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container theme-showcase" role="main">
<header>
	<h1>Escrito a la Vista</h1>
</header>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-collapse collapse">
		<div class="navbar-header">
					<a class='navbar-brand'>Panel de Administración</a>
		
		</div>
			<ul class="nav navbar-nav">
				<li><a id='inicio' href='LogInAdmin'>INICIO</a></li>
				<!-- <li><a href="#">CATEGORIA</a></li> -->
				<c:choose>
					<c:when test="${not empty requestScope.admin}">
						<li><a id='logout' href="LogOutAdmin">Logout</a></li>
						<input id='user' type='hidden' value='${requestScope.userId}' />

					</c:when>
					<c:otherwise>
						<li><a id='identificarse' href='administrador.jsp'>IDENTIFICARSE</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>