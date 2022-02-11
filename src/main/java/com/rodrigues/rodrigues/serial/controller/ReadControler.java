package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.gui.FXMLController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.CalculatorData;

public class ReadControler implements Runnable{

    private final SerialProperties properties = new SerialProperties("COM4");
    private FXMLController fxmlController;
    private SerialService service;
    private SerialController controller;

    private int lostConection = 0;
    private int attemptToReconnect = 5;
    private byte[] numGadgets;
    private byte[] bufferRead= new byte[8];

    private Thread thread = new Thread(this);



    public ReadControler(int num, FXMLController fxmlController, SerialController controller){
        this.numGadgets = new byte[num];
        this.fxmlController = fxmlController;
        this.controller = controller;
    }

    public void read() throws InterruptedException {if(!thread.isAlive()){thread.run();}}

    //Nova thered para não travar o programa quando estiver na tentativa de uma nova leitura
    @Override
    public void run() {
        //numero de aparelhos nao pode ser nulo
        if(numGadgets!=null){
            //faz a varredura nos aparelos em ordem crescente
            for(int i=0; i < numGadgets.length; i++){
                sweep(i+1);

            }
        }
        thread.stop();
    }

    //Varredura dos aparelhos
    private void sweep(int i) {
        try{
            service = new SerialService(properties.getPorta(), properties.getBaud(),properties.getTimeout());
            bufferRead = CalculatorData.addressRead(i);

            if(service.getPortIdentifier()) {
                service.openPort();
                service.writeData(bufferRead);
                Thread.sleep(100);
                service.readData();
                Thread.sleep(300);
                indicadores(i);
            }
            //em caso de erro na coneção, vamos tentar algumas vezes e depois cancelar a cenecção
            else{lostConection++; if(lostConection >= attemptToReconnect)thread.interrupt(); controller.timerCancel();}

        }catch(Exception ex) {System.out.println("Erro na leitura do aparelho de endereço = " + (i) + "STATUS: ");
            ex.printStackTrace();}
    }

    //Ate desenvolver algo melhor
    //essa vai ser a varredura dos aparelhos
    private void indicadores(int i) {
        if(service.getDisplay()!=null){
            String display = service.getDisplay();
            if(i==1){fxmlController.cq1.setText(display);}
            if(i==2){ fxmlController.cq2.setText(display);}
            if(i==3){ fxmlController.cq3.setText(display);}
            if(i==4){ fxmlController.s1.setText(display);}
            if(i==5){ fxmlController.s2.setText(display);}
            if(i==6){ fxmlController.s3.setText(display);}
            if(i==7){ fxmlController.topoE.setText(display);}
            if(i==7){ fxmlController.topoD.setText(display);}
            if(i==8){ fxmlController.coroaE.setText(display);}
            if(i==8){ fxmlController.coroaD.setText(display);}
            if(i==9){ fxmlController.ptopo.setText(display);}
            if(i==10){ fxmlController.vazao.setText(display);}
            if(i==11){ fxmlController.psm.setText(display);}
        }
    }
}
