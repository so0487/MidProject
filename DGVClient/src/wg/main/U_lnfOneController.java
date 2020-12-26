package wg.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import wg.vo.LnFVO;

public class U_lnfOneController {

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

	@FXML
	void click(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(U_lnfOneController.class.getResource("../fxml/U_lnfDetail.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}



	private LnFVO lnfVo;

	public LnFVO getlnfVo() {
		return lnfVo;
	}


	public void setLnfVo(LnFVO lnfVo) {
		this.lnfVo = lnfVo;


		Image image = new Image(U_lnfOneController.class.getResourceAsStream("../img/"+lnfVo.getLnf_photo()));
		LnfImgView.setImage(image);

		lblDate.setText(lnfVo.getLnf_time());
		lblSpot.setText(lnfVo.getLnf_spot());
	}


	@FXML
	void initialize() {

	}
}
