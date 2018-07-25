package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.Userimage;

public interface UserimageDao {
	//更换头像
    public void updataImage(Userimage userimage);
    
    //插入头像
    public void registerImage(Userimage userimage);
    
    //查询头像
    public Userimage imageTo(Userimage userimage);
}
