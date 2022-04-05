package com.rodrigues.rodrigues.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.rodrigues.rodrigues.gui.RelatorioViewController;
import com.rodrigues.rodrigues.serial.dao.WriteValueAccumulated;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class HistoryService implements Runnable {
	private WriteValueAccumulated accumulated = new WriteValueAccumulated();
	
	
	public static String[] newBalancas = new String[10];
	public String[] oldBalancas = new String[10];
	
	private Integer[] historySaveHC = new Integer[10];
	private Integer[] historySaveH1 = new Integer[10];
	private Integer[] historySaveH2 = new Integer[10];
	private Integer[] historySaveH3 = new Integer[10];
	private Integer[] historySaveH4 = new Integer[10];
	private Integer[] historySaveH5= new Integer[10];
	
	//
	private Integer auxCarga, carga;
	private Boolean carvaoPassou;
	
	private Boolean[] auxSave = new Boolean[10];
	private Boolean auxTrocaHorario = false;

	private SimpleDateFormat formatarTime = new SimpleDateFormat("HHmm");
	private Integer time;
	private Integer nextTime=0;
	private Date data;
	
	
	private int bordaDeSubida = 200;//k/10
	private int bordaDeDescida = 200;
	private int balancaVazia = 100;
	
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
			
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i=0; i<newBalancas.length; i++ ) {
	        	if((newBalancas[i]!=null)&&(newBalancas[i]!="Error") ) {
	        		
	        		/*
	        		System.out.println("New Balancas["+ i + "] é diferente de null");
	        		System.out.println("New Balancas["+ i + "] = "+ newBalancas[i]);
        			System.out.println("New Balancas["+ i + "] = "+ newBalancas[i].replaceAll("[^0-9]+", ""));
        			System.out.println("Old Balancas["+ i + "] = " + oldBalancas[i]);
        			System.out.println("Old Balancas["+ i + "] Depois do compare= " + oldBalancas[i]);
	        		System.out.println();
        			*/ 
	        		oldBalancas[i] = toCompare(newBalancas[i].replaceAll("[^0-9]+", ""),oldBalancas[i], i);

	        	}
        	}

	        data = new Date(System.currentTimeMillis()); 			
	        time = Integer.parseInt(formatarTime.format(data));
	        
	        try {
		        if((time>=nextTime)&&auxTrocaHorario&&(carvaoPassou)) {
		        	System.out.print(time);
		        	System.out.println();   
		        	System.out.println();
		        	
			        for(int i=0; i< historySaveHC.length; i++) {
			        	
			        	if(historySaveH4[i]==null)historySaveH5[i] = 0;
			        	else historySaveH5[i] = historySaveH4[i].intValue();
			        	
			        	if(historySaveH3[i]==null)historySaveH4[i] = 0;
			        	else historySaveH4[i] = historySaveH3[i].intValue();
			        	
			        	if(historySaveH2[i]==null)historySaveH3[i] = 0;
			        	else historySaveH3[i] = historySaveH2[i].intValue();
			        	
			        	if(historySaveH1[i]==null)historySaveH2[i] = 0;
			        	else historySaveH2[i] = historySaveH1[i].intValue();
			        	
			        	if(historySaveHC[i]==null) {
			        		historySaveH1[i] = 0;
			        		historySaveHC[i] = 0;
			        	}
			        	else {
			        		historySaveH1[i] = historySaveHC[i].intValue();
			        		historySaveHC[i] = 0;
			        	}
			        }
		        	
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
			        System.out.println();
			        for(Integer x : historySaveHC) {
			        	System.out.print(x + " - ");
			        }
			        System.out.println();
		        
			        System.out.println();
			        System.out.println("Fim");
			        System.out.println();
			        if(time>=2300)
			        	nextTime = 0;
			        nextTime = time + 100;
			        
			        
			        
			        //
			        carga = auxCarga;
			        auxCarga=0;
			        
			        
			        
			        auxTrocaHorario=false;
			        
				}else if(auxTrocaHorario==false){
					//System.out.println("Else");
					auxTrocaHorario=true;
				}
	        }catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	private String toCompare(String newValue, String oldValue, int i) {
		if(auxSave[i] == null) auxSave[i]=true;
		
		if(oldValue == null) oldValue = "0";
		if(oldValue == null) newValue = "0";
		
		Integer oldValueInt = null;
		Integer newValueInt = null;
		
		try {
			oldValueInt = Integer.parseInt(oldValue);
			newValueInt = Integer.parseInt(newValue);
		}catch(NumberFormatException e) {
			System.out.println("NumberFormatException in HistoryService");
		}catch(NullPointerException e) {
			System.out.println("NullPoint in HistoryService");
		}	
		
		try {
		if(newValueInt > (oldValueInt + bordaDeSubida)&&auxSave[i]) {
			auxSave[i] = true;
			System.out.println("Retornando " + newValueInt);
			return Integer.toString(newValueInt);
			
		}else if(newValueInt < (oldValueInt - bordaDeDescida)&& auxSave[i]) {
			
			if(historySaveHC[i]==null) historySaveHC[i] = 0;
			
			historySaveHC[i] += oldValueInt;
			
			System.out.println("Salvando no historico " + oldValueInt + "\n Historico depois de Salvar " + historySaveHC[i]);
			auxSave[i] = false;
		}
		if((auxSave[i]==false)&&(newValueInt<balancaVazia)) {
			auxSave[i] = true;
			
			
			//
			if(i==9) {
				auxCarga++;
			}
			
			
			
			
			//System.out.println("Retornando " + newValueInt +  " Para iniciar uma nova era");
			return Integer.toString(200);
		}
		}catch(NullPointerException e ) {
			System.out.println("NullPoint in HistoryService no if");
			e.printStackTrace();
		}


		return oldValue;
	}

	public void updatedValue() {
		accumulated.write(historySaveHC, historySaveH1, historySaveH2, historySaveH3, historySaveH4, historySaveH5);
		
	}
}





























