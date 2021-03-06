package com.estsoft.futures.aradongbros.travelfriend.vo;

public class CityVo 
{
	private int no;
	private int schedule_no;
	private int cityList_no;
	private Status status;  // start, end, none
	private int cityOrder;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}
	public int getCityList_no() {
		return cityList_no;
	}
	public void setCityList_no(int cityList_no) {
		this.cityList_no = cityList_no;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getCityOrder() {
		return cityOrder;
	}
	public void setCityOrder(int cityOrder) {
		this.cityOrder = cityOrder;
	}
	@Override
	public String toString() {
		return "CityVo [no=" + no + ", schedule_no=" + schedule_no + ", cityList_no=" + cityList_no + ", status="
				+ status + ", cityOrder=" + cityOrder + "]";
	}
	
	
	
}
