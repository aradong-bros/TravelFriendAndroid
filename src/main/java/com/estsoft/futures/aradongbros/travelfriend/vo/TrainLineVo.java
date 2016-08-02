package com.estsoft.futures.aradongbros.travelfriend.vo;

public class TrainLineVo
{
	private int no;
	private String name;
	private Orientation orientation;
	
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
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String toString() {
		return "TrainLineVo [no=" + no + ", name=" + name + ", orientation=" + orientation + "]";
	}
}
