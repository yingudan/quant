package com.ujuit.quant.utils;

import java.math.BigDecimal;

public class DataUtils {

	public static BigDecimal formatBd(BigDecimal bd) {
		if (bd == null) {
			return null;
		}
		BigDecimal thebd = bd.setScale(4,BigDecimal.ROUND_HALF_DOWN);
		int size = thebd.toString().length();
		if(size>9){
			return  new BigDecimal(thebd.toString().substring(thebd.toString().length()-9, thebd.toString().length()));
		}
		return new BigDecimal(thebd.toString());
	}
	
	public static BigDecimal formateTwo(BigDecimal bd){
		if (bd == null) {
			return null;
		}
		bd=bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return bd;
	}
	
	
	public static BigDecimal formateFour(BigDecimal bd){
		if (bd == null) {
			return null;
		}
		bd=bd.setScale(4, BigDecimal.ROUND_HALF_DOWN);
		return bd;
	}
}
