package cn.wellcare.controller;

import cn.wellcare.entity.SystemUsers;
import cn.wellcare.shiro.SessionManager;
import cn.wellcare.utils.Md5;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class SysOrgLoginController {
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "login")
	public String login() throws Exception {
		return "system/login";
	}

	@RequestMapping(value = "doLogin", method = { RequestMethod.POST })
	public String doLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap)
			throws Exception {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		String verify_number = SessionManager.getVerifyNumber(request);

		if (name == null) {
			dataMap.put("message", "用户名不能为空");
			return "system/login";
		}

		if (password == null) {
			dataMap.put("message", "用户名不能为空");
			return "system/login";
		}

		if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
			dataMap.put("message", "验证码不正确");
			return "system/login";
		}

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, Md5.getMd5String(password).toCharArray());
		// token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			log.error("账号不存在：", e);
			dataMap.put("message", "账号不存在");
			return "system/login";
		} catch (DisabledAccountException e) {
			log.error("账号未启用：", e);
			dataMap.put("message", "账号未启用");
			return "system/login";
		} catch (IncorrectCredentialsException e) {
			log.error("密码错误：", e);
			dataMap.put("message", "账号或密码错误");
			return "system/login";
		} catch (RuntimeException e) {
			log.error("未知错误,请联系管理员：", e);
			dataMap.put("message", "未知错误,请联系管理员");
			return "system/login";
		}

		SystemUsers users = (SystemUsers) subject.getPrincipal();
		/*if (org.getStatus().intValue() != SysOrganization.ORG_STATUS_NORM) {
			dataMap.put("message", "账号停用不能登录");
			return "system/login";
		}*/

		SessionManager.putAdminUser(request, users);

		return "redirect:/system/index";
	}

	@RequestMapping(value = "exit")
	public String exit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		HttpSession session = request.getSession();
		Enumeration<?> em = session.getAttributeNames();
		// 清空session
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		// 清除cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
		// 重定向
		return "redirect:/admin/login";
	}

}
