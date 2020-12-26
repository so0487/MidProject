package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.AlertUtil;
import wg.book_detail.service.IBook_DetailService;
import wg.bookmovie.service.IBookMovieService;
import wg.discount.service.IDiscountService;
import wg.pay.service.IPayService;
import wg.seatSch.service.ISeatSchService;
import wg.vo.BookMovieViewVO;
import wg.vo.DiscountVO;

public class M_bookMovieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookMovieViewVO> table;

    @FXML
    private TableColumn<?, ?> bookidCol;

    @FXML
    private TableColumn<?, ?> booktimeCol;


    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> showCol;

    @FXML
    private TableColumn<?, ?> theaterCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> refundCol;

   

    @FXML
    private Button refund;
    BookMovieViewVO bvo;

    @FXML
    void refund(ActionEvent event) throws RemoteException {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("작업오류", "환불하실 예매내역을 선택해주세요.");
    		return;
    	}else if( bvo.getPay_refund().equals("Y")) {
    		AlertUtil.errorMsg("작업오류", "이미 환불된 예매내역입니다.");
    		return;
    	}
    	
    	String book_id = bvo.getBook_id();
    	int cnt1 = 0;
    	int cnt2 = 0;
    	cnt1 = seatSchService.updateToN(book_id);
    	cnt2 = payService.updatePayRefund(book_id);
    	if(cnt1>0&&cnt2>0) {
    		AlertUtil.infoMsg("작업결과", "환불이 완료되었습니다.");
    		getAllList();
    	}

    }

    @FXML
    void click(MouseEvent event) {
    	
    	bvo = table.getSelectionModel().getSelectedItem();
    	

    }
    IBookMovieService service;
    private ObservableList<BookMovieViewVO> list;
    IPayService payService;
	ISeatSchService seatSchService;
	IBook_DetailService book_detailService;
    

    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IBookMovieService) reg.lookup("bookMovieService");
			payService = (IPayService) reg.lookup("payService");
            seatSchService = (ISeatSchService) reg.lookup("seatSchService");
            book_detailService = (IBook_DetailService) reg.lookup("book_detailService");
			
			bookidCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));
			booktimeCol.setCellValueFactory(new PropertyValueFactory<>("book_time"));
			titleCol.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
			showCol.setCellValueFactory(new PropertyValueFactory<>("show_time"));
			theaterCol.setCellValueFactory(new PropertyValueFactory<>("theater_name"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("pay_price"));
			refundCol.setCellValueFactory(new PropertyValueFactory<>("pay_refund"));
			
			
			getAllList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
    
    private void getAllList() {
    	List<BookMovieViewVO> data;
    	try {
			data = service.getAllBook();
			list = FXCollections.observableArrayList(data);
			table.setItems(list);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}