package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.ScheduleDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Service
public class ScheduleService 
{
	@Autowired
	private ScheduleDao ScheduleDao;
	
	//삽입
	public void insertScheduleData(ScheduleVo schVo)
	{
		ScheduleDao.insertScheduleData(schVo);
	}
	
	//삭제
	public void deleteScheduleData(int no)
	{
		ScheduleDao.deleteScheduleData(no);
	}
}
