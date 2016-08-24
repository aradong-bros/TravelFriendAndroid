package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.Status;

@Repository
public class CityDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
	// 조회
	public List<CityVo> selectCityListBySchedule(int schedule_no)
	{
		List<CityVo> cityListBySchedule = sqlSession.selectList("city.selectCityListBySchedule", schedule_no);
		
		return cityListBySchedule;
	}
	
	//스케쥴 넘버로 도시 가져오는데 외래키로 다른 정보들도 다 가져옴
	public List<Map<String, Object>> selectCityListBySchedule_no(int schedule_no) 
	{
		return sqlSession.selectList("city.selectCityListBySchedule_no", schedule_no);
	}
	
	// 스케줄의 첫번째 도시 조회
	public CityVo selectFirstCityVo(int schedule_no)
	{
		CityVo firstCityVo = sqlSession.selectOne("city.selectFirstCityVo", schedule_no);
		
		return firstCityVo;
	}
	
	// no로 city 전체 받아오기
	public CityVo selectCityByNo(int no)
	{
		CityVo cityVo = sqlSession.selectOne("city.selectCityByNo", no);
		
		return cityVo;
	}
	
	// 삽입
	public void insertCityData(CityVo cityVo)
	{
		sqlSession.insert("city.insertCityData", cityVo);
	}
    
	// 삭제
	public void deleteCityData(int no)
	{			
		sqlSession.delete("city.deleteCityData", no);
	}
	
	// 스케줄과 함꼐 삭제
	public void deleteScheduleRelatedData(int no)
	{
		sqlSession.delete("city.deleteScheduleRelatedData", no);
	}

	// 수정 : status
	public void modifyStatus(int no, Status status)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("no", no);
		map.put("status", status);
		
		sqlSession.update("city.modifyStatus", map);
	}
	
	// 수정 : order
	public void modifyOrder(int no, int order)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("no", no);
		map.put("order", order);
		
		sqlSession.update("city.modifyOrder", map);
	}
}
