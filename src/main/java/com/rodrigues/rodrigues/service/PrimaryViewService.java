package com.rodrigues.rodrigues.service;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.gui.SetPointController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class PrimaryViewService{
	
    private PrimaryViewController primaryViewController;
    private SetPointController setPointController;
      
	
	public void writeText(int end, String display) {
		instanciates();
		
		
		try {        
            if(end==1){primaryViewController.cq1.setText(display);}
            if(end==2){ primaryViewController.cq2.setText(display);}
            if(end==3){ primaryViewController.cq3.setText(display);}
            
            if(end==4){ primaryViewController.cm1.setText(display);}
            if(end==5){ primaryViewController.cm2.setText(display);}
            if(end==6){ primaryViewController.cm3.setText(display);}
            
            if(end==7){ primaryViewController.s1.setText(display);}
            if(end==8){ primaryViewController.s2.setText(display);}
            if(end==9){ primaryViewController.s3.setText(display);}
            
            if(end==10){ primaryViewController.topoE.setText(display); 
            			primaryViewController.topoE1.setText(display);}
            
            if(end==11){ primaryViewController.topoD.setText(display);
            			primaryViewController.topoD1.setText(display);}
            
            if(end==13){ primaryViewController.coroaE.setText(display);
            			primaryViewController.coroaE1.setText(display);}
            
            if(end==14){ primaryViewController.coroaD.setText(display);
            			primaryViewController.coroaD1.setText(display);}
            
            if(end==12){ primaryViewController.ptopo.setText(display);
            			primaryViewController.ptopo1.setText(display);}
            
            if(end==15){ primaryViewController.pcoroa.setText(display);
            			primaryViewController.pcoroa1.setText(display);}
            
            if(end==17){ primaryViewController.vazao.setText(display);
            			primaryViewController.vazao1.setText(display);}
            
            if(end==18){ primaryViewController.psm.setText(display);
            			primaryViewController.psm1.setText(display);}
            
            if(end==16){ primaryViewController.pirometro.setText(display);
            			primaryViewController.pirometro1.setText(display);}
            
            if(end==19){ primaryViewController.Balanca01.setText(display);
            HistoryService.newBalancas[0] = display;}
            
            if(end==20){ primaryViewController.Balanca02.setText(display);
            HistoryService.newBalancas[1] = display;}
            
            if(end==21){ primaryViewController.Balanca03.setText(display);
            HistoryService.newBalancas[2] = display;}
            
            if(end==22){ primaryViewController.Balanca04.setText(display);
            HistoryService.newBalancas[3] = display;}
            
            if(end==23){ primaryViewController.Balanca05.setText(display);
            HistoryService.newBalancas[4] = display;}
            
            if(end==24){ primaryViewController.Balanca06.setText(display);
            HistoryService.newBalancas[5] = display;}
            
            if(end==25){ primaryViewController.Balanca07.setText(display);
            HistoryService.newBalancas[6] = display;}
            
            if(end==26){ primaryViewController.Balanca08.setText(display);
            HistoryService.newBalancas[7] = display;}
            
            if(end==27){ primaryViewController.Balanca09.setText(display);
            HistoryService.newBalancas[8] = display;}
            
            if(end==28){ primaryViewController.Balanca10.setText(display);
            HistoryService.newBalancas[9] = display;}
            
            

    	}catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public void writeTextSetPoints(int balança, String[] setPoints) {
		
			setPointController.setPointVazia.setText(setPoints[0]);
			setPointController.setPoint01.setText(setPoints[1]);
			setPointController.setPoint02.setText(setPoints[2]);
			setPointController.setPoint03.setText(setPoints[3]);
		
		
	}
	
	private void instanciates() {
		if(primaryViewController == null)primaryViewController = DependencyInjection.getPrimaryViewController();
		if(setPointController == null)setPointController = DependencyInjection.getSetPointController();
	}

}
