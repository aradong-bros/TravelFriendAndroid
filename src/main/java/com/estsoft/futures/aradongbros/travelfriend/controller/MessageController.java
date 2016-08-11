package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.MessageService;
import com.estsoft.futures.aradongbros.travelfriend.vo.MessageVo;

@Controller
@RequestMapping("/message")
public class MessageController 
{
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/getSomeMessage")
	@ResponseBody
	public Map<String, Object> getSomeMessage(
			@RequestParam("talk_no")int talk_no,
			@RequestParam("no1")int no1,
			@RequestParam("no2")int no2)
	{
		Map<String, Object> map = new HashMap<>();
		List<MessageVo> messageList = messageService.getSomeMessage(talk_no, no1, no2);
		map.put("messageList", messageList);
		
		return map;
	}
}
