package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import util.AlertUtil;
import wg.seat.service.ISeatService;
import wg.theater.service.ITheaterService;
import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public class M_seatController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane scroll;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancle;

    @FXML
    private ComboBox<TheaterVO> theaterCombo;

    @FXML
    private TextField th_name;

    @FXML
    private TextField seat_id;

    @FXML
    private ComboBox<String> seat_name;

    @FXML
    private ComboBox<String> seat_status;

    @FXML
    private TextField seat_price;

    @FXML
    private TableView<SeatVO> seatTable;

    @FXML
    private TableColumn<?, ?> thCol;

    @FXML
    private TableColumn<?, ?> seatIdCol;

    @FXML
    private TableColumn<?, ?> seatNameCol;

    @FXML
    private TableColumn<?, ?> seatStatusCol;

    @FXML
    private TableColumn<?, ?> seatPriceCol;

    @FXML
    private Button btnReg;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDel;

    @FXML
    void btnCancleClick(ActionEvent event) {
    	th_name.setEditable(false);
    	seat_id.setEditable(false);
    	seat_name.setEditable(false);
    	seat_status.setEditable(false);
    	seat_price.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	btnReg.setDisable(false);
    	btnUpdate.setDisable(false);
    	btnDel.setDisable(false);
    	seatTable.setDisable(false);
    	theaterCombo.setDisable(false);

    	seat_status.setDisable(true);
    	seat_name.setDisable(true);
    	
    	if(!seatTable.getSelectionModel().isEmpty()) {
    		SeatVO seat = seatTable.getSelectionModel().getSelectedItem();
    		
    		th_name.setText(seat.getTheater_id());
    		seat_id.setText(seat.getSeat_id());
    		seat_name.setValue("이코노미");
    		seat_status.setValue(seat.getSeat_status());
    		seat_price.setText(Integer.toString(seat.getSeat_price()));
    	}
    }

    @FXML
    void btnDelClick(ActionEvent event) throws RemoteException {
    	Map<String, String> param = new HashMap<String, String>();
    	
    	if(seatTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("좌석선택", "삭제할 좌석을 선택하십시오.");
    		return;
    	}
    	String theater_id = th_name.getText();
    	String seat_id2 = seat_id.getText();
    	param.put("theater_id", theater_id);
    	param.put("seat_id",seat_id2);
    	
    	int cnt = service.deletSeat(param);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", theater_id+"의"+seat_id2+"를(을) 삭제했습니다.");
    		service2.updateSeat(theater_id);
    		
    		getSeatList(theaterCombo.getValue().getTheater_id());
    		
    		seat_id.clear();
    		seat_name.setValue("이코노미");
    		seat_status.setValue("좌석상태선택");
    		seat_price.clear();
    	}else {
    		AlertUtil.warnMsg("작업결과", "좌석 삭제 실패");
    	}
    }

    @FXML
    void btnOkClick(ActionEvent event) throws RemoteException {
    	String theater_id = th_name.getText();
    	String seId = seat_id.getText();
    	String name = seat_name.getValue();
    	String status = seat_status.getValue();
    	String price = seat_price.getText();
    	
    	
    	if(seId.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "좌석ID를 입력하세요.");
    		return;
    	}
    	if(name.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "좌석명을 입력하세요.");
    		return;
    	}
    	
    	for(int i=0;i<price.length();i++){
    		 char c = price.charAt(i);
    		 if(c<48 || c> 57){//숫자가 아닌 경우
    		  AlertUtil.warnMsg("입력오류", "숫자만 입력하세요.");
    		  return;
    		 }
    		}
    	
    	if(price.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "좌석가격을 입력하세요.");
    		return;
    	}
    	
    	SeatVO seat = new SeatVO();
    	seat.setTheater_id(theater_id);
    	seat.setSeat_id(seId);
    	seat.setSeat_name(name);
    	seat.setSeat_status(status);
    	seat.setSeat_price(Integer.parseInt(price));
    	
    	if("add".equals(strWord)) {
    		int cnt = service.insertSeat(seat);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "좌석 등록 완료");
    			
    			service2.updateSeat(theater_id);
    			
    		}else {
    			AlertUtil.warnMsg("작업결과", "좌석 등록 실패");
    			seat_name.setValue("이코노미");
    		}
    		
    	}else if("eddit".equals(strWord)) {
    		int cnt = service.updateSeat(seat);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", theater_id+"의"+seId+"좌석 수정 완료");
    		}else {
    			AlertUtil.warnMsg("작업결과", "좌석 수정 실패");
    			seat_name.setValue("이코노미");
    		}
    		
    	}
    	
    	//getSeatList2();
    	getSeatList(theaterCombo.getValue().getTheater_id());
    	
    	th_name.clear();
    	seat_id.clear();
    	seat_name.setValue("이코노미");
    	seat_status.setValue("좌석상태선택");
    	seat_price.clear();
    	
    	th_name.setEditable(false);
    	seat_id.setEditable(false);
    	seat_name.setEditable(false);
    	seat_status.setEditable(false);
    	seat_price.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	btnReg.setDisable(false);
    	btnUpdate.setDisable(false);
    	btnDel.setDisable(false);
    	seatTable.setDisable(false);
    	
    	strWord="";
    	
    	
    	
    	
    }

    @FXML
    void btnRegClick(ActionEvent event) {
    	th_name.setEditable(false);
    	seat_id.setEditable(true);
    	seat_price.setEditable(true);
    	
    	btnOk.setDisable(false);
    	btnCancle.setDisable(false);
    	
    	btnReg.setDisable(true);
    	btnUpdate.setDisable(true);
    	btnDel.setDisable(true);
    	seatTable.setDisable(true);
    	seat_name.setDisable(false);
    	seat_status.setDisable(false);
    	
    	seat_id.clear();
    	seat_name.setValue("이코노미");
    	seat_status.setValue("Y");
    	seat_price.clear();
    	
    	seat_id.requestFocus();
    	strWord="add";
    	
    }
    
    @FXML
    void btnUpClick(ActionEvent event) {
    	if(seatTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("좌석선택", "수정할 좌석을 선택해주십시오.");
    		return;
    	}
    	
    	seat_id.setEditable(false);
    	//seat_name.setEditable(true);
    	//seat_status.setEditable(true);
    	seat_price.setEditable(true);
    	
    	btnOk.setDisable(false);
    	btnCancle.setDisable(false);
    	
    	btnReg.setDisable(true);
    	btnUpdate.setDisable(true);
    	btnDel.setDisable(true);
    	seatTable.setDisable(true);
    	theaterCombo.setDisable(true);
    	seat_status.setDisable(false);
    	seat_name.setDisable(false);
    	
    	seat_name.setValue(seatTable.getSelectionModel().getSelectedItem().getSeat_name());
    	seat_status.requestFocus();
    	strWord="eddit";
    	
    
    }

    @FXML
    void tableClick(MouseEvent event) {
    	if(seatTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	SeatVO seat = seatTable.getSelectionModel().getSelectedItem();
    	
    	th_name.setText(seat.getTheater_id());
    	seat_id.setText(seat.getSeat_id());
    	seat_name.setValue(seat.getSeat_name());
    	seat_status.setValue(seat.getSeat_status());
    	seat_price.setText(Integer.toString(seat.getSeat_price()));
    	
    	seat_name.setEditable(false);
    	seat_status.setEditable(false);
    	seat_price.setEditable(false);
    }
    
    private ObservableList<TheaterVO> theaterList;
    private ObservableList<SeatVO> seatList;
    private ISeatService service;
    private ITheaterService service2;
    private String strWord="";
    

    @FXML
    void initialize() {
       
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost", 9988);
			service = (ISeatService) reg.lookup("seatService");
			service2 = (ITheaterService) reg.lookup("theaterService");
			thCol.setCellValueFactory(new PropertyValueFactory<>("theater_id"));
			seatIdCol.setCellValueFactory(new PropertyValueFactory<>("seat_id"));
			seatNameCol.setCellValueFactory(new PropertyValueFactory<>("seat_name"));
			seatStatusCol.setCellValueFactory(new PropertyValueFactory<>("seat_status"));
			seatPriceCol.setCellValueFactory(new PropertyValueFactory<>("seat_price"));
			
			
			List<TheaterVO> list = service2.getAllTheaterList();
			theaterList = FXCollections.observableArrayList(list);
			
			//getSeatList2();
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	for (int i = 0; i < theaterList.size(); i++) {
    		theaterCombo.getItems().add(theaterList.get(i));		
		}
    	
    	theaterCombo.setCellFactory(new Callback<ListView<TheaterVO>, ListCell<TheaterVO>>() {
			
			@Override
			public ListCell<TheaterVO> call(ListView<TheaterVO> param) {
				
				return new ListCell<TheaterVO>() {
					@Override
					protected void updateItem(TheaterVO item, boolean empty) {
						super.updateItem(item, empty);
						if(item==null || empty) {
							setText(null);
						}else {
							setText(item.getTheater_name());
						}
					}
				};
			}
		});
    	
    	theaterCombo.setButtonCell(new ListCell<TheaterVO>(){
    		@Override
    		protected void updateItem(TheaterVO item, boolean empty) {
    			super.updateItem(item, empty);
    			if(item==null||empty) {
    				setText(null);
    			}else {
    				setText(item.getTheater_name());
    			}
    		}
    	});
    	
    	//콤보박스 변경 바뀔 때
    	theaterCombo.valueProperty().addListener(new ChangeListener<TheaterVO>() {

			@Override
			public void changed(ObservableValue<? extends TheaterVO> observable, TheaterVO oldValue,
					TheaterVO newValue) {
				
				TheaterVO theater = theaterCombo.getSelectionModel().getSelectedItem();
		    	List<SeatVO> seat = new ArrayList<SeatVO>();
		    	try {
					seat = service.getSeatList(theater.getTheater_id());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		    	seatList = FXCollections.observableArrayList(seat);
		    	seatTable.setItems(seatList);
		    	th_name.setText(theaterCombo.getValue().getTheater_id());
				
			}
		});
    	
    	theaterCombo.setValue(theaterList.get(0));
    	
    	th_name.setEditable(false);
    	seat_id.setEditable(false);
    	seat_name.setEditable(false);
    	seat_price.setEditable(false);
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	seat_name.getItems().addAll("이코노미","스탠다드","프라임","특별관");
    	seat_name.setValue("이코노미");
    	seat_name.setDisable(true);
    	
    	seat_status.getItems().addAll("Y", "N");
    	seat_status.setValue("Y");
    	seat_status.setDisable(true);
    	
    	
    	
    }
    
    private void getSeatList2() throws RemoteException{
    	List<SeatVO> seList = service.getAllseatList();
    	seatList = FXCollections.observableArrayList(seList);
    	seatTable.setItems(seatList);
    }
    
    private void getSeatList(String th_name) throws RemoteException {
    	List<SeatVO> list = service.getSeatList(th_name);
    	seatList = FXCollections.observableArrayList(list);
    	seatTable.setItems(seatList);
    }
}

