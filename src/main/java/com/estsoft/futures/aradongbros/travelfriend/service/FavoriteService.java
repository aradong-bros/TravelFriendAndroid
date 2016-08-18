package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.FavoriteDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.FavoriteVo;

@Service
public class FavoriteService 
{
	@Autowired
	private FavoriteDao favoriteDao;
	
	public List<FavoriteVo> selectFavoriteList(int user_no)
	{
		List<FavoriteVo> favoList = favoriteDao.selectFavoriteList(user_no);
		
		return favoList;
	}
	
	public void insertFavoriteData(int user_no, int schedule_no)
	{
		favoriteDao.insertFavoriteData(user_no, schedule_no);
	}
	
	public void deleteFavoriteData(int no)
	{
		favoriteDao.deleteFavoriteData(no);
	}
}
