<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<style>
* {box-sizing: border-box;}

.header {
  overflow: hidden;
  background-color: #f1f1f1;
  padding: 0px 10px;
}

.header a {
  float: left;
  color: black;
  text-align: center;
  padding: 5px 10px;
  text-decoration: none;
  font-size: 14px; 
  line-height: 25px;
  border-radius: 4px;
}

.header a.logo {
  font-size: 25px;
  font-weight: bold;
}

.header a:hover {
  background-color: #ddd;
  color: black;
}

.header a.active {
  background-color: #4eb8dd;
  color: white;
}

.header-right {
  float: right;
}

@media screen and (max-width: 500px) {
  .header a {
    float: none;
    display: block;
    text-align: left;
  }
  .header-right {
    float: none;
  }
}
</style>

</head>
 
<c:set var="url" value ="/BarcodeGenerateV2/WEB-INF/pages/home.jsp" />
<c:set var="loginUrl" value ="/BarcodeGenerateV2/WEB-INF/pages/loginPage.jsp" />
<c:set var= "currentUrl" value= "${pageContext.request.requestURI}"/>

<%--  <c:out value=" ${ currentUrl}"/> --%>

 <div class="header">
 <a <c:if test = "${currentUrl == url}">
         class='active'
      </c:if>
       href="${pageContext.request.contextPath}/">Home</a>
 <div class=" header-right">  

<%--    <a href="${pageContext.request.contextPath}/userInfo">User Info</a> --%>
  
<!--   | &nbsp; -->
  
  <%-- <a href="${pageContext.request.contextPath}/admin">Admin</a> --%>
  
  <c:if test="${pageContext.request.userPrincipal.name == null}">  
     <a 
     <c:if test = "${currentUrl == loginUrl}">
         class='active'
      </c:if>
      href="${pageContext.request.contextPath}/login">Log in</a>     
  </c:if>
  
  <c:if test="${pageContext.request.userPrincipal.name == null}">     
     <a href="${pageContext.request.contextPath}/signup">Sign up</a>     
  </c:if>
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
     
     <a href="${pageContext.request.contextPath}/logout">Log out</a>
     
  </c:if>
  </div>
</div>