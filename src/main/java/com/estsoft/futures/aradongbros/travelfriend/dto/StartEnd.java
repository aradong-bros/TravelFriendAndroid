package com.estsoft.futures.aradongbros.travelfriend.dto;

public class StartEnd 
{	
	private int city_no;
	private int start;
	private int end;
	
	public StartEnd() {}
	
	public StartEnd(int city_no, int start, int end) 
	{
		this.city_no = city_no;
		this.start = start;
		this.end = end;
	}
	
	public int getCity_no() {
		return city_no;
	}

	public void setCity_no(int city_no) {
		this.city_no = city_no;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "StartEnd [city_no=" + city_no + ", start=" + start + ", end=" + end + "]";
	}
}
