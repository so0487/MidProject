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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.discount.service.IDiscountService;
import wg.genre.service.IGenreService;
import wg.vo.DiscountVO;
import wg.vo.GenreVO;

public class M_discountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    AnchorPane discount;

    @FXML
    private StackPane scroll;

    @FXML
    private TextField tf_dis_id;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCxl;

    @FXML
    private TextField tf_dis_name;

    @FXML
    private TextField tf_dis_price;

    @FXML
    private TextField tf_dis_if;

    @FXML
    private TableView<DiscountVO> tb_Discount;

    @FXML
    private TableColumn<?, ?> col_dis_id;

    @FXML
    private TableColumn<?, ?> col_dis_name;

    @FXML
    private TableColumn<?, ?> col_dis_price;

    @FXML
    private TableColumn<?, ?> col_dis_if;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;

    @FXML
    void btnAddClick(ActionEvent event) {
    	String dis_id = "";
    	try {
			dis_id = service.getMaxDis_id();
			tf_dis_id.setText(dis_id);
			
			tf_dis_name.clear();
			tf_dis_price.clear();
			tf_dis_if.clear();
			
			tf_dis_name.setDisable(false);
			tf_dis_name.requestFocus();
			tf_dis_price.setDisable(false);
			tf_dis_if.setDisable(false);
			
			btnOk.setDisable(false);
			btnCxl.setDisable(false);
			func = "add";
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnCxlClick(ActionEvent event) {
    	tf_dis_name.setDisable(true);
    	tf_dis_price.setDisable(true);
		tf_dis_if.setDisable(true);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	
    	if(!tb_Discount.getSelectionModel().isEmpty()) {
    		DiscountVO dvo = tb_Discount.getSelectionModel().getSelectedItem();
    		
    		tf_dis_id.setText(dvo.getDis_id());
        	tf_dis_name.setText(dvo.getDis_name());
    	}
    	func="";
    }

    @FXML
    void btnDelClick(ActionEvent event) {
    	if(tb_Discount.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("할인 선택", "삭제할 할인를 선택하십시오.");
    		return;
    	}
    	
    	String dis_id = tf_dis_id.getText();
    	try {
			int cnt = service.deleteDiscount(dis_id);
			if(cnt>0) {
	    		AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");
	    		
	    		getAllDiscountList();
	    		
	    		tf_dis_id.clear();
	    		tf_dis_name.clear();
	    		
	    		tf_dis_name.setDisable(true);
	    		tf_dis_price.setDisable(true);
	    		tf_dis_if.setDisable(true);
	    		
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
    void btnModClick(ActionEvent event) {
    	if(tb_Discount.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("할인선택", "수정할 할인를 선택해주십시오.");
    		return;
    	}
    	tf_dis_name.setDisable(false);
    	tf_dis_price.setDisable(false);
		tf_dis_if.setDisable(false);
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	
    	func = "mod";
    }

    @FXML
    void btnOkClick(ActionEvent event) {
    	String dis_id = tf_dis_id.getText();
    	String dis_name = tf_dis_name.getText();
    	
    	if(dis_name.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "할인명을 입력하세요.");
    		return;
    	}
    	DiscountVO dvo = new DiscountVO();
    	dvo.setDis_id(dis_id);
    	dvo.setDis_name(dis_name);
    	
    	if("add".equals(func)) {
    		try {
				int cnt = service.insertDiscount(dvo);
				if(cnt>0) {
					AlertUtil.infoMsg("작엽결과", "할인 추가 완료");
				}else {
					AlertUtil.infoMsg("작엽결과", "할인 추가 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}else if("mod".equals(func)) {
    		try {
				int cnt = service.updateDiscount(dvo);
				if(cnt>0) {
					AlertUtil.infoMsg("작엽결과", "할인 수정 완료");
				}else {
					AlertUtil.infoMsg("작엽결과", "할인 수정 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	
    	getAllDiscountList();
    	tf_dis_id.clear();
    	tf_dis_name.clear();
    	tf_dis_price.clear();
    	tf_dis_if.clear();
    	
    	tf_dis_name.setDisable(true);
    	tf_dis_price.setDisable(true);
		tf_dis_if.setDisable(true);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    }

    @FXML
    void tbDiscountClick(MouseEvent event) {
    	if(tb_Discount.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	DiscountVO dvo = tb_Discount.getSelectionModel().getSelectedItem();
    	tf_dis_id.setText(dvo.getDis_id());
    	tf_dis_name.setText(dvo.getDis_name());
    	tf_dis_price.setText(dvo.getDis_price()+"");
    	tf_dis_if.setText(dvo.getDis_if());
    }
    
    private ObservableList<DiscountVO> disList;
    private IDiscountService service;
    private String func = "";

    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IDiscountService) reg.lookup("discountService");
			
			col_dis_id.setCellValueFactory(new PropertyValueFactory<>("dis_id"));
			col_dis_name.setCellValueFactory(new PropertyValueFactory<>("dis_name"));
			col_dis_price.setCellValueFactory(new PropertyValueFactory<>("dis_price"));
			col_dis_if.setCellValueFactory(new PropertyValueFactory<>("dis_if"));
			
			getAllDiscountList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
    
    private void getAllDiscountList() {
    	List<DiscountVO> disData;
    	try {
			disData = service.getAllDiscount();
			disList = FXCollections.observableArrayList(disData);
			tb_Discount.setItems(disList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}
