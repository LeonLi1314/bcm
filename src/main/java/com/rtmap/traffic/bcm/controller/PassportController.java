package com.rtmap.traffic.bcm.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rtmap.traffic.bcm.common.Constants;
import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.service.ISysUserService;

@Controller
@RequestMapping(value = "/passport")
public class PassportController {
	private Logger logger = LoggerFactory.getLogger("com.rtmap.traffic.bcm.controller.PassportController");

	/*
	 * Spring不但支持自己定义的@Autowired注解，还支持JSR-250规范定义的注解，它们分别是@Resource、
	 * @PostConstruct以及@PreDestroy。
	 * 
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按
	 * byName自动注入。
	 */

	@Resource
	private ISysUserService userService;

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysUser user = userService.getUserByUsername(username);

		if (user != null && password != null && password.equals(user.getPassword())) {
			request.getSession().setAttribute(Constants.AUTHENTICATION_KEY, user);

			String backToUrl = request.getParameter("backToUrl");
			if (backToUrl == null || backToUrl.trim().length() == 0) {
				backToUrl = "admin".equalsIgnoreCase(user.getUserCd()) ? "/management" : "/";
			} else {
				try {
					backToUrl = java.net.URLDecoder.decode(backToUrl, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			logger.info("backToUrl: " + backToUrl);

			return new ModelAndView("redirect:" + backToUrl);
		}

		return new ModelAndView("login", "error", "用户名或密码错误。");
	}
}
