package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wg.saleCount.service.ISaleCountService;

public class M_saleCountController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BarChart<String, Number> saleCountMonth;









	String pay_time;
	ISaleCountService service;
	







	@FXML
	void initialize() {
		Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ISaleCountService) reg.lookup("saleCountService");

	


			//System.out.println(pay_date);
			XYChart.Series<String, Number> sale1 = new XYChart.Series<String, Number>();
			sale1.setName("매출통계");
//			XYChart.Series<String, Number> sale2 = new XYChart.Series<String, Number>();
//			sale2.setName("2월");
//			XYChart.Series<String, Number> sale3 = new XYChart.Series<String, Number>();
//			sale3.setName("3월");
//			XYChart.Series<String, Number> sale4 = new XYChart.Series<String, Number>();
//			sale4.setName("4월");
//			XYChart.Series<String, Number> sale5 = new XYChart.Series<String, Number>();
//			sale5.setName("5월");
//			XYChart.Series<String, Number> sale6 = new XYChart.Series<String, Number>();
//			sale6.setName("6월");
//			XYChart.Series<String, Number> sale7 = new XYChart.Series<String, Number>();
//			sale7.setName("7월");
//			XYChart.Series<String, Number> sale8 = new XYChart.Series<String, Number>();
//			sale8.setName("8월");
//			XYChart.Series<String, Number> sale9 = new XYChart.Series<String, Number>();
//			sale9.setName("9월");
//			XYChart.Series<String, Number> sale10 = new XYChart.Series<String, Number>();
//			sale10.setName("10월");
//			XYChart.Series<String, Number> sale11 = new XYChart.Series<String, Number>();
//			sale11.setName("11월");
//			XYChart.Series<String, Number> sale12 = new XYChart.Series<String, Number>();
//			sale12.setName("12월");
			
			
			
			
			
			
			pay_time = "2020-01";
			sale1.getData().add(new Data<String, Number>("1월",service.getSaleCount(pay_time)));
			pay_time = "2020-02";
			sale1.getData().add(new Data<String, Number>("2월",service.getSaleCount(pay_time)));
			pay_time = "2020-03";
			sale1.getData().add(new Data<String, Number>("3월",service.getSaleCount(pay_time)));
			pay_time = "2020-04";
			sale1.getData().add(new Data<String, Number>("4월",service.getSaleCount(pay_time)));
			pay_time = "2020-05";
			sale1.getData().add(new Data<String, Number>("5월",service.getSaleCount(pay_time)));
			pay_time = "2020-06";
			sale1.getData().add(new Data<String, Number>("6월",service.getSaleCount(pay_time)));
			pay_time = "2020-07";
			sale1.getData().add(new Data<String, Number>("7월",service.getSaleCount(pay_time)));
			pay_time = "2020-08";
			sale1.getData().add(new Data<String, Number>("8월",service.getSaleCount(pay_time)));
			pay_time = "2020-09";
			sale1.getData().add(new Data<String, Number>("9월",service.getSaleCount(pay_time)));
			pay_time = "2020-10";
			sale1.getData().add(new Data<String, Number>("10월",service.getSaleCount(pay_time)));
			pay_time = "2020-11";
			sale1.getData().add(new Data<String, Number>("11월",service.getSaleCount(pay_time)));
			pay_time = "2020-12";
			sale1.getData().add(new Data<String, Number>("12월",service.getSaleCount(pay_time)));
			

//			saleCountMonth.getData().addAll(sale1,sale2,sale3,sale4,sale5,sale6,sale7,sale8,sale9,sale10,sale11,sale12);
			saleCountMonth.getData().addAll(sale1);
			
			
			
		}
		catch (RemoteException e) {
			// TODO: handle exception
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
