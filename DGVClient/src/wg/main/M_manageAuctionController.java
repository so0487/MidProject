package wg.main;

import java.net.URL;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.auction.service.IAuctionService;
import wg.seatSch.service.ISeatSchService;
import wg.smovie.service.ISMovieService;
import wg.vo.AuctionTTVO;
import wg.vo.AuctionVO;
import wg.vo.SeatSchVO;
import wg.vo.SmovieShortInfoVO;
import wg.vo.TheaterVO;



public class M_manageAuctionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane scroll;

    @FXML
    private TableView<AuctionVO> table;

    @FXML
    private TableColumn<?, ?> aucNoCol;

    @FXML
    private TableColumn<?, ?> sseatCol;

    @FXML
    private TableColumn<?, ?> aucTitleCol;

    @FXML
    private TableColumn<?, ?> aucseatCol;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> endCol;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;
   
    @FXML
    private Button btnOk;

    @FXML
    private Button btnCxl;
    
    @FXML
    private ComboBox<String> movie;

    @FXML
    private ComboBox<String> date;

    @FXML
    private ComboBox<String> theatertime;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<String> seat;
    
    @FXML
    void dateSelect(ActionEvent event) throws RemoteException {
    	if(movie.getValue()==null) {
    		AlertUtil.warnMsg("알림","영화를 선택해주세요." );
    		return;
    	}else {
    		getAllTT();

    	}

    }
    

    @FXML
    void ttSelect(ActionEvent event) throws RemoteException {
    	if(theatertime.getValue()==null) {
    		AlertUtil.warnMsg("알림","관/시간을 선택해주세요." );
    		return;
    	}else {
    		getseat();
    	}

    }


    AuctionVO auc;
    ISeatSchService seatService;

    @FXML
    void add(ActionEvent event) throws RemoteException {
    	
    	
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	table.setDisable(true);
    	
    	movie.setDisable(false);
    	date.setDisable(false);
    	theatertime.setDisable(false);
    	seat.setDisable(false);
    	title.setEditable(true);
    	
    	
    	strWord="add";
    	
    	
    }

    @FXML
    void btnCxlClick(ActionEvent event) {
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	table.setDisable(false);
    	
    	if(!table.getSelectionModel().isEmpty()) {
    		AuctionVO ac = table.getSelectionModel().getSelectedItem();
    		
    	}
    	strWord="";
    }

    @FXML
    void btnOkClick(ActionEvent event) throws RemoteException {
    	if(title.getText().isEmpty()) {
    		AlertUtil.warnMsg("등록 오류", "경매 제목을 입력해주세요.");
    		return;
    	}
    	AuctionVO ac = new AuctionVO();
    	
    	ac.setSseat_no(Integer.parseInt(seat.getValue().substring(seat.getValue().indexOf("/")+1, seat.getValue().length())));
    	ac.setAuc_title(title.getText());
    	
    	if("add".equals(strWord)) {
    		int cnt = service.insertAuction(ac);
    		if(cnt>0) {
    			AlertUtil.infoMsg("등록 결과", "경매가 등록 되었습니다.");
    			seatService.updateToY(ac.getSseat_no());
    		}else {
    			AlertUtil.errorMsg("등록 결과", "경매 등록 실패");
    			return;
    		}
    		
    		
    	} else if("mod".equals(strWord)) {
    		int cnt =service.updateAuction(ac);
    		if(cnt>0) {
    			AlertUtil.infoMsg("수정 결과", "경매가 수정 되었습니다.");
    		}else {
    			AlertUtil.errorMsg("수정 결과", "경매 수정 실패");
    			return;
    		}
    		
    	}
    	
    	getAllAuctionList();
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	
    	movie.setDisable(true);
    	date.setDisable(true);
    	theatertime.setDisable(true);
    	seat.setDisable(true);
    	title.setEditable(false);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	table.setDisable(false);
    	
    	strWord="";
    	auc =null;
    }

    @FXML
    void del(ActionEvent event) throws RemoteException {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("경매방 선택", "삭제할 경매방을 선택하십시오.");
    		return;
    	}
    	
    	int auc_no = auc.getAuc_no();
    	String title = auc.getAuc_title();
    	
    	int cnt = service.deleteAuction(auc_no);
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", title+" 경매방을 삭제했습니다.");
    		
    		getAllAuctionList();
    		
    		
    	}else {
    		AlertUtil.warnMsg("작업결과", title+"경매방 삭제 실패");
    	}
    }

    @FXML
    void mod(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("경매방 선택", "수정할 경매방을 선택해주십시오.");
    		return;
    	}
    	
    	
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	
    	
    	movie.setDisable(false);
    	date.setDisable(false);
    	theatertime.setDisable(false);
    	seat.setDisable(false);
    	title.setEditable(true);
    	
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	table.setDisable(true);
    	
    	
    	strWord="mod";
    }
    @FXML
    void tableClick(MouseEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	auc = table.getSelectionModel().getSelectedItem();
    	
    	
    	
  
    }
    
    private ISMovieService smService;
    private ObservableList<AuctionVO> auctionList;
    private ObservableList<String> movieNameList;
    private ObservableList<String> dateList;
    private ObservableList<String> ttList;
    private ObservableList<String> seatList;
    private IAuctionService service;
    private String strWord="";
    
    @FXML
    void initialize() {
    	Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IAuctionService) reg.lookup("auctionService");
			seatService = (ISeatSchService) reg.lookup("seatSchService");
			smService =  (ISMovieService) reg.lookup("sMovieService");
			aucNoCol.setCellValueFactory(new PropertyValueFactory<>("auc_no"));
			sseatCol.setCellValueFactory(new PropertyValueFactory<>("sseat_no"));
			aucTitleCol.setCellValueFactory(new PropertyValueFactory<>("auc_title"));
			startCol.setCellValueFactory(new PropertyValueFactory<>("auc_startTime"));
			endCol.setCellValueFactory(new PropertyValueFactory<>("auc_endTime"));
			getAllAuctionList();
			getAllMovieList();
			getDate();
	    	btnOk.setDisable(true);
	    	btnCxl.setDisable(true);
	    	movie.setDisable(true);
	    	date.setDisable(true);
	    	theatertime.setDisable(true);
	    	seat.setDisable(true);
	    	title.setEditable(false);
	    	
	    	
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void getAllAuctionList() throws RemoteException  {
    		List<AuctionVO> aucData= service.getAllAuctionList();
			auctionList = FXCollections.observableArrayList(aucData);
			table.setItems(auctionList);
		
			
		
    }
    
    private void getAllMovieList() throws RemoteException  {
    	List<String> movieList = new ArrayList<String>();
    	List<SmovieShortInfoVO> smList = smService.sMovieNameList();
    	for (int i = 0; i < smList.size(); i++) {
			movieList.add(smList.get(i).getMovie_name());
		}
    	movieNameList = FXCollections.observableArrayList(movieList);
    	movie.setItems( movieNameList);
    }
    
    private void getDate() throws RemoteException  {
    	List<String> list = new ArrayList<String>();
    	Calendar cal = Calendar.getInstance();
		// 시작날짜(오늘) 구하기
		int year = cal.get(cal.YEAR);
		
		int month = cal.get(cal.MONTH)+1;
		
		String months = Integer.toString(month);
		if(months.length()==1) {
			months = "0"+months;
		}
		
		int dates = cal.get(cal.DATE);
		
		
		int tmp_day = cal.get(Calendar.DAY_OF_WEEK);
		
		String day = "";
    	
    	for (int i = 0; i <7; i++) {
    		if(tmp_day==1) {
    			day = "일";
    		}else if(tmp_day==2) {
    			day ="월";
    		}else if(tmp_day==3) {
    			day="화";
    		}else if(tmp_day==4) {
    			day = "수";
    		}else if(tmp_day ==5) {
    			day ="목";
    		}else if(tmp_day ==6) {
    			day="금";
    		}else if(tmp_day==7) {
    			day="토";
    		}
    		
    		String datess = Integer.toString(dates);
    		if(datess.length()==1) {
    			datess = "0"+dates;
    		}
    		list.add(year+"-"+months+"-"+datess+"("+day+")");
    		dates++;
    		tmp_day++;
    		if(tmp_day==8) {
    			tmp_day=1;
    		}
    	}
    	dateList = FXCollections.observableArrayList(list);
    	date.setItems(dateList);
    }
    private void getAllTT() throws RemoteException  {
    	List<String> list = new ArrayList<String>();
    	AuctionTTVO vo = new AuctionTTVO();
    	vo.setTheater_name(movie.getValue());
    	vo.setShow_time(date.getValue().substring(0,date.getValue().indexOf("(")));
    	List<AuctionTTVO> aucList = service.searchtt(vo);
    	
    	
    	
    	for (int i = 0; i < aucList.size(); i++) {
    		list.add(aucList.get(i).getTheater_name()+"/"+aucList.get(i).getShow_time());
		}
    	ttList = FXCollections.observableArrayList(list);
    	theatertime.setItems( ttList);
    }
    private void getseat() throws RemoteException  {
    	List<String> list = new ArrayList<String>();
    	Map<String, String> param = new HashMap<String, String>();
    	param.put("movie_name", movie.getValue());
    	param.put("show_time", theatertime.getValue().substring(theatertime.getValue().indexOf("/")+1, theatertime.getValue().length()));
    	
    	List<SeatSchVO> seatList2 = service.searchseat(param);
    	for (int i = 0; i < seatList2.size(); i++) {
    		list.add(seatList2.get(i).getSeat_id()+"/"+seatList2.get(i).getSseat_no());
    	}
    	seatList = FXCollections.observableArrayList(list);
    	seat.setItems( seatList);
    }
}
