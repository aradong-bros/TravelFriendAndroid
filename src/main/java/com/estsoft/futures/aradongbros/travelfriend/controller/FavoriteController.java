package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.FavoriteService;
import com.estsoft.futures.aradongbros.travelfriend.vo.FavoriteVo;

@Controller
@RequestMapping("/favorite")
public class FavoriteController 
{
	@Autowired
	private FavoriteService favoriteService;
	
	// 조회
	@RequestMapping("/selectFavoriteList/{user_no}")
	@ResponseBody
	public Map<String, Object> selectFavoriteList(@PathVariable("user_no") int user_no)
	{		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<FavoriteVo> favoList = favoriteService.selectFavoriteList(user_no);
		
		
		
		
		map.put("???", favoList);
		
		return map;
	}
	
	// 삽입
	
	// 삭제
	

}
