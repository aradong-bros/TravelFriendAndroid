package com.estsoft.futures.aradongbros.travelfriend.vo;

public class TrainStationVo
{
	private int no;
	private String name;
	private String location;
	private int cityNum;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getCityNum() {
		return cityNum;
	}
	
	public void setCityNum(int cityNum) {
		this.cityNum = cityNum;
	}

	@Override
	public String toString() {
		return "TrainStationVo [no=" + no + ", name=" + name + ", location=" + location + ", cityNum=" + cityNum + "]";
	}
}
