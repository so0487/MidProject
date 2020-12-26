package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import wg.bookGenderCnt.service.IBookGenderCntService;




public class M_bookMemCntController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<String, Number> barchart;
    
    
    IBookGenderCntService service;

    
    List<String>smovieList;
    
    public void setSmovieList(List<String> smovieList) {
		this.smovieList = smovieList;
		try {
			//smovieList.add(service.getbookgenCntList());
			
			
			//System.out.println(array);
			
			XYChart.Series<String, Number> member = new XYChart.Series<String, Number>();
			member.setName("영화별 예매율");
			
			for(int i=0; i<smovieList.size();i++) {
				String movie_name = smovieList.get(i);
				
				member.getData().add(new Data<String, Number>(movie_name,service.getbookcount(movie_name)));
				
			}
			barchart.getData().add(member);
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

    
   
 
  	
    
    

    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
//			reg = LocateRegistry.getRegistry("192.168.31.32",9988);
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IBookGenderCntService) reg.lookup("bookGenderCntService");
			
			
			List<String> smovieList = service.getbookgenCntList();
			
			setSmovieList(smovieList);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
}
