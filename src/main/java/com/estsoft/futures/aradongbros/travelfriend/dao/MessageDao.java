package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.MessageVo;

@Repository
public class MessageDao 
{
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> getSomeMessage(int talk_no, int no1, int no2) 
	{
		Map<String, Integer> map = new HashMap<>();
		map.put("talk_no", talk_no);
		map.put("no1", no1);
		map.put("no2", no2);
		
		List<Map<String, Object>> list = sqlSession.selectList("message.getSomeMessage", map);
		return list;
	}

}
