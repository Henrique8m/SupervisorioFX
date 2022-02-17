package com.rodrigues.rodrigues.serial.properties;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.rodrigues.rodrigues.MainApp;

public class SerialProperties {
    private String porta;
    private int baud = 9600;
    private int timeout = 500;
    private int stopBits = 1;
    private String paridade = "None";
    
    
    public SerialProperties(String porta){
        this.porta = porta;
    }

    public SerialProperties(){
		String pathToProperties = null;
    	try {
    		pathToProperties = MainApp.class.getResource("Test.csv").getPath().toString();
    	}catch(NullPointerException e) {
    		System.out.println(MainApp.class.getResource(MainApp.class.getSimpleName() + ".java").getPath().toString() );
    		/*
    		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(MainApp.class.getPackageName()))){
    			for(ProductFile x : controler.reader) {
    				bw1.write(x.toString());
    				bw1.newLine();
    			}
    		}
    		catch (IOException e ) {
    			e.printStackTrace();
    		}
    		*/
    		
    	}
    	try(BufferedReader br = new BufferedReader(new FileReader(pathToProperties) ) ){
			
		String itemsCsv = br.readLine();

			while(itemsCsv != null) {				
				String[] line = itemsCsv.split(",");
				System.out.println(itemsCsv);								
				itemsCsv = br.readLine();
			}	
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	
    	/*
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(path1))){
			for(ProductFile x : controler.reader) {
				bw1.write(x.toString());
				bw1.newLine();
			}
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		*/
	}

	public String getPorta() {
        return porta;
    }

    public int getBaud() {
        return baud;
    }

    public int getTimeout() {
        return timeout;
    }
    
    public int getStopBits() {
        return stopBits;
    }

	public String getParidade() {
		return paridade;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}
	
}
