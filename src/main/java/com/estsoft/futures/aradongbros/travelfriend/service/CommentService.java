package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.CommentDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.CommentVo;

@Service
public class CommentService 
{
	@Autowired
	private CommentDao commentDao;
	
	public CommentVo insertComment(CommentVo commentVo)
	{
		commentVo = commentDao.insertComment(commentVo);
		commentVo = commentDao.getComment(commentVo.getNo());
		
		return commentVo;
	}

	public List<CommentVo> getPostCommentList(int postList_no) 
	{
		return commentDao.getPostCommentList(postList_no);
	}

	public int deleteComment(int no) 
	{
		int affectNum = commentDao.deleteComment(no);
		return affectNum;
	}

	public CommentVo modifyComment(CommentVo commentVo) 
	{
		commentDao.modifyComment(commentVo);
		CommentVo dbCommentVo = commentDao.getComment(commentVo.getNo());
		
		return dbCommentVo;
	}
}
