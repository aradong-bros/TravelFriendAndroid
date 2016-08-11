package com.estsoft.futures.aradongbros.travelfriend.vo;

public class PostVo 
{
	private int no;
	private int city_no;
	private int postList_no;
	private int postOrder;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
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
	public int getPostOrder() {
		return postOrder;
	}
	public void setPostOrder(int postOrder) {
		this.postOrder = postOrder;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", city_no=" + city_no + ", postList_no=" + postList_no + ", postOrder=" + postOrder
				+ "]";
	}
}
