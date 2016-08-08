package com.eooz.common.mask;

import java.util.ArrayList;

public enum Mask {

	ACTIVE(1);
	
	private int value;
	
	Mask(int mask){
		value = mask;
	}
	
	public int value(){
		return this.value;
	}
	
	//checks whether the decimal contains the mask value. 
	//returns true if it contains else returns false.
	public boolean contains(Mask mask, int decimal)throws NullPointerException{
		

		int value = mask.value();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list = Binary.valuesFor(decimal);
		
		return list.contains(value);
	}
	
	
}
