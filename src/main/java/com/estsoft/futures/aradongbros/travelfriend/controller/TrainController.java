package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estsoft.futures.aradongbros.travelfriend.dto.StartEnd;
import com.estsoft.futures.aradongbros.travelfriend.service.AndroidService;
import com.estsoft.futures.aradongbros.travelfriend.service.CityService;
import com.estsoft.futures.aradongbros.travelfriend.service.ScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.service.TrainScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.service.TrainService;

@Controller
@RequestMapping("/train")
public class TrainController 
{
	@Autowired
	private TrainService trainService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private TrainScheduleService trainScheduleService;
	@Autowired
	private AndroidService androidService; //관광지정보
	
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
	
	//trainSchedule 짜기
	@RequestMapping("/makeTrainSchedule")
	@ResponseBody
	public String makeTrainSchedule(
			@ModelAttribute Map<String, Object> map,
			RedirectAttributes redirectAttributes)
	{
		int schedule_no = (int) map.get("schedule_no");
		
		List<StartEnd> list = (ArrayList<StartEnd>) map.get("StartEndByCity");
		List<Map<String,Object>> startEndList = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			Map<String, Object> startEnd = new HashMap<>();
			startEnd.put("city_no", list.get(i).getCity_no());
			startEnd.put("startStation", trainService.getNearStation(androidService.selectAllAtrByNo(list.get(i).getStart()).getLocation(), list.get(i).getCity_no()));
			startEnd.put("endStation", trainService.getNearStation(androidService.selectAllAtrByNo(list.get(i).getEnd()).getLocation(), list.get(i).getCity_no()));
			startEnd.put("isUsed", false);
		}
		
		List<Integer> cityOrderList = new ArrayList<>();
		for(int i=0; i<startEndList.size(); i++){
			
		}
		
		redirectAttributes.addFlashAttribute("cityOrderList", cityOrderList);
		return "redirect:/android/cityModifyOrder";
	}
	
	//기차역 이름 조회
	@RequestMapping("/getTrainStationName")
	@ResponseBody
	public List<String> getTrainStationName()
	{
		List<String> stationNameList = trainService.getAllStationName();
		return stationNameList;
	}
}