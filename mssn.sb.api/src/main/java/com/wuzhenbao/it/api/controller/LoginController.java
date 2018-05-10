package com.wuzhenbao.it.api.controller;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping("/getUser")
	public String getUserInfo(String userId) {
		Random random = new Random();
		int time = random.nextInt(10);
		log.info("随机生成的time是:"+time);
		time = time*1000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("开始执行LoginController");
		if(StringUtils.isNotEmpty(userId)) {
			return "wuzhenbao";
		}else {
			return "zhangsan";
		}
	}
}
