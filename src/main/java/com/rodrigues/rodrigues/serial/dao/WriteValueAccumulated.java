package com.rodrigues.rodrigues.serial.dao;

import com.rodrigues.rodrigues.gui.RelatorioViewController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class WriteValueAccumulated {
	private RelatorioViewController view = DependencyInjection.getRelatorioviewcontroller();
	
	
	public void write(Integer[] historySaveHC, Integer[] historySaveH1, Integer[] historySaveH2,
			Integer[] historySaveH3, Integer[] historySaveH4, Integer[] historySaveH5) {
       
		for(int i =0; i<10; i++) {
			balanca(historySaveH5, i, 5);
			balanca(historySaveH4, i, 4);
			balanca(historySaveH3, i, 3);
			balanca(historySaveH2, i, 2);
			balanca(historySaveH1, i, 1);
			balanca(historySaveHC, i, 0);
	        
	    }
	}

	private void balanca(Integer[] history, int i, int horaHist) {
		try {
			if(i == 0) {			
				if(horaHist == 5) {
					view.B1_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B1_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B1_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B1_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B1_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B1_AC.setText(Integer.toString(history[i]) );
				}
			}	
			if(i == 1) {			
				if(horaHist == 5) {
					view.B2_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B2_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B2_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B2_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B2_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B2_AC.setText(Integer.toString(history[i]) );
				}
			}
			if(i == 2) {			
				if(horaHist == 5) {
					view.B3_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B3_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B3_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B3_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B3_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B3_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 3) {			
				if(horaHist == 5) {
					view.B4_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B4_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B4_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B4_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B4_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B4_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 4) {			
				if(horaHist == 5) {
					view.B5_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B5_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B5_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B5_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B5_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B5_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 5) {			
				if(horaHist == 5) {
					view.B6_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B6_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B6_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B6_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B6_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B6_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 6) {			
				if(horaHist == 5) {
					view.B7_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B7_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B7_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B7_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B7_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B7_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 7) {			
				if(horaHist == 5) {
					view.B8_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B8_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B8_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B8_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B8_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B8_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 8) {			
				if(horaHist == 5) {
					view.B9_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B9_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B9_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B9_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B9_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B9_AC.setText(Integer.toString(history[i]) );
				}
			}
			
			if(i == 9) {			
				if(horaHist == 5) {
					view.B10_AC_5.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 4) {
					view.B10_AC_4.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 3) {
					view.B10_AC_3.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 2) {
					view.B10_AC_2.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 1) {
					view.B10_AC_1.setText(Integer.toString(history[i]) );
				}
				if(horaHist == 0) {
					view.B10_AC.setText(Integer.toString(history[i]) );
				}
			}
		}catch (NumberFormatException e ) {
			e.printStackTrace();
		}
		
	}
	

}
