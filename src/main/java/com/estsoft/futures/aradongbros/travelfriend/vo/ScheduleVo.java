package com.estsoft.futures.aradongbros.travelfriend.vo;

public class ScheduleVo 
{
	private int no;
	private int user_no;
	private String title;
	private int isPublic;
	private String startDate;
	private String endDate;
	private String isfinished;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getIsfinished() {
		return isfinished;
	}
	public void setIsfinished(String isfinished) {
		this.isfinished = isfinished;
	}
	@Override
	public String toString() {
		return "ScheduleVo [no=" + no + ", user_no=" + user_no + ", title=" + title + ", isPublic=" + isPublic
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", isfinished=" + isfinished + "]";
	}
}
