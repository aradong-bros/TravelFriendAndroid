package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.List;

public class StartEndStation 
{
	private int city_no;
	private List<String> startStation;
	private List<String> endStation;
	private int total_time;
	
	public StartEndStation(){
		super();
	}
	
	public StartEndStation(int city_no, List<String> startStation, List<String> endStation, int total_time){
		this.city_no = city_no;
		this.startStation = startStation;
		this.endStation = endStation;
		this.total_time = total_time;
	}

	public int getCity_no() {
		return city_no;
	}

	public void setCity_no(int city_no) {
		this.city_no = city_no;
	}

	public List<String> getStartStation() {
		return startStation;
	}

	public void setStartStation(List<String> startStation) {
		this.startStation = startStation;
	}

	public List<String> getEndStation() {
		return endStation;
	}

	public void setEndStation(List<String> endStation) {
		this.endStation = endStation;
	}

	public int getTotal_time() {
		return total_time;
	}

	public void setTotal_time(int total_time) {
		this.total_time = total_time;
	}

	@Override
	public String toString() {
		return "StartEndStation [city_no=" + city_no + ", startStation=" + startStation + ", endStation=" + endStation
				+ ", total_time=" + total_time + "]";
	}
}
