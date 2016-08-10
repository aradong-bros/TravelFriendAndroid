package com.estsoft.futures.aradongbros.travelfriend.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;

@Repository
public class ScheduleDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
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
}
