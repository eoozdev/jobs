/**
 * converts decimal values into binary. The main method in here is
 * getBinary(int decimal): this returns a list of binary values for a
 * passed in decimal values
 * Note: this method was taken from legacy src and adapted to use an arrayList instead of Vector. (reduce overhead on synchronization)
 * **/

package com.eooz.common.mask;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Binary {
	
	private static Logger logger = LoggerFactory.getLogger(Binary.class);
	
	/**
	 * converts the decimal value and prepares list of binary values
	 * @param decimal :int
	 * @return a list of binary values
	 * **/
	public static ArrayList<Integer> valuesFor(int decimal) { 

		ArrayList<Integer> binList = new ArrayList<Integer>();
		
		try{
			
			int i = 0;
			
			if (decimal > 1) {
				
				while (decimal > 1) {
					
					int y = decimal % 2;
					int binVal = new Double(Math.pow(2, i) * y).intValue();
					binList.add(binVal);
					decimal = decimal / 2;
					
					if (decimal < 2) {
						binVal = new Double(Math.pow(2, i + 1) * decimal)
								.intValue();
						binList.add(binVal); 
					}
					i++;
				}
				
			} else {
				binList.add(decimal);
			}

			
		}
		
		catch(Exception e){
			logger.error("--> Binary(): ERR --> "+e);
		}
		
		return binList;
	}

}


