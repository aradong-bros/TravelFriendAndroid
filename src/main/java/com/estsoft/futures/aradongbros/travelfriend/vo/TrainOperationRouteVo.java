package com.estsoft.futures.aradongbros.travelfriend.vo;

import java.sql.Time;

public class TrainOperationRouteVo {
	private int no;
	private int trainInfo_no;
	private int trainLine_no;
	private int trainStation_no;
	private int trainCategory_no;
	private int order;
	private Time departureTime;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getTrainInfo_no() {
		return trainInfo_no;
	}
	
	public void setTrainInfo_no(int trainInfo_no) {
		this.trainInfo_no = trainInfo_no;
	}
	
	public int getTrainLine_no() {
		return trainLine_no;
	}
	
	public void setTrainLine_no(int trainLine_no) {
		this.trainLine_no = trainLine_no;
	}
	
	public int getTrainStation_no() {
		return trainStation_no;
	}
	
	public void setTrainStation_no(int trainStation_no) {
		this.trainStation_no = trainStation_no;
	}
	
	public int getTrainCategory_no() {
		return trainCategory_no;
	}
	
	public void setTrainCategory_no(int trainCategory_no) {
		this.trainCategory_no = trainCategory_no;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public Time getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		String timeStr[] = departureTime.split(":");
		int hour = Integer.parseInt(timeStr[0]);
		int minute = Integer.parseInt(timeStr[1]);
		int seceond = Integer.parseInt(timeStr[2]);
		this.departureTime = new Time(hour, minute, seceond);
	}
	
//	public void setDepartureTime(Time departureTime) {
//		this.departureTime = departureTime;
//	}
	
	@Override
	public String toString() {
		return "TrainOperationRouteVo [no=" + no + ", trainInfo_no=" + trainInfo_no + ", trainLine_no=" + trainLine_no
				+ ", trainStation_no=" + trainStation_no + ", trainCategory_no=" + trainCategory_no + ", order=" + order
				+ ", departureTime=" + departureTime + "]";
	}
}
