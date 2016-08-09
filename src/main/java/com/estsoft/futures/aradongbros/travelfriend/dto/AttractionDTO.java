package com.estsoft.futures.aradongbros.travelfriend.dto;

public class AttractionDTO
{
	private int no;
	private String location;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "AttractionDTO [no=" + no + ", location=" + location + "]";
	}
}
