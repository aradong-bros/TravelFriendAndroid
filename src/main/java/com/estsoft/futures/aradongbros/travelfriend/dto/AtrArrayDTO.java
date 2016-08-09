package com.estsoft.futures.aradongbros.travelfriend.dto;

import java.util.Arrays;

public class AtrArrayDTO 
{
	private AttractionDTO[] array;

	public AttractionDTO[] getArray() {
		return array;
	}

	public void setArray(AttractionDTO[] array) {
		this.array = array;
	}

	@Override
	public String toString() {
		return "AtrArrayDTO [array=" + Arrays.toString(array) + "]";
	}
	
	
	
	
}
