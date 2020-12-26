package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import wg.lnf.service.ILnFService;
import wg.vo.LnFVO;

public class S_lnfOneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView LnfImgView;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblSpot;
    
    
    private LnFVO lnfVo;
    
    public LnFVO lnfVo() {
    	return lnfVo();
    }
    
    public void setLnfVo(LnFVO lnfVo) {
    	this.lnfVo = lnfVo;
    	lblDate.setText(lnfVo.getLnf_time());
    	lblSpot.setText(lnfVo.getLnf_spot());

    	
    }
    
    
    ILnFService service;
    
    

    @FXML
    void Click(MouseEvent event) {
    	Parent root;
    	FXMLLoader loader = new FXMLLoader(S_lnfOneController.class.getResource("../fxml/S_lnfDetail.fxml"));
    	
    	try {
			root = loader.load();
			Scene scene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

    @FXML
    void initialize() throws RemoteException{

    }
}
