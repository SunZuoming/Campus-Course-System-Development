package com.courseplatform.util;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.courseplatform.po.User;

public final class StringUtil {
	
	/**
	 * 根据封号类型确定封号时间
	 * 
	 * @param user
	 * @return
	 */
	public static Integer getProhibitLoginSurplusDays(User user) {
		int proSurplusDays = 0;
		switch (user.getProhibitLoginnum()) {
		case 0:
			proSurplusDays = 3;
			break;
		case 1:
			proSurplusDays = 7;
			break;
		case 2:
			proSurplusDays = 365;
			break;
		case 3:
			proSurplusDays = 3 * 365;
			break;
		default:
			proSurplusDays = 9999;
			break;
		}
		return proSurplusDays;
	}
	
	/**
	 * 删除文件
	 * @param folder
	 */
	public static void deleteFile(File folder) {
		File[] files = folder.listFiles();
		if(files!=null) {
			for (File file : files) {
				if(file.isDirectory()) {
					deleteFile(file);
				}else {
					file.delete();
				}
			}
		}
		folder.delete();
	}
	
	/**
	 * 获取客户端IP
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");
		
		String ip = null;
		if(realIp == null) {
			if(forwarded == null) {
				ip = remoteAddr;
			}else {
				ip = remoteAddr + "/" + forwarded;
			}
		} else {
			if(realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				ip = realIp + "/" + forwarded.replaceAll(","+realIp, "");
			}
		}
		return ip;
	}

	/**
	 * 获取20位随机数字、字母
	 * @return
	 */
	public static String getNo(int length){
		Random random = new Random();
		String uuId = UUID.randomUUID().toString();
		uuId = uuId.replace("-", "");
		StringBuffer uuIdBuffer = new StringBuffer();
		for (int i = 0; i < uuId.length(); i++) {
			int j = random.nextInt(uuId.length()-1);
			uuIdBuffer.append(uuId.charAt(j));
			if (uuIdBuffer.length()>=length) {
				break;
			}
		}
		return uuIdBuffer.toString();
	}   


    
    /**
     * 处理字符串
     * @param arg0 要处理的字符串
     * @return 若arg0为空(null)则返回"",否则返回arg0除去前后空格之后的值
     */
    
    public static String parseString(String arg0){
       	return arg0 == null ? "" : arg0.trim();
    }

    /**
     * 处理字符串
     * @param arg0 要处理的对象
     * @return 若obj为空(null)则返回"",否则返回obj转换成字符串且除去该字符前后空格之后的值
     */
    
    public static String parseString(Object arg0){
        return arg0 == null ? "" : arg0.toString().trim();
    }
    
	/**
	 * 将字符串转换为int型整数。
	 * @return int 整数
	 */
	public static int ch2Int(String str){
	    try{
		    return(Integer.parseInt(str));
	    }
	    catch(NumberFormatException e){
		    return(-1);
	    }
	}    
	/**
	 * 将字符串转换为double型。
	 * @return double 长整型
	 */
	public static double ch2Double(String str){
	    try{
		    return(Double.parseDouble(str));
	    }
	    catch(NumberFormatException e){
		    return(-1);
	    }
	}    
	/**
	 * 中文转码方法
	 * @param arg0 要转换编码的字符串
	 * @return 转换编码后的字符串
	 */
	
	public static String toGBK(String arg0){

		try{			
			arg0 = (arg0 == null) ? "" : new String(arg0.getBytes("ISO-8859-1"),"GBK");
		}
		catch(UnsupportedEncodingException e){
		}					
		return arg0;
	}
	
	/**
	 * 过滤字符串方法
	 * @param arg0 需要过滤的字符串
	 * @param arg1 要过滤掉的字符串
	 * @return 返回过滤后的字符串
	 */
	public static String filterString(String arg0,String arg1){
		
		int pos;
		String str1 = StringUtil.parseString(arg0);
		String str2 = StringUtil.parseString(arg1);
		String str = str1;	
		
		while(str.indexOf(str2) >= 0){
			pos = str.indexOf(str2);
			str = str.substring(0,pos)+str.substring(pos+str2.length());
		}
		return str;
	}
	
	
	/**
	 * 将HashMap转换成带","分割的string List
	 * 
	 * @param hm  要转换的HashMap
	 * @param ifkey  是否转换key:	1转换key,0转换value
	 * @return 转换后的字符串
	 */
	
	public static String hmtoList(HashMap hm,int ifkey){
		StringBuffer listBuff=new StringBuffer();
		int i=0;
		if( hm==null) {
			return null;
		}
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			if(ifkey==1){
				listBuff.append("'").append(it.next()).append("'").append(",");
			}else{
				listBuff.append("'").append(hm.get(it.next())).append("'").append(",");
			}
			
			i++;
		}
		if(i>0){
			listBuff.deleteCharAt(listBuff.length()-1);	//去掉最后一个'号
		}
		//System.out.println("list="+listBuff.toString());
		return listBuff.toString();
	}
	
	/**
	 * 字符转换ISO-8859-1 To GBK
	 * */
	public static String isoToGBK(String src)
	{
		try
		{ 
			if(src!=null)
			{
				src =new String(src.getBytes("iso-8859-1"),"gbk");
			}
		}
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        return src;
	}
	/**
	 * 编码"\"
	 * */
	public static String  escapea (String src)
	{  		
		char ch;  
		StringBuffer tmpStr = new StringBuffer(); 
		
		for (int i=0;i<src.length() ;i++ )  
		{  
			ch = src.charAt(i); 
			if(ch=='\\')
			{
				tmpStr.append("\\").append(ch);
			}
			else
			{
				tmpStr.append(ch); 
			}			
		}  
		return tmpStr.toString();
	} 
	/**
	 * 编码"\"
	 * */
	public static String  escapeb (String src)
	{  		
		char ch;  
		StringBuffer tmpStr = new StringBuffer(); 
		
		for (int i=0;i<src.length() ;i++ )  
		{  
			ch = src.charAt(i); 
			if(ch=='/')
			{
				tmpStr.append("\\/").append(ch);
			}
			else
			{
				tmpStr.append(ch); 
			}			
		}  
		return tmpStr.toString();
	} 
	
	/**
	 * 字符串替换,替换所有的数据
	 * content 需替换字符串(String replace)
	 * rep1 原字符串
	 * rep2 替换后的字符串
	 * return String NULL 表示替换失败
	 * */
	public static String replaceAllForUnix(String content, String rep1, String rep2)
    {
		try
		{            
            String newStr=content; //替换后的字符串
            
            int index = content.lastIndexOf(rep1);  
            
            StringBuffer rep=new StringBuffer();
            
            while(index>=0)
            {            	
            	rep.append(newStr.substring(0,index));
            	
            	rep.append(rep2);
            	
            	rep.append(newStr.substring(index+rep1.length(),newStr.length()));
            	
            	newStr = rep.toString();            	
            	
            	index = (newStr).lastIndexOf(rep1);
            	
            	rep.delete(0, rep.length());
            }
            
            return newStr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }
	
	
	/**
	 * 分割短信使用
	 * 将字符串按照规定的长度进行拆分，拆分完之后按照"|"进行分割。
	 * 
	 * @param src
	 *            需要差分的字符串
	 * @param len
	 *            拆分长度,以字节为计算(bytes)
	 * @return 分割后的字符串
	 */
	public static String getSmsContent(String src, int len) {

		if (src == null || src.equals("") || len <= 5) {
			return src;
		}

		byte[] byteOfSrc = src.getBytes();

		int byteCount = byteOfSrc.length;
		int smsTimes = 0;

		StringBuffer des = new StringBuffer();

		if (byteCount > len) {
			len = len - 5;
		}

		if (byteCount % len != 0) {
			smsTimes = byteCount / len + 1;
		} else {
			smsTimes = byteCount / len;
		}

		int beginIndex = 0;
		boolean flag = true;
		int indeOfSrc = 0;
		String smsSend = null;
		for (int i = 0; i < smsTimes; i++) {
			if (smsTimes - i == 1) {
				len = byteCount - beginIndex;
				flag = false;
			}

			if (smsTimes > 1) {
				indeOfSrc = i + 1;
				des.append("(" + indeOfSrc + "/" + smsTimes + ")");
			}

			smsSend = new String(byteOfSrc, beginIndex, len).trim();

			if (src.indexOf(smsSend) == -1) {
				smsSend = new String(byteOfSrc, beginIndex, len - 1).trim();
				beginIndex = beginIndex + len - 1;
			} else {
				beginIndex = beginIndex + len;
			}
			des.append(smsSend);

			if (flag) {
				des.append("|");
			}
		}
		return des.toString();

	}

	/**
	 * 分割短信使用
	 * 将字符串按照规定的长度进行拆分，拆分完之后按照"|"进行分割。
	 * 
	 * @param src
	 *            需要差分的字符串
	 * @param len
	 *            拆分长度,以字符为计算(Char)
	 * @return 分割后的字符串
	 */
	public static String getSmsContentChar(String src, int len) {

		if (src == null || src.equals("") || len <= 5) {
			return src;
		}

		// byte[] byteOfSrc = src.getBytes();

		int byteCount = src.length();
		int smsTimes = 0;

		StringBuffer des = new StringBuffer();

		if (byteCount > len) {
			len = len - 5;
		}

		if (byteCount % len != 0) {
			smsTimes = byteCount / len + 1;
		} else {
			smsTimes = byteCount / len;
		}
		int beginIndex = 0;
		boolean flag = true;
		int indeOfSrc = 0;
		String smsSend = null;
		for (int i = 0; i < smsTimes; i++) {
			if (smsTimes - i == 1) {
				len = byteCount - beginIndex;
				flag = false;
			}
			if (smsTimes > 1) {
				indeOfSrc = i + 1;
				des.append("(" + indeOfSrc + "/" + smsTimes + ")");
			}
			// smsSend = new String(byteOfSrc, beginIndex, len).trim();
			smsSend = src.substring(beginIndex,beginIndex+len).trim();
			if (src.indexOf(smsSend) == -1) {
				// smsSend = new String(byteOfSrc, beginIndex, len - 1).trim();
				smsSend = src.substring(beginIndex,beginIndex+len-1).trim();
				beginIndex = beginIndex + len - 1;
			} else {
				beginIndex = beginIndex + len;
			}
			des.append(smsSend);

			if (flag) {
				des.append("|");
			}
		}
		return des.toString();

	}
	
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	
	
	
	/**
	 * 得到文件的扩展名.
	 *
	 * @param fileName 需要处理的文件的名字.
	 * @return the extension portion of the file's name.
	 */
	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}
	
	
	
	public static boolean isNullorEmpty(String id) {
		if (id == null || id.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	public static String changePathSeparator(String path) {
		if(path == null)
			return null;
		return path.replace("\\", "/");
	}

	/**
	 * 在字符串左边添加字符
	 * @param content
	 * @param length
	 * @param fill
	 * @return
	 */
	public static String fillL(String content, int length, char fill)
	{
		if (content == null)
		{
			content = "";
		}
		char[] source = content.trim().toCharArray();
		if (length <= source.length)
		{
			return new String(source, 0, length);
		}
		char[] retChar = new char[length];
		int part = 0;
		for (part = 0; part < length - source.length; part++)
		{
			retChar[part] = (char) fill;
		}
		for (int i = 0; i < source.length; i++)
		{
			retChar[i + part] = source[i];
		}
		return new String(retChar, 0, length);
	}
	
}