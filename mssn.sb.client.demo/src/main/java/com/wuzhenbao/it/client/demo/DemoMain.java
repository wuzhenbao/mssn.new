package com.wuzhenbao.it.client.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemoMain {

	public static void main(String[] args) {
//		String response = HttpServiceUtil.insureResponsePost("https://127.0.0.1:8443/json", null);
//		System.out.println(response);
//		String result = HttpClientUtil.doGet("https://127.0.0.1:8443/json");
//		System.out.println(result);
		multThreadClient();
	}
	public static void singleThreadClient() {
		long startTime = System.currentTimeMillis();
		for(int i = 0;i<3;i++) {
			String result = ClientCustomSSL.doGet("D:\\code\\keys\\tomcat.p12", "123456", "https://wuzhenbao.com:8443/json", null);
			System.out.println(Thread.currentThread().getName()+"返回结果："+result);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("共耗时:"+(endTime-startTime));
	}
	public static void multThreadClient() {
		long startTime = System.currentTimeMillis();
		try {
			ExecutorService service = Executors.newCachedThreadPool();
			Future<String> future =service.submit(new Callable<String>() {
				public String call() {
					String result = ClientCustomSSL.doGet("D:\\code\\keys\\tomcat.p12", "123456", "https://wuzhenbao.com:8443/json", null);
					System.out.println(Thread.currentThread().getName()+"返回结果："+result);
					return "yes";
				}
			});
			Future<String> future1 = service.submit(new Callable<String>() {
				public String call() {
					String result = ClientCustomSSL.doGet("D:\\code\\keys\\tomcat.p12", "123456", "https://wuzhenbao.com:8443/json", null);
					System.out.println(Thread.currentThread().getName()+"返回结果："+result);
					return "success";
				}
			});
			Future<String> future2 = service.submit(new Callable<String>() {
				public String call() {
					String result = ClientCustomSSL.doGet("D:\\code\\keys\\tomcat.p12", "123456", "https://wuzhenbao.com:8443/json", null);
					System.out.println(Thread.currentThread().getName()+"返回结果："+result);
					return "ok";
				}
			});
			//if(future.isDone()) {
				System.out.println("第1"+future.get());
			//}
			//if(future1.isDone()) {
				System.out.println("第2"+future1.get());
			//}
			//if(future2.isDone()) {
				System.out.println("第3"+future2.get());
			//}
		}catch(ExecutionException e) {
			
		}catch( InterruptedException e) {
			
		}
		long endTime = System.currentTimeMillis();
		System.out.println("共耗时:"+(endTime-startTime));
	}
}
