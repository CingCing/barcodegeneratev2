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
</head>
<body>	
<div id="ui" ng-app="">
    <table cellspacing="0" cellpadding="0" id="mainpanel">
        <tbody>
        <tr>
            <td align="left" style="vertical-align: top;">
  				<form:form modelAttribute="qrcodeForm" action="home" method="POST" enctype="multipart/form-data">
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
                                    <td class="secondColumn"><select class="gwt-ListBox" ng-model="selectedContent" name="dataType">
                                        <!--<option value="Calendar event">Calendar event</option> -->
                                        <!--<option value="Contact information">Contact information</option>-->
										<!-- <option value="">Please select data type </option> -->
										<option value="">Text</option>
                                        <option value="1">Email address</option>
                                       <!-- <option value="Geo location">Geo location</option> -->
                                        <option value="2">Phone number</option>
                                        <option value="3" >SMS</option>
                                        
                                        <option value="4">URL</option>
                                        <!--<option value="Wifi network">Wifi network</option> -->
                                    </select></td>
                                </tr>
                                <tr ng-if="selectedContent == 1">
                                    <td class="firstColumn">Email</td>
                                    <td class="secondColumn"><input type="text" name="email" class="gwt-TextBox" required autofocus></td>
                                </tr>
								
								<tr ng-if="selectedContent == 2 || selectedContent == 3">
									<td class="firstColumn">Phone number</td>
									<td class="secondColumn"><input type="text" name="phone" class="gwt-TextBox " required autofocus></td>
								</tr>
								
								<tr ng-if="selectedContent == 3">
									<td class="firstColumn">Message</td>
									<td class="secondColumn"><textarea name="msg" class="gwt-TextArea" required></textarea></td>
								</tr>
								
                                <tr ng-if="selectedContent == '' || !selectedContent">
									<td class="firstColumn">Text content</td>
									<td class="secondColumn"><textarea name="text" class="gwt-TextArea " rows="5" required autofocus></textarea></td>
								</tr>
								
								<tr ng-if="selectedContent == 5">
									<td class="firstColumn">URL</td>
									<td class="secondColumn"><input type="url" name="qrtext" class="gwt-TextBox" value="http://" placeholder="http://" required autofocus></td>
								</tr>
								
								<tr >
									<td class="firstColumn">Upload with logo: </td>
									<td class="secondColumn"><input type="file" name="fileData" path="fileData" /></td>
								</tr>
								
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
                                    <td class="secondColumn"><select class="gwt-ListBox" name="size">
                                        <option value="120">Small</option>
                                        <option value="230">Medium</option>
                                        <option value="350" selected>Large</option>
                                    </select></td>
                                </tr>
                                <tr ng-if="barcodeType != '1d'">
                                    <td class="firstColumn">Error correction</td>
                                    <td class="secondColumn"><select class="gwt-ListBox" name="errorCorrection">
                                        <option value="L">L</option>
                                        <option value="M">M</option>
                                        <option value="Q">Q</option>
                                        <option value="H">H</option>
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
                                        <button type="submit" ng-disabled="" class="gwt-Button">Generate --></button>
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
                                	<c:if test="${empty input }">
										<img src="resources/image/helloworld.png" class="gwt-Image">
									</c:if>
									<c:if test="${not empty input }">
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
