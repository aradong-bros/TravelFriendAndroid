package com.estsoft.futures.aradongbros.travelfriend.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;

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
	
	// 수정
}
