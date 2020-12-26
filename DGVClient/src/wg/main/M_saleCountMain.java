package wg.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class M_saleCountMain extends Application {

	public static BorderPane root;

	@Override
	public void start(Stage primaryStage) {


		try {
			Parent root = FXMLLoader.load(S_centerMain.class.getResource("../fxml/M_saleCount.fxml"));


			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();

		}
	}





	public static void main(String[] args) {
		launch(args);
	}
}
