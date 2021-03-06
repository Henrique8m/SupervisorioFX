package com.rodrigues.rodrigues.relatorio.servicies;

import com.rodrigues.rodrigues.entities.Balancas;
import com.rodrigues.rodrigues.entities.Carvao;
import com.rodrigues.rodrigues.entities.Pirometro;
import com.rodrigues.rodrigues.entities.Pyrometry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RelatorioService {
	private static ObservableList<Balancas> listBalancas = FXCollections.observableArrayList();
	private static ObservableList<Pyrometry> listPyrometry = FXCollections.observableArrayList();
	private static ObservableList<Carvao> listCarvao = FXCollections.observableArrayList();
    public static ObservableList<Pirometro> listPirometro = FXCollections.observableArrayList();
	
	public static ObservableList<Balancas> getListBalancas() {
		return listBalancas;
	}
	
	public static ObservableList<Pyrometry> getListPyrometry() {
		return listPyrometry;
	}
	
	public static ObservableList<Carvao> getListCarvao() {
		return listCarvao;
	}
	
	public static ObservableList<Pirometro> getListPirometro() {
		return listPirometro;
	}
	
	public static void addListBalancas(Balancas balancas) {
		listBalancas.add(balancas);
	}
	
	public static void addListPyrometry(Pyrometry pyrometry) {
		listPyrometry.add(pyrometry);
	}
	
	public static void addListCarvao(Carvao carvao) {
		listCarvao.add(carvao);
	}
	
	public static void addListPirometro(Pirometro pirometro) {
		listPirometro.add(pirometro);
	}
}
