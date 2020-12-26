package wg.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class U_paySanckSetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPay;

    @FXML
    void btnPayClick(ActionEvent event) {
    	
    }

    @FXML
    void initialize() {
        assert btnPay != null : "fx:id=\"btnPay\" was not injected: check your FXML file 'U_paySanckSet.fxml'.";
        
    }
}

