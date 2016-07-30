package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.AndroidDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;

@Service
public class AndroidService 
{
	@Autowired
	private AndroidDao androidDao;
	
	public List<AttractionVo> selectListByCityNo(int cityList_no)
	{
		List<AttractionVo> atrList = androidDao.selectListByCityNo(cityList_no);
		
		return atrList;
	}
	
	public AttractionVo selectAtrByNo(int no)
	{
		AttractionVo atrVo = androidDao.selectAtrByNo(no);
		
		return atrVo;
	}
	
	public AttractionVo selectAllAtrByNo(int no)
	{
		AttractionVo allAtrVo = androidDao.selectAllAtrByNo(no);
		
		return allAtrVo;
	}
}
