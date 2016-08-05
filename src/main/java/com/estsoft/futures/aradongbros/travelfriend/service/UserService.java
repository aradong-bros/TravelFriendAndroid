package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.UserDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.Platform;
import com.estsoft.futures.aradongbros.travelfriend.vo.UserVo;

@Service
public class UserService 
{
	@Autowired
	private UserDao userDao;

	public UserVo getUser(Platform platform, String userID) 
	{
		UserVo userVo = userDao.getUser(platform, userID);
		return userVo;
	}

	public UserVo insertUser(UserVo userVo) 
	{
		return userDao.insertUser(userVo);
	}

	public UserVo modifyUser(UserVo userVo) 
	{
		return userDao.modifyUser(userVo);
	}
}