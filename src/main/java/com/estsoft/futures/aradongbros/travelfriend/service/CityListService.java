package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.CityListDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityListVo;

@Service
public class CityListService 
{
	@Autowired
	private CityListDao CityListDao;
	
	// no로 cityList 전체 받아오기
	public CityListVo selectCityListByNo(int no)
	{
		CityListVo cityListVo = CityListDao.selectCityListByNo(no);
		
		return cityListVo;
	}
}
