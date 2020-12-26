package wg.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.AlertUtil;
import wg.book_detail.service.IBook_DetailService;
import wg.bookmovie.service.IBookMovieService;
import wg.pay.service.IPayService;
import wg.seatSch.service.ISeatSchService;
import wg.vo.BookMovieViewVO;
import wg.vo.LoginVO;
import wg.vo.ReviewVO;

public class U_myBookMovieOneController {
	
	private BookMovieViewVO bookMovieViewVo;
    public BookMovieViewVO getBookMovieViewVo() {
		return bookMovieViewVo;
	}
    
    private U_myBookMovieMainController parentCtrl;
    public void setParentCtrl(U_myBookMovieMainController parentCtrl) {
    	this.parentCtrl = parentCtrl;
    }
    public U_myBookMovieMainController getParentCtrl() {
    	return parentCtrl;
    }


	public void setBookMovieViewVo(BookMovieViewVO bookMovieViewVo) {
		this.bookMovieViewVo = bookMovieViewVo;
		
		File file = new File(bookMovieViewVo.getMovie_poster());
		Image image = new Image(file.toURI().toString());
    	posterImg.setImage(image);
    	txtMyBmId.setText(bookMovieViewVo.getBook_id());
    	txtBookDate.setText(bookMovieViewVo.getBook_time());
    	txtMovieName.setText(bookMovieViewVo.getMovie_name());
    	txtWatchDate.setText(bookMovieViewVo.getShow_time());
    	txtTheaterName.setText(bookMovieViewVo.getTheater_name());
    	txtPrice.setText(String.valueOf(bookMovieViewVo.getPay_price())) ;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView posterImg;

    @FXML
    private Label txtMyBmId;

    @FXML
    private Label txtBookDate;

    @FXML
    private Label txtMovieName;

    @FXML
    private Label txtTheaterName;

    @FXML
    private Label txtWatchDate;

    @FXML
    private Label txtPrice;

    @FXML
    private Label refStamp;
    
    @FXML
    private Button btnReview;

    @FXML
    private Button btnRefund;
    
    @FXML
    private Label lblCancel;

    private ReviewVO rvo;
    public void setRvo(ReviewVO rvo) {
    	this.rvo = rvo;
    }
    
    
    @FXML
    void dataCancel(MouseEvent event) {

    }
    
    @FXML
    private Label movieCancel;   //평상시엔 안보이게, 환불했으면 예매취소보이게 

    IPayService payService; 
    ISeatSchService seatSchService; 
    IBook_DetailService book_detailService; 
    
    //환불할때 
    @FXML
    void dataRefund(ActionEvent event) throws RemoteException {
    	String book_id = bookMovieViewVo.getBook_id();
	    //상영시간 20분 이전에만 환불할수있도록 조건
    	// 날짜및시간 비교를 위한 양식설정
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date now = new Date();
			
			// 현재 시간에 20분을 더한다.
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.MINUTE, 18);
			Date add20 = new Date(cal.getTimeInMillis());
			
			// 영화시작 시간을 가져온다.
			Date showTime = format.parse(bookMovieViewVo.getShow_time());
			
			if(add20.getTime()<showTime.getTime()) {
		    	int cnt1 = 0;
		    	int cnt2 = 0;
		    	cnt1 = seatSchService.updateToN(book_id);
		    	cnt2 = payService.updatePayRefund(book_id);
		    	if(cnt1>0&&cnt2>0) {
		    		AlertUtil.infoMsg("작업결과", "환불이 완료되었습니다.");
		    		parentCtrl.setBookMovieList();
		    	}
			}else {
	    		AlertUtil.warnMsg("환불불가", "인터넷 예매는 온라인 상으로 영화상영 20분 전까지 취소 가능하며 20분 후에는 현장에서 취소를 하셔야 합니다.");
	    	}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    
    private Stage mainStage;
    //리뷰작성 또는 리뷰 조회
    @FXML
    void dataReview(ActionEvent event) {
		try {
			mainStage = (Stage) btnReview.getScene().getWindow();
			// 리뷰 창
			Stage secStage = new Stage(StageStyle.UTILITY);
			secStage.initModality(Modality.WINDOW_MODAL);
			secStage.initOwner(mainStage);
			FXMLLoader loader = new FXMLLoader(U_myBookMovieOneController.class.getResource("../fxml/U_review2.fxml"));
			Parent childRoot = loader.load();
			U_reviewController2 reviewCtrl = loader.getController();
			reviewCtrl.setBookMovieViewVo(bookMovieViewVo);
			reviewCtrl.setMovieName(bookMovieViewVo.getMovie_name());
			reviewCtrl.setMainCtrl(this);
			Scene childScene = new Scene(childRoot);
			
			// 이미 리뷰를 작성한 경우 
			if(btnReview.getText().equals("리뷰조회")) {
				// 작성한 리뷰를 띄운다
				secStage.setScene(childScene);
				secStage.setTitle("리뷰조회");
				// 리뷰 내용
				reviewCtrl.setReview(rvo.getRev_content(), rvo.getRev_rating());
				// disable
				secStage.show();
				
			}else {
				// 리뷰를 작성해야 하는 경우
				// 상영시간이  현재를 지나고, 상영일자+7일이 현재보다 클 때(지금이 더 작아야함)
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					// 상영시간
					Date showTime = format.parse(bookMovieViewVo.getShow_time());
					
					// 현재 시간에 7일을 더한다.
					Calendar cal = Calendar.getInstance();
					cal.setTime(showTime);
					cal.add(Calendar.DATE, 7);
					Date add7 = new Date(cal.getTimeInMillis());
					
					Date now = new Date();
					
					// 상영시간 이전이면
					if(showTime.getTime()>now.getTime()) {
						AlertUtil.warnMsg("리뷰작성불가", "관람 전에는 리뷰작성이 불가능합니다.");
						return;
					}else if(add7.getTime()<now.getTime()) {
						// 상영 후 7일이 지났으면
						AlertUtil.warnMsg("리뷰작성 기한만료", "리뷰작성은 상영 후 7일까지 가능합니다.");
						return;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				secStage.setScene(childScene);
				secStage.setTitle("리뷰작성");
				secStage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    IBookMovieService service;
    
    @FXML
    void initialize() throws RemoteException {
    	Registry reg = null;
    	try {
    		reg = LocateRegistry.getRegistry("localhost",9988);
            service = (IBookMovieService) reg.lookup("bookMovieService");
            payService = (IPayService) reg.lookup("payService");
            seatSchService = (ISeatSchService) reg.lookup("seatSchService");
            book_detailService = (IBook_DetailService) reg.lookup("book_detailService");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void setVisible() {
    	refStamp.setVisible(true);
    	btnRefund.setVisible(false);
    	btnReview.setVisible(false);
    }
    
    public void hasReview() {
    	btnReview.setText("리뷰조회");
    }
}
