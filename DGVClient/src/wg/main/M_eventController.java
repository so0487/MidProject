package wg.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.AlertUtil;
import wg.event.service.IEventService;
import wg.member.service.IMemberService;
import wg.vo.EventVO;
import wg.vo.MemberVO;
import wg.vo.OnOffViewVO;
import wg.vo.SnackSetVO;

public class M_eventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;

    @FXML
    private Label lbl_id;

    @FXML
    private TextField txtEventId;

    @FXML
    private TextField txtWriterId;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_price;

    @FXML
    private TextField txtMemId;
    
    @FXML
    private TextField txtImage;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    @FXML
    private ImageView imgView;

    @FXML
    private TextField txtEventName;

    @FXML
    private Button btnImg;

    @FXML
    private Label lbl_img;

    @FXML
    private TextField txtEventDate;

    @FXML
    private Label lbl_img1;

    @FXML
    private TableView<EventVO> tableView;

    @FXML
    private TableColumn<?, ?> eventIdCol;

    @FXML
    private TableColumn<?, ?> writerIdCol;

    @FXML
    private TableColumn<?, ?> memIdCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> dateCol;
    private IMemberService memberService;
    private IEventService service;
    private Map<String, String> searchMap; 
   // private ObservableList<EventVO> eventList; 
    
    @FXML
	void btnAddClick(ActionEvent event) {
    	try {
			txtEventId.setDisable(true);
			txtWriterId.setDisable(true);
			txtEventDate.setDisable(true);
			
			txtEventName.clear();
			txtEventName.setEditable(true);
			txtEventName.clear();
			
			//txtEventDate.setEditable(true);
			//txtEventDate.clear();
			
			txtImage.setEditable(false);
			txtImage.setDisable(true);
			txtImage.clear();
			
			txtMemId.setEditable(true);
			txtMemId.clear();
			
			txtMemId.setDisable(false);
			txtEventName.setDisable(false);
			
			btnImg.setDisable(false);
			btnOK.setDisable(false);
			btnCancel.setDisable(false);
			strWork="add";
			
			
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			btnDel.setDisable(true);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
	}


	@FXML
	void btnDelClick(ActionEvent event) {
		if(tableView.getSelectionModel().isEmpty()) {
			AlertUtil.warnMsg("이벤트 선택", "삭제할 이벤트를 선택하세요");
			return;
		}

		//삭제할 eventID 구하기 
		int eventId  = tableView.getSelectionModel().getSelectedItem().getEvent_no();

		try {
			int cnt = service.deleteEvent(eventId);

			if(cnt>0) {
				AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");


				getAllEvent( );


				txtEventId.clear();
				txtWriterId.clear();
				txtMemId.clear();
				txtEventName.clear();
				txtEventDate.clear();
				
				
//				txtEventId.setDisable(true);
//				txtWriterId.setDisable(true);
				txtMemId.setDisable(true);
				txtEventName.setDisable(true);
				txtEventDate.setDisable(true);


				btnOK.setDisable(true);
				btnCancel.setDisable(true);

			}else {
				AlertUtil.warnMsg("작업결과", "삭제를 실패했습니다.");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void btnEditClick(ActionEvent event) {
		if(tableView.getSelectionModel().isEmpty()) {
			AlertUtil.warnMsg("자료선택", "수정할 이벤트를 선택하세요");
			return;
		}
		
		int eventId  = tableView.getSelectionModel().getSelectedItem().getEvent_no();

		txtMemId.setEditable(true);
    	txtEventName.setEditable(true);
    	txtImage.setEditable(false);
    	//txtEventDate.setEditable(true);
    	txtEventName.requestFocus();
//		txtEventName.setDisable(false);
//		txtEventDate.setDisable(false);
		txtImage.setDisable(true);
//		txtMemId.setDisable(false);
		txtEventDate.setDisable(true);
		
		txtMemId.setDisable(false);
		txtEventName.setDisable(false);
		


    	//확인, 취소버튼 활성화 
    	btnOK.setDisable(false);
    	btnCancel.setDisable(false);
    	btnImg.setDisable(false);
    	
    	//추가, 수정, 삭제 , 테이블뷰를 비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	tableView.setDisable(true);
    	
    	strWork = "edit";
	}

	@FXML
	void btnImgClick(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("이미지 선택");
		fc.setInitialDirectory(new File("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img"));

		ExtensionFilter imgType = new ExtensionFilter("img file", "*.jpg","*.gif","*.png");

		fc.getExtensionFilters().add(imgType);

		File path = fc.showOpenDialog(null);

		//System.out.println(path);



		try {
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			txtImage.setText(path.getAbsolutePath());


			Image img = new Image(bis);
			imgView.setImage(img);




			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void dataCancelClick(ActionEvent event) {
//		txtEventId.setEditable(false);
//    	txtWriterId.setEditable(false);
//    	txtMemId.setEditable(false);
//    	txtEventName.setEditable(false);
//    	txtEventDate.setEditable(false);
//    	txtImage.setEditable(false);
		txtEventName.setDisable(false);
		txtEventDate.setDisable(false);
		txtImage.setDisable(false);
		txtMemId.setDisable(false);
		
		


		btnAdd.setDisable(false);
		btnEdit.setDisable(false);
		btnDel.setDisable(false);
		tableView.setDisable(false);
		
		btnImg.setDisable(false);
		btnOK.setDisable(false);
		btnCancel.setDisable(false);
		tableView.setEditable(true);

		if(!tableView.getSelectionModel().isEmpty()) {
			EventVO eventvo = tableView.getSelectionModel().getSelectedItem();


			txtEventId.setText(Integer.toString(eventvo.getEvent_no()));
			txtWriterId.setText(eventvo.getEvent_writer());
			txtMemId.setText(eventvo.getMem_id());
			txtEventName.setText(eventvo.getEvent_title());
			txtEventDate.setText(eventvo.getEvent_time());
			txtImage.setText(eventvo.getEvent_photo());
		}
		txtMemId.clear();
		txtEventName.clear();
		txtImage.clear();




		strWork="";

	}

	@FXML
	void dataOKClick(ActionEvent event) throws RemoteException  {
		
		String writer_id = txtWriterId.getText();
		String mem_id = txtMemId.getText();
		String event_name = txtEventName.getText();
		String event_date = txtEventDate.getText();
		String event_photo = txtImage.getText();

		if(event_name.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "이벤트 제목을 입력하세요");
			return;
		}
//		if(event_date.isEmpty()) {
//			AlertUtil.warnMsg("입력오류", "이벤트 게시날짜를 입력하세요  ");
//			return;
//		}
		
//		int cnt1 = memberService.idChkMember(mem_id);
//		if(cnt1>0) {
//			AlertUtil.warnMsg("오류", "당첨자ID를 확인하세요");
//			return; 
//		}
		
		if(event_photo.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "이벤트 사진을 등록하세요 ");
			return;
		}
		

		EventVO eventvo = new EventVO();
		//eventvo.setEvent_no(event_id);
		eventvo.setEvent_writer(writer_id);
		eventvo.setMem_id(mem_id);
		eventvo.setEvent_title(event_name);
		eventvo.setEvent_time(event_date);
		eventvo.setEvent_photo(event_photo);

		if("add".equals(strWork)) {  //추가 작업
    		
    		//VO값을 DB에 저장 인서트!회원가입 
    		int cnt = service.insertEvent(eventvo); 
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "추가성공");
    		}else {
    			AlertUtil.warnMsg("작업결과", "당첨자ID를 다시 입력해주세요");
    		}
    			
    	}else if("edit".equals(strWork)) { //수정작업 
    		int event_id = Integer.parseInt(txtEventId.getText());
    		eventvo.setEvent_no(event_id);
    		int cnt = service.updateEvent(eventvo);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "수정성공");
    		}else {
    			AlertUtil.warnMsg("작업결과", "당첨자ID를 다시 입력해주세요");
    		}
    	}
		getAllEvent( ) ;

		//TextField들을 편집 가능 상태로 만든다 
//    	txtEventId.setEditable(false);
//    	txtWriterId.setEditable(false);
//    	txtMemId.setEditable(false);
//    	txtEventName.setEditable(false);
//    	txtEventDate.setEditable(false);
//    	txtImage.setEditable(false);
    	
    	
    	
    	
    	txtEventId.clear();
    	txtWriterId.clear();
		txtMemId.clear();
		txtEventName.clear();
		txtEventDate.clear();
		txtImage.clear();
		
		txtEventName.setDisable(true);
		txtEventDate.setDisable(true);
		txtImage.setDisable(true);
		txtMemId.setDisable(true);
		
		//추가, 수정, 삭제 , 테이블뷰를 비활성화
    	btnAdd.setDisable(false);
    	btnEdit.setDisable(false);
    	btnDel.setDisable(false);
    	tableView.setDisable(false);
    	
    	//확인, 취소버튼 활성화 
    	btnOK.setDisable(false);
    	btnCancel.setDisable(false);
    	btnImg.setDisable(false);
    	
    	
    	

	}

	@FXML
	void tableViewClick(MouseEvent event)  {
		btnOK.setDisable(true);
		btnCancel.setDisable(true);
		btnImg.setDisable(true);
		
		if(tableView.getSelectionModel().isEmpty()) {
			return;
		}

		EventVO eventvo = tableView.getSelectionModel().getSelectedItem();


		txtMemId.setText(eventvo.getMem_id());
		txtWriterId.setText(eventvo.getEvent_writer());
		txtEventId.setText(Integer.toString(eventvo.getEvent_no()));
		txtEventName.setText(eventvo.getEvent_title());
		txtImage.setText(eventvo.getEvent_photo());
		txtEventDate.setText(eventvo.getEvent_time());
		 
//		String path = txtImage.getText();
//
//
//		File file = new File(path);
		//Image image = new Image(M_eventController.class.getResourceAsStream(eventvo.getEvent_photo()));
		File file = new File(eventvo.getEvent_photo());
		Image image = new Image(file.toURI().toString());
		imgView.setImage(image);
	}
	
	
	private ObservableList<EventVO> eventList;
	private String strWork="";
	private EventVO eventVo; 

	
	

	@FXML
	void initialize() {
		Registry reg = null;

		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IEventService) reg.lookup("eventService");
			
			eventIdCol.setCellValueFactory(new PropertyValueFactory<>("event_no"));
			writerIdCol.setCellValueFactory(new PropertyValueFactory<>("event_writer"));
			memIdCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<>("event_title"));
			dateCol.setCellValueFactory(new PropertyValueFactory<>("event_time"));
			
			getAllEvent();




		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	
	
	
	private void getAllEvent() throws RemoteException {
		List<EventVO> eventData;

		try {
			eventData = service.getAllEvent();
			eventList = FXCollections.observableArrayList(eventData);
			tableView.setItems(eventList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
