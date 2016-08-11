package com.estsoft.futures.aradongbros.travelfriend.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

@Repository
public class PostDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
	// 조회
	
	// 삽입
	public void insertPostData(PostVo postVo)
	{
		sqlSession.insert("post.insertPostData", postVo);
	}
	
	// 삭제
	public void deletePostData(int no)
	{			
		sqlSession.delete("post.deletePostData", no);
	}
	
	// 수정
}
