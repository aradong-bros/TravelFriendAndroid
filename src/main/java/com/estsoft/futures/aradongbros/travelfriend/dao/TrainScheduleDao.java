package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.List;

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

	public void deleteTrainScheduleByScheduleNo(int schedule_no) 
	{
		sqlSession.delete("trainschedule.deleteTrainScheduleByScheduleNo", schedule_no);
	}

	public List<TrainScheduleVo> selectTrainScheduleBySchedule_no(int schedule_no) 
	{
		return sqlSession.selectList("trainschedule.selectTrainScheduleByScheduleNo", schedule_no);
	}
}
