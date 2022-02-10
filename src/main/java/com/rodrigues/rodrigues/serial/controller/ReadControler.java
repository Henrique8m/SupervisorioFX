package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.CalculatorData;

public class ReadControler implements Runnable{
    private SerialService service;
    private byte[] numGadgets;
    private byte[] bufferRead= new byte[8];
    private Thread thread = new Thread(this);

    public ReadControler(SerialService service, int num){
        this.service = service;
        this.numGadgets = new byte[num];
    }

    public void read() throws InterruptedException {if(!thread.isAlive()){thread.run();}}

    @Override
    public void run() {
        System.out.println(thread.isAlive() + "  " +  thread.getName());
        if(numGadgets!=null){
            for(int i=0; i < numGadgets.length; i++){
                try{
                    bufferRead = CalculatorData.addressRead(i+1);
                    Thread.sleep(100);
                    service.getPortIdentifier();
                    service.openPort();
                    service.writeData(bufferRead);
                    Thread.sleep(100);
                    service.readData();
                    System.out.println("Teste varredura Gatgets = " + (i + 1));
                }catch(Exception ex) {System.out.println("Erro na leitura do aparelho de endereço = " + (i+1) + "STATUS: ");
                ex.printStackTrace();}
            }
        }
        thread.stop();
    }
}
