package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.AlertUtil;
import wg.coupon.service.ICouponService;
import wg.vo.CouponVO;

public class M_couponController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_cou_id;

    @FXML
    private TextField tf_cou_name;

    @FXML
    private TextField tf_cou_sale;
    
    @FXML
    private TextField tf_cou_time;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCxl;

    @FXML
    private TableView<CouponVO> tb_coupon;
    
    @FXML
    private TableColumn<?, ?> col_cou_id;

    @FXML
    private TableColumn<?, ?> col_cou_name;

    @FXML
    private TableColumn<?, ?> col_cou_sale;

    @FXML
    private TableColumn<?, ?> col_cou_time;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;
    
    @FXML
    void tbCouponClick(MouseEvent event) {
    	if(tb_coupon.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	CouponVO cvo = tb_coupon.getSelectionModel().getSelectedItem();
    	tf_cou_id.setText(cvo.getCou_id());
    	tf_cou_name.setText(cvo.getCou_name());
    	tf_cou_sale.setText(cvo.getCou_sale()+"");
    	tf_cou_time.setText(cvo.getCou_time());
    	
    }

    @FXML
    void btnOkClick(ActionEvent event) {
    	if(!Pattern.matches("^[0-9]{1,5}$", tf_cou_sale.getText())) {
    		AlertUtil.warnMsg("입력오류", "할인금액은 숫자만 입력이 가능합니다.");
    		tf_cou_sale.requestFocus();
    		return;
    	}
    	String cou_id = tf_cou_id.getText();
    	String cou_name = tf_cou_name.getText();
    	String str_cou_sale = tf_cou_sale.getText();
    	int cou_sale = Integer.parseInt(str_cou_sale);
    	String temp_cou_time = tf_cou_time.getText();
    	String cou_time = temp_cou_time;
    	
    	if(tf_cou_name.getText().trim().isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "쿠폰명을 입력하세요");
    	}
    	if(cou_sale<1000) {
    		AlertUtil.warnMsg("입력오류", "할인금액은 1000원보다 커야합니다.");
    	}
    	if(tf_cou_time.getText().trim().isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "유효기간을 입력하세요");
    	}
    	
    	CouponVO cvo = new CouponVO();
    	cvo.setCou_id(cou_id);
    	cvo.setCou_name(cou_name);
    	cvo.setCou_sale(cou_sale);
    	cvo.setCou_time(cou_time);
    	
    	if("add".equals(func)) {
    		try {
				int cnt = service.insertCoupon(cvo);
				if(cnt>0) {
					AlertUtil.infoMsg("작엽결과", "쿠폰 추가 완료");
				}else {
					AlertUtil.infoMsg("작엽결과", "쿠폰 추가 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}else if("mod".equals(func)) {
    		try {
				int cnt = service.updateCoupon(cvo);
				if(cnt>0) {
					AlertUtil.infoMsg("작엽결과", "쿠폰 수정 완료");
				}else {
					AlertUtil.infoMsg("작엽결과", "쿠폰 수정 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	
    	getAllCouponList();
    	tf_cou_id.clear();
    	tf_cou_name.clear();
    	
    	tf_cou_name.setDisable(true);
    	tf_cou_sale.setDisable(true);
    	tf_cou_time.setDisable(true);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    }
    
    @FXML
    void btnCxlClick(ActionEvent event) {
    	tf_cou_name.setDisable(true);
    	tf_cou_sale.setDisable(true);
    	tf_cou_time.setDisable(true);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	
    	if(!tb_coupon.getSelectionModel().isEmpty()) {
    		CouponVO cvo = tb_coupon.getSelectionModel().getSelectedItem();
    		
    		tf_cou_id.setText(cvo.getCou_id());
        	tf_cou_name.setText(cvo.getCou_name());
    	}
    	func="";
    }
    
    @FXML
    void btnAddClick(ActionEvent event) {
    	String cou_id = "";
    	try {
			cou_id = service.getMaxCou_id();
			tf_cou_id.setText(cou_id);
			tf_cou_name.clear();
			tf_cou_sale.clear();
			tf_cou_time.clear();
			
			tf_cou_name.setDisable(false);
			tf_cou_sale.setDisable(false);
			tf_cou_time.setDisable(false);
			
			btnOk.setDisable(false);
			btnCxl.setDisable(false);
			func = "add";
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnModClick(ActionEvent event) {
    	if(tb_coupon.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("쿠폰선택", "수정할 쿠폰을 선택해주십시오.");
    		return;
    	}
    	tf_cou_name.setDisable(false);
    	tf_cou_sale.setDisable(false);
    	tf_cou_time.setDisable(false);
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	
    	func = "mod";
    }
    
    @FXML
    void btnDelClick(ActionEvent event) {
    	if(tb_coupon.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("쿠폰선택", "삭제할 쿠폰을 선택해주십시오.");
    		return;
    	}
    	String cou_id = tf_cou_id.getText();
    	try {
			int cnt = service.deleteCoupon(cou_id);
			if(cnt>0) {
	    		AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");
	    		
	    		getAllCouponList();
	    		
	    		tf_cou_id.clear();
	    		tf_cou_name.clear();
	    		tf_cou_sale.clear();
	    		tf_cou_time.clear();
	    		
	    		tf_cou_name.setDisable(true);
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
    
    private ObservableList<CouponVO> couList;
    private ICouponService service;
    private String func = "";
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
    		btnOk.setDisable(true);
    		btnCxl.setDisable(true);
    		
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ICouponService) reg.lookup("couponService");
			
			col_cou_id.setCellValueFactory(new PropertyValueFactory<>("cou_id"));
			col_cou_name.setCellValueFactory(new PropertyValueFactory<>("cou_name"));
			col_cou_sale.setCellValueFactory(new PropertyValueFactory<>("cou_sale"));
			col_cou_time.setCellValueFactory(new PropertyValueFactory<>("cou_time"));
			
			getAllCouponList();
		} catch (RemoteException e) {
			// TODO: handle exception
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void getAllCouponList() {
    	List<CouponVO> couData;
    	try {
			couData = service.getAllCoupon();
			couList = FXCollections.observableArrayList(couData);
			tb_coupon.setItems(couList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}
