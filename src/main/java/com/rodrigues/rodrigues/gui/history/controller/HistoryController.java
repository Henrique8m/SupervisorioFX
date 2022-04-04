package com.rodrigues.rodrigues.gui.history.controller;

import com.rodrigues.rodrigues.gui.history.service.HistoryService;

public class HistoryController {
	private HistoryService service = new HistoryService();

	public void startHistory() {
		service.startHistory();		
	}
	
	public void updatedValue() {
		service.updatedValue();		
	}
}
