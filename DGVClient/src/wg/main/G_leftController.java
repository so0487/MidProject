//package wg.main;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import util.AlertUtil;
//import wg.vo.LoginVO;
//
//public class G_leftController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private AnchorPane leftAncho_box;
//
//    @FXML
//    private Button btnLaF;
//
//    @FXML
//    private Button btnMyPage;
//
//    @FXML
//    private Button btnLogin;
//
//    @FXML
//    private Button btnCenter;
//
//    @FXML
//    private Button btnSpecial;
//    
//    private BorderPane bp;
//    
//    public BorderPane getBp() {
//		return bp;
//	}
//
//	public void setBp(BorderPane bp) {
//		this.bp = bp;
//	}
//
//	@FXML
//    private Label log;
//    
//
//    
//
//    public Label getLog() {
//		return log;
//	}
//
//	public void setLog(Label log) {
//		this.log = log;
//	}
//
//	@FXML
//    void goCenter(ActionEvent event) {
//    	
//    }
//
//    @FXML
//    void goLaF(ActionEvent event) {
//
//    }
//
//    @FXML
//    void goLogin(ActionEvent event) {
//    	try {
//			FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/G_login.fxml"));
//			StackPane center = loader.load();
//			G_loginController lcon = loader.getController();
//			lcon.setCon2(this);
//			U_main.root.setCenter(center);
////			lcon.setBp(bp);
//			
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	
//    }
//
//    @FXML
//    void goMyPage(ActionEvent event) {
//		try {
//			// 비로그인 상태인 경우
//			if (LoginVO.getCurrVo() == null) {
//				AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
//				FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/G_login.fxml"));
//				StackPane center = loader.load();
//				G_loginController lcon = loader.getController();
//				lcon.setCon2(this);
//				U_main.root.setCenter(center);
//				return;
//			}
//
//			FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/U_info.fxml"));
//			StackPane center = loader.load();
//			U_infoController lcon = loader.getController();
//			U_main.root.setCenter(center);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    }
//
//    @FXML
//    void goSpecial(ActionEvent event) {
//
//    }
//
//    @FXML
//    void initialize() {
//
//    }
//}
//||||||| .r131911
//package wg.main;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import util.AlertUtil;
//import wg.vo.LoginVO;
//
//public class G_leftController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private AnchorPane leftAncho_box;
//
//    @FXML
//    private Button btnLaF;
//
//    @FXML
//    private Button btnMyPage;
//
//    @FXML
//    private Button btnLogin;
//
//    @FXML
//    private Button btnCenter;
//
//    @FXML
//    private Button btnSpecial;
//    
//    private BorderPane bp;
//    
//    @FXML
//    private Label log;
//    
//
//    
//
//    public Label getLog() {
//		return log;
//	}
//
//	public void setLog(Label log) {
//		this.log = log;
//	}
//
//	@FXML
//    void goCenter(ActionEvent event) {
//    	
//    }
//
//    @FXML
//    void goLaF(ActionEvent event) {
//
//    }
//
//    @FXML
//    void goLogin(ActionEvent event) {
//    	try {
//			FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/G_login.fxml"));
//			StackPane center = loader.load();
//			G_loginController lcon = loader.getController();
//			lcon.setCon2(this);
//			U_main.root.setCenter(center);
//			
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	
//    }
//
//    @FXML
//    void goMyPage(ActionEvent event) {
//		try {
//			// 비로그인 상태인 경우
//			if (LoginVO.getCurrVo() == null) {
//				AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
//				FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/G_login.fxml"));
//				StackPane center = loader.load();
//				G_loginController lcon = loader.getController();
//				lcon.setCon2(this);
//				U_main.root.setCenter(center);
//				return;
//			}
//
//			FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/U_info.fxml"));
//			StackPane center = loader.load();
//			U_infoController lcon = loader.getController();
//			U_main.root.setCenter(center);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    }
//
//    @FXML
//    void goSpecial(ActionEvent event) {
//
//    }
//
//    @FXML
//    void initialize() {
//
//    }
//}
//}
////package wg.main;
////
////import java.io.IOException;
////import java.net.URL;
////import java.util.ResourceBundle;
////import javafx.event.ActionEvent;
////import javafx.fxml.FXML;
////import javafx.fxml.FXMLLoader;
////import javafx.scene.control.Button;
////import javafx.scene.control.Label;
////import javafx.scene.layout.AnchorPane;
////import javafx.scene.layout.BorderPane;
////import javafx.scene.layout.StackPane;
////import util.AlertUtil;
////import wg.vo.LoginVO;
////
////public class G_leftController {
////
////    @FXML
////    private ResourceBundle resources;
////
////    @FXML
////    private URL location;
////
////    @FXML
////    private AnchorPane leftAncho_box;
////
////    @FXML
////    private Button btnLaF;
////
////    @FXML
////    private Button btnMyPage;
////
////    @FXML
////    private Button btnLogin;
////
////    @FXML
////    private Button btnCenter;
////
////    @FXML
////    private Button btnSpecial;
////    
////    private BorderPane bp;
////    
////    @FXML
////    private Label log;
////    
////
////    
////
////    public Label getLog() {
////		return log;
////	}
////
////	public void setLog(Label log) {
////		this.log = log;
////	}
////
////	@FXML
////    void goCenter(ActionEvent event) {
////    	
////    }
////
////    @FXML
////    void goLaF(ActionEvent event) {
////
////    }
////
////    @FXML
////    void goLogin(ActionEvent event) {
////    	try {
////			FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/G_login.fxml"));
////			StackPane center = loader.load();
////			G_loginController lcon = loader.getController();
////			lcon.setCon2(this);
////			U_main.root.setCenter(center);
////			
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////    	
////    }
////
////    @FXML
////    void goMyPage(ActionEvent event) {
////		try {
////			// 비로그인 상태인 경우
////			if (LoginVO.getCurrVo() == null) {
////				AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
////				FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/G_login.fxml"));
////				StackPane center = loader.load();
////				G_loginController lcon = loader.getController();
////				lcon.setCon2(this);
////				U_main.root.setCenter(center);
////				return;
////			}
////
////			FXMLLoader loader = new FXMLLoader(G_leftController.class.getResource("../fxml/U_info.fxml"));
////			StackPane center = loader.load();
////			U_infoController lcon = loader.getController();
////			U_main.root.setCenter(center);
////
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////    	
////    }
////
////    @FXML
////    void goSpecial(ActionEvent event) {
////
////    }
////
////    @FXML
////    void initialize() {
////
////    }
////}
//>>>>>>> .r131995
