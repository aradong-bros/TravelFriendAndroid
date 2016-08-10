package com.estsoft.futures.aradongbros.travelfriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.futures.aradongbros.travelfriend.service.ScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Controller
@RequestMapping("/schedule")
public class ScheduleController
{
	@Autowired
	private ScheduleService scheduleService;
	
	// 조회
	
	// 삽입
	@RequestMapping("/schInsert")
	//url : localhost:8080/TravelFriendAndroid/schedule/schInsert
	public void insertScheduleData(@RequestBody ScheduleVo schVo)
	{	
		scheduleService.insertScheduleData(schVo);
	}
	
	// 삭제
	
	// 수정
}