package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.UserService;
import com.estsoft.futures.aradongbros.travelfriend.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/*
	 * 유저 insert 메소드
	 * 
	 * 필요 파라미터 : name, picture, platform, userID
	 * 모든 파라미터는 인코딩 필요
	 * 리턴타입 : jsonString
	 */
	@RequestMapping("/insertUser")
	@ResponseBody
	public Map<String, UserVo> insertUser(
			@ModelAttribute UserVo userVo)
	{
		UserVo dbUserVo = userService.getUser(userVo.getPlatform(), userVo.getUserID());
		Map<String, UserVo> map = new HashMap<>();
		
		if(dbUserVo == null){ //db에 유저가 없음
			map.put("userVo", userService.insertUser(userVo));
		}
		else{ //db에 유저가 있음
			map.put("userVo", dbUserVo);
		}
		
		return map;
	}
}
