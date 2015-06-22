<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <ul class="breadcrumb">
<c:forEach items="${requestScope.breadcrumb}" var="bread">
 <li><a href='<c:out value="${bread.url}"></c:out>'><c:out value="${bread.name}"></c:out></a>	</li>
</c:forEach>
</ul>