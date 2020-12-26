package wg.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class U_bookMovie_btnMovieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnMovie;
    
    @FXML
    private ImageView imgAdult;

    @FXML
    private HBox btnBox;

    @FXML
    void initialize() {

    }
    
    public void btnMovieSetText(String smovie_name) {
    	this.btnMovie.setText(smovie_name);
    }
    
    public String btnMovieGetText() {
    	return btnMovie.getText();
    }
    
    public void colorNone(){
    	this.btnBox.setStyle("-fx-background-color:none; -fx-text-fill:black; -fx-alignment:center-left; -fx-border-color: #c2baba;");
    }
    
    public void colorGrey() {
    	this.btnBox.setStyle("-fx-background-color:#c2baba; -fx-text-fill:white; -fx-alignment:center-left; -fx-border-color: #c2baba;");
    }
    
    public void setVisible() {
    	this.imgAdult.setVisible(true);
    }
    
    public boolean isVisible() {
    	return imgAdult.isVisible();
    }
    
    public Button getBtnMovie() {
    	return this.btnMovie;
    }
}
