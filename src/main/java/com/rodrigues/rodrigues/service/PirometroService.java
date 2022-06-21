package com.rodrigues.rodrigues.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.rodrigues.rodrigues.entities.Pirometro;
import com.rodrigues.rodrigues.gui.servicies.RelatorioViewService;

public class PirometroService {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy - HH:mm");
	private static Date date;
	
	public static void addTempList(int temp) {
		date = new Date(System.currentTimeMillis());
		String data = dateFormat.format(date);
		//System.out.println(data);
		String strTemp;
		try {
			strTemp = Integer.toString(temp);
			RelatorioViewService.addListPirometro(new Pirometro(strTemp, data));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}

	}

}
