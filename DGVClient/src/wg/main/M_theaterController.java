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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.AlertUtil;
import wg.theater.service.ITheaterService;
import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public class M_theaterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TheaterVO> screenTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> seatNumCol;

//    @FXML
//    private TableColumn<?, ?> statusCol;

    @FXML
    private Button btnReg;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDel;

    @FXML
    private TextField th_id;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancle;

    @FXML
    private TextField th_name;

    @FXML
    private TextField th_seatNum;

    @FXML
    private ComboBox<String> th_status;

    @FXML
    void btnCancleClick(ActionEvent event) {
    	th_id.setEditable(false);
    	th_name.setEditable(false);
    	th_seatNum.setEditable(false);
    	th_status.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	th_status.setDisable(true);
    	
    	btnReg.setDisable(false);
    	btnUpdate.setDisable(false);
    	btnDel.setDisable(false);
    	screenTable.setDisable(false);
    	screenTable.requestFocus();
    	
    	if(!screenTable.getSelectionModel().isEmpty()) {
    		TheaterVO the = screenTable.getSelectionModel().getSelectedItem();
    		
    		th_id.setText(the.getTheater_id());
        	th_name.setText(the.getTheater_name());
        	th_seatNum.setText(Integer.toString(the.getTheater_numOfSeat()));
        	th_status.setValue("OFF");
    	}
    	th_id.clear();
    	th_name.clear();
    	th_seatNum.clear();
    	strWord="";
    }

    @FXML
    void btnDelClick(ActionEvent event) throws RemoteException {
    	if(screenTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("상영관선택", "삭제할 상영관을 선택하십시오.");
    		return;
    	}
    	
    	String id = th_id.getText();
    	String name = th_name.getText();
    	
    	System.out.println(th_id.getText());
    	
    	int cnt2 = service.deleteThSeat(th_id.getText());
    	System.out.println(cnt2);
    	
    	int cnt = service.deleteTheater(id);
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", name+"상영관을 삭제했습니다.");
    		
    		getAllTheaterList2();
    		
    		th_id.clear();
    		th_name.clear();
    		th_seatNum.clear();
    		th_status.setValue("OFF");
    	}else {
    		AlertUtil.warnMsg("작업결과", name+"상영관 삭제 실패");
    	}
    }

    @FXML
    void btnOkClick(ActionEvent event) throws RemoteException {
    	String id = th_id.getText();
    	String name = th_name.getText();
    	String seatOfNum = th_seatNum.getText();
    	String status = th_status.getValue();
    	
    	if(id.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "상영관ID를 입력하세요.");
    		return;
    	}
    	if(name.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "상영관이름을 입력하세요.");
    		return;
    	}
    	
    	for(int i=0;i<seatOfNum.length();i++){
   		 char c = seatOfNum.charAt(i);
   		 if(c<48 || c> 57){//숫자가 아닌 경우
   		  AlertUtil.warnMsg("입력오류", "숫자만 입력하세요.");
   		  return;
   		 }
   		}
    	
    	TheaterVO the = new TheaterVO();
    	the.setTheater_id(id);
    	the.setTheater_name(name);
    	the.setTheater_numOfSeat(Integer.parseInt(seatOfNum));
    	the.setTheater_status(status);
    	
    	
    	if("add".equals(strWord)) {
    		int cnt = service.insertTheater(the);
    		
    		if(cnt >0) {
    			AlertUtil.infoMsg("작엽결과", "상영관 등록완료");
    			
    			char c = 'A';
    			int b = 1;
    			int a = Integer.parseInt(seatOfNum);
    			int x = 10;
    			int count = 0;
    			String seatName = "";
    			int price = 0;
    			
    			SeatVO seatvo = new SeatVO();
    			
    			if(a%10 == 0) {
    				for (int i = 0; i < a/10; i++) {
    					
    					for (int j = 0; j < 10; j++) {
    						
    						count++;
    						String seat = c+"0"+b;
    						if(count<=20) {
    							seatName = "이코노미";
    							price = 8000;
    						} else if(count>=21 && 51>count) {
    							seatName="스탠다드";
    							price = 9000;
    						}else {
    							seatName = "프라임";
    							price = 10000;
    						}
    						
    						if(b == 10) {
    							seat = c+"10";
    						}
    						seatvo.setSeat_id(seat);
    						seatvo.setSeat_name(seatName);
    						seatvo.setSeat_price(price);
    						seatvo.setTheater_id(id);
    						
    						service.insertMovSeat(seatvo);
    						++b;
    					}
    					c++;
    					b = 1;
    				}
    			} else{
    				for (int i = 0; i < a/10+1; i++) {
    					if(i==a/10) {
    						x = a%10;
    					}
    					for (int j = 0; j < x; j++) {
    						count++;
    						String seat = c+"0"+b;
    						if(count<=20) {
    							seatName = "이코노미";
    							price = 8000;
    						} else if(count>=21 && 51>count) {
    							seatName="스탠다드";
    							price = 9000;
    						}else {
    							seatName = "프라임";
    							price = 10000;
    						}
    						
    						if(b == 10) {
    							seat = c+"10";
    						}
    						seatvo.setSeat_id(seat);
    						seatvo.setSeat_name(seatName);
    						seatvo.setSeat_price(price);
    						seatvo.setTheater_id(id);
    						
    						service.insertMovSeat(seatvo);
    						++b;
    					}
    					c++;
    					b = 1;
    				}
    			}
    			
    			
    		}else {
    			AlertUtil.warnMsg("작엽결과", "등록실패");
    		}
    	} else if("eddit".equals(strWord)) {
    		int cnt = service.updateTheater(the);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작엽결과", name+"상영관 수정 완료");
    		}else {
    			AlertUtil.warnMsg("작업결과", name+"상영관 수정 실패");
    		}
    	}
    	
    	getAllTheaterList2();
    	
    	th_id.clear();
    	th_name.clear();
    	th_seatNum.clear();
    	th_status.setValue("OFF");
    	
    	th_id.setEditable(false);
    	th_name.setEditable(false);
    	th_seatNum.setEditable(false);
    	th_status.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	btnReg.setDisable(false);
    	btnUpdate.setDisable(false);
    	btnDel.setDisable(false);
    	screenTable.setDisable(false);
    	
    	strWord="";
    }

    @FXML
    void btnRegClick(ActionEvent event) {
    	String addTh_id = "";
    	try {
    		addTh_id = service.getMaxTh_id();
    		th_id.setText(addTh_id);
    		th_name.setEditable(true);
    		th_seatNum.setEditable(true);
    		
    		btnOk.setDisable(false);
    		btnCancle.setDisable(false);
    		th_status.setDisable(false);
    		
    		th_id.setDisable(true);
    		btnReg.setDisable(true);
    		btnUpdate.setDisable(true);
    		btnDel.setDisable(true);
    		screenTable.setDisable(true);
    		
    		//th_id.clear();
    		th_name.clear();
    		th_seatNum.clear();
    		th_status.setValue("OFF");
    		
    		th_id.requestFocus();
    		strWord="add";
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnUpdateClick(ActionEvent event) {
    	if(screenTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("상영관선택", "수정할 상영관을 선택해주십시오.");
    		return;
    	}
    	th_id.setEditable(false);
    	th_name.setEditable(true);
    	th_seatNum.setEditable(true);
    	
    	btnOk.setDisable(false);
    	btnCancle.setDisable(false);
    	th_status.setDisable(false);
    	
    	btnReg.setDisable(true);
    	btnUpdate.setDisable(true);
    	btnDel.setDisable(true);
    	screenTable.setDisable(true);
    	
    	th_name.requestFocus();
    	strWord="eddit";
    }

    @FXML
    void tableClick(MouseEvent event) {
    	if(screenTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	TheaterVO the = screenTable.getSelectionModel().getSelectedItem();
    	
    	th_id.setText(the.getTheater_id());
    	th_name.setText(the.getTheater_name());
    	th_seatNum.setText(Integer.toString(the.getTheater_numOfSeat()));
    	//th_status.setText(the.getTheater_status());
    	th_status.setValue(the.getTheater_status());
    	
    	th_id.setEditable(false);
    	th_name.setEditable(false);
    	th_seatNum.setEditable(false);
    }
    
    private ObservableList<TheaterVO> theaterList;
    private ITheaterService service;
    private String strWord="";
    
    @FXML
    void initialize() {
    	Registry reg = null;
        try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ITheaterService) reg.lookup("theaterService");
			idCol.setCellValueFactory(new PropertyValueFactory<>("theater_id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<>("theater_name"));
			seatNumCol.setCellValueFactory(new PropertyValueFactory<>("theater_numOfSeat"));
			//statusCol.setCellValueFactory(new PropertyValueFactory<>("theater_status"));
			
			getAllTheaterList2();
        
        } catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
        
        th_status.getItems().addAll("ON","OFF");
        th_status.setValue("OFF");
        th_status.setDisable(true);
        
        th_id.setEditable(false);
        th_name.setEditable(false);
        th_seatNum.setEditable(false);
    }
    
    private void getAllTheaterList2() throws RemoteException{
    	List<TheaterVO> list = service.getAllTheaterList();
    	theaterList = FXCollections.observableArrayList(list);
    	screenTable.setItems(theaterList);
    }
}
