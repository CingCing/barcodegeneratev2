package org.project.barcodegeneratev2.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.barcodegeneratev2.service.QrcodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.WriterException;

@Controller
public class WebApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value = { "/generator" }, method = RequestMethod.GET)	
	protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException{
		String context = request.getParameter("context");
		try {
			byte[] out = QrcodeService.getQRCodeImage(context, 250, 250);
			
			respone.setContentType("image/png");
			respone.setContentLength(out.length);
			
			OutputStream outStream = respone.getOutputStream();
			outStream.write(out);
			
			outStream.flush();
			outStream.close();
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
