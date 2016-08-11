package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.ScheduleDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.Isfinished;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Service
public class ScheduleService 
{
	@Autowired
	private ScheduleDao ScheduleDao;
	
	//조회
	public ScheduleVo selectScheduleData(int no)
	{
		ScheduleVo schVo = ScheduleDao.selectScheduleData(no);
		
		return schVo;	
	}
	
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
	
	// 수정
	
	// 수정 : isPublic
	public void modifyIsPublic(int no, int isPublic)
	{
		ScheduleDao.modifyIsPublic(no, isPublic);
	}
	
	// 수정 : isfinished
	public void modifyIsfinished(int no, Isfinished isfinished)
	{
		ScheduleDao.modifyIsfinished(no, isfinished);
	}
}
