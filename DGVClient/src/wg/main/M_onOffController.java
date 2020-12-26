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
import util.AlertUtil;
import wg.onoff.service.IOnOffService;
import wg.vo.HintVO;
import wg.vo.OnOffVO;
import wg.vo.OnOffViewVO;

public class M_onOffController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField txtOnNo;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtOffTime;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtOnTime;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;

    @FXML
    private TableView<OnOffViewVO> OnOffTable;

    @FXML
    private TableColumn<?, ?> onNoCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> onTimeCol;

    @FXML
    private TableColumn<?, ?> offTimeCol;

    
    private IOnOffService service;
    
    

    //취소버튼
    @FXML
    void dataCancel(ActionEvent event) {
    	//TextField들을 편집 가능 상태로 만든다 
    	txtOnNo.setEditable(false);
    	txtId.setEditable(false);
    	txtName.setEditable(false);
    	txtOnTime.setEditable(false);
    	txtOffTime.setEditable(false);
    	
    	//확인, 취소버튼 활성화 
    	btnOk.setDisable(true);
    	btnCancel.setDisable(true);
    	
    	//  삭제 , 테이블뷰를 비활성화
    	btnEdit.setDisable(false);
    	btnDel.setDisable(false);
    	OnOffTable.setDisable(false);
    	
    	//이전 작업 전에 TableView에서 선택된 데이터가 있으면 
    	//해당 데이터를 TextField에 출력된다 
    	
    	
    }
    
    //삭제버튼 
    @FXML
    void dataDel(ActionEvent event) throws RemoteException {
    	if(OnOffTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("선택", "삭제할 출결목록을 선택한 후 사용하세요! ");
    		return; 
    	}
    	
    	//삭제할 출결no 구하기 
    	int onNo = OnOffTable.getSelectionModel().getSelectedItem().getOn_no();
    	String name = OnOffTable.getSelectionModel().getSelectedItem().getMem_name();
    	
    	int cnt = service.deleteOnOff(onNo); 
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", name + "출결목록을 삭제했습니다.");
    		
    		getAllOnOff(); //삭제후  전체 데이터 다시 가져오기
    		
    		txtOnNo.clear();
    		txtId.clear();
    		txtName.clear();
    		txtOnTime.clear();
    		txtOffTime.clear();
    	}else {
    		AlertUtil.warnMsg("작업결과", name + "출결 삭제 실패 ");
    	}
    	
    }
    
	//수정버튼
    @FXML
    void dataEdit(ActionEvent event) {
    	
    	//수정할 데이터를 선택했는지 여부 검사 
    	if(OnOffTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("자료선택", "수정할 출결목록을 선택 한 후 사용하세요 ");
    		return; 
    	}
    	
    	
    	
    	//TextField들을 편집 가능 상태로 만든다 
    	txtName.setEditable(true);
    	txtOnTime.setEditable(true);
    	txtOffTime.setEditable(true);
    	
    	//확인, 취소버튼 활성화 
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	//수정, 삭제 , 테이블뷰를 비활성화
    	txtOnNo.setDisable(true);
    	txtId.setDisable(true);
    	txtName.setDisable(true);
    	
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	OnOffTable.setDisable(true);
    	
    	txtName.requestFocus(); //이름부터 편집할수 있도록 포커스를 이름에 두기 
    	//strWord = "edit";

    }
    //확인버튼
    @FXML
    void dataRun(ActionEvent event) throws RemoteException {
    	
    	int onno = Integer.parseInt(txtOnNo.getText());
    	String id = txtId.getText();
    	String name = txtName.getText();
    	String onTime = txtOnTime.getText();
    	String offTime = txtOffTime.getText();
    	
    	//오류체크 
    			
    			
    			if(name.isEmpty()) {
    				AlertUtil.warnMsg("입력오류", "직원이름을 입력하세요 ");
    				txtName.requestFocus();
    				return;
    			}
    			
    			
    			
    			//입력한 정보를 MemberVO객체에 저장한다. 
    	    	OnOffVO oovVo = new OnOffVO();
    	    	oovVo.setOn_no(onno);
    	    	oovVo.setOn_time(onTime);
    	    	oovVo.setOff_time(offTime);
    	    	

    	    	//추가버튼을 눌른 후의 작업인지 검사 
    	    //	if("edit".equals(strWork)) {  //수정 작업
    	    		
    	    		//VO값을 DB에 저장 
    	    		int cnt = service.updateOnOff(oovVo);
    	    		
    	    		if(cnt>0) {
    	    			AlertUtil.infoMsg("작업결과", "수정성공");
    	    			getAllOnOff();
    	    		}else {
    	    			AlertUtil.warnMsg("작업결과", "수정실패...");
    	    		}
    	    		
    	    		//TextField들을 편집 가능 상태로 만든다 
    	    		txtOnNo.setEditable(false);
    	    		txtId.setEditable(false);
    	        	txtOnTime.setEditable(false);
    	        	txtName.setEditable(false);
    	        	txtOffTime.setEditable(false);
    	        	
    	        	//확인, 취소버튼 활성화 
    	        	btnOk.setDisable(true);
    	        	btnCancel.setDisable(true);
    	        	
    	        	// 수정, 삭제 , 테이블뷰를 비활성화
    	        	btnEdit.setDisable(false);
    	        	btnDel.setDisable(false);
    	        	OnOffTable.setDisable(false);
    	        	
    	        	getAllOnOff();
    	        	
    	        	txtOnNo.clear();
    	        	txtId.clear();
    	    		txtName.clear();
    	    		txtOnTime.clear();
    	    		txtOffTime.clear();
    	    		
    	        	
    	        }
    
  //Table클릭했을때  값누르면 들어가게 하는거
    @FXML
    void tableClick(MouseEvent event) {
    	if(OnOffTable.getSelectionModel().isEmpty()) {
    		return; 
    	}
    	//선택한 데이터 가져오기 
    	OnOffViewVO oov = OnOffTable.getSelectionModel().getSelectedItem();
    	
    	txtOnNo.setText(Integer.toString(oov.getOn_no()));
    	txtId.setText(oov.getMem_id());
		txtName.setText(oov.getMem_name());
		txtOnTime.setText(oov.getOn_time());
		txtOffTime.setText(oov.getOff_time());
		
		
    }
    
 // TableView에 넣을 데이터가 저장될 List객체 변수선언
    private ObservableList<OnOffViewVO> onoffList;
    
    	    	

    	    @FXML
    	    void initialize() {
    	    	Registry reg = null;
    	    	
    	    	try {
    	            reg = LocateRegistry.getRegistry("localhost",9988);
    	            service = (IOnOffService) reg.lookup("onOffService");
    	            
    	            //TableView의 컬럼들과 VO를 연결한다.
    	            idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	            nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
    	            onNoCol.setCellValueFactory(new PropertyValueFactory<>("on_no"));
    	            onTimeCol.setCellValueFactory(new PropertyValueFactory<>("on_time"));
    	            offTimeCol.setCellValueFactory(new PropertyValueFactory<>("off_time"));
    	            
    	            getAllOnOff();
    	            
    	            
    	         } catch (RemoteException e) {
    	            e.printStackTrace();
    	         } catch (NotBoundException e) {
    	            e.printStackTrace();
    	         }

    	       }
    	    

    	     
private void getAllOnOff() throws RemoteException   {
    List<OnOffViewVO> list = service.getAllOnOff();
    System.out.println(list);
    onoffList = FXCollections.observableArrayList(list);
    OnOffTable.setItems(onoffList);
	
}

}


