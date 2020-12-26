package wg.main;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class M_main extends Application {

   @Override
   public void start(Stage primaryStage) {
      
      try {
         BorderPane root = new BorderPane();
         
         //center
         FXMLLoader loader1 = new FXMLLoader(M_main.class.getResource("../fxml/M_genre.fxml"));
         //FXMLLoader loader1 = new FXMLLoader(M_main.class.getResource("../fxml/U_bookMovieMain.fxml"));
         
         Parent center = loader1.load();
         root.setCenter(center);
         
         //top
         FXMLLoader loader2 = new FXMLLoader(M_main.class.getResource("../fxml/M_top_white.fxml"));
         AnchorPane top = loader2.load();
         root.setTop(top);

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
