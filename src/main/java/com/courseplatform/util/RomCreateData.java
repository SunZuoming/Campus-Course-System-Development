package com.courseplatform.util;

import java.util.Random;


public class RomCreateData {
	
	public static void main(String[] args) {
        //随机生成纯数字
       
            createData(10);
       
    }
	
	public static void createData(int length) {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<length;i++)
        {
            sb.append(rand.nextInt(10));
        }
        String data=sb.toString();
        System.out.println(length+" random data: "+data);
    }
}

