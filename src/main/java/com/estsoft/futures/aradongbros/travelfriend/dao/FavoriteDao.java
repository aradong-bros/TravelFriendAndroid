package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void insertFavoriteData(int user_no, int schedule_no)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("user_no", user_no);
		map.put("schedule_no", schedule_no);
		
		sqlSession.selectList("favorite.insertFavoriteData", map);
	}
	
	public void deleteFavoriteData(int no)
	{
		sqlSession.delete("favorite.deleteFavoriteData", no);
	}
	
	public void deleteFavoriteData2(int user_no, int schedule_no)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("user_no", user_no);
		map.put("schedule_no", schedule_no);
		
		sqlSession.delete("favorite.deleteFavoriteData2", map);
	}
}
