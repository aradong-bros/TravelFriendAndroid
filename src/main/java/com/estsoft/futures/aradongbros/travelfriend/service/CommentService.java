package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.CommentDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.CommentVo;

@Service
public class CommentService 
{
	@Autowired
	private CommentDao commentDao;
	
	public Map<String,Object> insertComment(CommentVo commentVo)
	{
		commentVo = commentDao.insertComment(commentVo);
		
		return commentDao.getComment(commentVo.getNo());
	}

	public List<Map<String, Object>> getPostCommentList(int postList_no) 
	{
		return commentDao.getPostCommentList(postList_no);
	}

	public int deleteComment(int no) 
	{
		int affectNum = commentDao.deleteComment(no);
		return affectNum;
	}

	public Map<String, Object> modifyComment(CommentVo commentVo) 
	{
		commentDao.modifyComment(commentVo);
		Map<String, Object> dbCommentVo = commentDao.getComment(commentVo.getNo());
		
		return dbCommentVo;
	}
}
