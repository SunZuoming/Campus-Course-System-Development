package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.SharedfileTable;

public interface SharedfileTableDao {

	/**
	 * 文件审核通过
	 * @param fileTable
	 */
	public void examineFile(SharedfileTable fileTable);
	
	/**
	 * 获取未审核的文件信息
	 * @return
	 */
	public List<SharedfileTable> noExamineFileList(SharedfileTable sharedfileTable);
	
	/**
	 * 获取共享文件总数目
	 * @return
	 */
	public long getSharedFileNum();
	
	/**
	 * 获取某人在某日上传文件的数目
	 * @param param
	 * @return
	 */
	public Integer getSharedFileNumByNo(Map<String, String> param);
	
	/**
	 * 根据文件编号获取文件信息
	 * @param sharedFileNo
	 * @return
	 */
	public SharedfileTable getSharedFile(String sharedFileNo);
	
	/**
	 * 根据文件编号删除文件
	 * @param sharedFileNo
	 */
	public void deleteSharedFile(String sharedFileNo);
	
	int addfile(SharedfileTable sharefile);
    
    SharedfileTable queryById(String sharefile);
    
    List<SharedfileTable> listShare();
    
    void update(SharedfileTable sharefile);
    
    void delete(SharedfileTable id);
    
    /**
	 * 上传共享文件
	 * @param sharedFileNo
	 */
	public void upSharedFile(SharedfileTable sharedfileTable);
	
	/**
	 * 下载共享文件
	 * @param sharedFileNo
	 */
	public List<SharedfileTable> downSharedFile(Map<String, Integer> param);
	
	/**
	 * 更据路径查找文件名
	 * @param sharedFileNo
	 */
	public SharedfileTable sSharedFile(SharedfileTable sharedfileTable);
	
	/**
	 * 文件搜索
	 * @param sharedFileNo
	 */
	public List<SharedfileTable> souSharedFile(String fileto);
	
	/**
	 * 得到文件数量
	 */
	public Integer getfileNum();
	
	/**
	 * 更新文件下载次数
	 */
	public void updatafileNum(SharedfileTable sharedfileTable);
}
