package wg.main;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class U_bookMovie_timeBoxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnTime;

    @FXML
    private Label lblOpenSeatNum;

    @FXML
    private Label lblSeatNum;

    @FXML
    private ImageView icon;

    @FXML
    void initialize() {

    }
    
    public void btnTimeSetText(String time) {
    	this.btnTime.setText(time);
    }
    
    public String btnTimeGetText() {
    	return btnTime.getText();
    }
    
    public void lblOpenSeatNum(int OpenSeatNum) {
    	this.lblOpenSeatNum.setText(OpenSeatNum+"/");
    }
    
    public void lblSeatNum(int seatNum) {
    	this.lblSeatNum.setText(seatNum+"");
    }
    
    public void setImg(InputStream is) {
    	Image img = new Image(is);
    	this.icon.setImage(img);
    }
    
    public Button getBtn() {
    	return this.btnTime;
    }
    
    public void setDisable() {
    	btnTime.setDisable(true);
    }
    
    
}
