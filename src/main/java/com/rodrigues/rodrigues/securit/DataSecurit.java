package com.rodrigues.rodrigues.securit;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataSecurit {
	
	public Boolean validateData() {
		Date data = new Date(System.currentTimeMillis()); 
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyyMMdd");
		int dataInt = Integer.parseInt(formatarDate.format(data));
		System.out.println(dataInt);
		if(dataInt > 20220328)
			return false;
		return true;
	}

}
