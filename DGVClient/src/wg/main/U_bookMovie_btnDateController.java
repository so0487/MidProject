package wg.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class U_bookMovie_btnDateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDate;

    @FXML
    void initialize() {

    }
    
    public void btnDateSetText(String dnd) {
    	this.btnDate.setText(dnd);
    }
    
    public String btnDateGetText() {
    	return this.btnDate.getText();
    }
    
    public void btnDateSetStyle(String style) {
    	this.btnDate.setStyle(style);
    }
    
    public void colorNone(String day) {
    	if(day.equals("토")) {
    		this.btnDate.setStyle("-fx-background-color:none; -fx-text-fill:blue;");
    	}else if(day.equals("일")) {
    		this.btnDate.setStyle("-fx-background-color:none; -fx-text-fill:red;");
    	}else {
    		this.btnDate.setStyle("-fx-background-color:none;");
    	}
    	
    }
    
    public void colorGrey(String day) {
    	if(day.equals("토")) {
    		this.btnDate.setStyle("-fx-background-color:#c2baba; -fx-text-fill:blue;");
    	}else if(day.equals("일")) {
    		this.btnDate.setStyle("-fx-background-color:#c2baba; -fx-text-fill:red;");
    	}else {
    		this.btnDate.setStyle("-fx-background-color:#c2baba;");
    	}
    }
    
}
