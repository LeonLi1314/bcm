package com.rtmap.traffic.bcm.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rtmap.traffic.bcm.service.ISysUserService;

@Controller
@RequestMapping(value = "/passport")
public class PassportController {
	private Logger logger = LoggerFactory.getLogger("com.rtmap.traffic.bcm.controller.PassportController");

	@Resource
	private ISysUserService userService;

	
}
