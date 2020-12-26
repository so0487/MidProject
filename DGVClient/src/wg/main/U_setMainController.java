package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.AlertUtil;
import wg.snackSet.service.ISnackSetService;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_setMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane outerBox;
    
    @FXML
    private Button cart;

    @FXML
    void goCart(ActionEvent event) {

    }
    
    @FXML
    private VBox vbox;
    ISnackSetService service;
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ISnackSetService) reg.lookup("snackService");
	    	List<SnackSetVO> list =service.getAllSnackSetList();
	    	int i = 0;
	    	HBox hb = null;
	    	for(SnackSetVO snackVo : list) {
	    		if(i%2 == 0 ) {
	    			hb = new HBox();
	    			vbox.getChildren().add(hb);
	    		}
    			FXMLLoader loader1 = new FXMLLoader(U_setMainController.class.getResource("../fxml/U_snackSet.fxml"));
    			Parent center = loader1.load();
    			U_setOneController setOneController = loader1.getController();
    			setOneController.setSnackSetVo(snackVo);
    			final SnackSetVO tt = snackVo;
    			center.setOnMouseClicked(e->{
    				try {
	    				FXMLLoader loader = new FXMLLoader(U_setMainController.class.getResource("../fxml/U_buysnackbar.fxml"));
	        			Parent selectedSnack = loader.load();
	    				outerBox.setCenter(selectedSnack);
	    				U_buySnackBarController ub = loader.getController();
	    				ub.setVo(tt);
//	    				System.out.println(SelectSnackSetVO.currSnackVo.getSet_name());
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
    			});
    			hb.getChildren().add(center);
				i++;
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
