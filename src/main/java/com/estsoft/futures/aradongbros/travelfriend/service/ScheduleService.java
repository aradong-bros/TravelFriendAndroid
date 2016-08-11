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
	
	// 사용자꺼 전체 조회 
    public List<ScheduleVo> selectScheduleAllDataByUser(int user_no)
    {
    	List<ScheduleVo> schList = scheduleDao.selectScheduleAllDataByUser(user_no);
    	
    	return schList;
    }
    
    //다른사람들꺼 전체 조회
    public List<ScheduleVo> selectScheduleAllDataByOther(int user_no)
    {
    	List<ScheduleVo> schList = scheduleDao.selectScheduleAllDataByOther(user_no);
    	
    	return schList;
    }
	
	// 스케줄 1개 조회
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
