package com.estsoft.futures.aradongbros.travelfriend.vo;

public class CityListVo 
{
	private int no;
	private int areaCode;
	private int sigunguCode;
	private String name;
	private String location;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public int getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(int sigunguCode) {
		this.sigunguCode = sigunguCode;
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
	@Override
	public String toString() {
		return "CityListVo [no=" + no + ", areaCode=" + areaCode + ", sigunguCode=" + sigunguCode + ", name=" + name
				+ ", location=" + location + "]";
	}
}
