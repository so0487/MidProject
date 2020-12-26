package wg.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class U_bookMovie_theaterBoxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTheaterName;
    
    @FXML
    private VBox box;

    @FXML
    void initialize() {

    }
    
    public void SetLblTheaterName (String theater_name) {
    	this.lblTheaterName.setText(theater_name);
    }
    
    public String GetLblTheaterName () {
    	return lblTheaterName.getText();
    }
    
    public void addHbox(Node node) {
    	this.box.getChildren().add(node);
    }
    
}
