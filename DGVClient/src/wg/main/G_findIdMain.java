package wg.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class G_findIdMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 
				FXMLLoader.load(
						G_findIdMain.class.getResource("../fxml/G_findId.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("아이디찾기");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
