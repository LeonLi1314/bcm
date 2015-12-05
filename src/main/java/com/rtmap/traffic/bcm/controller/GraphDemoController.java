package com.rtmap.traffic.bcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("graph")
public class GraphDemoController {
	
	@RequestMapping("graphTest")
	public String graphTest(){
		return "/graph/graphTest";
	}
}
