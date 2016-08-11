package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			if ( cityList[i].getStatus() != Enum.valueOf(Status.class, "none") )
			{
				cityList[i].setStatus(Enum.valueOf(Status.class, "none"));
			}
			
			if ( cityList[i].getCityOrder() != -1 )
			{
				cityList[i].setCityOrder(-1);
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
	
	// 수정 : status -> url : http://222.239.250.207:8080/TravelFriendAndroid/city/cityModifyStatus?no={no 값}&status={status 값}
	@RequestMapping("/cityModifyStatus")
	@ResponseBody
	public Map<String,Object> modifyStatus(@RequestParam("no") int no,
										   @RequestParam("status") Status status)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		cityService.modifyStatus(no, status);
		
		return map;
	}
	
	// 수정 : status -> url : http://222.239.250.207:8080/TravelFriendAndroid/city/cityModifyOrder?no={no 값}&order={order 값}
	@RequestMapping("/cityModifyOrder")
	@ResponseBody
	public Map<String,Object> modifyOrder(@RequestParam("no") int no,
										  @RequestParam("order") int order)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		cityService.modifyOrder(no, order);
		
		return map;
	}
}