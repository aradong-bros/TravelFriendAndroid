package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.ScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Controller
@RequestMapping("/schedule")
public class ScheduleController
{
	@Autowired
	private ScheduleService scheduleService;
	
	// 조회
	
	// 삽입  -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schInsert
	@RequestMapping("/schInsert")
	@ResponseBody
	public Map<String,Object> insertScheduleData(@RequestBody ScheduleVo schVo)
	{	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isfinished", schVo.getIsfinished());
		
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

		return map;
	}
	
	// 삭제 -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schDelete/{no}
	@RequestMapping("/schDelete/{no}")
	public void deleteScheduleData(@PathVariable("no") int no)
	{			
		scheduleService.deleteScheduleData(no);
	}
	
	// 수정
}