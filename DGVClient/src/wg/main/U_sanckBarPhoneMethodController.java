package wg.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class U_sanckBarPhoneMethodController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label phonePayText;

    @FXML
    void initialize() {
        assert phonePayText != null : "fx:id=\"phonePayText\" was not injected: check your FXML file 'U_snackBarPhoneMethod.fxml'.";

    }
    public void setPay_price(String pay_price) {
    	phonePayText.setText(pay_price);
    }
}
