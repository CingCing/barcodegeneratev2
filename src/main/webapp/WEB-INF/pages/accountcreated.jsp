<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Refresh" content="3; url=${pageContext.request.contextPath}/">
<title>Redirecting...</title>
<style>
.fullscreenDiv {
    background-color: #e8e8e8;
    width: 100%;
    height: auto;
    bottom: 0px;
    top: 0px;
    left: 0;
    position: absolute;
}
.center {
    position: absolute;
    width: 450px;
    height: 50px;
    top: 25%;
    left: 40%;
    margin-left: -50px; /* margin is -0.5 * dimension */
    margin-top: -25px; 
}​
</style>
</head>
<body>
<div class='fullscreenDiv'>
    	<div class="center">
    		<h1>Your account have been created</h1>
			<h3>Redirecting to home page ...</h3>
    	</div>
</div>

</body>
</html>