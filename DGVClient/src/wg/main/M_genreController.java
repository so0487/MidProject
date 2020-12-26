package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.genre.service.IGenreService;
import wg.vo.GenreVO;
import wg.vo.TheaterVO;

public class M_genreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane scroll;

    @FXML
    private Button btnGenre;

    @FXML
    private Button btnMovie;

    @FXML
    private Button btnTheater;

    @FXML
    private Button btnSeat;

    @FXML
    private Button btnMSchedule;

    @FXML
    private Button btnReview;

    @FXML
    private Button btnDiscount;

    @FXML
    private Button btnSnackSet;

    @FXML
    private Button btnMember;

    @FXML
    private Button btnCoupon;

    @FXML
    private Button btnIssue;

    @FXML
    private Button btnStaff;

    @FXML
    private Button btnOnOff;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnStats;

    @FXML
    private Button btnBMnR;

    @FXML
    private Button btnBSnR;

    @FXML
    private Button btnAuction;
    
    @FXML
    private BorderPane outerBox;
    
    @FXML
    private BorderPane genre;

    @FXML
    private TextField tf_gen_id;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCxl;

    @FXML
    private TextField tf_gen_name;

    @FXML
    private TableView<GenreVO> tb_Genre;

    @FXML
    private TableColumn<?, ?> col_gen_id;

    @FXML
    private TableColumn<?, ?> col_gen_name;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;

    @FXML
    void tbGenreClick(MouseEvent event) {
    	if(tb_Genre.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	GenreVO gvo = tb_Genre.getSelectionModel().getSelectedItem();
    	tf_gen_id.setText(gvo.getGen_id());
    	tf_gen_name.setText(gvo.getGen_name());
    }
    
    @FXML
    void btnOkClick(ActionEvent event) {
    	String gen_id = tf_gen_id.getText();
    	String gen_name = tf_gen_name.getText();
    	
    	if(gen_name.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "장르명을 입력하세요.");
    		return;
    	}
    	GenreVO gvo = new GenreVO();
    	gvo.setGen_id(gen_id);
    	gvo.setGen_name(gen_name);
    	
    	if("add".equals(func)) {
    		try {
				int cnt = service.insertGenre(gvo);
				if(cnt>0) {
					AlertUtil.infoMsg("작엽결과", "장르 추가 완료");
				}else {
					AlertUtil.infoMsg("작엽결과", "장르 추가 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}else if("mod".equals(func)) {
    		try {
				int cnt = service.updateGenre(gvo);
				if(cnt>0) {
					AlertUtil.infoMsg("작엽결과", "장르 수정 완료");
				}else {
					AlertUtil.infoMsg("작엽결과", "장르 수정 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	
    	getAllGenreList();
    	tf_gen_id.clear();
    	tf_gen_name.clear();
    	
    	tf_gen_name.setDisable(true);
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    }
    
    @FXML
    void btnCxlClick(ActionEvent event) {
    	tf_gen_name.setDisable(true);
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	
    	if(!tb_Genre.getSelectionModel().isEmpty()) {
    		GenreVO gvo = tb_Genre.getSelectionModel().getSelectedItem();
    		
    		tf_gen_id.setText(gvo.getGen_id());
        	tf_gen_name.setText(gvo.getGen_name());
    	}
    	func="";
    }
    

    @FXML
    void btnAddClick(ActionEvent event) {
    	String gen_id = "";
		try {
			gen_id = service.getMaxGen_id();
			tf_gen_id.setText(gen_id);
			tf_gen_name.clear();
			tf_gen_name.setDisable(false);
			tf_gen_name.requestFocus();
			
			btnOk.setDisable(false);
			btnCxl.setDisable(false);
			func = "add";
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void btnModClick(ActionEvent event) {
    	if(tb_Genre.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("장르선택", "수정할 장르를 선택해주십시오.");
    		return;
    	}
    	tf_gen_name.setDisable(false);
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	
    	func = "mod";
    }
    
    @FXML
    void btnDelClick(ActionEvent event) {
    	if(tb_Genre.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("장르 선택", "삭제할 장르를 선택하십시오.");
    		return;
    	}
    	
    	String gen_id = tf_gen_id.getText();
    	try {
			int cnt = service.deleteGenre(gen_id);
			if(cnt>0) {
	    		AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");
	    		
	    		getAllGenreList();
	    		
	    		tf_gen_id.clear();
	    		tf_gen_name.clear();
	    		
	    		tf_gen_name.setDisable(true);
	        	btnAdd.setDisable(false);
	        	btnMod.setDisable(false);
	        	btnDel.setDisable(false);
	        	
	        	btnOk.setDisable(true);
	        	btnCxl.setDisable(true);
	    	}else {
	    		AlertUtil.warnMsg("작업결과", "삭제를 실패하였습니다.");
	    	}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnDiscountClick(ActionEvent event) throws IOException {
    	// 백그라운드 색 변경
    	changeColor();
    	btnDiscount.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	// 화면변경
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_discount.fxml"));
    	Parent discount = loader.load(); 
    	outerBox.setCenter(discount);
    }

    @FXML
    void btnGenreClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnGenre.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	outerBox.setCenter(genre);
    }

    @FXML
    void btnAuctionClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnAuction.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_bidMain.fxml"));
    	Parent book = loader.load(); 
    	
    	outerBox.setCenter(book);
    	
    }

    @FXML
    void btnBMnRClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnBMnR.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_bookMovieMain.fxml"));
    	Parent book = loader.load(); 
    	
    	outerBox.setCenter(book);
    }

    @FXML
    void btnBSnRClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnBSnR.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_buySnackSetMain.fxml"));
    	Parent buy = loader.load(); 
    	
    	outerBox.setCenter(buy);
    }

    @FXML
    void btnCouponClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnCoupon.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_coupon.fxml"));
    	Parent coupon = loader.load(); 
    	
    	outerBox.setCenter(coupon);
    }

    @FXML
    void btnIssueClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnIssue.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	// 화면변경
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_issue.fxml"));
    	Parent issue = loader.load(); 
    	outerBox.setCenter(issue);
    }

    @FXML
    void btnMScheduleClick(ActionEvent event) throws IOException {
    	changeColor();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_mschedule.fxml"));
    	Parent mschedule = loader.load(); 
    	
    	M_mscheduleController controller = loader.getController();
		controller.caln2.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
    	
    	outerBox.setCenter(mschedule);
    	
    	btnMSchedule.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    }

    @FXML
    void btnMemberClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnMember.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_memberUpdate.fxml"));
    	Parent member = loader.load(); 
    	outerBox.setCenter(member);
    	
    }
    
    @FXML
    void btnMovieClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnMovie.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_movie.fxml"));
    	Parent movie = loader.load(); 
    	outerBox.setCenter(movie);
    }

    @FXML
    void btnOnOffClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnOnOff.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_onOff.fxml"));
    	Parent onOff = loader.load(); 
    	outerBox.setCenter(onOff);
    }

    @FXML
    void btnReviewClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnReview.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_review.fxml"));
    	Parent coupon = loader.load(); 
    	
    	outerBox.setCenter(coupon);
    }

    @FXML
    void btnSalesClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnSales.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_saleCount.fxml"));
    	Parent msale  = loader.load(); 
    	outerBox.setCenter(msale);
    }

    @FXML
    void btnSeatClick(ActionEvent event) throws IOException {
    	changeColor();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_seat.fxml"));
    	Parent seat = loader.load(); 
    	
    	outerBox.setCenter(seat);
    	
    	btnSeat.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    }

    @FXML
    void btnSnackSetClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnSnackSet.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	// 화면변경
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_snackSet.fxml"));
    	Parent snackSet = loader.load(); 
    	outerBox.setCenter(snackSet);
    }

    @FXML
    void btnStaffClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnStaff.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	// 화면변경
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_staffUpdate.fxml"));
    	Parent staff = loader.load(); 
    	outerBox.setCenter(staff);
    }

    @FXML
    void btnStatsClick(ActionEvent event) {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnStats.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	// 화면변경
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_bookGenderCnt.fxml"));
			Parent stats;
			stats = loader.load();
			outerBox.setCenter(stats);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

    }

    @FXML
    void btnTheaterClick(ActionEvent event) throws IOException {
    	// 버튼백그라운드 색 변경
    	changeColor();
    	btnTheater.setStyle("-fx-border-color:#c7c9c8; -fx-background-color: #f2d2b3;");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_theater.fxml"));
    	Parent theater = loader.load(); 
    	
    	outerBox.setCenter(theater);
    }

    private ObservableList<GenreVO> genList;
    private IGenreService service;
    private String func = "";
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IGenreService) reg.lookup("genreService");
			
			col_gen_id.setCellValueFactory(new PropertyValueFactory<>("gen_id"));
			col_gen_name.setCellValueFactory(new PropertyValueFactory<>("gen_name"));
			
			getAllGenreList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
    
    private void getAllGenreList() {
    	List<GenreVO> genData;
    	try {
			genData = service.getAllGenre();
			genList = FXCollections.observableArrayList(genData);
			tb_Genre.setItems(genList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    //백그라운드 변경 메서드
    private void changeColor() {
    	btnGenre.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnMovie.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnTheater.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnSeat.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnMSchedule.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnReview.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnDiscount.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnSnackSet.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnMember.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnCoupon.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnIssue.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnStaff.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnOnOff.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnSales.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnStats.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnBMnR.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnBSnR.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    	btnAuction.setStyle("-fx-border-color:#c7c9c8; -fx-background-color:white;");
    }
}


