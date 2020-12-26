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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.movie.service.IMovieService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.CartSnackSetVO;
import wg.vo.SnackSetVO;

public class U_cartOneController {
	private CartSnackSetVO snackSetVo;

    public CartSnackSetVO getSnackSetVo() {
		return snackSetVo;
	}

	public void setCartSnackSetVO(CartSnackSetVO snackSetVo) {
		this.snackSetVo = snackSetVo;
		File file = new File(snackSetVo.getSet_photo());
		Image image = new Image(file.toURI().toString());
    	setImg.setImage(image );
    	setId.setText(snackSetVo.getSet_id());
    	setName.setText(snackSetVo.getSet_name());
    	price.setText(snackSetVo.getSet_price()+"원");
    	count.setText(snackSetVo.getNum()+"개");
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
    private Label count;

    @FXML
    private Label price;
    
    @FXML
    private Button cancel;
    
    ISnackSetService service;
    
    @FXML
    void goCancel(ActionEvent event) {
    
    	for (int i = 0; i < U_buySnackBarController.cartList.size(); i++) {
    		if(snackSetVo.getSet_id().equals(U_buySnackBarController.cartList.get(i).getSet_id()) && snackSetVo.getNum()== U_buySnackBarController.cartList.get(i).getNum()) {
    			U_buySnackBarController.cartList.remove(i);
    			try {
    				FXMLLoader loader = new FXMLLoader(U_buySnackBarController.class.getResource("../fxml/U_cartSnackSetMain.fxml"));
    				U_paySnackBarController up = loader.getController();
    				StackPane center = loader.load();
    				U_main.root.setCenter(center);
    				
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		}
		}
    	
    }
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
