package com.rodrigues.rodrigues.securit;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataSecurit {
	private Integer dataSpired = 20220405;
	
	public Boolean validateData() {
		Date data = new Date(System.currentTimeMillis()); 
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyyMMdd");
		int dataInt = Integer.parseInt(formatarDate.format(data));
		//System.out.println(dataInt);
		if(dataInt > dataSpired)
			return false;
		return true;
	}
	
	public Integer getSpiredData() {
		return dataSpired;
	}

}
