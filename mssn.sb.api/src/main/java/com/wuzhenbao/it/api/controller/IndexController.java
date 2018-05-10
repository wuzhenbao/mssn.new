package com.wuzhenbao.it.api.controller;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wuzhenbao.sb.it.commons.vo.UserVO;

import net.sf.json.JSONObject;


@RestController
public class IndexController {
	private static Logger log = Logger.getLogger(IndexController.class);
	@RequestMapping("/")
	public String index() {
		log.info("welcome to mssn system!");
		return "welcome to mssn system!";
	}
	@RequestMapping("/json")
	public String indexJson() {
		log.info("excu indexJson ");
		Random random = new Random();
		int time = (random.nextInt(10)+1)*10000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UserVO user = new UserVO();
		user.setUserId(100000);
		user.setUserNameCn("wuzhenbao");
		user.setUserEmail("wuzhenbao@126.com");
		user.setDescription("sleep time is " + time);
		JSONObject obj = JSONObject.fromObject(user);
		log.info("excu indexJson .result "+obj.toString());
		return obj.toString();
	}
}
