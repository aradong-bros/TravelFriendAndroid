package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.Platform;
import com.estsoft.futures.aradongbros.travelfriend.vo.UserVo;

@Repository
public class UserDao 
{
	@Autowired
	private SqlSession sqlSession;

	public UserVo getUser(Platform platform, String userID) {
		Map<String, Object> map = new HashMap<>();
		map.put("platform", platform);
		map.put("userID", userID);
		
		UserVo userVo = sqlSession.selectOne("user.selectUserByPlatformAndUserID", map);
		return userVo;
	}

	public UserVo insertUser(UserVo userVo) {
		sqlSession.insert("user.insertUser", userVo);
		return userVo;
	}

	public UserVo modifyUser(UserVo userVo) {
		sqlSession.update("user.modifyUser", userVo);
		UserVo modifiedUser = sqlSession.selectOne("user.selectUserByNo", userVo.getNo());
		return modifiedUser;
	}

	public UserVo getUserNameAndPicture(int user_no) {
		return sqlSession.selectOne("user.getUserNameAndPicture", user_no);
	}
}
