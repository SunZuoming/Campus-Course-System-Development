package com.courseplatform.util;

import java.security.MessageDigest;

import org.eclipse.jdt.core.dom.ThisExpression;

public class MD5Util {

		private final static String[] hexDigits={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

		
		private static String byteArrayToHexString(byte[] b){
			StringBuffer result=new StringBuffer();
			for(int i=0;i<b.length;i++){
				result.append(byteToHexString(b[i]));
			}
			return result.toString();
		}
		
		private static String byteToHexString(byte b) {
			int n=b;
			if(n<0)
				n=256+n;
			int d1=n/16;
			int d2=n%16;
			return hexDigits[d1]+hexDigits[d2];
		}
		
		public static String encryptMD5(String password) throws Exception{
			String newpassword=null;
			if(password!=null){
				MessageDigest md5=MessageDigest.getInstance("MD5");
				byte[] result=md5.digest(password.getBytes());
				newpassword=byteArrayToHexString(result);
			}
			return newpassword.toUpperCase(); 
		}
		
		
		public static void main(String[] args) {
			String passwod="admin";
			try {
//				String nowdate = CalendarUtil.getSysTimeYMD();
//				System.out.println(nowdate);
//				String dates = CalendarUtil.getDayOfStrDateTime(nowdate);
//				System.out.println(dates);
//				String predate = CalendarUtil.getFixedDate(nowdate, -Integer.valueOf(dates));
//				System.out.println(predate);
//				System.out.println(CalendarUtil.getMonthOfStrDateTime(predate));
//				System.out.println(predate.substring(0, 7));
				System.out.println(encryptMD5(passwod));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
}
