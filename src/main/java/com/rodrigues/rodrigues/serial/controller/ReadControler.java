package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.CalculatorData;

public class ReadControler {
    private SerialService service;
    private byte[] numGadgets;

    public ReadControler(SerialService service, int num){
        this.service = service;
        this.numGadgets = new byte[num];
    }

    public void read() throws InterruptedException {
        if(!(numGadgets==null)){
            for(int i=0; i < numGadgets.length; i++){
                if(service.WriteData(CalculatorData.addressRead(i+1))){
                    service.LerDados();
                    System.out.println("Teste varredura Gatgets = " + (i + 1));
                }
            }
        }
    }
}
