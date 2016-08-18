package com.estsoft.futures.aradongbros.travelfriend.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.TrainScheduleVo;

@Repository
public class TrainScheduleDao 
{
	@Autowired
	private SqlSession sqlSession;

	public void insertTrainSchedule(TrainScheduleVo vo) {
		sqlSession.insert("trainschedule.insertTrainSchedule", vo);
	}
}