package com.rodrigues.rodrigues.controller;

import com.rodrigues.rodrigues.service.MediaPirometriaService;

public class MediaPirometriaController {
	private MediaPirometriaService service = new MediaPirometriaService();
	
	public void startMedia() {
		service.startMedia();
	}

}
