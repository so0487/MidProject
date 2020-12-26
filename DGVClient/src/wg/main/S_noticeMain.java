package wg.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class S_noticeMain extends Application{

	@Override

	public void start(Stage primaryStage) throws Exception {

		try {

			FXMLLoader loader2 = new FXMLLoader(S_noticeMain.class.getResource("../fxml/S_NoticeMain.fxml"));
			
			Parent top = loader2.load();


			Scene scene = new Scene(top);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			//primaryStage.sizeToScene();
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		launch(args);
	}

}
