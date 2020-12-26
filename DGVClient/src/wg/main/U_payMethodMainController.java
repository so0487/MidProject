package wg.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class U_payMethodMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> cbCoupon;

    @FXML
    private Label disPrice;

    @FXML
    private ComboBox<?> cbPayMethod;

    @FXML
    private Button pay;

    @FXML
    private Button cancel;

    @FXML
    void goCancel(ActionEvent event) {

    }

    @FXML
    void goPay(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
