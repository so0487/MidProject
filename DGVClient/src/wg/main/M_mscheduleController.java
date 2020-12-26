package wg.main;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class M_mscheduleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    BorderPane calen;

    @FXML
    StackPane caln2;

    @FXML
    private Button btnSch;

    @FXML
    private Button btnSmovie;

    @FXML
    void btnSchClick(ActionEvent event) throws IOException {
    	calen.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_mschedule.fxml"));
    	Parent mschedule = loader.load(); 
    	
    	M_mscheduleController controller = loader.getController();
		controller.caln2.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
		
		calen.setCenter(mschedule);
    }

    @FXML
    void btnSmovieClick(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_smovie.fxml"));
    	Parent smoive = loader.load();
    	
    	calen.setRight(smoive);
    	caln2.getChildren().remove(0);
    	
    }

    @FXML
    void initialize() {

    }
}


