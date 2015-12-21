package com.rtmap.traffic.bcm.controller;

import java.util.ArrayList;
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

import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.service.ISysMenuService;
import com.rtmap.traffic.bcm.service.ISysUserService;

import lqs.frame.util.MD5;
import my.web.AjaxMsg;
import my.web.BaseController;
import my.web.IUser;
import operamasks.ui.model.OmMenuModel;

@Controller
public class MainController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Spring不但支持自己定义的@Autowired注解，还支持java5中的JSR-250规范定义的注解，它们分别是@Resource、
	 * 
	 * @PostConstruct以及@PreDestroy。
	 * 
	 * @Autowired，如果要允许null
	 * 值，可以设置它的required属性为false，如：@Autowired(required=false)
	 * ，如果我们想使用名称装配可以结合@Qualifier注解进行使用
	 * 
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按
	 * byName自动注入，按类型匹配不到bean时才按照类型进行装配。名称可以通过name属性进行指定，需要注意的是，如果name属性一旦指定，
	 * 就只会按照名称进行装配。
	 */

	@Resource
	private ISysUserService sysUserService;
	@Resource
	private ISysMenuService sysMenuService;

	@RequestMapping("/")
	public String base() {
		logger.debug("login");

		return "login";
	}

	@RequestMapping("/login")
	public String login() {
		logger.debug("login");

		return "login";
	}

	@RequestMapping("/logout")
	public String logout() {
		logger.debug("login");
		deleteUserInCookie();

		return "login";
	}

	@RequestMapping("/desktop")
	public String desktop() {
		return "desktop";
	}

	@RequestMapping("/main")
	public String dologin(HttpServletRequest request, final Model m) {
		String username = param("username", "");
		String password = param("password", "");
		SysUser sysUser = sysUserService.getUserByUsername(username);

		if (sysUser == null) {
			m.addAttribute("msg", "用户不存在");
			return "login";
		} else if (!MD5.checkPassword(password, sysUser.getPassword())) {
			m.addAttribute("msg", "密码错误");
			return "login";
		} else {
			sysUserService.getUserPrivs(sysUser);
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
	@RequestMapping("/modifyPwd.do")
	public AjaxMsg modifypassword(Model m) {
		String oldpassword = param("oldpassword", "");
		String newpassword = param("newpassword", "");
		String newpassword2 = param("newpassword2", "");

		if (!newpassword.equals(newpassword2)) {
			return AjaxMsg.error("两次输入的密码不一致！");
		}
		IUser user = getLoginUer();

		SysUser sysUser = sysUserService.getUserByUsername(user.getUserName());
		if (MD5.checkPassword(oldpassword, sysUser.getPassword())) {
			sysUserService.modifyPasswordByUserCd(user.getUserName(), newpassword);
		} else {
			return AjaxMsg.error("原密码输入错误");
		}

		return AjaxMsg.ok("修改成功");
	}

	@ResponseBody
	@RequestMapping(value = "/getAllEnabledSysMenus.do", method = RequestMethod.GET)
	public List<OmMenuModel> getAllEnabledSysMenus() {
		return sysMenuService.getAllEnabledOmMenus();
	}

	@ResponseBody
	@RequestMapping(value = "/getCurrentUserSysMenus.do", method = RequestMethod.GET)
	public List<OmMenuModel> getCurrentUserSysMenus() {
		// 获取当前登陆人的权限集合
		List<String> privCds = new ArrayList<>();
		privCds.add("RPT_BROWSE");
		return sysMenuService.getOmMenusByUserPriv(privCds);
	}
}
