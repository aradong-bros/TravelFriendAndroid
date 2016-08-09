package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.ArrayList;
import java.util.List;

import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;

public class TravelRootDTO 
{
	private List<AttractionDTO> atrList = new ArrayList<AttractionDTO>();

	public List<AttractionDTO> getAtrList() {
		return atrList;
	}

	public void setAtrList(List<AttractionDTO> atrList) {
		this.atrList = atrList;
	}

	@Override
	public String toString() {
		return "TravelRootDTO [atrList=" + atrList + "]";
	}
}
