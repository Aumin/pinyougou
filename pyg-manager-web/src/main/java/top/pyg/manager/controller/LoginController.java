package top.pyg.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
	/**
	 * 获取当前登录用户信息
	 */
	@RequestMapping("/loadLoginName")
	public Map loadLoginName() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Map map = new HashMap();
		
		map.put("loginName", username);
		
		return map;
	}
	

}
