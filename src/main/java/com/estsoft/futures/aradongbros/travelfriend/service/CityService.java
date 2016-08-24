package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.CityDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.Status;

@Service
public class CityService 
{
	@Autowired
	private CityDao cityDao;
	
	// 조회
	public List<CityVo> selectCityListBySchedule(int schedule_no)
	{
		List<CityVo> cityListBySchedule = cityDao.selectCityListBySchedule(schedule_no);
		
		return cityListBySchedule;
	}
	
	// 스케줄의 첫번째 도시 조회
	public CityVo selectFirstCityVo(int schedule_no)
	{
		CityVo firstCityVo = cityDao.selectFirstCityVo(schedule_no);
		
		return firstCityVo;
	}
	
	// no로 city 전체 받아오기
	public CityVo selectCityByNo(int no)
	{
		CityVo cityVo = cityDao.selectCityByNo(no);
		
		return cityVo;
	}
	
	// 삽입
	public void insertCityData(CityVo cityVo)
	{
		cityDao.insertCityData(cityVo);
	}
	
	
	
	// 삭제
	public void deleteCityData(int no)
	{			
		cityDao.deleteCityData(no);
	}
	
	// 스케줄과 함꼐 삭제
	public void deleteScheduleRelatedData(int no)
	{
		cityDao.deleteScheduleRelatedData(no);
	}
	
	// 수정 : status
	public void modifyStatus(int no, Status status)
	{
		cityDao.modifyStatus(no, status);
	}
	
	// 수정 : order
	public void modifyOrder(int no, int order)
	{
		cityDao.modifyOrder(no, order);
	}

	public List<Map<String, Object>> selectCityListBySchedule_no(int schedule_no) 
	{
		return cityDao.selectCityListBySchedule_no(schedule_no);
	}
}
