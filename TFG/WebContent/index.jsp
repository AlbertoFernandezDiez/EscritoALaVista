<%@page import="packBD.GestorBD"%>
<%@page import="packClases.ListaObras"%>
<%@page import="packClases.Obra"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta charset = 'utf8'>
   <title>Proyecto</title>
 <!--    <link rel=”stylesheet” href=”estilos.css”>-->
  <link rel="stylesheet" href="css/style.css"/>
  
</head>
<body>
<div id="contenedor">
    <header>
	<h1>Nombre de la web</h1>
	</header>
	<nav>
	<ul>
	<li>INICIO</li>
	<li>CATEGORIA</li>
	<li>IDENTIFICARSE</li>
	</ul>
	</nav>
	<section>
	<article>
	<ul>
	<li>Primera obra</li>
	<li>09/04/2015</li>
	<li>10/04/2015</li>
	<li> <a href="#">Yo</a> </li>
	</ul>
	<div id = 'resumen'><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p></div>
	</article>
	<article>
	<ul>
	<li>Segunda obra</li>
	<li>09/04/2015</li>
	<li>10/04/2015</li>
	<li> <a href="#">Yo</a> </li>
	</ul>
	<div id = 'resumen'><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p></div>
	</article>
	
	<article>
	<ul>
	<li>Tercera obra</li>
	<li>09/04/2015</li>
	<li>10/04/2015</li>
	<li> <a href="#">Yo</a> </li>
	</ul>
	<div id = 'resumen'><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p></div>
	</article>
<!-- 	</section>
	</div>-->
	<%
	ListaObras lista = GestorBD.getGestorBD().getObras();
	 Iterator<Obra>it = lista.getIterator();
	 
	 Obra aux = null;
	 while (it.hasNext()){
	 aux = it.next();
	 %>
	 <article>
	<ul>
	<li><%=aux.getTitulo()%></li>
	<li><%=aux.getFecha_in()%></li>
	<li><%=aux.getFecha_mod()%></li>
	<li> <a href="#">Yo</a> </li>
	</ul>
	<div id = 'resumen'><p><%=aux.getResumen() %></p></div>
	</article>
	</section>
	</div>
	 <%
	 }
	%>
</body>
</html>
