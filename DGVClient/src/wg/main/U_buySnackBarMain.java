package wg.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class U_buySnackBarMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 
					FXMLLoader.load(
							G_findIdMain.class.getResource("../fxml/U_buysnackbar.fxml"));
				
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("스낵바세트구매");
				primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}