package com.ch.recsysapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpConnection {

	/**
	 * @param args
	 */
	
	/*public static void main(String[] args) {
		String strURL = "http://192.168.1.105:8080/RecSysServer/servlet/GetList";
		try {
			String result = getDataFromURL(strURL, null);
			System.out.println(result);//输出获取到的内容，测试用
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接url错误");
		}
	}*/

	public static String handleGet(String strUrl) {
		String result = null;
		StringBuffer buffer = null;
		HttpGet request = new HttpGet(strUrl);//实例化一个HttpGet请求(指定URL)
		DefaultHttpClient client = new DefaultHttpClient();//实例化一个客户端
		try {
			HttpResponse response = client.execute(request);//调用客户端的execute(request)执行请求
			//获取服务器端返回的状态码是否等于200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());//调用getEntity获取返回值，需通过EntityUtils把实体转成String
			} else {
				result = response.getStatusLine().toString();
			}
		} catch (Exception e) { }
		if(result != null){
			result = result.replace("\"", "'");
		}
		return result;
	}
	/*
	 * 空值返回，原因不明，停用
	 */
	public static String getDataFromURL(String strURL, Map<String, Object> param)  {
		String result;
		try {
			URL url = new URL(strURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			final StringBuilder sb; 
			if (param != null) {
				sb = new StringBuilder(param.size() << 4);// 4次方
				final Set<String> keys = param.keySet();
				for (final String key : keys) {
					final Object value = param.get(key);
					sb.append(key); // 不能包含特殊字符
					sb.append('=');
					sb.append(value);
					sb.append('&');
				}
				// 将最后的 '&' 去掉
				sb.deleteCharAt(sb.length() - 1);
			}else{
				sb = new StringBuilder(100 << 4);
			}
			// writer.write("email=fd3589@163.com&password=123");
			writer.write(sb.toString());
			writer.flush();
			writer.close();

			InputStreamReader reder = new InputStreamReader(conn.getInputStream(), "utf-8");

			BufferedReader breader = new BufferedReader(reder);

			//BufferedWriter w = new BufferedWriter(new FileWriter("d:/1.txt"));//获取内容输出到指定文件

			String content = null;
			result = null;
			while ((content = breader.readLine()) != null) {
				result += content;
			}
			if(result != null){
				result = result.replace("\"", "'");
			}
			return result;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
