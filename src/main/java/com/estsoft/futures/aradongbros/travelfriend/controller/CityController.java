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
	public Map<String,Object> insertCityData(@RequestBody CityVo cityVo)
	{	
		Map<String,Object> map = new HashMap<String,Object>();

		cityService.insertCityData(cityVo);

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
