package com.estsoft.futures.aradongbros.travelfriend.vo;

import java.sql.Date;

public class CommentVo {
	private int no;
	private int user_no;
	private int postList_no;
	private Date date;
	private String content;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getUser_no() {
		return user_no;
	}
	
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	public int getPostList_no() {
		return postList_no;
	}
	
	public void setPostList_no(int postList_no) {
		this.postList_no = postList_no;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "CommentService [no=" + no + ", user_no=" + user_no + ", postList_no=" + postList_no + ", date=" + date
				+ ", content=" + content + "]";
	}
}
