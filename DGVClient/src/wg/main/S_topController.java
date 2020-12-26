package wg.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import util.AlertUtil;
import wg.vo.LoginVO;

public class S_topController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView btnLogo;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnMyPage;
    
    private BorderPane bp;
	public BorderPane getBp() {
		return bp;
	}
	public void setBp(BorderPane bp) {
		this.bp = bp;
	}

    @FXML
    void btnLogOutClick(ActionEvent event) {
    	try {
    		AlertUtil.infoMsg("로그아웃 되었습니다.","감사합니다." );
    		LoginVO.setCurrVo(null);
    		
    		FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_top.fxml"));
    		Parent top = loader.load();
    		U_main.root.setTop(top);

    		FXMLLoader loader2 = new FXMLLoader(G_topController.class.getResource("../fxml/G_center.fxml"));
    		Parent center = loader2.load();
    		U_main.root.setCenter(center);
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void btnLogoClick(MouseEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(S_topController.class.getResource("../fxml/S_center.fxml"));
    		Parent center = loader.load();
    		U_main.root.setCenter(center);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void btnMyPageClick(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
