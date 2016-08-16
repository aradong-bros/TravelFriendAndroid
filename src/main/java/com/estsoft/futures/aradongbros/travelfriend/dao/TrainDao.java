package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.TrainInfoVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainOperationRouteVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainStationVo;

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
		List<TrainOperationRouteVo> trainTimeList = sqlSession.selectList("train.selectEndTrainTime", map);
		return trainTimeList;
	}

	public List<Integer> getTransferStationList(int startStationNo, int endStationNo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startStationNo", startStationNo);
		map.put("endStationNo", endStationNo);
		
		List<Integer> list = sqlSession.selectList("train.selectTransferStationList", map);
		return list;
	}

	public List<TrainOperationRouteVo> getTransferStartTrainTimeList(
			int transferStationNo, int endStationNo, int categoryNo, Time boardingTime) 
	{
		Map<String, Object> map = new HashMap<>();
		map.put("transferStationNo", transferStationNo);
		map.put("endStationNo", endStationNo);
		map.put("categoryNo", categoryNo);
		map.put("boardingTime", boardingTime);
		
		List<TrainOperationRouteVo> trainTimeList = sqlSession.selectList("train.selectTransferStartTrainTime", map);
		return trainTimeList;
	}

	public List<TrainOperationRouteVo> getEndTrainTimeAfterList(
			int transferStationNo, int endStationNo, int categoryNo, Time boardingTime) 
	{
		Map<String, Object> map = new HashMap<>();
		map.put("transferStationNo", transferStationNo);
		map.put("endStationNo", endStationNo);
		map.put("categoryNo", categoryNo);
		map.put("boardingTime", boardingTime);
		
		List<TrainOperationRouteVo> trainTimeList = sqlSession.selectList("train.selectEndTrainTimeAfter", map);
		return trainTimeList;
	}
	
	public String getStationName(int no)
	{
		String name = sqlSession.selectOne("train.selectStationName", no);
		return name;
	}

	public List<TrainStationVo> getCityStationList(int cityNum) {
		List<TrainStationVo> cityStationList = sqlSession.selectList("train.selectTrainStationByCityNo", cityNum);
		return cityStationList;
	}

	public List<String> getAllStationName() 
	{
		List<String> stationNameList = sqlSession.selectList("train.selectAllStationName");
		return stationNameList;
	}
}
