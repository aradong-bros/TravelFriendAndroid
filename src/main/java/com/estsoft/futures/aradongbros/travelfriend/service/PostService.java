package com.estsoft.futures.aradongbros.travelfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.PostDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

@Service
public class PostService 
{
	@Autowired
	private PostDao postDao;
	
	// 조회
	
	// 삽입
	public void insertPostData(PostVo postVo)
	{
		postDao.insertPostData(postVo);
	}
	
	// 삭제
	public void deletePostData(int no)
	{			
		postDao.deletePostData(no);
	}
	
	// 수정 : postOrder
	public void modifyPostOrder(int no, int postOrder)
	{
		postDao.modifyPostOrder(no, postOrder);
	}
}
