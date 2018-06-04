package org.project.barcodegeneratev2.controller;

import java.security.Principal;

import org.project.barcodegeneratev2.dao.UserInfoDAO;
import org.project.barcodegeneratev2.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Transactional
public class LoginController {
	
	@Autowired
	private UserInfoDAO userInfoDao;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	   public String welcomePage(Model model) {
	       model.addAttribute("title", "Welcome");
//	       QrTextInfo qrcodeForm = new QrTextInfo();
//	       model.addAttribute("qrcodeForm", qrcodeForm);
	       return "home";
	   }
	 
	   @RequestMapping(value = "/admin", method = RequestMethod.GET)
	   public String adminPage(Model model) {
	       return "adminPage";
	   }
	 
	   @RequestMapping(value = "/login", method = RequestMethod.GET)
	   public String loginPage(Model model ) {
	        
	       return "loginPage";
	   }
	 
	   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	   public String logoutSuccessfulPage(Model model) {	       
	       return "logoutSuccessfulPage";
	   }
	 
	   @RequestMapping("/newaccount")
	   public String showNewAccount(Model model ) {
	       model.addAttribute("userInfo", new UserInfo()); 
	       return "signupPage";
	   }
	   
	   @RequestMapping(value="/createaccount", method = RequestMethod.POST)
	   public String createAccount(Model model,@Validated UserInfo userInfo ,BindingResult result) {	   
		   if(result.hasErrors()) {
			   return "signupPage";
		   }
		   
		   if(userInfoDao.exists(userInfo.getUsername())) {
			   result.rejectValue("username", "DuplicateKey.userInfo.username", "This username already exisits");
			   return "signupPage";
		   }
		   		   		   
		   try {
			   userInfoDao.insertUserInfo(userInfo);
		   } catch (Exception e) {
			   // TODO: handle exception
			   result.rejectValue("username", "DuplicateKey.userInfo.username", "This username already exisits");
			   return "signupPage";
		   }
		   
	       return "accountcreated";
	   }
	   
	   @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	   public String userInfo(Model model, Principal principal) {
	 
	  
	       // Sau khi user login thanh cong se co principal
	       String userName = principal.getName();
	 
	       System.out.println("User Name: "+ userName);
	 
	       return "userInfoPage";
	   }
	 
	   @RequestMapping(value = "/403", method = RequestMethod.GET)
	   public String accessDenied(Model model, Principal principal) {
	        
	       if (principal != null) {
	           model.addAttribute("message", "Hi " + principal.getName()
	                   + "<br> You do not have permission to access this page!");
	       } else {
	           model.addAttribute("msg",
	                   "You do not have permission to access this page!");
	       }
	       return "403Page";
	   }
	
}
