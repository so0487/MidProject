package wg.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import wg.event.service.IEventService;
import wg.movie.service.IMovieService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.EventVO;
import wg.vo.SnackSetVO;

public class U_eventOneController {
	private EventVO eventVo;

    public EventVO getEventVo() {
		return eventVo;
	}

	public void setEventVo(EventVO eventVo) {
		this.eventVo = eventVo;
		
		File file = new File(eventVo.getEvent_photo());
		Image image = new Image(file.toURI().toString());
		
//		Image image = new Image(U_eventOneController.class.getResourceAsStream("../img/"+eventVo.getEvent_photo()));
    	eventImg.setImage(image );
    	System.out.println(eventVo.getEvent_photo());
		
    	eventName.setText(eventVo.getEvent_title());
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView eventImg;


    @FXML
    private Label eventName;

    
    IEventService service;
    
    @FXML
    void click(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(U_eventOneController.class.getResource("../fxml/U_eventBig.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    
    
    
    @FXML
    void initialize() throws RemoteException {
    	
    }
    
}
