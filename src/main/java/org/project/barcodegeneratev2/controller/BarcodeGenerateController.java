package org.project.barcodegeneratev2.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.project.barcodegeneratev2.model.Qrcode;
import org.project.barcodegeneratev2.service.QrcodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.WriterException;


@Controller
//@MultipartConfig
public class BarcodeGenerateController extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	Qrcode qrcode = new Qrcode();
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qrtext = request.getParameter("qrtext");
		qrcode.setQrtext(qrtext);
//		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
////		InputStream fileContent = filePart.getInputStream();
//		System.out.println("xaxa " + fileName);
		String Logo = "./src/main/webapp/resources/image/cmonbruh.png";
		try {
			byte[] out = QrcodeService.generateQRCodeImageOverlayWebData(qrtext, 250, 250, Logo);
//			byte[] out = QrcodeService.getQRCodeImage(qrtext, 250,250);
			byte[] encodeBase64 = Base64.getEncoder().encode(out);
			String base64DataString = new String(encodeBase64 , "UTF-8");
			request.setAttribute("output", base64DataString);
			request.setAttribute("input", qrtext);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
