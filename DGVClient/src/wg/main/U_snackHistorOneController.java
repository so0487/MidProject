package wg.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.AlertUtil;
import wg.book_detail.service.IBook_DetailService;
import wg.bookmovie.service.IBookMovieService;
import wg.buysnack.service.IBuySnackService;
import wg.hint.service.IHintService;
import wg.member.service.IMemberService;
import wg.movie.service.IMovieService;
import wg.pay.service.IPayService;
import wg.seatSch.service.ISeatSchService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;
import wg.vo.LoginVO;
import wg.vo.MemberVO;
import wg.vo.PayVO;
import wg.vo.SeatSchVO;
import wg.vo.SnackUserViewVO;

public class U_snackHistorOneController {
	
	private SnackUserViewVO snackUserViewVo;

    public SnackUserViewVO getSnackUserViewVo() {
		return snackUserViewVo;
	}


	public void setSnackUserViewVo(SnackUserViewVO snackUserViewVo) {
		this.snackUserViewVo = snackUserViewVo;
		
		File file = new File(snackUserViewVo.getSet_photo());
		Image image = new Image(file.toURI().toString());
	//	Image image = new Image(U_myBookMovieOneController.class.getResourceAsStream("../img/"+bookMovieViewVo.getMovie_poster()));
		snackImg.setImage(image);
    	System.out.println(snackUserViewVo.getSet_photo());
    	lblSnackName.setText(snackUserViewVo.getSet_name());
    	lblBuyQty.setText(String.valueOf(snackUserViewVo.getBuydet_qty()));
    	lblBuydetNo.setText(String.valueOf(snackUserViewVo.getBuydet_no()));
    	lblPrice.setText(String.valueOf(snackUserViewVo.getPay_price()));
	}

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ImageView snackImg;

	    @FXML
	    private Label lblBuydetNo;

	    @FXML
	    private Label lblSnackName;

	    @FXML
	    private Label lblBuyQty;

	    @FXML
	    private Label lblPrice;

	    @FXML
	    private Button btnRefund;

	    @FXML
	    private Label lblCancel;

    @FXML
    void dataCancel(MouseEvent event) {

    }
    
    @FXML
    private Label movieCancel;   //평상시엔 안보이게, 환불했으면 예매취소보이게 

//    IPayService payService; 
    IBuySnackService buySnackService; 
//    ISnackSetService snackService; 
    
    //환불할때 
    @FXML
    void dataRefund(ActionEvent event) throws RemoteException {
    	
    	
//    	String id = snackUserViewVo.getm;
////    	int payNo = bookMovieViewVo.getPay_no();
//    	//pay_refund, pay_price 수정하기위해서 
//	   // int cnt = payService.updatePayRefund(payNo);
//	    
//	    
//	    
//	    //sseat_status수정하기위해서 
//	    SeatSchVO sschvo = new SeatSchVO(); 
//	    
//	    int cnt2 = seatSchService.updateToN(sschvo.getSseat_no()); 
//	    
//	    //상영시간 20분 이전에만 환불할수있도록..
//	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			Date exp = (Date) format.parse(bookMovieViewVo.getShow_time());
//			System.out.println(exp);
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(exp);
//			cal.add(Calendar.MINUTE, -20);
//			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String to = fm.format(cal.getTime());
//	//		couTime.setText(to);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	    
//	    
//	    
//	    if(cnt>0) {
//			AlertUtil.infoMsg("작업결과", "환불성공");
//		}else {
//			AlertUtil.warnMsg("작업결과", "환불실패...");
//		}
    
    }
    
    
    private Stage mainStage;
    
    
    
    
    @FXML
    void initialize() throws RemoteException {
    	Registry reg = null;
    	try {
    		reg = LocateRegistry.getRegistry("localhost",9988);
            buySnackService = (IBuySnackService) reg.lookup("snackService");
            //buy_detailService = (IBook_DetailService) reg.lookup("buy_detailService");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void setVisible() {
    	
    }
    
    
}
