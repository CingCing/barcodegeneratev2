<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
 
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
 
  <a href="${pageContext.request.contextPath}/">Home</a>
  
<%--    <a href="${pageContext.request.contextPath}/userInfo">User Info</a> --%>
  
<!--   | &nbsp; -->
  
  <%-- <a href="${pageContext.request.contextPath}/admin">Admin</a> --%>
  
  <c:if test="${pageContext.request.userPrincipal.name == null}">
  
     | &nbsp;
     <a href="${pageContext.request.contextPath}/login">Log in</a>
     
  </c:if>
  
  <c:if test="${pageContext.request.userPrincipal.name == null}">
  
     | &nbsp;
     <a href="${pageContext.request.contextPath}/">Sign up</a>
     
  </c:if>
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
  
     | &nbsp;
     <a href="${pageContext.request.contextPath}/logout">Log out</a>
     
  </c:if>
  
</div>