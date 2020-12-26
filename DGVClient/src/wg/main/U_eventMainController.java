package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.AlertUtil;
import wg.event.service.IEventService;
import wg.vo.EventVO;
import wg.vo.NoticeVO;
import wg.vo.SelectEventVO;

public class U_eventMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane outerBox;
    
    @FXML
    private VBox vbox;
    
    IEventService service;
   // private Map<String, String> searchMap;
    private ObservableList<EventVO> eventList;
    @FXML
    void initialize() {
    	Registry reg = null;
    	//searchMap = new HashMap<String, String>();
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IEventService) reg.lookup("eventService");
	    	List<EventVO> list =service.getAllEvent();
	    	int i = 0;
	    	HBox hb = null;
	    	for(EventVO eventVo : list) {
	    		if(i%3 == 0 ) {
	    			hb = new HBox();
	    		}
	    		hb.setId("hb"+i);
	    		System.out.println(hb.getId());
	    		if(hb.getId().equals("hb"+i)) {
	    			SelectEventVO.setCurrEventVo(list.get(i));
	    		}
    			FXMLLoader loader1 = new FXMLLoader(U_eventMainController.class.getResource("../fxml/U_event.fxml"));
    			Parent center = loader1.load();
    			U_eventOneController eventOneController = loader1.getController();
    			eventOneController.setEventVo(eventVo);
    			final EventVO tt = eventVo;
    			center.setOnMouseClicked(e->{
    				try {
	    				FXMLLoader loader = new FXMLLoader(U_eventMainController.class.getResource("../fxml/U_eventBig.fxml"));
	        			Parent selectedEvent = loader.load();
	    				outerBox.setCenter(selectedEvent);
	    				U_eventBigController ub = loader.getController();
	    				ub.setVo(tt);
	    				System.out.println(SelectEventVO.currEventVo.getEvent_title());
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
    			});
    			hb.getChildren().add(center);
				if(i%3 == 2) {
					vbox.getChildren().add(hb);
				}
				i++;
	    	}
	    	
	    	if(list.size()%3!=0) {  // 한줄에 3개가 모두 채워지지 않았을 때
	    		vbox.getChildren().add(hb);
	    	}
	    	
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
