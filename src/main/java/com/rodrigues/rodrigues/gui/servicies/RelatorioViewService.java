package com.rodrigues.rodrigues.gui.servicies;

import com.rodrigues.rodrigues.entities.Balancas;
import com.rodrigues.rodrigues.entities.Pyrometry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RelatorioViewService {
	private static ObservableList<Balancas> listBalancas = FXCollections.observableArrayList();
	private static ObservableList<Pyrometry> listPyrometry = FXCollections.observableArrayList();
	
	public static ObservableList<Balancas> getListBalancas() {
		return listBalancas;
	}
	
	public static ObservableList<Pyrometry> getListPyrometry() {
		return listPyrometry;
	}
	
	public static void addListBalancas(Balancas balancas) {
		listBalancas.add(balancas);
	}
	
	public static void addListPyrometry(Pyrometry pyrometry) {
		listPyrometry.add(pyrometry);
	}
}
