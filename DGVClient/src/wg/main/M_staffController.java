package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import util.AlertUtil;
import wg.hint.service.IHintService;
import wg.member.service.IMemberService;
import wg.vo.HintVO;
import wg.vo.MemberVO;

public class M_staffController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane scroll;
    
    @FXML
    private ComboBox<HintVO> cmbHint;
    
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
    
    

    @FXML
    private RadioButton radioWoman;

    @FXML
    private RadioButton radioMan;

    @FXML
    private TextField txtTel;


    @FXML
    private TextField txtAnswer;

    @FXML
    private TextField txtBir;

    @FXML
    private TextField txtAddr;

    @FXML
    private TableView<MemberVO> StaffTable;;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> genCol;

    @FXML
    private TableColumn<?, ?> birCol;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TableColumn<?, ?> telCol;

    @FXML
    private TableColumn<?, ?> addrCol;

    @FXML
    private TableColumn<?, ?> hintCol;

    @FXML
    private TableColumn<?, ?> answerCol;
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;
    
    private IMemberService service;
    private IHintService hintService;
    
    

    //취소버튼
    @FXML
    void dataCancel(ActionEvent event) {
    	//TextField들을 편집 가능 상태로 만든다 
    	txtId.setEditable(false);
    	txtPw.setEditable(false);
    	txtName.setEditable(false);
    	txtTel.setEditable(false);
    	txtAddr.setEditable(false);
    	txtAnswer.setEditable(false);
    	txtBir.setEditable(false);
    	txtEmail.setEditable(false);
    	cmbHint.setEditable(false);
    	
    	//확인, 취소버튼 활성화 
    	btnOk.setDisable(true);
    	btnCancel.setDisable(true);
    	
    	//  삭제 , 테이블뷰를 비활성화
    	btnAdd.setDisable(false);
    	btnEdit.setDisable(false);
    	btnDel.setDisable(false);
    	StaffTable.setDisable(false);
    	
    	//이전 작업 전에 TableView에서 선택된 데이터가 있으면 
    	//해당 데이터를 TextField에 출력된다 
    	if(!StaffTable.getSelectionModel().isEmpty()) {
    		MemberVO mem = StaffTable.getSelectionModel().getSelectedItem();
    		
    		txtId.setText(mem.getMem_id());
    		txtName.setText(mem.getMem_name());
    		txtTel.setText(mem.getMem_tel());
    		txtAddr.setText(mem.getMem_addr());
    	}
    	
    	strWork = "";
    	
    	
    }
    
    //추가버튼
    @FXML
    void dataAdd(ActionEvent event) {
    	
    	txtId.setDisable(false);
    	txtPw.setDisable(false);
    	txtName.setDisable(false);
    	txtTel.setDisable(false);
    	txtAddr.setDisable(false);
    	txtAnswer.setDisable(false);
    	txtBir.setDisable(false);
    	txtEmail.setDisable(false);
    	radioMan.setDisable(false);
    	radioWoman.setDisable(false);
    	cmbHint.setDisable(false);
    	//TextField들을 편집 가능 상태로 만든다 
    	txtId.setEditable(true);
    	txtPw.setEditable(true);
    	txtName.setEditable(true);
    	txtTel.setEditable(true);
    	txtAddr.setEditable(true);
    	txtAnswer.setEditable(true);
    	txtBir.setEditable(true);
    	txtEmail.setEditable(true);
    	
    	//확인, 취소버튼 활성화 
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	//추가, 수정, 삭제 , 테이블뷰를 비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	StaffTable.setDisable(true);
    	
//    	//TextField들의 내용을 모두 삭제한다 
    	txtId.clear();
    	txtName.clear();
    	txtTel.clear();
    	txtAddr.clear();
    	txtAnswer.clear();
    	txtBir.clear();
    	txtEmail.clear();
    	txtPw.clear();
    
    	
    	
    	txtId.requestFocus(); //id입력하는곳에 focus주기 
    	strWork = "add";
    	
    	
    }
    
    //삭제버튼 
    @FXML
    void dataDel(ActionEvent event) throws RemoteException {
    	if(StaffTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("직원선택", "삭제할 직원을 선택한 후 사용하세요! ");
    		return; 
    	}
    	
    	//삭제할 직원ID 구하기 
    	String memId = StaffTable.getSelectionModel().getSelectedItem().getMem_id();
    	
    	int cnt = service.deleteMember(memId); 
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", memId + "직원을 삭제했습니다.");
    		
    		getAllStaff() ; //삭제후 직원 전체 데이터 다시 가져오기
    		
    		txtId.clear();
    		txtPw.clear();
    		txtName.clear();
    		txtTel.clear();
    		txtAddr.clear();
    		txtEmail.clear();
    		txtBir.clear();
    		txtAnswer.clear();
    	}else {
    		AlertUtil.warnMsg("작업결과", memId + "직원삭제 실패 ");
    	}
    	
    }
    
	//수정버튼
    @FXML
    void dataEdit(ActionEvent event) {
    	
    	//수정할 데이터를 선택했는지 여부 검사 
    	if(StaffTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("자료선택", "수정할 직원을 선택 한 후 사용하세요 ");
    		return; 
    	}
    	
    	txtName.setDisable(false);
    	txtTel.setDisable(false);
    	txtAddr.setDisable(false);
    	txtAnswer.setDisable(false);
    	txtBir.setDisable(false);
    	txtEmail.setDisable(false);
    	radioMan.setDisable(false);
    	radioWoman.setDisable(false);
    	cmbHint.setDisable(false);
    	
    	txtName.setEditable(true);
    	txtTel.setEditable(true);
    	txtAddr.setEditable(true);
    	txtAnswer.setEditable(true);
    	txtBir.setEditable(true);
    	txtEmail.setEditable(true);
    	//cmbHint.setEditable(true);
    	
    	//확인, 취소버튼 활성화 
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	//추가, 수정, 삭제 , 테이블뷰를 비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	StaffTable.setDisable(true);
    	
    	txtName.requestFocus(); //이름부터 편집할수 있도록 포커스를 이름에 두기 
    	strWork = "edit";

    }
    //확인버튼
    @FXML
    void dataRun(ActionEvent event) throws RemoteException {
    	
    	String id = txtId.getText();
    	String pw = txtPw.getText();
    	String name = txtName.getText();
    	String gen = "";
    	if(radioMan.isSelected()) {
    		gen = "남자";
    	}else {
    		gen = "여자";
    	}
    	String tel = txtTel.getText();
    	String email = txtEmail.getText();
    	String bir = txtBir.getText();
    	String addr = txtAddr.getText();
    	
    	HintVO temp = cmbHint.getValue(); 
    	int hint = temp.getHint_no();
    	
    	String answer = txtAnswer.getText();
    	
    	if(id.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "직원ID를 입력하세요 ");
			txtId.requestFocus();
			return;
		}
		if(name.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "직원이름을 입력하세요 ");
			txtName.requestFocus();
			return;
		}
		if(pw.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "비밀번호를 입력하세요 ");
			txtPw.requestFocus();
			return;
		}
		if(gen.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "성별을 클릭해주세요  ");
			return;
		}
		if(tel.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "휴대폰번호를 입력해주세요  ");
			txtTel.requestFocus();
			return;
		}
		
		if(email.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "이메일을 입력해주세요  ");
			txtEmail.requestFocus();
			return;
		}
		if(bir.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "생년월일을 입력해주세요  ");
			txtBir.requestFocus();
			return;
		}
		if(addr.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "주소를  입력해주세요  ");
			txtAddr.requestFocus();
			return;
		}
		
		
		if(cmbHint.getValue() ==null) {
			AlertUtil.warnMsg("입력오류", "나만의질문을 선택해주세요  ");
			cmbHint.requestFocus();
			return;
		}
		if(answer.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "나만의 답을 입력해주세요  ");
			txtAnswer.requestFocus();
			return;
		}
		
		
		//아이디 정규식 
		String idPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,10}$";
	      Matcher idMatcher = Pattern.compile(idPattern).matcher(id);
	      if (!idMatcher.matches()) {
	    	  AlertUtil.warnMsg("입력오류", " 아이디의 첫문자는 영문자로 시작하고 4~10자리 이내로 생성해주세요.(특수문자는 불가능) ");
	         return;
	      } 
	      
	      //비밀번호정규식
	      String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}])[A-Za-z[0-9]!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}]{8,12}$";
	      Matcher pwMatcher = Pattern.compile(pwPattern).matcher(pw);
	      if(!pwMatcher.matches()  ){
	    	  AlertUtil.warnMsg("입력오류", "비밀번호는 영문자,숫자,특수문자를 조합하여   8~12자리 이내로 생성해주세요  ");
		         return;
	      }else {
	    	  System.out.println("된다 ");
	      }
	      
	      //이메일
	      String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

	      Matcher emailMatcher = Pattern.compile(emailPattern).matcher(email);
	      if(!emailMatcher.matches()  ){
	    	  AlertUtil.warnMsg("입력오류", "이메일형식을 지켜주세요 (예: kde3603@naver.com)   ");
		         return;
	      }
	      
	    //전화번호
	      String telPattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
	      Matcher telMatcher = Pattern.compile(telPattern).matcher(tel);
	      if(!telMatcher.matches()  ){
	    	  AlertUtil.warnMsg("입력오류", "전화번호를 양식에 맞게 입력해주세요 (예: 010-1111-2222) ");
		         return;
	      }
	      
	    //생년월일
	      String birPattern = "([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))";
	      Matcher birMatcher = Pattern.compile(birPattern).matcher(bir);
	      if(!birMatcher.matches()  ){
	    	  AlertUtil.warnMsg("입력오류", "생년월일을 6자리로 입력해주세요(예:960826)    ");
		         return;
	      }
    			
    			
    			
    			//입력한 정보를 MemberVO객체에 저장한다. 
    	    	MemberVO memVo = new MemberVO(); 
    	    	memVo.setMem_id(id);
    	    	memVo.setMem_pw(pw);
    	    	memVo.setMem_name(name);
    	    	memVo.setMem_gen(gen);
    	    	memVo.setMem_tel(tel);
    	    	memVo.setMem_email(email);
    	    	memVo.setMem_bir(bir);
    	    	memVo.setMem_addr(addr);
    	    	memVo.setHint_no(hint);
    	    	memVo.setMem_answer(answer);
    	    	memVo.setMem_lev(2);
    	    	

    	    	//추가버튼을 눌른 후의 작업인지 검사 
    	    	if("add".equals(strWork)) {  //추가 작업
    	    		
    	    		//VO값을 DB에 저장 인서트!회원가입 
    	    		int cnt = service.insertMember(memVo); 
    	    		
    	    		if(cnt>0) {
    	    			AlertUtil.infoMsg("작업결과", "추가성공");
    	    		}else {
    	    			AlertUtil.warnMsg("작업결과", "추가실패...");
    	    		}
    	    			
    	    	}else if("edit".equals(strWork)) { //수정작업 
    	    		
    	    		int cnt = service.updateMember(memVo);
    	    		
    	    		if(cnt>0) {
    	    			AlertUtil.infoMsg("작업결과", "수정성공");
    	    		}else {
    	    			AlertUtil.warnMsg("작업결과", "수정실패...");
    	    		}
    	    	}
    	    	
    	    		//TextField들을 편집 가능 상태로 만든다 
    	        	txtId.setEditable(false);
    	        	//txtPw.setEditable(false);
    	        	txtName.setEditable(false);
    	        	txtTel.setEditable(false);
    	        	txtAddr.setEditable(false);
    	        	txtAnswer.setEditable(false);
    	        	txtBir.setEditable(false);
    	        	txtEmail.setEditable(false);
    	        	cmbHint.setEditable(false);
    	        	
    	        	//확인, 취소버튼 활성화 
    	        	btnOk.setDisable(true);
    	        	btnCancel.setDisable(true);
    	        	
    	        	//추가, 수정, 삭제 , 테이블뷰를 비활성화
    	        	btnAdd.setDisable(false);
    	        	btnEdit.setDisable(false);
    	        	btnDel.setDisable(false);
    	        	StaffTable.setDisable(false);
    	        	
    	        	getAllStaff() ;
    	        	
    	        	txtId.clear();
    	        	txtPw.clear();
    	    		txtName.clear();
    	    		txtTel.clear();
    	    		txtAddr.clear();
    	    		txtAnswer.clear();
    	    		txtBir.clear();
    	    		txtEmail.clear();
    	    		//cmbHint.clear();
    	        	
    	        }
    
  //Table클릭했을때  값누르면 들어가게 하는거
    @FXML
    void tableClick(MouseEvent event) {
    	if(StaffTable.getSelectionModel().isEmpty()) {
    		return; 
    	}
    	//선택한 데이터 가져오기 
    	MemberVO mem = StaffTable.getSelectionModel().getSelectedItem();
    	
    	txtId.setText(mem.getMem_id());
    	txtPw.setText(mem.getMem_pw());
		txtName.setText(mem.getMem_name());
		txtTel.setText(mem.getMem_tel());
		txtAddr.setText(mem.getMem_addr());
		txtAnswer.setText(mem.getMem_answer());
		txtBir.setText(mem.getMem_bir().substring(2, 4)+mem.getMem_bir().substring(5, 7)
				+mem.getMem_bir().substring(8, 10));
		txtEmail.setText(mem.getMem_email());
		if(mem.getMem_gen().equals("남자")) {
			radioMan.setSelected(true);
		}else {
			radioWoman.setSelected(true);
		}
		
		for(HintVO hint : cmbHint.getItems()) {
			if(hint.getHint_no()==mem.getHint_no()) {
				cmbHint.setValue(hint);
				
			}
		}
		
    }
    
 // TableView에 넣을 데이터가 저장될 List객체 변수선언
    private ObservableList<MemberVO> memberList;
    private ObservableList<HintVO> hintList;
    private String strWork = "";  //추가, 수정 상태를 구분하기 위한 변수 선언 
    
    	    	

    	    @FXML
    	    void initialize() {
    	    	Registry reg = null;
    	    	List<HintVO> hintData;
    	    	
    	    	try {
    	            reg = LocateRegistry.getRegistry("localhost",9988);
    	            service = (IMemberService) reg.lookup("memberService");
    	            hintService = (IHintService) reg.lookup("hintService");
    	            
    	            
    	            hintData = hintService.getAllHint();
    	            hintList = FXCollections.observableArrayList(hintData);
    	            cmbHint.setItems(hintList);
    	            cmbHint.setValue(hintList.get(0));
    	            
    	          //콤보박스의 데이터 목록이 보여지는 곳의 내용 변경하기 
    	    		cmbHint.setCellFactory(
    	    				new Callback<ListView<HintVO>, ListCell<HintVO>>() {
    	    			
    	    					
    	    			@Override
    	    			public ListCell<HintVO> call(ListView<HintVO> param) {
    	    				
    	    				return new ListCell<HintVO>() {
    	    					@Override
    	    					protected void updateItem(HintVO item, boolean empty) {
    	    						// TODO Auto-generated method stub
    	    						super.updateItem(item, empty);
    	    						if(item == null  || empty) {
    	    							setText(null);
    	    						}else {
    	    							setText(item.getHint_question() );
    	    						}
    	    					}
    	    				};
    	    			}
    	    		});
    	    		
    	    		//콤보박스의 선택한 데이터가 표시되는 영역을 버튼 영역이라한다  
    	    		//이 부분의 내용도 같이 변경해주어야한다. 
    	    		cmbHint.setButtonCell(new ListCell<HintVO>() {
    	    			@Override
    	    			protected void updateItem(HintVO item, boolean empty) {
    	    				// TODO Auto-generated method stub
    	    				super.updateItem(item, empty);
    	    				
    	    				if(item == null  || empty) {
    	    					setText(null);
    	    				}else {
    	    					setText(item.getHint_question() );
    	    				}
    	    			}
    	    		});
    	            
    	            //TableView의 컬럼들과 VO를 연결한다.
    	            idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	            nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
    	            telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
    	            addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
    	            genCol.setCellValueFactory(new PropertyValueFactory<>("mem_gen"));
    	            birCol.setCellValueFactory(new PropertyValueFactory<>("mem_bir"));
    	            emailCol.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
    	            hintCol.setCellValueFactory(new PropertyValueFactory<>("hint_no"));
    	            answerCol.setCellValueFactory(new PropertyValueFactory<>("mem_answer"));
    	            
    	            getAllStaff() ;
    	            
    	            
    	         } catch (RemoteException e) {
    	            e.printStackTrace();
    	         } catch (NotBoundException e) {
    	            e.printStackTrace();
    	         }

    	       }
    	    

    	     
private void getAllStaff() throws RemoteException   {
    List<MemberVO> list = service.getAllStaff();
    memberList = FXCollections.observableArrayList(list);
    StaffTable.setItems(memberList);
	
}

private void getAllHint() {
	List<HintVO> hintData;
	try {
		hintData = hintService.getAllHint();
		hintList = FXCollections.observableArrayList(hintData);
		cmbHint.setItems(hintList);
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	
}
}


