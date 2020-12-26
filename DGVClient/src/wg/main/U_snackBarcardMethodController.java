package wg.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class U_snackBarcardMethodController {
	U_payController mainCtrl;
	
	U_paySnackBarController main;
	
	
	
	public U_paySnackBarController getMain() {
		return main;
	}

	public void setMain(U_paySnackBarController main) {
		this.main = main;
	}

	public U_payController getMainCtrl() {
		return mainCtrl;
	}

	public void setMainCtrl(U_payController mainCtrl) {
		this.mainCtrl = mainCtrl;
	}
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cardCombo;
    
    private String selectedCard;
    
    public static String card;
    @FXML
    void initialize() {
    	cardCombo.getItems().addAll("삼성카드", "BC카드", "현대카드", "롯대카드", "국민카드");
    	
    	cardCombo.valueProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			selectedCard = cardCombo.getValue();
    			card = cardCombo.getValue();
    		}
		});
    }
    
    public String getSelectedCard() {
    	return selectedCard;
    }
    
    
    
}

