package com.rodrigues.rodrigues.controller;

import com.rodrigues.rodrigues.service.HistoryService;

public class HistoryController {
	private HistoryService service = new HistoryService();

	public void startHistory() {
		service.startHistory();		
	}
	
	public void updatedValue() {
		service.updatedValue();		
	}
}
