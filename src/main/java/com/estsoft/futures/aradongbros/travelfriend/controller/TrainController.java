package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.TrainService;

@Controller
@RequestMapping("/train")
public class TrainController 
{
	@Autowired
	private TrainService trainService;
	
	//직통 기차 검색
	@RequestMapping("/getDirectPath")
	@ResponseBody
	public Map<String, Object> getDirectPath(
			@RequestParam("goDate")Date goDate,
			@RequestParam("goTime")Time goTime,
			@RequestParam("startStation")String startStation,
			@RequestParam("endStation")String endStation)
	{
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String, Object>> trainTimeList = trainService.getTrainTimeList(
				startStation, endStation, goDate, goTime);
		
		map.put("trainTimeList", trainTimeList);
		return map;
	}
	
	//환승 기차 검색 결과
	@RequestMapping("/getTransferPath")
	@ResponseBody
	public Map<String, Object> getTransferPath(
			@RequestParam("goDate")Date goDate,
			@RequestParam("goTime")Time goTime,
			@RequestParam("startStation")String startStation,
			@RequestParam("endStation")String endStation)
	{
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String, Object>> trainTimeList = trainService.getTransferTrainTimeList(
				startStation, endStation, goDate, goTime);
		
		map.put("trainTimeList", trainTimeList);
		return map;
	}
	
	//리다이렉트로 맵 받기
	@RequestMapping("/sample")
	@ResponseBody
	public String sample(@ModelAttribute Map<String, Object> map)
	{
		return map + "잘 받음";
	}
}
