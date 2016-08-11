package com.estsoft.futures.aradongbros.travelfriend.vo;

import java.sql.Date;

public class MessageVo {
	private int no;
	private int talk_no;
	private int user_no;
	private String content;
	private Date time;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getTalk_no() {
		return talk_no;
	}
	public void setTalk_no(int talk_no) {
		this.talk_no = talk_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "MessageVo [no=" + no + ", talk_no=" + talk_no + ", user_no=" + user_no + ", content=" + content
				+ ", time=" + time + "]";
	}
}