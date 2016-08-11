package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.PostService;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.Status;

@Controller
@RequestMapping("/post")
public class PostController 
{
	@Autowired
	private PostService postService;
	
	// 조회
	
	
	// 삽입  -> url : http://222.239.250.207:8080/TravelFriendAndroid/post/postInsert
	@RequestMapping("/postInsert")
	@ResponseBody
	public Map<String,Object> insertPostData(@RequestBody PostVo[] postList)
	{	
		Map<String,Object> map = new HashMap<String,Object>();

		for ( int i = 0; i < postList.length; i++ )
		{
			if ( postList[i].getPostOrder() != -1 )
			{
				postList[i].setPostOrder(-1);
			}
			
			postService.insertPostData(postList[i]);			
		}

		return map;
	}
	
	// 삭제  -> url : http://222.239.250.207:8080/TravelFriendAndroid/post/postDelete/{no}
	@RequestMapping("/postDelete/{no}")
	@ResponseBody
	public Map<String,Object> deletePostData(@PathVariable("no") int no)
	{			
		Map<String,Object> map = new HashMap<String,Object>();
		
		postService.deletePostData(no);
		
		return map;
	}
	
	// 수정
}
