package org.project.barcodegeneratev2.controller;

import org.project.barcodegeneratev2.model.Qrcode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BarcodeGenerateController {
	@RequestMapping("/hello")
    public String hello(Model model) {        
//        String text = qrcode.getQrtext();
        
		model.addAttribute("greeting", "Hello Spring MVC");
        
        return "hello";
        
    }
}
