package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import util.AlertUtil;
import wg.coupon.service.ICouponService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.vo.CouponVO;
import wg.vo.IssueCouponVO;
import wg.vo.IssueViewVO;
import wg.vo.TheaterVO;

public class M_issueController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_mem_id;

    @FXML
    private Button btnIssue;

    @FXML
    private Button btnCxlIssue;

    @FXML
    private ComboBox<CouponVO> cmb_coupon;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<IssueViewVO> tb_Issue;

    @FXML
    private TableColumn<?, ?> col_issue_no;

    @FXML
    private TableColumn<?, ?> col_cou_name;

    @FXML
    private TableColumn<?, ?> col_mem_id;

    @FXML
    private TableColumn<?, ?> col_issue_time;
    
    @FXML
    void tbIssueClick(MouseEvent event) {
    }

    @FXML
    void btnIssueClick(ActionEvent event) {
    	String cou_id = cmb_coupon.getValue().getCou_id();
    	String mem_id = tf_mem_id.getText();
    	
    	if(cmb_coupon.getValue().equals("")) {
    		AlertUtil.warnMsg("입력오류", "쿠폰 아이디어를 선택하세요.");
    		return;
    	}
    	if(mem_id.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "회원id를 입력하세요.");
    		return;
    	}
    	
    	IssueCouponVO icvo = new IssueCouponVO();
    	icvo.setCou_id(cou_id);
    	icvo.setMem_id(mem_id);
    	
    	try {
			int cnt = iService.insertIssueCoupon(icvo);
			if(cnt>0) {
				AlertUtil.infoMsg("작엽결과", "쿠폰 발급 완료");
				btnIssue.setDisable(false);
				
			}else {
				AlertUtil.infoMsg("작엽결과", "쿠폰 발급 실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	
    	// 테이블뷰 리셋
    	getAllIssueList();
    	
    	cmb_coupon.setValue(null);
    	tf_mem_id.clear();
    }
    
    @FXML
    void btnCxlIssueClick(ActionEvent event) {
    	if(tb_Issue.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("발급내역 선택", "철회할 발급내역을 선택하세요.");
    		return;
    	}
    	IssueViewVO ivo = tb_Issue.getSelectionModel().getSelectedItem();
    	int issue_no = ivo.getIssue_no();
    	
    	int cnt;
		try {
			cnt = iService.deleteIssueCoupon(issue_no);
			if (cnt>0) {
				AlertUtil.infoMsg("작업결과", "철회를 완료했습니다.");
				getAllIssueList();
			}else {
				AlertUtil.warnMsg("작업결과", "실패하였습니다.");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		getAllIssueList();
		
		cmb_coupon.setValue(null);
    	tf_mem_id.clear();
    }
    
    // 메인 창의 Stage객체 변수
    private Stage mainStage; 

    @FXML
    void btnSearchClick(ActionEvent event) {
		try {
			mainStage = (Stage) btnSearch.getScene().getWindow();
			
			Stage secStage = new Stage(StageStyle.UTILITY);
			secStage.initModality(Modality.WINDOW_MODAL);
			secStage.initOwner(mainStage);
			
			
			FXMLLoader loader = new FXMLLoader(M_issueController.class.getResource("../fxml/M_idSearch.fxml"));
			Parent childRoot = loader.load();
			
			M_idSearchController ctrl = loader.getController();
			ctrl.setMainCtrl(this);
			
			Scene childScene = new Scene(childRoot);
			secStage.setScene(childScene);
			secStage.setTitle("회원ID 검색");
			secStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    private ObservableList<IssueViewVO> iList;
    private ObservableList<CouponVO> cList;
    private IIssueCouponService iService;
    private ICouponService cService;
    
    private String func = "";
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			iService = (IIssueCouponService) reg.lookup("issueCouponService");
			cService = (ICouponService) reg.lookup("couponService");
			
			col_issue_no.setCellValueFactory(new PropertyValueFactory<>("issue_no"));
			col_cou_name.setCellValueFactory(new PropertyValueFactory<>("cou_name"));
			col_mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			col_issue_time.setCellValueFactory(new PropertyValueFactory<>("issue_time"));
			
			// 콤보박스에 CouponVO 세팅
			List<CouponVO> cData = cService.getAllCoupon();
			cList = FXCollections.observableArrayList(cData);
			for (int i = 0; i < cList.size(); i++) {
				cmb_coupon.getItems().add(cList.get(i));
			}
			
			cmb_coupon.setCellFactory(new Callback<ListView<CouponVO>, ListCell<CouponVO>>() {
				@Override
				public ListCell<CouponVO> call(ListView<CouponVO> param) {
					
					return new ListCell<CouponVO>() {
						@Override
						protected void updateItem(CouponVO item, boolean empty) {
							super.updateItem(item, empty);
							if(item==null || empty) {
								setText(null);
							}else {
								setText(item.getCou_name());
							}
						}
					};
				}
			});
			
	    	cmb_coupon.setButtonCell(new ListCell<CouponVO>() {
	    		@Override
	    		protected void updateItem(CouponVO item, boolean empty) {
	    			super.updateItem(item, empty);
	    			if(item==null||empty) {
	    				setText(null);
	    			}else {
	    				setText(item.getCou_name());
	    			}
	    		}
	    	});
			
			getAllIssueList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
    	
    }
    
    private void getAllIssueList() {
    	List<IssueViewVO> iData;
		try {
			iData = iService.getAllIssueCoupon();
			iList = FXCollections.observableArrayList(iData);
			tb_Issue.setItems(iList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    
    public void setTf_mem_id(String mem_id) {
    	tf_mem_id.setText(mem_id);
    }
    
}
