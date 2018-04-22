package com.wuzhenbao.it.client.demo;

public class DemoMain {

	public static void main(String[] args) {
//		String response = HttpServiceUtil.insureResponsePost("https://127.0.0.1:8443/json", null);
//		System.out.println(response);
//		String result = HttpClientUtil.doGet("https://127.0.0.1:8443/json");
//		System.out.println(result);
		String result = ClientCustomSSL.doGet("D:\\code\\keys\\tomcat.p12", "123456", "https://127.0.0.1:8443/json", null);
		System.out.println("返回结果："+result);
	}

}
