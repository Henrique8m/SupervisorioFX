package com.rodrigues.rodrigues.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.comm.CommPortIdentifier;
import javax.comm.UnsupportedCommOperationException;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.controller.HistoryController;
import com.rodrigues.rodrigues.controller.LineChartController;
import com.rodrigues.rodrigues.controller.SerialController;
import com.rodrigues.rodrigues.entities.Balancas;
import com.rodrigues.rodrigues.entities.Pirometro;
import com.rodrigues.rodrigues.gui.util.Alerts;
import com.rodrigues.rodrigues.securit.DataSecurit;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.EndGadgets;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryViewController implements Initializable {
	
	private HistoryController historyController = new HistoryController();
	private LineChartController chartController = new LineChartController();
	private RelatorioViewController viewController;
	private static native int Read(int var0, long var1, int var3, int var4, byte[] var5);

	private static final Object defautPort = "COM4";
	private String lastPort = "";
	private SerialController serialController;
	private SerialProperties serialProperties;
	
	private DataSecurit securit;

	private List<String> avaliablePorts;
	
	public String balancaName;	

	@FXML
	public Text cf1, cf2, cf3;
	@FXML
	public Text cm1, cm2, cm3;
	@FXML
	public Text cq1, cq2, cq3;
	@FXML
	public Text s1, s2, s3;
	@FXML
	public Text topoE, topoD, coroaE, coroaD;
	@FXML
	public Text topoE1, topoD1, coroaE1, coroaD1;
	@FXML
	public Text pcoroa1, ptopo1;
	@FXML
	public Text pcoroa, ptopo;
	@FXML
	public Text vazao, vazao1;
	@FXML
	public Text psm, psm1;
	@FXML
	public Text pirometro, pirometro1;
	@FXML
	public Text txLog, txLog1;
	@FXML
	public Text Balanca01, Balanca02, Balanca03, Balanca04, Balanca05;
	@FXML
	public Text Balanca06, Balanca07, Balanca08, Balanca09, Balanca10;
	@FXML
	public LineChart<String, Double> lineChart;
	@FXML
	private TableColumn<Pirometro, String> dataTime;
	@FXML
	private TableColumn<Pirometro, String> temp;
	@FXML
	private TableView<Pirometro> table = new TableView<Pirometro>();
	
    public static ObservableList<Pirometro> obsListTableView;
    
    //////////////////////////////////////////////////////////////
    //table Balance
    
    @FXML
    private TableColumn<Balancas, String> data, hInicio, hFim, b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    @FXML
    private TableView<Balancas> tableBalancas;
    
    ///////////////////////////////////////////////////////////////

	@FXML
	private void view1(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		ScrollPane scrollPane= (ScrollPane) UtilitarioNewView.loadFXML("relatorioView", viewController);
		Scene main = new Scene(scrollPane);
		viewController.setStage(UtilitarioNewView.getNewView("Relatorio Das Balanças",main));
		
	}
	@FXML
	private void view2(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		ScrollPane scrollPane= (ScrollPane) UtilitarioNewView.loadFXML("relatorioView2", viewController);
		Scene main = new Scene(scrollPane);
		viewController.setStage(UtilitarioNewView.getNewView("Relatorio Das Balanças",main));
		
	}
	
	@FXML
	private void comPort(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("propertiesCom", null, "Status License", MainApp.getStage(),
				DependencyInjection.getCheckLicenseController());
	}
	
	@FXML
	private void checkLicense(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("checkLicense", null, "Status License", MainApp.getStage(),
				DependencyInjection.getCheckLicenseController());
	}

	@FXML
	public void stopComunication(ActionEvent event) throws UnsupportedCommOperationException, IOException {

		if (securit.validateData()) {
			serialController.stopCommunication();
			chartStop();
			txLog.setText("Comunication Stop");
			txLog1.setText("Comunication Stop");
		} else {
			showError();
		}
	}
	public void chartStop() {
		chartController.lineChartStop();
		
	}
	@FXML
	private void startComunication(ActionEvent event) throws UnsupportedCommOperationException, InterruptedException {
		if (securit.validateData()) {
			serialController.startCommunication();
			chartController.lineChartStart();
		} else {
			showError();
		}
	}
	
	@FXML
	private void SetPointB1(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_01.getEnd());
		balancaName = "Balança 1";
		loadView("setPointConfigView", null, "Configuração de Set Points B1", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB2(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_02.getEnd());
		balancaName = "Balança 2";
		loadView("setPointConfigView", null, "Configuração de Set Points B2", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB3(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_03.getEnd());
		balancaName = "Balança 3";
		loadView("setPointConfigView", null, "Configuração de Set Points B3", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB4(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_04.getEnd());
		balancaName = "Balança 4";
		loadView("setPointConfigView", null, "Configuração de Set Points B4", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB5(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_05.getEnd());
		balancaName = "Balança 5";
		loadView("setPointConfigView", null, "Configuração de Set Points B5", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB6(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_06.getEnd());
		balancaName = "Balança 6";
		loadView("setPointConfigView", null, "Configuração de Set Points B6", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB7(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_07.getEnd());
		balancaName = "Balança 7";
		loadView("setPointConfigView", null, "Configuração de Set Points B7", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB8(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_08.getEnd());
		balancaName = "Balança 8";
		loadView("setPointConfigView", null, "Configuração de Set Points B8", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB9(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_09.getEnd());
		balancaName = "Balança 9";
		loadView("setPointConfigView", null, "Configuração de Set Points B9", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	@FXML
	private void SetPointB10(MouseEvent event) throws UnsupportedCommOperationException, IOException {
		serialController.readController.setEndReadSetPoints(EndGadgets.Balança_10.getEnd());
		balancaName = "Balança 10";
		loadView("setPointConfigView", null, "Configuração de Set Points B10", MainApp.getStage(),
				DependencyInjection.getSetPointController());
		
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		instanciates();
		DependencyInjection.setPrimaryViewController(this);
		if(securit.validateData()) {		
			
			avaliablePorts = new ArrayList<>();
			@SuppressWarnings("unchecked")
			Enumeration<CommPortIdentifier> enume = CommPortIdentifier.getPortIdentifiers();		
			while(enume.hasMoreElements()) {
				avaliablePorts.add(enume.nextElement().getName());		
			}
			DependencyInjection.setAvaliablePortsNames(avaliablePorts);
			
			for(String e : avaliablePorts)
				if(e.equals(defautPort))
					serialProperties.setPorta("COM4");
				else if(e.equals(lastPort))
				serialProperties.setPorta(lastPort);
			
			serialController.startCommunication();
		}else {
			showError();
		}
				
		chartController.lineChartStart();
		historyController.startHistory();
		
		strartTablePirometro();
				
	}



	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction, String title,
			Stage stageEvent, Object controller) {
		try {
			UtilitarioNewView.getNewViewModal(title, (Pane) UtilitarioNewView.loadFXML(absoluteName, controller),
					stageEvent);
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
		}
	}

	private void instanciates() {
		if (serialController == null)
			serialController = DependencyInjection.getSerialController();
		if (serialProperties == null)
			serialProperties = DependencyInjection.getSerialProperties();
		if (securit == null)
			securit = DependencyInjection.getDataSecurit();
		if(viewController ==null)
			viewController = DependencyInjection.getRelatorioviewcontroller();
	}

	private void showError() {
		Alerts.showAlert("Securit", "Error, validação da licença ",
				"Erro ao validar a licença, entre em contato com o adim", AlertType.ERROR);
	}

	
	
	
	@SuppressWarnings("unchecked")
	public void strartTablePirometro() {
		table.setMinWidth(225);
		
			
		obsListTableView =
	            (ObservableList<Pirometro>) FXCollections.observableArrayList(
	            		new Pirometro("1530","09/04/1994 - 08:40"));
	    
	   table.setEditable(false);
	 
       dataTime = new TableColumn<Pirometro, String>("Data Time");
        
       dataTime.setMinWidth(125); 
       dataTime.setSortType(TableColumn.SortType.DESCENDING);
       dataTime.setCellValueFactory(
                new PropertyValueFactory<Pirometro, String>("dataTime"));

 
        temp = new TableColumn<Pirometro, String>("Temperatura");
        temp.setMinWidth(100);
        temp.setCellValueFactory(
                new PropertyValueFactory<Pirometro, String>("temp"));

        table.setItems(obsListTableView);
        table.getColumns().addAll(dataTime, temp);
 
	}
       
}


