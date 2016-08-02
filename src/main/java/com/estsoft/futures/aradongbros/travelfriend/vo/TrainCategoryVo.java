package com.estsoft.futures.aradongbros.travelfriend.vo;

public class TrainCategoryVo {
	private int no;
	private String workDate;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getWorkDate() {
		return workDate;
	}
	
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	@Override
	public String toString() {
		return "TrainCategoryVo [no=" + no + ", workDate=" + workDate + "]";
	}
}
