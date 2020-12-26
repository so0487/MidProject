package wg.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class U_lnfMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader2 = new FXMLLoader(U_main.class.getResource("../fxml/U_lnfMain.fxml"));
			Parent top = loader2.load();
			
			
			Scene scene = new Scene(top,1200,1000);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
