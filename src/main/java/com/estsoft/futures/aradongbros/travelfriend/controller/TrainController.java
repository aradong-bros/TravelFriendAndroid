package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
				startStation,
				endStation,
				goDate,
				goTime);
		
		map.put("trainTimeList", trainTimeList);
		return map;
	}
}
