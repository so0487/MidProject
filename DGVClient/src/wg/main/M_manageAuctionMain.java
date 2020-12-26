package wg.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class M_manageAuctionMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 
				FXMLLoader.load(
						M_manageAuctionMain.class.getResource("../fxml/M_manageAuction.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("경매방관리");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}