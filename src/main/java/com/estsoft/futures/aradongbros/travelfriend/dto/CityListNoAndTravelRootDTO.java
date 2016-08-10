package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.List;

public class CityListNoAndTravelRootDTO 
{
	private int cityList_no;
	private List<int[]> TRAVEL_ROOT;
	
	public int getCityList_no() {
		return cityList_no;
	}
	public void setCityList_no(int cityList_no) {
		this.cityList_no = cityList_no;
	}
	public List<int[]> getTRAVEL_ROOT() {
		return TRAVEL_ROOT;
	}
	public void setTRAVEL_ROOT(List<int[]> tRAVEL_ROOT) {
		TRAVEL_ROOT = tRAVEL_ROOT;
	}
	@Override
	public String toString() {
		return "CityListNoAndTravelRootDTO [cityList_no=" + cityList_no + ", TRAVEL_ROOT=" + TRAVEL_ROOT + "]";
	}
	
}
