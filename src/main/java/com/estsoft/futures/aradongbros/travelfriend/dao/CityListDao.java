package com.estsoft.futures.aradongbros.travelfriend.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.CityListVo;

@Repository
public class CityListDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
	// no로 cityList 전체 받아오기
	public CityListVo selectCityListByNo(int no)
	{
		CityListVo cityListVo = sqlSession.selectOne("citylist.selectCityListByNo", no);
		
		return cityListVo;
	}
}
