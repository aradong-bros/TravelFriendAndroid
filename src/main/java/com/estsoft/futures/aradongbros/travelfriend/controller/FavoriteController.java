package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.FavoriteService;
import com.estsoft.futures.aradongbros.travelfriend.service.ScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.vo.FavoriteVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Controller
@RequestMapping("/favorite")
public class FavoriteController 
{
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private ScheduleService scheduleService;
	
	// 조회 -> URL : http://222.239.250.207:8080/TravelFriendAndroid/favorite/selectFavoriteList/{user_no 값}
	@RequestMapping("/selectFavoriteList/{user_no}")
	@ResponseBody
	public Map<String, Object> selectFavoriteList(@PathVariable("user_no") int user_no)
	{		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<FavoriteVo> favoList = favoriteService.selectFavoriteList(user_no);
		
		List<ScheduleVo> schList = new ArrayList<ScheduleVo>();
		
		for ( int i = 0; i < favoList.size(); i++ )
		{
			schList.add(scheduleService.selectScheduleData(favoList.get(i).getSchedule_no()));			
		}

		map.put("schList", schList);
		
		return map;
	}
	
	// 삽입 -> URL : http://222.239.250.207:8080/TravelFriendAndroid/favorite/insertFavoriteData?user_no={user_no 값}&schedule_no={schedule_no 값}
	@RequestMapping("/insertFavoriteData") 
	@ResponseBody
	public Map<String, Object> insertFavoriteData(@RequestParam("user_no") int user_no,
												  @RequestParam("schedule_no") int schedule_no)
	{		
		Map<String, Object> map = new HashMap<String, Object>();
		
		favoriteService.insertFavoriteData(user_no, schedule_no);
		
		return map;
	}
	
	// 삭제 -> URL : http://222.239.250.207:8080/TravelFriendAndroid/favorite/deleteFavoriteData?no={no 값}
	@RequestMapping("/deleteFavoriteData") 
	@ResponseBody
	public Map<String, Object> deleteFavoriteData(@RequestParam("no") int no)
	{		
		Map<String, Object> map = new HashMap<String, Object>();
		
		favoriteService.deleteFavoriteData(no);
		 
		return map;
	}
}
