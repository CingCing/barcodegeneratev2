package org.project.barcodegeneratev2.controller;

import java.io.IOException;
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
		// TODO Auto-generated method stub
		String context;
		String url = request.getParameter("url");
		String phone = request.getParameter("phone");
		String msg = request.getParameter("msg");
		String text = request.getParameter("text");
		String email = request.getParameter("email");		
		String dataType = request.getParameter("dataType");
		String errorCorrection = request.getParameter("errorCorrection");
		String barcodeType = request.getParameter("barcodeType");
		String size = request.getParameter("size");
		
		int sizeConvert = Integer.parseInt(size); //convert string to int
		char level = errorCorrection.charAt(0); // convert string to char
		
		
		System.out.println("dataType: " + dataType);
		System.out.println("errorCorrection: " + level);
		System.out.println("barcodeType: " + barcodeType);
		System.out.println("size: " + sizeConvert);
		
		System.out.println("url: " + url);
		System.out.println("phone: " + phone);
		System.out.println("msg: " + msg);
		System.out.println("text: " + text);
		System.out.println("email: " + email);
		
		switch(dataType) {
		case "": context = text; break;
		case "1": context = "mailto:" + email; break; //email
		case "2": context = "tel:" + phone; break; //phone
		case "3": context = "smsto:" + phone + ":" + msg; break; //sms
		case "4": context = url; break; //url
		default: context = text; break;
		}
		
		System.out.println("context: " + context);
		qrTextInfo.setContext(context);	
		
//		String Logo = "./src/main/webapp/resources/image/cmonbruh.png";

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "guest";
		if(auth.getName() != null) {			
			username = auth.getName();
		}		 
		qrTextInfo.setUsername(username);
		
		try {		
			byte[] out;
			
			if(barcodeType == "1d") {
				out = QrcodeService.generateCode128(context, sizeConvert, sizeConvert);
			}else {
				out = QrcodeService.generateQRCode(context, sizeConvert, sizeConvert, level);
				if(qrtextForm.getFileData() != null) {
					MultipartFile file = qrtextForm.getFileData();
					if(!file.isEmpty()) {
						byte[] imageByte = file.getBytes();
						out = QrcodeService.generateQRCodeImageOverlayWebData(context, sizeConvert, sizeConvert, imageByte);
					}				
				}
			}		
			
//			this.qrTextDAO.insertQrText(qrTextInfo);
			
			byte[] encodeBase64 = Base64.getEncoder().encode(out);
			String base64DataString = new String(encodeBase64 , "UTF-8");
			request.setAttribute("output", base64DataString);
			request.setAttribute("input", context);
			request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
