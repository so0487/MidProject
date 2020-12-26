package wg.main;

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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import util.AlertUtil;
import wg.movie.service.IMovieService;
import wg.vo.LoginVO;
import wg.vo.MovieVO;

public class G_mediaViewController {

    @FXML
    private StackPane mainColor;
    @FXML
    private AnchorPane mainColor2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img;
    
    @FXML
    private Button bookAladin;

    @FXML
    private Button bookVivarium;

    @FXML
    private Button bookBombshell;

    @FXML
    private Button bookGangChulBi;

    @FXML
    private Button btnMore;

    @FXML
    private MediaView media;

    @FXML
    private BorderPane outer;

    @FXML
    private ImageView pBando;
    
    @FXML
    private ImageView pAladdin;

    @FXML
    private ImageView pVivarium;

    @FXML
    private ImageView pBombshell;

    @FXML
    private ImageView pGangChulBi;
    
    @FXML
    private Label dirLabel;

    @FXML
    private Label dirLabel2;

    @FXML
    private Label actLabel;

    @FXML
    private Label actLabel2;

    @FXML
    private Label genLabel;

    @FXML
    private Label genLabel2;
    
    private G_topController topCtrl;
    
    public void setTopCtrl(G_topController topCtrl) {
    	this.topCtrl = topCtrl;
    }
    
    public BorderPane getOuter() {
		return outer;
	}

	public void setOuter(BorderPane outer) {
		this.outer = outer;
	}

	@FXML
    void play(ActionEvent event) {

    }
	
	@FXML
    void bookAladinClick(ActionEvent event) {
		String movieName = "알라딘";
		String moviePos = "c:/soo/A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/aladdin.jpg";
		goBook(movieName, moviePos);
    }

    @FXML
    void bookBombshellClick(ActionEvent event) {
    	String movieName = "밤쉘: 세상을 바꾼 폭탄 선언";
    	String moviePos = "c:/soo/A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/bombshell.jpg";
    	goBook(movieName, moviePos);
    }

    @FXML
    void bookGangChulBiClick(ActionEvent event) {
    	String movieName = "강철비2";
    	String moviePos = "c:/soo/A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/gangchulbi2.jpg";
    	goBook(movieName, moviePos);
    }

    @FXML
    void bookVivariumClick(ActionEvent event) {
    	String movieName = "비바리움";
    	String moviePos = "c:/soo/A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/vivarium.jpg";
    	goBook(movieName, moviePos);
    }

    @FXML
    void btnMoreClick(ActionEvent event) {
    	goInfo("반도");
    }

    @FXML
    void pAladdinClick(MouseEvent event) {
    	goInfo("알라딘");
    }

    @FXML
    void pBandoClick(MouseEvent event) {
    	goInfo("반도");
    }

    @FXML
    void pBombshellClick(MouseEvent event) {
    	goInfo("밤쉘");
    }

    @FXML
    void pGangChulBiClick(MouseEvent event) {
    	goInfo("강철비2");
    }

    @FXML
    void pVivariumClick(MouseEvent event) {
    	goInfo("비바리움");
    }

    private IMovieService mService;
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	
    	try {
    		//reg = LocateRegistry.getRegistry("192.168.31.32", 9988);
    		reg = LocateRegistry.getRegistry("localhost", 9988);
    		mService = (IMovieService) reg.lookup("movieService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	Media media1 = new Media(getClass().getResource("../img/media1.mp4").toString());
        
        MediaPlayer mediaPlayer = new MediaPlayer(media1);
        media.setMediaPlayer(mediaPlayer);
        
        mediaPlayer.setOnReady(new Runnable() {
          
          @Override
          public void run() {
             
            

             mediaPlayer.setAutoPlay(true);
          }
       });
        
    }
    
    public void goBook(String movieName, String moviePos) {
    	// 비로그인 상태인 경우
		if (LoginVO.getCurrVo() == null) {
			AlertUtil.warnMsg("알림", "로그인 후 이용가능한 서비스입니다.");
			// 로그인화면으로 이동
			try {
				topCtrl.changeColor();
				FXMLLoader loader1 = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
				Parent login = loader1.load();
				G_loginController loginCtrl = loader1.getController();
				
				// 로그인 페이지에 자기 객체, top 객체 넘겨주기
				loginCtrl.setCenterCtrl(this);
				loginCtrl.setTopCtrl(topCtrl);
				
				U_main.root.setCenter(login);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
    	try {
    		// 로그인 상태인 경우
    		// 예매화면으로 이동
    		topCtrl.changeColor();
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_bookMovieMain.fxml"));
			Parent book = loader.load();
			U_bookMovieMainController bookMainCtrl = loader.getController();
			bookMainCtrl.setTopCtrl(topCtrl);
			int cnt = bookMainCtrl.picker(movieName, moviePos);
			if(cnt>0) return;
			
			U_main.root.setCenter(book);
		} catch (IOException e) {
			e.printStackTrace();		
		}
    }
    
    public void goInfo(String movieName) {
    	try {
    		topCtrl.changeColor();
			FXMLLoader loader = new FXMLLoader(G_mediaViewController.class.getResource("../fxml/U_movieMain.fxml"));
			Parent info = loader.load();
			U_movieController infoCtrl = loader.getController();
			
			List<MovieVO> list = mService.umovNameList(movieName);
			MovieVO mvo = list.get(0);
			
			infoCtrl.callInfo(mvo);
			
			U_main.root.setCenter(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void changeColor() {
    	mainColor.setStyle("-fx-background-color:white;");
    	mainColor2.setStyle("-fx-background-color:white;");
    	dirLabel.setStyle("-fx-text-fill:black;");
    	dirLabel2.setStyle("-fx-text-fill:black;");
    	actLabel.setStyle("-fx-text-fill:black;");
    	actLabel2.setStyle("-fx-text-fill:black;");
    	genLabel.setStyle("-fx-text-fill:black;");
    	genLabel2.setStyle("-fx-text-fill:black;");
    }
    
    
}
