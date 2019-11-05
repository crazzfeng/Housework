package com.house.work.util;

import java.security.MessageDigest;

public class MD5Util {
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String encode(String string){
		try{
			byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
			StringBuilder hex = new StringBuilder(hash.length * 2);
			for (byte b : hash) {
				if ((b & 0xFF) < 0x10) {
					hex.append("0");
				}
				hex.append(Integer.toHexString(b & 0xFF));
			}
			return hex.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static String funMd(String arg){
		//加干涉码
		String scur;
		scur=arg;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(scur.getBytes()); 
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16)
				buf.append("0");
				buf.append(Integer.toHexString(i)); 
			}
			md=null;
			b=null;
			return buf.toString().substring(8,24);
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5("1234456"));
	}
}