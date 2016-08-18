package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.FavoriteVo;

@Repository
public class FavoriteDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
	public List<FavoriteVo> selectFavoriteList(int user_no)
	{
		List<FavoriteVo> favoList = sqlSession.selectList("favorite.selectFavoriteList", user_no);
		
		return favoList;
	}
}
