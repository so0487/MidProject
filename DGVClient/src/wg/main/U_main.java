package wg.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class U_main extends Application {

	public static BorderPane root;

	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			
			//top
			FXMLLoader loader2 = new FXMLLoader(U_main.class.getResource("../fxml/G_top.fxml"));
			AnchorPane top = loader2.load();
			
			// topCtrl객체 얻기
			G_topController topCtrl = loader2.getController();
			root.setTop(top);
			
			
			//center
			FXMLLoader loader1 = new FXMLLoader(U_main.class.getResource("../fxml/G_center.fxml"));
			StackPane center = loader1.load();
			G_mediaViewController centerCtrl = loader1.getController();
			
			// center에게 topCtrl 넘겨주기
			centerCtrl.setTopCtrl(topCtrl);
			root.setCenter(center);
			
			Scene scene = new Scene(root,1200,1000);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
