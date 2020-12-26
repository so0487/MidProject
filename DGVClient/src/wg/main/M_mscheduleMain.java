package wg.main;

import java.time.YearMonth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class M_mscheduleMain extends Application{

	//상영스케줄확인용
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_mschedule.fxml"));
		Parent center = loader.load();
		root.setCenter(center);
		
		M_mscheduleController controller = loader.getController();
		//controller.calen.setCenter(new FullCalendarView(YearMonth.now()).getView());
		controller.caln2.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
		
		//top
        FXMLLoader loader2 = new FXMLLoader(M_main.class.getResource("../fxml/M_top_white.fxml"));
        AnchorPane top = loader2.load();
        root.setTop(top);
        
        Scene scene = new Scene(root,1200,1000);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
