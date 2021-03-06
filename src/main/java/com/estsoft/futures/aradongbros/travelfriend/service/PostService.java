package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.estsoft.futures.aradongbros.travelfriend.dao.PostDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

@Service
public class PostService 
{
	@Autowired
	private PostDao postDao;
	
	// 조회
	public List<PostVo> selectPostListBySchedule(int city_no)
	{
		List<PostVo> postListBySchedule = postDao.selectPostListBySchedule(city_no);
				
		return postListBySchedule;
	}
	
	//selectPostCountByCity
	public int selectPostCountByCity(@PathVariable int city_no)
	{	
		int postCount = 0;
		
		postCount = postDao.selectPostCountByCity(city_no);
		
		return postCount;
	}
	
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

	public List<Map<String,Object>> selectPostByCity_no(int city_no) 
	{
		return postDao.selectPostByCity_no(city_no);
	}

	public List<Map<String,Object>> selectPostBySchedule_no(int schedule_no) 
	{
		return postDao.selectPostBySchedule_no(schedule_no);
	}
}
