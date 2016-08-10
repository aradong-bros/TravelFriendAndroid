package com.estsoft.futures.aradongbros.travelfriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 삽입  -> url : localhost:8080/TravelFriendAndroid/schedule/schInsert
	@RequestMapping("/schInsert")
	public void insertScheduleData(@RequestBody ScheduleVo schVo)
	{	
		if ( schVo.getIsfinished() == "0" )
		{
			schVo.setIsfinished("ongoing");
		}
		else if ( schVo.getIsfinished() == "1" )
		{
			schVo.setIsfinished("finished");
		}
		
		schVo.getStartDate().replaceAll("/", "-");
		schVo.getEndDate().replaceAll("/", "-");
		
		scheduleService.insertScheduleData(schVo);
	}
	
	// 삭제 -> url : localhost:8080/TravelFriendAndroid/schedule/schDelete/{no}
	@RequestMapping("/schDelete/{no}")
	public void deleteScheduleData(@PathVariable("no") int no)
	{			
		//scheduleService.deleteScheduleData(no);
	}
	// 수정
}