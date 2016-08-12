package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.AndroidDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityListVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

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
	
	public CityListVo selectCityByNo(int no)
	{
		CityListVo cityVo = androidDao.selectCityByNo(no);
		
		return cityVo;
	}
	
	public List<AttractionVo> getPinDataByCategory(int cityList_no, String category)
	{
		List<AttractionVo> atrList = androidDao.getPinDataByCategory(cityList_no, category);
		
		return atrList;
	}
	
	public List<CityVo> getCityList(int schedule_no)
	{
		List<CityVo> cityVoList = androidDao.getCityList(schedule_no);
		
		return cityVoList;
	}
	
	public List<PostVo> getPostList(int city_no)
	{
		List<PostVo> postVoList = androidDao.getPostList(city_no);
		
		return postVoList;
	}
	
}
