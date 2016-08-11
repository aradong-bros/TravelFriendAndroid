package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.CityService;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.Status;

@Controller
@RequestMapping("/city")
public class CityController 
{
	@Autowired
	private CityService cityService;
	
	// 조회
	
	
	// 삽입  -> url : http://222.239.250.207:8080/TravelFriendAndroid/city/cityInsert
	@RequestMapping("/cityInsert")
	@ResponseBody
	public Map<String,Object> insertCityData(@RequestBody CityVo[] cityList)
	{	
		Map<String,Object> map = new HashMap<String,Object>();
		
		for ( int i = 0; i < cityList.length; i++ )
		{
			if ( cityList[i].getStatus() == null || cityList[i].getStatus().toString().equals("") )
			{
				cityList[i].setStatus(Enum.valueOf(Status.class, "none"));
			}
			
			if ( cityList[i].getOrder() != -1 )
			{
				cityList[i].setOrder(-1);
			}
			
			cityService.insertCityData(cityList[i]);			
		}

		return map;
	}
	
	// 삭제  -> url : http://222.239.250.207:8080/TravelFriendAndroid/city/cityDelete/{no}
	@RequestMapping("/cityDelete/{no}")
	@ResponseBody
	public Map<String,Object> deleteCityData(@PathVariable("no") int no)
	{			
		Map<String,Object> map = new HashMap<String,Object>();
		
		cityService.deleteCityData(no);
		
		return map;
	}
	
	// 수정
}