<%@page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/stylev2.css" />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<title>QR Code Generator</title>
<jsp:include page="_menu.jsp" />

<!-- <script   type="text/javascript">
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope, $http){
		$http.get('http://localhost:8080/BarcodeGenerateV2/home')
		.then(function(response){
			$scope.output = response.data;
		});
	});
</script> -->

</head>
<body>	
<div id="ui" ng-app="">
    <table cellspacing="0" cellpadding="0" id="mainpanel">
        <tbody>
        <tr>
            <td align="left" style="vertical-align: top;">
  				<form:form modelAttribute="qrcodeForm" action="home" method="POST" enctype="multipart/form-data" >
                <table id="leftpanel">
                    <colgroup>
                        <col>
                    </colgroup>
                    <tbody>
                    <tr>
                    	<td>
                    		<table>
                    			<colgroup>
                                    <col class="firstColumn">
                                    <col class="secondColumn">
                                </colgroup>
                                <tbody>
                                	<tr>
                         				<td class="firstColumn">Barcode Type</td>
                         				<td class="secondColumn">
                         				<select class="gwt-ListBox" name="barcodeType" ng-model="barcodeType">
                         					<option value="">QR Code</option>  
                         					<option value="1d">Code 128</option>                            				                          	
                            			</select></td>
                         			</tr>
                                </tbody>
                         	</table>
                         </td>
                    </tr>
                    
                    <tr ng-if="barcodeType == '1d'">
						 <td>
                            <table>
                                <colgroup>
                                    <col class="firstColumn">
                                    <col class="secondColumn">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <td class="firstColumn">Content</td>
                                    <td class="secondColumn"><input type="text" name="qrtext" class="gwt-TextBox" required autofocus></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr> 
                    
                    <tr ng-if="barcodeType != '1d'">
                        <td>
                            <table>
                                <colgroup>
                                    <col class="firstColumn">
                                    <col class="secondColumn">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <td class="firstColumn">Data type</td>
                                    <td class="secondColumn"><select class="gwt-ListBox" ng-model="selectedContent" id="dataType" name="dataType" ng-init="selectedContent = '${dataType }'">
                                        <!--<option value="Calendar event">Calendar event</option> -->
                                        <!--<option value="Contact information">Contact information</option>-->
										<!-- <option value="">Please select data type </option> -->
										<option value="">Text</option>
                                        <option value="1" >Email address</option>
                                       <!-- <option value="Geo location">Geo location</option> -->
                                        <option value="2" >Phone number</option>
                                        <option value="3" >SMS</option>                                        
                                        <option value="4" >URL</option>
                                        <!--<option value="Wifi network">Wifi network</option> -->
                                    </select></td>
                                </tr>
                                <tr ng-if="selectedContent == 1">
                                    <td class="firstColumn">Email <span style="color: red">*</span></td>
                                    <td class="secondColumn"><input type="email" name="email" class="gwt-TextBox" 
                                    value='<c:if test="${not empty email}">${email }</c:if>'
                                    required autofocus></td>
                                </tr>
								
								<tr ng-if="selectedContent == 2 || selectedContent == 3">
									<td class="firstColumn">Phone number <span style="color: red">*</span></td>
									<td class="secondColumn"><input type="number" name="phone" class="gwt-TextBox" min="1" 
									value='<c:if test="${not empty phone}">${phone }</c:if>'
									required autofocus></td>
								</tr>
								
								<tr ng-if="selectedContent == 3">
									<td class="firstColumn">Message</td>
									<td class="secondColumn"><input type="text" name="msg" class="gwt-TextBox" 
									value='<c:if test="${not empty msg}">${msg }</c:if>'
									required></td>
								</tr>
								
                                <tr ng-if="selectedContent == '' || !selectedContent">
									<td class="firstColumn">Text content <span style="color: red">*</span></td>
									<td class="secondColumn"><input type="text" name="text" class="gwt-TextBox " rows="5" 
									value='<c:if test="${not empty text}">${text }</c:if>'
									required autofocus></td>
								</tr>
								
								<tr ng-if="selectedContent == 4">
									<td class="firstColumn">URL <span style="color: red">*</span></td>
									<td class="secondColumn"><input type="url" name="url" class="gwt-TextBox" 
									value='<c:if test="${not empty url}">${url }</c:if> <c:if test="${empty url}">http://</c:if>'
									placeholder="http://" required autofocus></td>
								</tr>
								
								<c:if test="${pageContext.request.userPrincipal.name != null}">
									<tr >
										<td class="firstColumn">Upload with logo: </td>
										<td class="secondColumn"><input type="file" name="fileData" path="fileData" /></td>
									</tr>
								</c:if>
								
                                </tbody>
                            </table>
                        </td>
                    </tr>					                    
	
					<tr>
                        <td><span id="errorMessageID" class="errorMessage">&nbsp;</span></td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <colgroup>
                                    <col class="firstColumn">
                                    <col class="secondColumn">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <td class="firstColumn">Barcode size</td>
                                    <td class="secondColumn"><select class="gwt-ListBox" name="size" id="size">
                                        <option value="120" <c:if test="${size == '120'}">selected</c:if>>Small</option>
                                        <option value="230" <c:if test="${empty size}">selected</c:if> <c:if test="${size == '230'}">selected</c:if>>Medium</option>
                                        <option value="350" <c:if test="${size == '350'}">selected</c:if>>Large</option>
                                    </select></td>
                                </tr>
                                <tr ng-if="barcodeType != '1d'">
                                    <td class="firstColumn">Error correction</td>
                                    <td class="secondColumn"><select class="gwt-ListBox" name="errorCorrection" id ="errorCorrection">
                                        <option value="L">L</option>
                                        <option value="M" <c:if test="${errorCorrection == 'M'}">selected</c:if>>M</option>
                                        <option value="Q" <c:if test="${errorCorrection == 'Q'}">selected</c:if>>Q</option>
                                        <option value="H" <c:if test="${errorCorrection == 'H'}">selected</c:if>>H</option>
                                    </select></td>
                                </tr>
                                
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <colgroup>
                                    <col class="firstColumn">
                                    <col class="secondColumn">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <td class="firstColumn">&nbsp;</td>
                                    <td class="secondColumn">
                                        <button class="button" type="submit" ng-disabled="" style="vertical-align:middle"><span>Generate </span></button>
                                    	
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            	</form:form>
            </td>
            <td align="left" style="vertical-align: top;">
                <table cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr>
                        <td align="left" style="vertical-align: top;">                        	
                            <div id="imageresult">
                                <div id="innerresult">
                                	<%-- <c:if test="${empty output }">
										<img src="resources/image/helloworld.png" class="gwt-Image">
									</c:if> --%>
									<c:if test="${not empty output }">
										<img src="data:image/png;base64,${output }" class="gwt-Image">
									</c:if>                                
                                </div>
                            </div>
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="left" style="vertical-align: top;">
                            <div class="gwt-HTML" id="downloadText" aria-hidden="true" style="display: none;"><a href="" id="downloadlink">Download</a> or embed with this URL:</div>
                        </td>
                    </tr>
                    <!-- <tr>
                        <td align="left" style="vertical-align: top;"><input type="text" class="gwt-TextBox" id="urlresult" aria-hidden="true" style="display: none;"></td>
                    </tr>
                    <tr>
                        <td align="left" style="vertical-align: top;"><textarea class="gwt-TextArea" id="rawtextresult" cols="50" rows="8" aria-hidden="true" style="display: none;"></textarea></td>
                    </tr> -->
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
    
</div>
</body>
</html>
