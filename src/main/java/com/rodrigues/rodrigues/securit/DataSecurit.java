package com.rodrigues.rodrigues.securit;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.rodrigues.rodrigues.MainApp;

public class DataSecurit {
	
	public Boolean validateData() {
		Date data = new Date(System.currentTimeMillis()); 
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyyMMdd");
		int dataInt = Integer.parseInt(formatarDate.format(data));
		//System.out.println(dataInt);
		if(dataInt > MainApp.dataSpired)
			return false;
		return true;
	}

}
