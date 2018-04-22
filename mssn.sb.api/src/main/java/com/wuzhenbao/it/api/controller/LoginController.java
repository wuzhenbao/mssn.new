package com.wuzhenbao.it.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping("/getUser")
	public String getUserInfo(String userId) {
		log.info("开始执行LoginController");
		if(StringUtils.isNotEmpty(userId)) {
			return "wuzhenbao";
		}else {
			return "zhangsan";
		}
	}
}
