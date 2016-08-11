package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.CityDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;

@Service
public class CityService 
{
	@Autowired
	private CityDao cityDao;
	
	// 조회
	
	// 삽입
	public void insertCityData(CityVo cityVo)
	{
		cityDao.insertCityData(cityVo);
	}
	
	// 삭제
	public void deleteCityData(int no)
	{			
		cityDao.deleteCityData(no);
	}
	
	// 수정
}
