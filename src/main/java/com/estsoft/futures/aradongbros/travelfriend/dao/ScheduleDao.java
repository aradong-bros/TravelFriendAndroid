package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.Isfinished;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Repository
public class ScheduleDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
    //조회
    public ScheduleVo selectScheduleData(int no)
    {
    	ScheduleVo schVo = sqlSession.selectOne("schedule.selectScheduleData", no);
    	
    	return schVo;
    }
    
	//삽입
	public void insertScheduleData(ScheduleVo schVo)
	{
		sqlSession.insert("schedule.insertScheduleData", schVo);
	}
	
	//삭제
	public void deleteScheduleData(int no)
	{
		sqlSession.delete("schedule.deleteScheduleData", no);
	}
	
	// 수정 : isPublic
	public void modifyIsPublic(int no, int isPublic)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("no", no);
		map.put("isPublic", isPublic);
		
		sqlSession.update("schedule.modifyIsPublic", map);
	}
	
	// 수정 : isfinished
	public void modifyIsfinished(int no, Isfinished isfinished)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("no", no);
		map.put("isfinished", isfinished);
		
		sqlSession.update("schedule.modifyIsfinished", map);
	}
}
