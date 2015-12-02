package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.SysMenu;
import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.service.ISysUserService;

import my.web.AjaxMsg;
import my.web.BaseController;
import my.web.IUser;

@Controller
public class MainController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Spring不但支持自己定义的@Autowired注解，还支持JSR-250规范定义的注解，它们分别是@Resource、
	 * 
	 * @PostConstruct以及@PreDestroy。
	 * 
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按
	 * byName自动注入。
	 */

	@Resource
	private ISysUserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		logger.debug("login");

		return "login";
	}

	@RequestMapping("/login")
	public String login(Model m) {
		logger.debug("login");

		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model m) {
		logger.debug("login");
		deleteUserInCookie();

		return "login";
	}

	@RequestMapping("/desktop")
	public String desktop(Model m) {
		return "desktop";
	}

	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request, final Model m) {
		String username = param("username", "");
		String password = param("password", "");
		SysUser sysUser = userService.getUserByUsername(username);

		if (sysUser == null) {
			m.addAttribute("msg", "用户不存在");
			return "login";
		} else if (!password.equals(sysUser.getPassword())) {
			m.addAttribute("msg", "密码不正确");
			return "login";
		} else {
			saveUserInCookie(new IUser() {

				@Override
				public String getUserXm() {
					return sysUser.getRealName();
				}

				@Override
				public String getUserName() {
					return sysUser.getUserCd();
				}

				@Override
				public String getUserId() {
					return "";
				}

				@Override
				public String getRoleNames() {
					return "";
				}
			}, true);
		}
		
		m.addAttribute("user", sysUser);
		return "main";
	}

	@ResponseBody
	@RequestMapping("/modifypassword")
	public AjaxMsg modifypassword(Model m) {
		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				String oldpassword = param("oldpassword", "");
				String newpassword = param("newpassword", "");
				String newpassword2 = param("newpassword2", "");

				if (!newpassword.equals(newpassword2)) {
					return AjaxMsg.error(i18n("NEW_PASSWORD_ERROR"));
				}

				IUser user = getLoginUer();

				SysUser sysUser = userService.getUserByUsername(user.getUserName());
				if (sysUser.getPassword().equals(oldpassword)) {
					sysUser.setPassword(newpassword);
					userService.updatePasswordByUserCd(user.getUserName(), newpassword);
				} else {
					return AjaxMsg.error(i18n("OLD_PASSWORD_ERROR"));
				}

				return AjaxMsg.ok();
			}
		});
	}

	@ResponseBody
	@RequestMapping("/menus.do")
	public List<SysMenu> getAllEnabledSysMenus() {
		return userService.getAllEnabledSysMenus();
	}
}
