package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import wg.buysnack.service.IBuySnackService;
import wg.vo.LoginVO;
import wg.vo.MemberVO;
import wg.vo.SnackUserViewVO;

public class U_snackHistorMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane outerBox;

    @FXML
    private VBox vbox;
    
    private IBuySnackService buySnackService; 
    

    @FXML
    void initialize() {
       Registry reg = null;
       try {
         reg = LocateRegistry.getRegistry("localhost",9988);
         buySnackService = (IBuySnackService) reg.lookup("buySnackService");
         
         MemberVO vo = LoginVO.getCurrVo(); //로그인 정보를 불러와야한다.
         
         	List<SnackUserViewVO> list =buySnackService.getBuySnack(vo.getMem_id());
         	
         	int i = 0;
	    	VBox vb = null;
	    	
	    	for(SnackUserViewVO bmvVo : list) {
	    		if(i%3 == 0 ) {
	    			vb = new VBox();
	    		}
	    		System.out.println(vb.getId());
	    		
    			FXMLLoader loader1 = new FXMLLoader(U_snackHistorMainController.class.getResource("../fxml/U_snackHistor.fxml"));
    			Parent center = loader1.load();
    			U_snackHistorOneController myOneController = loader1.getController();
    			myOneController.setSnackUserViewVo(bmvVo);
    			if(bmvVo.getPay_refund().equals("Y")) {
    			}
    			final SnackUserViewVO bmv = bmvVo;
    			
    			vb.getChildren().add(center);
					vbox.getChildren().add(vb);
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