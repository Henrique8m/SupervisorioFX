package com.rodrigues.rodrigues.gui.history.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class HistoryService implements Runnable {
	
	public static String[] newBalancas = new String[10];
	public String[] oldBalancas = new String[10];
	
	private Integer[] historySaveH1 = new Integer[10];
	private Integer[] historySaveH2 = new Integer[10];
	private Integer[] historySaveH3 = new Integer[10];
	private Integer[] historySaveH4 = new Integer[10];
	private Integer[] historySaveH5= new Integer[10];
	
	private Boolean[] auxSave = new Boolean[10];
	private Boolean auxTrocaHorario = false;

	private SimpleDateFormat formatarTime = new SimpleDateFormat("HHmm");
	private Integer time;
	private Integer nextTime=0;
	private Date data;
	
	private Thread thread = new Thread(this);

	public void startHistory() {
		
		if(!thread.isAlive()){
			thread.start();
		}	
		else {
			thread.resume();
		}
	}

	@Override
	public void run() {
		while(true) {			
	        for(int i=0; i<newBalancas.length; i++ ) {
	        	oldBalancas[i] = toCompare(newBalancas[i].replaceAll("[^0-9]+", ""),oldBalancas[i], i);
			}
	        data = new Date(System.currentTimeMillis()); 			
	        time = Integer.parseInt(formatarTime.format(data));
	        
	        System.out.print(time);
	        
	        if(time>nextTime) {
	        	
		        for(Integer x : historySaveH5) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH4) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH3) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH2) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH1) {
		        	System.out.print(x + " - ");
		        }
	        	
	        	
	        	historySaveH5 = historySaveH4;
	        	historySaveH4 = historySaveH3;
	        	historySaveH3 = historySaveH2;
	        	historySaveH1 = null;
	       
		        for(Integer x : historySaveH5) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH4) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH3) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH2) {
		        	System.out.print(x + " - ");
		        }
		        System.out.println();
		        for(Integer x : historySaveH1) {
		        	System.out.print(x + " - ");
		        }
			}
		}
	}

	private String toCompare(String newValue, String oldValue, int i) {
		if(auxSave[i] == null) auxSave[i]=true;
		try {
			Integer oldValueInt = Integer.parseInt(oldValue);
			Integer newValueInt = Integer.parseInt(newValue);
			
			if(newValueInt > (oldValueInt - 20)) {
				auxSave[i] = true;
				return Integer.toString(newValueInt);
				
			}else if(newValueInt < (oldValueInt - 20)&& auxSave[i]) {
				historySaveH1[i] += oldValueInt;
				auxSave[i] = false;
			}
		}catch(NumberFormatException e) {
			//Não para o software seu merda
			return oldValue;
		}catch(NullPointerException e) {
			
		}

		return oldValue;
	}
}





























