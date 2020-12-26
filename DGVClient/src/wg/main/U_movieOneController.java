package wg.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.AlertUtil;
import wg.movie.service.IMovieService;
import wg.vo.LoginVO;
import wg.vo.MovieVO;

public class U_movieOneController {
	private MovieVO movieVo;
	
	public MovieVO getMvoieVo() {
		return movieVo;
	}
	
	public void setMovieVo(MovieVO movieVo) {
		this.movieVo = movieVo;
		String path = movieVo.getMovie_poster();
		
		if(path == null ) {
			path = "c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/NoImage.jpg";
		}
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		movieImg.setImage(image);
		movieName.setText(movieVo.getMovie_name()+" | 개봉일 "+movieVo.getMovie_release().substring(0,11));
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView movieImg;

    @FXML
    private Label movieName;

    @FXML
    private Button btnBook;
    
    @FXML
    private Label movRel;
    
    private G_topController topCtrl;
    public void setTopCtrl(G_topController topCtrl) {
    	this.topCtrl=topCtrl;
    }
    
    
    @FXML
    void btnBookClick(ActionEvent event) {
    	try {
    		if(LoginVO.getCurrVo()==null) {
    			// 비로그인 상태인 경우
    			if (LoginVO.getCurrVo() == null) {
    				AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
    				try {
    					FXMLLoader loader1 = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
    					Parent login = loader1.load();
    					G_loginController loginCtrl = loader1.getController();
    					loginCtrl.setTopCtrl(topCtrl);
    					
    					U_main.root.setCenter(login);
    					return;
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    				return;
    			}
    		}
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_bookMovieMain.fxml"));
			Parent book = loader.load();
			U_bookMovieMainController bookMainCtrl = loader.getController();
			
			
			String movieName = movieVo.getMovie_name();
			String moviePos = movieVo.getMovie_poster();
			bookMainCtrl.setTopCtrl(topCtrl);
			
			int cnt = bookMainCtrl.picker(movieName, moviePos);
			if(cnt>0) return;
			
			U_main.root.setCenter(book);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }

    
    public void setBtn() {
    	btnBook.setText("상영예정");
    	btnBook.setDisable(true);
    	btnBook.setStyle("-fx-background-color:white; -fx-border-color:gray;");
    }
    
    public void setBtnClear() {
    	btnBook.setText("상영종료");
    	btnBook.setDisable(true);
    	btnBook.setStyle("-fx-background-color:white; -fx-border-color:gray;");
    }
    
    public void setBtnNo() {
    	btnBook.setText("미상영");
    	btnBook.setDisable(true);
    	btnBook.setStyle("-fx-background-color:white; -fx-border-color:gray;");
    }
    
}

