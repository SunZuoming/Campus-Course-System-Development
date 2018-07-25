package com.courseplatform.util;

/** 生成随机字符串的工具类
*/
import java.util.UUID;
public class UUIDUtils {
   public static String getUUID(){
   	return UUID.randomUUID().toString().replace("-", "");
   }
}

