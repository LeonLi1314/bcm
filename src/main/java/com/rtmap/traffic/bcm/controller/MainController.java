package com.rtmap.traffic.bcm.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger("com.rtmap.traffic.bcm.controller.MainController");
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model){
	    logger.debug("login");
	    
		model.addAttribute("welcome", "hello world");

		return "login";
	}
}
