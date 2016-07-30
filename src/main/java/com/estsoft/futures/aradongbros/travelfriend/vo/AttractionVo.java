package com.estsoft.futures.aradongbros.travelfriend.vo;

public class AttractionVo
{
	private int no;
	private int cityList_no;
	private String name;
	private String location;
	private String picture;
	private String info;
	private Category category;
	private String address;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCityList_no() {
		return cityList_no;
	}
	public void setCityList_no(int cityList_no) {
		this.cityList_no = cityList_no;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "AttractionVo [no=" + no + ", cityList_no=" + cityList_no + ", name=" + name + ", location=" + location
				+ ", picture=" + picture + ", info=" + info + ", category=" + category + ", address=" + address + "]";
	}
}
