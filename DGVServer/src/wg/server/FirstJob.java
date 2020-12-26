package wg.server;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstJob extends Application implements Job  {
	 
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
 
        long starttimes = System.currentTimeMillis();
        System.out.println("반도 경매 방이 시작되었습니다.");
		
		launch(null);
        
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ServerMain.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("채팅 - Server");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	