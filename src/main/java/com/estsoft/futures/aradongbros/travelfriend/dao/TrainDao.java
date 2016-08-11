package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.TrainInfoVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainOperationRouteVo;

@Repository
public class TrainDao 
{
	@Autowired
	private SqlSession sqlSession;

	public Integer getStationNo(String stationName) 
	{
		Integer no = sqlSession.selectOne("train.selectStationNo", stationName);
		return no;
	}

	public TrainInfoVo selectTrainInfoByNo(int no) 
	{
		TrainInfoVo trainInfoVo = sqlSession.selectOne("train.selectTrainInfoByNo", no);
		return trainInfoVo;
	}

	public List<TrainOperationRouteVo> getStartTrainTimeList(int startStationNo, int endStationNo, int categoryNo, Time goTime) 
	{
		Map<String, Object> map = new HashMap<>();
		map.put("startStationNo", startStationNo);
		map.put("endStationNo", endStationNo);
		map.put("categoryNo", categoryNo);
		map.put("goTime", goTime);
		List<TrainOperationRouteVo> trainTimeList = sqlSession.selectList("train.selectStartTrainTime", map);
		return trainTimeList;
	}
	
	public List<TrainOperationRouteVo> getEndTrainTimeList(int startStationNo, int endStationNo, int categoryNo, Time goTime) 
	{
		Map<String, Object> map = new HashMap<>();
		map.put("startStationNo", startStationNo);
		map.put("endStationNo", endStationNo);
		map.put("categoryNo", categoryNo);
		map.put("goTime", goTime);
		List<TrainOperationRouteVo> trainTimeList = sqlSession.selectList("train.selectENdTrainTime", map);
		return trainTimeList;
	}
}
