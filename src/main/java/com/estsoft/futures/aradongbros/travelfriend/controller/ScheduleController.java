package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.ScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.vo.Isfinished;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Controller
@RequestMapping("/schedule")
public class ScheduleController
{
	@Autowired
	private ScheduleService scheduleService;

	// 사용자꺼 전체 조회  -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schSelectByUser/{user_no}
	@RequestMapping("/schSelectByUser/{user_no}")
	@ResponseBody
	public Map<String,Object> selectScheduleAllDataByUser(@PathVariable("user_no") int user_no)
	{			
		List<ScheduleVo> schList = scheduleService.selectScheduleAllDataByUser(user_no);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("schList", schList);
		
		return map;
	}
	
	// 다른사람들꺼 전체 조회  -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schSelectByOther/{user_no}
	@RequestMapping("/schSelectByOther/{user_no}")
	@ResponseBody
	public Map<String,Object> selectScheduleAllDataByOther(@PathVariable("user_no") int user_no)
	{			
		List<ScheduleVo> schList = scheduleService.selectScheduleAllDataByOther(user_no);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("schList", schList);
		
		return map;
	}
	
	// 1개 조회  -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schSelect/{no}
	@RequestMapping("/schSelect/{no}")
	@ResponseBody
	public Map<String,Object> selectScheduleData(@PathVariable("no") int no)
	{			
		ScheduleVo schVo = scheduleService.selectScheduleData(no);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("schVo", schVo);
		
		return map;
	}
	
	// 삽입  -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schInsert
	@RequestMapping("/schInsert")
	@ResponseBody
	public Map<String,Object> insertScheduleData(@RequestBody ScheduleVo schVo)
	{	
		Map<String,Object> map = new HashMap<String,Object>();
		
		// Enum.valueOf(Isfinished.class, "ongoing");   <- String to Enum 파싱

		if ( schVo.getIsfinished() == null || schVo.getIsfinished().toString().equals("") )
		{
			schVo.setIsfinished(Enum.valueOf(Isfinished.class, "ongoing"));
		}
		
		schVo.getStartDate().replaceAll("/", "-");
		schVo.getEndDate().replaceAll("/", "-");
		
		int no = scheduleService.insertScheduleData(schVo);
		
		map.put("no", no);

		return map;
	}
	
	// 삭제 -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schDelete/{no}
	@RequestMapping("/schDelete/{no}")
	@ResponseBody
	public Map<String,Object> deleteScheduleData(@PathVariable("no") int no)
	{			
		Map<String,Object> map = new HashMap<String,Object>();
		
		scheduleService.deleteScheduleData(no);
		
		return map;
	}
	
	// 수정 : 아직 구현 안되있음 
	@RequestMapping("/schModify/")
	@ResponseBody
	public Map<String,Object> modifyScheduleData()
	{			
		Map<String,Object> map = new HashMap<String,Object>();
		
		return map;
	}
	
	// 수정 : isPublic -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schModifyIsPublic?no={no 값}&isPublic={isPublic 값}
	@RequestMapping("/schModifyIsPublic")
	@ResponseBody
	public Map<String,Object> modifyIsPublic(@RequestParam("no") int no,
											 @RequestParam("isPublic") int isPublic)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		scheduleService.modifyIsPublic(no, isPublic);
		
		return map;
	}
		
	// 수정 : isfinished -> url : http://222.239.250.207:8080/TravelFriendAndroid/schedule/schModifyIsfinished?no={no 값}&isfinished={isfinished 값}
	@RequestMapping("/schModifyIsfinished")
	@ResponseBody
	public Map<String,Object> modifyIsfinished(@RequestParam("no") int no,
											   @RequestParam("isfinished") Isfinished isfinished)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		scheduleService.modifyIsfinished(no, isfinished);
		
		return map;
	}
}