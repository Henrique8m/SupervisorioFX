package com.rodrigues.rodrigues.controller;

import com.rodrigues.rodrigues.service.LineChartService;

public class LineChartController {

	private LineChartService service = new LineChartService();
	
	public void lineChartStart() {		
		service.beginTimer();
	}

	public void lineChartStop() {
		service.cancelTimer();		
	}

}
