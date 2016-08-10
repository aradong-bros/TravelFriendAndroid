package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.Arrays;

public class CityAndPostDTO 
{
	private int cityList_no;
	private AttractionDTO[] atrDTOArray;
	public int getCityList_no() {
		return cityList_no;
	}
	public void setCityList_no(int cityList_no) {
		this.cityList_no = cityList_no;
	}
	public AttractionDTO[] getAtrDTOArray() {
		return atrDTOArray;
	}
	public void setAtrDTOArray(AttractionDTO[] atrDTOArray) {
		this.atrDTOArray = atrDTOArray;
	}
	@Override
	public String toString() {
		return "CityAndPostDTO [cityList_no=" + cityList_no + ", atrDTOArray=" + Arrays.toString(atrDTOArray) + "]";
	}
	
}
