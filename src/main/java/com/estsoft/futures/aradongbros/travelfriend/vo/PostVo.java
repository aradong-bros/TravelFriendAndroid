package com.estsoft.futures.aradongbros.travelfriend.vo;

public class PostVo 
{
	private int city_no;
	private int postList_no;
	
	public int getCity_no() {
		return city_no;
	}
	public void setCity_no(int city_no) {
		this.city_no = city_no;
	}
	public int getPostList_no() {
		return postList_no;
	}
	public void setPostList_no(int postList_no) {
		this.postList_no = postList_no;
	}
	@Override
	public String toString() {
		return "PostVo [city_no=" + city_no + ", postList_no=" + postList_no + "]";
	}
}
