<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Refresh" content="3; url=${pageContext.request.contextPath}/">

<title>Logout</title>
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
}
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 50px;
  height: 50px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
</head>
<body>
	<div class='fullscreenDiv'>
    	<div class="center">
    		<h1>Logout Successful!</h1>
    		<h3>Redirecting to home page ...</h3>
    		<div class="loader" style="margin-left: 70px;"></div>
    	</div>
	</div>​
    
</body>
</html>