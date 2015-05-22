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
	 <%
	 }
	%>
	</section>
	</div>
</body>
</html>
