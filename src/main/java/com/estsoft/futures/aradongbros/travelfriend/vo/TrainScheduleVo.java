package com.estsoft.futures.aradongbros.travelfriend.vo;

import java.sql.Date;

public class TrainScheduleVo 
{
	private int no;
	private int schedule_no;
	private int city_no;
	private String startStation;
	private String endStation;
	private String startDate;
	private String arrivalDate;
	
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
	
	public int getCity_no() {
		return city_no;
	}
	
	public void setCity_no(int city_no) {
		this.city_no = city_no;
	}
	
	public String getStartStation() {
		return startStation;
	}
	
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	
	public String getEndStation() {
		return endStation;
	}
	
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public String toString() {
		return "TrainScheduleVo [no=" + no + ", schedule_no=" + schedule_no + ", city_no=" + city_no + ", startStation="
				+ startStation + ", endStation=" + endStation + ", startDate=" + startDate + ", arrivalDate="
				+ arrivalDate + "]";
	}
}