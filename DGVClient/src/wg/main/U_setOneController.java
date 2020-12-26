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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import wg.movie.service.IMovieService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.SnackSetVO;

public class U_setOneController {
	private SnackSetVO snackSetVo;

    public SnackSetVO getSnackSetVo() {
		return snackSetVo;
	}

	public void setSnackSetVo(SnackSetVO snackSetVo) {
		this.snackSetVo = snackSetVo;
		File file = new File(snackSetVo.getSet_photo());
		Image image = new Image(file.toURI().toString());
    	setImg.setImage(image );
//    	System.out.println(snackSetVo.getSet_photo());
    	setId.setText(snackSetVo.getSet_id());
    	setName.setText(snackSetVo.getSet_name());
    	price.setText(snackSetVo.getSet_price()+"원");
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView setImg;

    @FXML
    private Label setId;

    @FXML
    private Label setName;

    @FXML
    private Label price;
    
    ISnackSetService service;
    
    @FXML
    void click(MouseEvent event) {
//		try {
//			FXMLLoader loader = new FXMLLoader(U_setOneController.class.getResource("../fxml/U_buysnackbar.fxml"));
//			StackPane root;
//			root = loader.load();
//			Scene scene = new Scene(root);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
    }
    
    
    
    
    @FXML
    void initialize() throws RemoteException {
    	
    }
    
}
