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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import wg.bookmovie.service.IBookMovieService;
import wg.movie.service.IMovieService;
import wg.vo.BookMovieVO;

public class U_bookMovieOneController {
	private BookMovieVO bookMovieVo;

    public BookMovieVO getBookMovieVo() {
		return bookMovieVo;
	}

//	public void bookMovieVo(BookMovieVO bookMovieVo) {
//		this.bookMovieVo = bookMovieVo;
//		File file = new File(bookMovieVo.getSet_photo());
//		Image image = new Image(file.toURI().toString());
//    	setImg.setImage(image );
//    	System.out.println(bookMovieVo.getSet_photo());
//    	setId.setText(bookMovieVo.getSet_id());
//    	setName.setText(bookMovieVo.getSet_name());
//    	price.setText(Integer.toString(bookMovieVo.getSet_price()));
//	}

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
    
    IBookMovieService service;
    
//    @FXML
//    void click(MouseEvent event) {
//    	FXMLLoader loader = new FXMLLoader(U_bookMovieOneController.class.getResource("../fxml/U_buysnackbar.fxml"));
//		ScrollPane root;
//		try {
//			root = loader.load();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//    }
    
    @FXML
    void initialize() throws RemoteException {
    	
    }
    
}
