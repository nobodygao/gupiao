package com.test.thread;

import java.io.IOException;

import sun.misc.BASE64Decoder;
//谢谢第二个产品
public class TestBase64 {

	public static void main(String[] args) throws IOException {
		String BASIC = "YTE1ZTZhMTEtNTk5My00MTA5LWI0N2MtMzUyOGFhNzA3ZmYwOjMxNjAwNDdlLTBhNWEtNDc1OS05ZDQ1LWM5N2ZkY2E3OGU5MQ==";
		//String BASIB = "YTE1ZTZhMTEtNTk5My00MTA5LWI0N2MtMzUyOGFhNzA3ZmYwOjMxNjAwNDdlLTBhNWEtNDc1OS05ZDQ1LWM5N2ZkY2E3OGU5MQ==";
		String s=new String(new BASE64Decoder().decodeBuffer(BASIC),"utf-8");
		System.out.println(s);
		

	}

}
