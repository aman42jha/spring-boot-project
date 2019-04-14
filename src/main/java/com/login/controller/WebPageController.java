package com.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
