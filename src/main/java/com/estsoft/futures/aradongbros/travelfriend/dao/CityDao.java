package com.estsoft.futures.aradongbros.travelfriend.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
}
