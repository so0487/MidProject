package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
	public static void infoMsg(String header, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		
		info.setTitle("INFORMATION");
		info.setHeaderText(header);
		info.setContentText(msg);
		info.showAndWait(); 
	}
	
	public static void errorMsg(String header, String msg) {
		Alert error = new Alert(AlertType.ERROR);
		
		error.setTitle("ERROR");
		error.setHeaderText(header);
		error.setContentText(msg);
		error.showAndWait();
	}
	
	public static void warnMsg(String header, String msg) {
		Alert warn = new Alert(AlertType.WARNING);
		
		warn.setTitle("WARNING");
		warn.setHeaderText(header);
		warn.setContentText(msg);
		warn.showAndWait();
	}
	
	public static boolean confirm(String header, String msg) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("CONFIRMATION");
		confirm.setHeaderText(header);
		confirm.setContentText(msg);
		
		ButtonType confirmResult = confirm.showAndWait().get();
		
		boolean select = false;
		
		if(confirmResult == ButtonType.OK) { 
			select = true;
		}
		
		return select;
	}
	
	public static String prompt(String msg) {
		TextInputDialog prompt = new TextInputDialog("기본값");
		
		prompt.setTitle("PROMPTâ");
		prompt.setHeaderText(msg);
		prompt.setContentText("입력 : ");
		
		Optional<String> result = prompt.showAndWait();
		
		String value = null;
		if(result.isPresent()) {  
			value = result.get(); 
		}
		
		return value;
	}
	
}
