package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.ScheduleDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.Isfinished;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Service
public class ScheduleService 
{
	@Autowired
	private ScheduleDao scheduleDao;
	
    public List<ScheduleVo> selectScheduleAllData()
    {
    	List<ScheduleVo> schList = scheduleDao.selectScheduleAllData();
    	
    	return schList;
    }
	
	//조회
	public ScheduleVo selectScheduleData(int no)
	{
		ScheduleVo schVo = scheduleDao.selectScheduleData(no);
		
		return schVo;	
	}
	
	//삽입
	public void insertScheduleData(ScheduleVo schVo)
	{
		scheduleDao.insertScheduleData(schVo);
	}
	
	//삭제
	public void deleteScheduleData(int no)
	{
		scheduleDao.deleteScheduleData(no);
	}
	
	// 수정
	
	// 수정 : isPublic
	public void modifyIsPublic(int no, int isPublic)
	{
		scheduleDao.modifyIsPublic(no, isPublic);
	}
	
	// 수정 : isfinished
	public void modifyIsfinished(int no, Isfinished isfinished)
	{
		scheduleDao.modifyIsfinished(no, isfinished);
	}
}
