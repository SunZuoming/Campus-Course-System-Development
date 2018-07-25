package com.courseplatform.service;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.DTO.ProhibitDTO;

public interface ProhibitDTOService {

	/**
	 * 获取封号记录，分页
	 * @param param
	 * @return
	 */
	public List<ProhibitDTO> getProhibitDTO(Map<String, Integer> param);
	
	/**
	 * 获取封号记录
	 * @param param
	 * @return
	 */
	public ProhibitDTO getProhibitDTOByNo(String userNo);
	
	/**
	 * 解除封号
	 * @param userNo
	 */
	public void cancelPro(String userNo);
}
