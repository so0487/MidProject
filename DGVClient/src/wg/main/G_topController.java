package wg.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.vo.LoginVO;

public class G_topController {

	@FXML
	private AnchorPane outerBox;

	@FXML
	private Button movieTop;

	@FXML
	private Button bookTop;

	@FXML
	private ResourceBundle resources;

	@FXML
	private ImageView blackLogo;

	@FXML
	private Button btnNotice;

	@FXML
	private URL location;

	@FXML
	private Button btnRegister;

	@FXML
	private Button btnEvent;

	@FXML
	private BorderPane bp;

	@FXML
	private Button snackSet;

	@FXML
	private Button auction;

	public Button getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(Button btnRegister) {
		this.btnRegister = btnRegister;
	}

	@FXML
	void bookTopClick(ActionEvent event) {
		// 비로그인 상태인 경우
		if (LoginVO.getCurrVo() == null) {
			AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
			try {
				changeColor();
				FXMLLoader loader1 = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
				Parent center1 = loader1.load();

				G_loginController glog = loader1.getController();
				glog.setTopCtrl(this);
				U_main.root.setCenter(center1);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 영화예매화면으로 이동
		try {
			changeColor();
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_bookMovieMain.fxml"));
			Parent bookMovie = loader.load();
			U_bookMovieMainController bookMovieCtrl = loader.getController();
			
			bookMovieCtrl.setTopCtrl(this);
			U_main.root.setCenter(bookMovie);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 네비의 영화버튼 클릭 시
	@FXML
	void movieTopClick(ActionEvent event) {
		changeColor();
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_movieMain.fxml"));
			StackPane movieIntro = loader.load();
			U_movieController movieIntroCtrl = loader.getController();
			movieIntroCtrl.setTopCtrl(this);
			U_main.root.setCenter(movieIntro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 마이페이지 버튼 클릭 시
	@FXML
	void goMyPage(ActionEvent event) {
		try {
			// 비로그인 상태인 경우
			if (LoginVO.getCurrVo() == null) {
				AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
				try {
					changeColor();
					FXMLLoader loader1 = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
					Parent center1 = loader1.load();

					G_loginController glog = loader1.getController();
					glog.setTopCtrl(this);
					U_main.root.setCenter(center1);
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			changeColor();
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_info.fxml"));
			StackPane info = loader.load();
			U_infoController infoCtrl = loader.getController();
			infoCtrl.setTopCtrl(this); // 자기자신을 이렇게 넘겨줌
			U_main.root.setCenter(info);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void dataEvent(ActionEvent event) {
		changeColor();
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_eventMain.fxml"));
			StackPane center = loader.load();
			U_main.root.setCenter(center);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void goRegister(ActionEvent event) {
		changeColor();
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_register.fxml"));
			StackPane register = loader.load();
			G_registerController regiCtrl = loader.getController();
			// 회원가입페이지에 자기객체(top) 넘겨주기
			regiCtrl.setTopCtrl(this);

			U_main.root.setCenter(register);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BorderPane getBp() {
		return bp;

	}

	public void setBp(BorderPane bp) {
		this.bp = bp;
	}

	@FXML
	void goLog(ActionEvent event) {
		if (btnLogin.getText().equals("로그인")) {
			try {
				FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
				Parent m_center = loader.load();
				G_loginController lcon = loader.getController();
				lcon.setBp(bp);
				lcon.setCon(this);
				lcon.setTopCtrl(this);
				changeColor();

				U_main.root.setCenter(m_center);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				AlertUtil.infoMsg("로그아웃 되었습니다.", "감사합니다.");
				backToBlack();
				btnLogin.setText("로그인");
				LoginVO.setCurrVo(null);
				FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_center.fxml"));
				StackPane m_center = loader.load();
				U_main.root.setCenter(m_center);
				// btnRegister.setDisable(false);
				btnRegister.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	private Button btnLogin;

	@FXML
	void goSnackSet(ActionEvent event) {
		changeColor();
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_snackSetMain.fxml"));
			StackPane center = loader.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void goAuction(ActionEvent event) {
		changeColor();
		try {
			if (btnLogin.getText().equals("로그인")) {
				AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
				try {
					changeColor();
					FXMLLoader loader1 = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
					Parent center1 = loader1.load();

					G_loginController glog = loader1.getController();
					glog.setTopCtrl(this);
					U_main.root.setCenter(center1);
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_auctionMain.fxml"));
				StackPane center = loader.load();
				U_main.root.setCenter(center);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}

	@FXML
	void goNotice(ActionEvent event) {
		changeColor();
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_NoticeMain.fxml"));
			Parent center = loader.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void logoClick(MouseEvent event) {
		backToBlack();
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_center.fxml"));
			Parent center = loader.load();
			G_mediaViewController centerCtrl = loader.getController();

			centerCtrl.setTopCtrl(this);
			U_main.root.setCenter(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button btnMypage;

	@FXML
	void initialize() {

	}

	public void changeColor() {
		this.outerBox.setStyle("-fx-background-color:white;");
		String value = "-fx-background-color:white; -fx-text-fill:black;";
		btnLogin.setStyle(value);
		btnEvent.setStyle(value);
		btnMypage.setStyle(value);
		btnNotice.setStyle(value);
		btnRegister.setStyle(value);
		movieTop.setStyle(value);
		bookTop.setStyle(value);
		auction.setStyle(value);
		snackSet.setStyle(value);

		try {
			Image logo = new Image(new FileInputStream(
					"c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/dgbLogo.jpg"));
			blackLogo.setImage(logo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void backToBlack() {
		this.outerBox.setStyle("-fx-background-color:black;");
		String value = "-fx-background-color:black; -fx-text-fill:white;";
		btnLogin.setStyle(value);
		btnEvent.setStyle(value);
		btnMypage.setStyle(value);
		btnNotice.setStyle(value);
		btnRegister.setStyle(value);
		movieTop.setStyle(value);
		bookTop.setStyle(value);
		auction.setStyle(value);
		snackSet.setStyle(value);

		if (LoginVO.getCurrVo() == null) {
			btnLogin.setText("로그인");
		} else {
			btnLogin.setText("로그아웃");
		}

		if (LoginVO.getCurrVo() == null) {
			btnRegister.setVisible(true);
		} else {
			btnRegister.setVisible(false);
		}

		try {
			Image logo = new Image(new FileInputStream(
					"c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/blackDGV.jpg"));
			blackLogo.setImage(logo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}