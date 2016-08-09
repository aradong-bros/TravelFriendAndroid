package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.CommentService;
import com.estsoft.futures.aradongbros.travelfriend.vo.CommentVo;

@Controller
@RequestMapping("/comment")
public class CommentController 
{
	@Autowired
	private CommentService commentService;
	
	/*
	 * 덧글 insert 메소드
	 * 
	 * 필요 파라미터 : user_no, postList_no, content
	 * 모든 파라미터는 인코딩 필요
	 * 리턴타입 : jsonString(인서트 된 덧글을 다시 돌려줌(no, date 포함해서)
	 */
	@RequestMapping("/insertComment")
	@ResponseBody
	public Map<String, CommentVo> insertComment(
			@ModelAttribute CommentVo commentVo)
	{
		Map<String, CommentVo> map = new HashMap<>();
		map.put("commentVo", commentService.insertComment(commentVo));
		
		return map;
	}
	
	/*
	 * 관광지 정보의 덧글 모두 가져오기 메소드
	 * 
	 * 필요 파라미터 : postList_no
	 * 리턴타입 : jsonString(comment의 모든 정보들을 jsonArray로 넘김)
	 */
	@RequestMapping("/postCommentList")
	@ResponseBody
	public Map<String, List<CommentVo>> getPostCommentList(
			@RequestParam("postList_no") int postList_no)
	{
		Map<String, List<CommentVo>> map = new HashMap<>();
		
		map.put("comments", commentService.getPostCommentList(postList_no));
		
		return map;
	}
	
	/*
	 * 덧글 삭제하기 메소드
	 * 
	 * 필요 파라미터 : no
	 * 리턴타입 : success,fail(삭제 성공여부만 리턴)
	 */
	@RequestMapping("/deleteComment")
	@ResponseBody
	public String deleteComment(
			@RequestParam("no") int no)
	{
		int affectNum = commentService.deleteComment(no);
		
		if(affectNum > 0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	/*
	 * 덧글 수정하기 메소드
	 * 
	 * 필요 파라미터 : no, content
	 * 리턴타입 : jsonString(수정된 덧글의 모든 정보를 다시 돌려줌)
	 */
	@RequestMapping("/modifyComment")
	@ResponseBody
	public Map<String, CommentVo> modifyComment(
			@ModelAttribute CommentVo commentVo)
	{
		CommentVo dbCommentVo = commentService.modifyComment(commentVo);
		
		Map<String, CommentVo> map = new HashMap<>();
		map.put("commentVo", commentVo);
		
		return map;
	}
}