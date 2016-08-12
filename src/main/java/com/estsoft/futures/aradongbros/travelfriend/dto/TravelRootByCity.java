package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.Arrays;

public class TravelRootByCity 
{
	int[] TRAVEL_ROOT;

	public TravelRootByCity() {}
	
	public TravelRootByCity(int[] TRAVEL_ROOT)
	{
		this.TRAVEL_ROOT = TRAVEL_ROOT;
	}
	
	public int[] getTRAVEL_ROOT() {
		return TRAVEL_ROOT;
	}

	public void setTRAVEL_ROOT(int[] tRAVEL_ROOT) {
		TRAVEL_ROOT = tRAVEL_ROOT;
	}

	@Override
	public String toString() {
		return "TravelRootByCity [TRAVEL_ROOT=" + Arrays.toString(TRAVEL_ROOT) + "]";
	}
}
