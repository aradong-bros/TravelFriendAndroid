package com.estsoft.futures.aradongbros.travelfriend.vo;

public class UserVo {
	private int no;
	private String name;
	private String picture;
	private Platform platform;
	private String userID;
	
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
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Platform getPlatform() {
		return platform;
	}
	
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", picture=" + picture + ", platform=" + platform + ", userID="
				+ userID + "]";
	}
}