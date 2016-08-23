package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.CommentVo;

@Repository
public class CommentDao 
{
	@Autowired
	private SqlSession sqlSession;
	
	public CommentVo insertComment(CommentVo commentVo)
	{
		sqlSession.insert("comment.insertComment",commentVo);
		return commentVo;
	}

	public CommentVo getComment(int no) 
	{
		CommentVo commentVo = sqlSession.selectOne("comment.selectCommentByNo", no);
		return commentVo;
	}

	public List<Map<String,Object>> getPostCommentList(int postList_no) 
	{
		return sqlSession.selectList("comment.selectCommentByPostList_no", postList_no);
	}

	public int deleteComment(int no) 
	{
		return sqlSession.delete("comment.deleteCommentByNo", no);
	}

	public void modifyComment(CommentVo commentVo) 
	{
		sqlSession.update("comment.modifyComment", commentVo);
	}
}
