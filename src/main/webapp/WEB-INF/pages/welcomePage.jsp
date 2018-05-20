<%@page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>QR Code Generator</title>
<jsp:include page="_menu.jsp" />
</head>
<h1></h1>
<body>	
<div class="container">
	<div class="w-100">
	<div class="container col-md-10">
  		<form:form modelAttribute="qrcodeForm" action="welcome" method="POST" enctype="multipart/form-data">
    		<div class="row">
      			<div class="col-md-3" style="margin-top: 6px;">
        			<label >Enter text to create QR Code:</label>
      			</div>
      			<div class="col-md-9" style="margin-top: 6px;">
      				<div class= row>
        				<input type="text" name="qrtext" placeholder="Write something..." value='<c:if test="${not empty input}">${input }</c:if>' required="required">
        			</div>
        			
<%--         			<c:if test="${not empty message}">   --%>
<%--    						<div class="message">${message}</div> --%>
<%-- 					</c:if> --%>
					
      			</div>      			
    		</div>    
    		
    		<c:if test="${pageContext.request.userPrincipal.name != null}">
    		<div class="row">
      			<div class="col-md-3" style="margin-top: 6px;">
        			<label>Upload with logo: </label>
      			</div>
      			<div class="col-md-9" style="margin-top: 6px;">
        			<div class= row>        				
        				<input type="file" name="fileData" path="fileData" />
        			</div>
      			</div>      			
    		</div>   
    		</c:if> 
    		
    		<div class="row">
      			<input type="submit" style="margin-right:14px" value="Create QR code">
    		</div>
  		</form:form>
	</div>
	<div class="container col-md-2 text-center">
		<!-- <img src="resources/image/zxc.png" style="width:175px; height:175px"> -->
		<c:if test="${empty input }">
			<img src="resources/image/helloworld.png" style="width:175px; height:175px">
		</c:if>
		<c:if test="${not empty input }">
			<img src="data:image/png;base64,${output }" style="width:175px; height:175px">
		</c:if>
	</div>
</div>
</div>
</body>
</html>
