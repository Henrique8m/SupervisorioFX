package com.rodrigues.rodrigues.securit;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SerialMotherboard {

	private static final String command = "wmic baseboard get serialnumber";
	private String serialNumber = null;
	
	public String getSerialMotherboard() {
        try {
        	Process SerialNumberProcess = Runtime.getRuntime().exec(command);
        	InputStreamReader ISR = new InputStreamReader(SerialNumberProcess.getInputStream());
        	BufferedReader br = new BufferedReader(ISR);
        	String items = br.readLine();
    		
			while(items != null) {
				
				if(findForFistInt(items) != null)
					serialNumber = items;	
				
				items = br.readLine();
			}	
		  	
        	SerialNumberProcess.waitFor();
        	br.close();
        }catch (Exception e) {
        	e.printStackTrace();
        	serialNumber = null;
        }
        return serialNumber;
	}
	
	 public String findForFistInt(String str) {
		 
		 for(int i=0; i<= 9; i++) {
			 String iStr = Integer.toString(i);	
			 
			 if(str.contains(iStr))
				return str;
		}
		 
		 return null;
	 }
}
