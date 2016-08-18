package com.estsoft.futures.aradongbros.travelfriend.vo;

public class FavoriteVo 
{
	private int no;
	private int user_no;
	private int schedule_no;
	
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
	public int getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}
	@Override
	public String toString() {
		return "FavoriteVo [no=" + no + ", user_no=" + user_no + ", schedule_no=" + schedule_no + "]";
	}
}
