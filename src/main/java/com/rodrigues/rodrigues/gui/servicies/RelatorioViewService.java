package com.rodrigues.rodrigues.gui.servicies;

import com.rodrigues.rodrigues.entities.Balancas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RelatorioViewService {
	private static ObservableList<Balancas> listBalancas = FXCollections.observableArrayList();
	
	public ObservableList<Balancas> getListBalancas() {
		return listBalancas;
	}

	public void setListBalancas(ObservableList<Balancas> listBalancas) {
		RelatorioViewService.listBalancas = listBalancas;
	}
	
	public void addList(Balancas balancas) {
		listBalancas.add(balancas);
	}
}
