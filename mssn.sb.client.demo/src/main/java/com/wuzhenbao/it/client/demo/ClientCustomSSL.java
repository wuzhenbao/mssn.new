package com.wuzhenbao.it.client.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class ClientCustomSSL {
	private static Logger log = Logger.getLogger(ClientCustomSSL.class);
	public static String doGet(String keyFile,String password,String url,Map<String, String> param) {
		KeyStore trustStore;
		FileInputStream instream=null;
		String resultString = null;
		try {
			trustStore = KeyStore.getInstance("PKCS12");
			// 加载证书文件
			instream = new FileInputStream(new File(keyFile));
			trustStore.load(instream, password.toCharArray());
			
			//sslcontext
			SSLContext context = SSLContexts.custom().loadTrustMaterial(trustStore).build();
			SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(context,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(factory).build();
			
			//准备请求参数
			CloseableHttpResponse response = null;
			//创建uri
			URIBuilder builder = new URIBuilder(url);
			if(null != param) {
				param.forEach((k,v)->{
					builder.addParameter(k, v);
				});
			}
			URI uri = builder.build();;
			HttpGet get = new HttpGet(uri);
			log.info("开始执行请求:"+url);
			response = httpclient.execute(get);
			log.info("请求返回:"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				log.error("get请求失败，错误原因：",e);
			}
		} catch (KeyStoreException e) {
			log.error("执行get请求时加载证书报错：",e);
		}catch (FileNotFoundException e) {
			log.error("执行get请求时没有找到证书文件报错：",e);
		}catch(CertificateException e) {
			log.error("执行get请求时处理证书报错：",e);
		}catch(Exception e) {
			log.error("执行get请求时因其他原因报错：",e);
		}finally {
			if(null != instream) {
				try {
					instream.close();
				} catch (IOException e) {
					log.error("关闭资源时出错：",e);
				}
			}
		}
		return resultString;
	}
	public final static void main(String[] args) throws Exception {
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		// 加载证书文件
		FileInputStream instream = new FileInputStream(new File("/home/victor/my.store"));
		try {
			trustStore.load(instream, "mypassword".toCharArray());
		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.build();
		try
		{

			HttpGet httpget = new HttpGet("https://www.alipay.com/");
			System.out.println("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println(EntityUtils.toString(entity));
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
}
