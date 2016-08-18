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
	
	// city와 함께 삭제
	public void deleteCityRelatedData(int no)
	{
		postDao.deleteCityRelatedData(no);
	}
	
	// 스케줄과 함께 삭제
	public void deleteCityRelatedDataInSchedule(int no)
	{
		postDao.deleteCityRelatedDataInSchedule(no);
	}
	
	// 수정 : postOrder
	public void modifyPostOrder(int postList_no, int postOrder)
	{
		postDao.modifyPostOrder(postList_no, postOrder);
	}
}
