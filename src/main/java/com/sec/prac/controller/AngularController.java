package com.sec.prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AngularController {
	
	@RequestMapping("/angular")
	public ModelAndView angularMainPage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", "박종석");
		mv.setViewName("angular");
		return mv;
	}
}
