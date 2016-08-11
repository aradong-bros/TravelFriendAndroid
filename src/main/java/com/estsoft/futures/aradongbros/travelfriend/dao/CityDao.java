package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
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
