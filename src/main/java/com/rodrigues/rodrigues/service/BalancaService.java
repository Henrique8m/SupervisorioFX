package com.rodrigues.rodrigues.service;

import com.rodrigues.rodrigues.entities.Balancas;
import com.rodrigues.rodrigues.relatorio.servicies.RelatorioService;

public class BalancaService {
	private Balancas balanca;


	public void newBalancaData(Integer[] historySaveHC, String timeStart, String time, String currentDate) {
		
		balanca = new Balancas();
		balanca.setBalanca01(Integer.toString(historySaveHC[0]));
		balanca.setBalanca02(Integer.toString(historySaveHC[1]));
		balanca.setBalanca03(Integer.toString(historySaveHC[2]));
		balanca.setBalanca04(Integer.toString(historySaveHC[3]));
		balanca.setBalanca05(Integer.toString(historySaveHC[4]));
		balanca.setBalanca06(Integer.toString(historySaveHC[5]));
		balanca.setBalanca07(Integer.toString(historySaveHC[6]));
		balanca.setBalanca08(Integer.toString(historySaveHC[7]));
		balanca.setBalanca09(Integer.toString(historySaveHC[8]));
		balanca.setBalanca10(Integer.toString(historySaveHC[9]));
		balanca.setDate(currentDate);
		balanca.setHInicio(timeStart);
		balanca.setHFim(time);	
		RelatorioService.addListBalancas(balanca);
		
	}
}
