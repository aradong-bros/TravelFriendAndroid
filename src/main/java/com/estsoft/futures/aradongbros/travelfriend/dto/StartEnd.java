package com.estsoft.futures.aradongbros.travelfriend.dto;

public class StartEnd 
{	
	private int city_no;
	private int start;
	private int end;
	private int TOTAL_TIME;
	
	public StartEnd() {}
	
	public StartEnd(int city_no, int start, int end, int TOTAL_TIME) 
	{
		this.city_no = city_no;
		this.start = start;
		this.end = end;
		this.TOTAL_TIME = TOTAL_TIME;
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
	public int getTOTAL_TIME() {
		return TOTAL_TIME;
	}

	public void setTOTAL_TIME(int tOTAL_TIME) {
		TOTAL_TIME = tOTAL_TIME;
	}

	@Override
	public String toString() {
		return "StartEnd [city_no=" + city_no + ", start=" + start + ", end=" + end + ", TOTAL_TIME=" + TOTAL_TIME
				+ "]";
	}
}
