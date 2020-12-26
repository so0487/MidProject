package wg.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.vo.LoginVO;

public class M_topController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnInOut;

    @FXML
    private ImageView m_logo;

    @FXML
    private Button btnManage;

    @FXML
    private Button btnNotice;

    @FXML
    private Button btnAuction;

    @FXML
    private Button btnLnF;

    @FXML
    private Button btnEvent;
    
    private G_loginController logCon;
    
    private G_mediaViewController meCon;
    
	public G_mediaViewController getMeCon() {
		return meCon;
	}
	public void setMecon(G_mediaViewController meCon) {
		this.meCon = meCon;
	}

    @FXML
    void btnAuctionClick(ActionEvent event) {
		
		try {
			FXMLLoader loader1 = new FXMLLoader(M_topController.class.getResource("../fxml/M_manageAuction.fxml"));
			ScrollPane center = loader1.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void btnEventClick(ActionEvent event) {
    	try {
			FXMLLoader loader1 = new FXMLLoader(M_topController.class.getResource("../fxml/M_event.fxml"));
			Parent center = loader1.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void btnInOutClick(ActionEvent event) {
    	try {
    		AlertUtil.infoMsg("로그아웃 되었습니다.", "감사합니다.");
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
    void btnLnFClick(ActionEvent event) {

    }

    @FXML
    void btnManageClick(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(M_topController.class.getResource("../fxml/M_genre.fxml"));
		Parent root;
		try {
			root = loader.load();
			((StackPane)((BorderPane)(btnManage.getScene().getRoot())).getCenter()).getChildren().add(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void btnNoticeClick(ActionEvent event) {
    	try {
			FXMLLoader loader1 = new FXMLLoader(M_topController.class.getResource("../fxml/S_noticeMain.fxml"));
			Parent center = loader1.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void logoClick(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(M_topController.class.getResource("../fxml/M_centerWhite.fxml"));
    	try {
			Parent center = loader.load();
			
			U_main.root.setCenter(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {

    }
}


