package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.ArrayList;
import java.util.List;

import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;

public class TravelRootDTO 
{
	private List<AttractionVo> atrList = new ArrayList<AttractionVo>();

	public List<AttractionVo> getAtrList() {
		return atrList;
	}

	public void setAtrList(List<AttractionVo> atrList) {
		this.atrList = atrList;
	}

	@Override
	public String toString() {
		return "AttractionDTO [atrList=" + atrList + "]";
	}
}
