package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.TrainScheduleDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainScheduleVo;

@Service
public class TrainScheduleService 
{
	@Autowired
	private TrainScheduleDao trainScheduleDao;
	
	public void insertTrainSchedule(TrainScheduleVo vo) 
	{
		trainScheduleDao.insertTrainSchedule(vo);
	}
	
	public void deleteTrainScheduleByScheduleNo(int schedule_no)
	{
		trainScheduleDao.deleteTrainScheduleByScheduleNo(schedule_no);
	}
}