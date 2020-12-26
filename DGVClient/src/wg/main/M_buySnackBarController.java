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
import wg.buysnack.service.IBuySnackService;
import wg.pay.service.IPayService;
import wg.seatSch.service.ISeatSchService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.BookMovieViewVO;
import wg.vo.SnackUserViewVO;

public class M_buySnackBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SnackUserViewVO> table;

    @FXML
    private TableColumn<?, ?> setidCol;

    @FXML
    private TableColumn<?, ?> setNameCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> buyidCol;

    @FXML
    private TableColumn<?, ?> paynoCol;

    @FXML
    private TableColumn<?, ?> buyqtyCol;

    @FXML
    private TableColumn<?, ?> refundCol;

    @FXML
    private Button refund;
    
    SnackUserViewVO vo;
    IBuySnackService service;
    IPayService payService;
    
    @FXML
    void click(MouseEvent event) {
    	vo = table.getSelectionModel().getSelectedItem();
    }

    @FXML
    void refund(ActionEvent event) throws RemoteException {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("작업오류", "환불하실 구매내역을 선택해주세요.");
    		return;
    	}else if( vo.getPay_refund().equals("Y")) {
    		AlertUtil.errorMsg("작업오류", "이미 환불된 구매내역입니다.");
    		return;
    	}
    	
    	String buy_id = vo.getBuy_id();
    	AlertUtil.infoMsg(vo.getSet_name(),"");
    	int cnt = 0;
    	cnt = payService.updateSnackPayRefund(buy_id);
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", "환불이 완료되었습니다.");
    		getAllList();
    	}

    }
  
    private ObservableList<SnackUserViewVO> list;
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IBuySnackService) reg.lookup("buySnackService");
			payService = (IPayService) reg.lookup("payService");
			
//			private String set_id;			
//			private String set_name;	
//			private String set_photo; 
//			private int pay_price;	
//			private String pay_refund;		
//			private String buy_id;		
//			private String mem_id;	
//			private int buydet_no;
//			private int buydet_qty;
			
			
			setidCol.setCellValueFactory(new PropertyValueFactory<>("set_id"));
			setNameCol.setCellValueFactory(new PropertyValueFactory<>("set_name"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("pay_price"));
			buyidCol.setCellValueFactory(new PropertyValueFactory<>("buy_id"));
			paynoCol.setCellValueFactory(new PropertyValueFactory<>("buydet_no"));
			buyqtyCol.setCellValueFactory(new PropertyValueFactory<>("buydet_qty"));
			refundCol.setCellValueFactory(new PropertyValueFactory<>("pay_refund"));
			
			
			getAllList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
    
    private void getAllList() {
    	
    	try {
    		List<SnackUserViewVO> data = service.getBuyAllSnack();
			list = FXCollections.observableArrayList(data);
			table.setItems(list);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}