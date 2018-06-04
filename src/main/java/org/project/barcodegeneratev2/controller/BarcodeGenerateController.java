package org.project.barcodegeneratev2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.barcodegeneratev2.dao.QrTextDAO;
import org.project.barcodegeneratev2.model.QrTextInfo;
import org.project.barcodegeneratev2.service.QrcodeService;
import org.project.barcodegeneratev2.validator.QrTextValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.zxing.WriterException;


@Controller
@Transactional
@EnableWebMvc
//@MultipartConfig

public class BarcodeGenerateController extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	QrTextInfo qrTextInfo = new QrTextInfo();
	
	@Autowired
	private QrTextDAO qrTextDAO;
	
//	@Autowired
//	private QrTextValidator qrTextValidator;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.POST)	
	protected void doGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute QrTextInfo qrtextForm) throws ServletException, IOException {
		String context = "";
		String DataColor = "0xFF000000";
		String QuiteZoneColor = "0xFFFFFFFF";
		
		String code128 = request.getParameter("code128");
		String url = request.getParameter("url");
		String phone = request.getParameter("phone");
		String msg = request.getParameter("msg");
		String text = request.getParameter("text");
		String email = request.getParameter("email");		
		String dataType = request.getParameter("dataType");
		String errorCorrection = request.getParameter("errorCorrection");
		String barcodeType = request.getParameter("barcodeType");
		String size = request.getParameter("size");
		String sDataColor = request.getParameter("sDataColor");
		String sQuiteZoneColor = request.getParameter("sQuiteZoneColor");
		
		int sizeConvert = Integer.parseInt(size); //convert string to int
		
		if(code128=="" || code128 == null) {			
			switch(dataType) { //format QR Code date type
			case "1": context = "mailto:" + email; break; //email
			case "2": context = "tel:" + phone; break; //phone
			case "3": context = "smsto:" + phone + ":" + msg; break; //sms
			case "4": context = url; break; //url
			default: context = text; break;
			}
		}
		
		if(sDataColor != "" && sDataColor != null) {
			DataColor = convertColor(sDataColor);			
		}
		if(sQuiteZoneColor != "" && sQuiteZoneColor != null) {
			QuiteZoneColor = convertColor(sQuiteZoneColor);			
		}
		
		qrTextInfo.setContext(context);	

		//get author name
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "guest";
		if(auth.getName() != null) {			
			username = auth.getName();
		}		 
		qrTextInfo.setUsername(username);
		
		try {		
			byte[] out;
			
			if((barcodeType == "1d" || barcodeType.equals("1d")) && !barcodeType.isEmpty()) {	 //generate Code 128
				out = QrcodeService.generateCode128(code128, sizeConvert, sizeConvert);
				request.setAttribute("code128", code128);
			}else {																			  	//generate QR Code
				char level = errorCorrection.toUpperCase().charAt(0); 							// convert string to char uppercase
				out = QrcodeService.generateQRCode(context, sizeConvert, sizeConvert, level, DataColor, QuiteZoneColor);	//generate QR Code without image inside
				if(qrtextForm.getFileData() != null) {
					MultipartFile file = qrtextForm.getFileData();
					if(!file.isEmpty()) {
						byte[] imageByte = file.getBytes();
						out = QrcodeService.generateQRCodeImageOverlayWebData(context, sizeConvert, sizeConvert, imageByte, DataColor, QuiteZoneColor);	//generate QR Code with image inside
					}				
				}
				
				this.qrTextDAO.insertQrText(qrTextInfo);										//save data to database
				request.setAttribute("input", context);											//set data to jsp page														
				request.setAttribute("dataType", dataType);										
				request.setAttribute("errorCorrection", errorCorrection);
				request.setAttribute("url", url);
				request.setAttribute("phone", phone);
				request.setAttribute("msg", msg);
				request.setAttribute("text", text);
				request.setAttribute("email", email);
				request.setAttribute("sDataColor", sDataColor);
				request.setAttribute("sQuiteZoneColor", sQuiteZoneColor);				
			}				
			
			byte[] encodeBase64 = Base64.getEncoder().encode(out);								//encode data to base64
			String base64DataString = new String(encodeBase64 , "UTF-8");						//convert database64 to String
			
			request.setAttribute("output", base64DataString);									//set data to jsp page	
			request.setAttribute("size", size);		
			request.setAttribute("barcodeType", barcodeType);
			request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);	//send data to jsp page
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String convertColor(String sColor) {
		sColor = sColor.substring(1);
		sColor = "0xFF" + sColor;
		return sColor;
	}
}
