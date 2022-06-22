package com.rodrigues.rodrigues.gui.controller;

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
import com.rodrigues.rodrigues.controller.MediaPirometriaController;
import com.rodrigues.rodrigues.controller.SerialController;
import com.rodrigues.rodrigues.db.DB;
import com.rodrigues.rodrigues.entities.Balancas;
import com.rodrigues.rodrigues.entities.Carvao;
import com.rodrigues.rodrigues.entities.Pirometro;
import com.rodrigues.rodrigues.entities.Pyrometry;
import com.rodrigues.rodrigues.gui.util.Alerts;
import com.rodrigues.rodrigues.relatorio.GeneratorPDF;
import com.rodrigues.rodrigues.relatorio.servicies.RelatorioService;
import com.rodrigues.rodrigues.securit.DataSecurit;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.EndGadgets;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryViewController implements Initializable {
	
	private HistoryController historyController = new HistoryController();
	private LineChartController chartController = new LineChartController();
	private MediaPirometriaController mediaPirometria = new MediaPirometriaController();
	private RelatorioViewController viewController;
	private static native int Read(int var0, long var1, int var3, int var4, byte[] var5);

	
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
	public ImageView pdf;
  
///////////////////////////////////////////////////////////////////////////////Butons////////////////////////////////////////////////////////////////
    
	@FXML
	private void gerarRelatorio(ActionEvent event) {
		if( RelatorioService.getListBalancas().size() > 0) {
			ObservableList<Balancas> obsList = RelatorioService.getListBalancas();
			List<Balancas> list = new ArrayList<>();
			list.addAll(obsList);
			GeneratorPDF.newDocument(list);
		}
		if( RelatorioService.getListPyrometry().size() > 0) {
			ObservableList<Pyrometry> obsList = RelatorioService.getListPyrometry();
			List<Pyrometry> list = new ArrayList<>();
			list.addAll(obsList);
			GeneratorPDF.newDocument(list);
		}
		if( RelatorioService.getListCarvao().size() > 0) {
			ObservableList<Carvao> obsList = RelatorioService.getListCarvao();
			List<Carvao> list = new ArrayList<>();
			list.addAll(obsList);
			GeneratorPDF.newDocument(list);
		}
		if( RelatorioService.getListPirometro().size() > 0) {
			ObservableList<Pirometro> obsList = RelatorioService.getListPirometro();
			List<Pirometro> list = new ArrayList<>();
			list.addAll(obsList);
			GeneratorPDF.newDocument(list);
		}
	}
	
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
	

/////////////////////////////////////////////////////////////////////////////// Metodos ////////////////////////////////////////////////////////////////
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		timerSecurit();
		instanciates();
		DependencyInjection.setPrimaryViewController(this);
		if(serialProperties.getPorta() == null ) {
			
			avaliablePorts = new ArrayList<>();
			@SuppressWarnings("unchecked")
			Enumeration<CommPortIdentifier> enume = CommPortIdentifier.getPortIdentifiers();		
			while(enume.hasMoreElements()) {
				avaliablePorts.add(enume.nextElement().getName());		
			}
			DependencyInjection.setAvaliablePortsNames(avaliablePorts);
			
			for(String e : avaliablePorts)
				if(e.equals(MainApp.defautPort) || !e.equals("COM1"))
					serialProperties.setPorta(e);
			serialProperties.writeFile();
		}
			
		serialController.startCommunication();

		
		//mediaPirometria.startMedia();
		chartController.lineChartStart();
		historyController.startHistory();
		
		alimentarTabelaTestes();
		Image image = new Image(MainApp.class.getResource("gui/resources/icons-pdf.png").toString() );
		pdf.setImage(image);
		DB.getConnection();	
		
	} 
	
	private Timeline timeline;
	
	private void timerSecurit() {
		
		timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(10), ev -> {			
			if(!securit.validateData()) {
				view();				
			}
		}));	
		timeline.setCycleCount(Animation.INDEFINITE); 
		timeline.play(); 
	}
	
	
	private void view() {		
		try {
			Pane pane = (Pane) UtilitarioNewView.loadFXML("checkLicense", new CheckLicenseController() );			
			MainApp.getStage().close();			
			UtilitarioNewView.getNewView("checkLicense",  new Scene(pane));
			timeline.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
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
/////////////////////////////////////////////////////////////////////////////// table Balance and pyrometry ////////////////////////////////////////////////////////////////
	
	
	@FXML
	private TableColumn<Pirometro, String> dataTime, temp;
	@FXML
	private TableView<Pirometro> tablePirometro;	
	
    @FXML
    private TableColumn<Balancas, String> data, hInicio, hFim, b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    @FXML
    private TableView<Balancas> tableBalancas;
    
    @FXML
    private TableColumn<Pyrometry, String> timeStartFinish, pressaoCoroa, pressaoTopo, tempCoroa, tempTopo,vazaoAr , secador;
    @FXML
    private TableView<Pyrometry> tablePyrometry;
    
    @FXML
    private TableColumn<Carvao, String> dataCarvao, horaCarvao, pesoCarvao, umidadeCarvao;
    @FXML
    private TableView<Carvao> tableCarvao;
        
   private void alimentarTabelaTestes() {    
	   tablePirometro.setEditable(false);
	
       dataTime.setSortType(TableColumn.SortType.DESCENDING);
       dataTime.setCellValueFactory(new PropertyValueFactory<Pirometro, String>("dataTime"));
       temp.setCellValueFactory(new PropertyValueFactory<Pirometro, String>("temp"));
       tablePirometro.setItems(RelatorioService.getListPirometro());       
       
	   tableBalancas.setEditable(false);
	   data.setCellValueFactory(new PropertyValueFactory<Balancas, String>("date"));
	   hInicio.setCellValueFactory(new PropertyValueFactory<Balancas, String>("hInicio"));
	   hFim.setCellValueFactory(new PropertyValueFactory<Balancas, String>("hFim"));
	   b1.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca01"));
	   b2.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca02"));
	   b3.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca03"));
	   b4.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca04"));
	   b5.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca05"));
	   b6.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca06"));
	   b7.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca07"));
	   b8.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca08"));
	   b9.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca09"));
	   b10.setCellValueFactory(new PropertyValueFactory<Balancas, String>("balanca10"));
	   tableBalancas.setItems(RelatorioService.getListBalancas());
	   
	   tablePyrometry.setEditable(false);
	   timeStartFinish.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("timeStartFinish"));
	   pressaoCoroa.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("pressaoCoroa"));
	   pressaoTopo.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("pressaoTopo"));
	   tempCoroa.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("tempCoroa"));
	   tempTopo.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("tempTopo"));
	   vazaoAr.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("vazaoAr"));
	   secador.setCellValueFactory(new PropertyValueFactory<Pyrometry, String>("secador"));	
//	   RelatorioService.addListBalancas(new Balancas("17/04/22", "10:01", "16:05", "1500", "1502", "1503", "1504", "1505", "1506", "1507", "1508", "1509", "1510"));
	   tablePyrometry.setItems(RelatorioService.getListPyrometry());
	   
	   tableCarvao.setEditable(false);
	   dataCarvao.setCellValueFactory(new PropertyValueFactory<Carvao, String>("dataCarvao"));
	   dataCarvao.setEditable(false);
	   horaCarvao.setCellValueFactory(new PropertyValueFactory<Carvao, String>("horaCarvao"));
	   horaCarvao.setSortable(true);
	   horaCarvao.setSortType(SortType.DESCENDING);
	   pesoCarvao.setCellValueFactory(new PropertyValueFactory<Carvao, String>("pesoCarvao"));
	   umidadeCarvao.setCellValueFactory(new PropertyValueFactory<Carvao, String>("umidadeCarvao"));
//	   RelatorioService.addListCarvao(new Carvao("19/04/2022", "10:35", "953,5 Kg", ""));
	   tableCarvao.setItems(RelatorioService.getListCarvao());
	   
   }
       
}


