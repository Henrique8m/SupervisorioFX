package com.rodrigues.rodrigues.lixo;

public class RestosMortais {
	
	/*
	instanciates();
	if(tarefa == null) {    		
    	primaryViewController.txLog.setText("Starting comunication...");
    	primaryViewController.txLog1.setText("Starting comunication...");
        try {
			timerInstantiated();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/
/*

private void timerInstantiated(){
    tarefa = new TimerTask() {
        @Override
        public void run() {
			try {
				readController.read();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    };
    timer.scheduleAtFixedRate(tarefa, 3000, 7000);
}
public void timerCancel(){
    if(tarefa != null) {
    	tarefa.cancel();
    	readController.threadCancel();
    	tarefa = null;
    }else
    readController.threadCancel();
}

*
*/
	
	
	
	
	
	/*

	CategoryAxis xAxis = new CategoryAxis();

	NumberAxis yAxis = new NumberAxis();

	xAxis.setLabel("Month");

	LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

	lineChart.setTitle("Stock Monitoring, 2010");

	XYChart.Series series1 = new XYChart.Series();
	series1.setName("Portfolio 1");

	series1.getData().add(new XYChart.Data("Jan", 23));
	series1.getData().add(new XYChart.Data("Feb", 14));
	series1.getData().add(new XYChart.Data("Mar", 15));
	series1.getData().add(new XYChart.Data("Apr", 24));
	series1.getData().add(new XYChart.Data("May", 34));
	series1.getData().add(new XYChart.Data("Jun", 36));
	series1.getData().add(new XYChart.Data("Jul", 22));
	series1.getData().add(new XYChart.Data("Aug", 45));
	series1.getData().add(new XYChart.Data("Sep", 43));
	series1.getData().add(new XYChart.Data("Oct", 17));
	series1.getData().add(new XYChart.Data("Nov", 29));
	series1.getData().add(new XYChart.Data("Dec", 25));

	XYChart.Series series2 = new XYChart.Series();
	series2.setName("Portfolio 2");
	series2.getData().add(new XYChart.Data("Jan", 33));
	series2.getData().add(new XYChart.Data("Feb", 34));
	series2.getData().add(new XYChart.Data("Mar", 25));
	series2.getData().add(new XYChart.Data("Apr", 44));
	series2.getData().add(new XYChart.Data("May", 39));
	series2.getData().add(new XYChart.Data("Jun", 16));
	series2.getData().add(new XYChart.Data("Jul", 55));
	series2.getData().add(new XYChart.Data("Aug", 54));
	series2.getData().add(new XYChart.Data("Sep", 48));
	series2.getData().add(new XYChart.Data("Oct", 27));
	series2.getData().add(new XYChart.Data("Nov", 37));
	series2.getData().add(new XYChart.Data("Dec", 29));

	XYChart.Series series3 = new XYChart.Series();
	series3.setName("Portfolio 3");
	series3.getData().add(new XYChart.Data("Jan", 44));
	series3.getData().add(new XYChart.Data("Feb", 35));
	series3.getData().add(new XYChart.Data("Mar", 36));
	series3.getData().add(new XYChart.Data("Apr", 33));
	series3.getData().add(new XYChart.Data("May", 31));
	series3.getData().add(new XYChart.Data("Jun", 26));
	series3.getData().add(new XYChart.Data("Jul", 22));
	series3.getData().add(new XYChart.Data("Aug", 25));
	series3.getData().add(new XYChart.Data("Sep", 43));
	series3.getData().add(new XYChart.Data("Oct", 44));
	series3.getData().add(new XYChart.Data("Nov", 45));
	series3.getData().add(new XYChart.Data("Dec", 44));

	lineChart.getData().addAll(series1, series2, series3);
*/
	
	
	
	
	
	
	
	/*
	 * private synchronized <T> void loadView(String absoluteName, Consumer<T>
	 * initializingAction) { try { FXMLLoader loader = new
	 * FXMLLoader(getClass().getResource(absoluteName)); VBox newVBox =
	 * loader.load(); Scene mainScene = MainApp.getMainScene(); VBox mainVBox =
	 * (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
	 * 
	 * Node mainMenu = mainVBox.getChildren().get(0);
	 * mainVBox.getChildren().clear(); mainVBox.getChildren().add(mainMenu);
	 * mainVBox.getChildren().addAll(newVBox.getChildren());
	 * 
	 * // T controller = loader.getController(); //
	 * initializingAction.accept(controller); } catch (IOException e) {
	 * Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(),
	 * AlertType.ERROR); } }
	 */
	
	
	
	
//	@SuppressWarnings({ "unchecked", "static-access" })
//	public Enumeration<CommPortIdentifier> getPortIdentifiers(){		
//		return cp.getPortIdentifiers();
//	}
	

	
	
	
	
	/*
	bufferWrite = CalculatorData.addressReadAlfa(19,Gadgets.ALFA_LEI_SETPOINTS.getRegistrador(),Gadgets.ALFA_LEI_SETPOINTS.getTotalRegistradores()); 
	bufferSizeRead = Gadgets.ALFA_LEI_SETPOINTS.getBufferRead();
	bufferSizeWrite = Gadgets.ALFA_LEI_SETPOINTS.getBufferWrite();
	serialService.writeData(bufferWrite, serial, bufferSizeWrite);
	bufferReadAlfa = serialService.readData(serial, bufferSizeRead);
	System.out.println(
			formatData.formatDataAlfaGeneric(
			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
			Gadgets.ALFA_FORMAT_SETPOINTS_VAZIA.getFormatDataLs(),
			Gadgets.ALFA_FORMAT_SETPOINTS_VAZIA.getFormatDataMs()));
	System.out.println(
			formatData.formatDataAlfaGeneric(
			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
			Gadgets.ALFA_FORMAT_SETPOINTS_1_4.getFormatDataLs(),
			Gadgets.ALFA_FORMAT_SETPOINTS_1_4.getFormatDataMs()));
	System.out.println(
			formatData.formatDataAlfaGeneric(
			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
			Gadgets.ALFA_FORMAT_SETPOINTS_2_5.getFormatDataLs(),
			Gadgets.ALFA_FORMAT_SETPOINTS_2_5.getFormatDataMs()));
	System.out.println(
			formatData.formatDataAlfaGeneric(
			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
			Gadgets.ALFA_FORMAT_SETPOINTS_3_6.getFormatDataLs(),
			Gadgets.ALFA_FORMAT_SETPOINTS_3_6.getFormatDataMs()));
	
	Thread.sleep(5000);
*/
	
	/*
	 * @SuppressWarnings("unused") private void beginTimer() { timeline = new
	 * Timeline(new KeyFrame(javafx.util.Duration.seconds(2), ev -> { //
	 * sComm.WriteData(); //sComm.formatDados();
	 * //lblOut.setText(sComm.getDisplay()); }));
	 * 
	 * timeline.setCycleCount(Animation.INDEFINITE); timeline.play(); time = true;
	 * 
	 * }
	 * 
	 * 	@SuppressWarnings("unused")
	private void cancelTimer() {
		timeline.stop();
	}
	 * 
	 */
	
	
	
	//serialController = DependencyInjection.getSerialController();
	//controller.setFxmlController(this);
	//controller = new SerialController(PrimaryViewController.this);
	
	
	
	
	/*
	Balancas balanca1 = new Balancas("Balança01", Integer.parseInt(viewController.Balanca01.getText() ) );
	Balancas balanca2 = new Balancas("Balança02", Integer.parseInt(viewController.Balanca02.getText() ) );
	Balancas balanca3 = new Balancas("Balança03", Integer.parseInt(viewController.Balanca03.getText() ) );
	Balancas balanca4 = new Balancas("Balança04", Integer.parseInt(viewController.Balanca04.getText() ) );
	Balancas balanca5 = new Balancas("Balança05", Integer.parseInt(viewController.Balanca05.getText() ) );
	Balancas balanca6 = new Balancas("Balança06", Integer.parseInt(viewController.Balanca06.getText() ) );
	Balancas balanca7 = new Balancas("Balança07", Integer.parseInt(viewController.Balanca07.getText() ) );
	Balancas balanca8 = new Balancas("Balança08", Integer.parseInt(viewController.Balanca08.getText() ) );
	Balancas balanca9 = new Balancas("Balança09", Integer.parseInt(viewController.Balanca09.getText() ) );
	Balancas balanca10 = new Balancas("Balança10", Integer.parseInt(viewController.Balanca10.getText() ) );
	*/
	

}
