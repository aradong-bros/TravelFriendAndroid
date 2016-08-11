package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.MessageDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.MessageVo;

@Service
public class MessageService 
{
	@Autowired
	private MessageDao messageDao;

	public List<Map<String, Object>> getSomeMessage(int talk_no, int no1, int no2) {
		List<Map<String, Object>> messageList = messageDao.getSomeMessage(talk_no, no1, no2);
		return messageList;
	}
}
