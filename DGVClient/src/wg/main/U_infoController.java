package wg.main;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import util.AlertUtil;
import wg.hint.service.IHintService;
import wg.member.service.IMemberService;
import wg.vo.HintVO;
import wg.vo.LoginVO;
import wg.vo.MemberVO;

public class U_infoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnMovieHistory;
	
	public Button getBtnMovieHistory() {
		return btnMovieHistory;
	}

	@FXML
	private Button btnMyInfo;

	@FXML
	private Button btnMyCoupon;

	@FXML
	private Button btnSnackHistory;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDel;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancel;

//    @FXML
//    private Button btnDelete1;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtEmail;
	
	@FXML
	private RadioButton radioWoman;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton radioMan;

	@FXML
	private TextField txtTel;

	@FXML
	private TextField txtBir;

	@FXML
	private TextField txtAddr;

	@FXML
	private TextField txtAnswer;

	@FXML
	private ComboBox<HintVO> cmbHint;

	@FXML
	private PasswordField txtPw;
	
	    

	   
	    

	@FXML
	private BorderPane outerBox;
	
	public BorderPane getOuterBox() {
		return outerBox;
	}

	private G_topController topCtrl;

	public void setTopCtrl(G_topController topCtrl) {
		this.topCtrl = topCtrl;
	}

	private IMemberService service;
	private IHintService hintService;

	   @FXML
	    void dataMyCoupon(ActionEvent event) {
	    	try {
	    		// 백그라운드 색 변경
	        	changeColor();
	        	btnMyCoupon.setStyle("-fx-border-color:#333; -fx-background-color: #333; -fx-border-width:1; -fx-border-radius:5; -fx-text-fill:white;  ");
	        	
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_coupon.fxml"));
	        	Parent review = loader.load(); 
	        	outerBox.setCenter(review);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    
	    @FXML
	    void dataSnackHis(ActionEvent event) {
	    	try {
	    		changeColor();
	    		btnSnackHistory.setStyle("-fx-border-color:#333; -fx-background-color: #333; -fx-border-width:1; -fx-border-radius:5; -fx-text-fill:white;   ");
	    		
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_snackHistorMain.fxml"));
	        	Parent snackHis = loader.load(); 
	        	outerBox.setCenter(snackHis);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    
	    @FXML
	    void dataMovieHistory(ActionEvent event) {
	    	try {
	    		// 백그라운드 색 변경
	        	changeColor();
	        	btnMovieHistory.setStyle("-fx-border-color:#333; -fx-background-color: #333; -fx-border-width:1; -fx-border-radius:5; -fx-text-fill:white;  ");
	    		
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_myBookMovieMain.fxml"));
	        	Parent bookMovieHis = loader.load(); 
	        	outerBox.setCenter(bookMovieHis);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    
	    @FXML
	    void dataMyInfo(ActionEvent event) {
	    	try {
	    		changeColor();
	    		btnMyInfo.setStyle("-fx-border-color:#333; -fx-background-color: #333; -fx-border-width:1; -fx-border-radius:5; -fx-text-fill:white;   ");
	    		
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_infoUpdate.fxml"));
	        	Parent info = loader.load(); 
	        	outerBox.setCenter(info);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

	    @FXML
	    void dataCancel(ActionEvent event) {
	    	//TextField들을 편집 가능 상태로 만든다 
	    	txtId.setEditable(false);
	    	txtName.setEditable(false);
	    	txtTel.setEditable(false);
	    	txtAddr.setEditable(false);
	    	txtAnswer.setEditable(false);
	    	txtBir.setEditable(false);
	    	txtEmail.setEditable(false);
	    	cmbHint.setEditable(false);
	    	
	    	//확인, 취소버튼 활성화 
	    	btnOk.setDisable(false);
	    	btnCancel.setDisable(false);
	    	
	    	btnEdit.setDisable(false);
//	    	btnDel.setDisable(false);
//	    	MemberTable.setDisable(false);
	    	
	    	//이전 작업 전에 TableView에서 선택된 데이터가 있으면 
	    	//해당 데이터를 TextField에 출력된다 
	    }
	    
	  
	    
	    @FXML
	    void dataDel(ActionEvent event) throws IOException {
	    	
	    	changeColor();
	    	btnDel.setStyle("-fx-border-color:#333; -fx-background-color: #333; -fx-border-width:1; -fx-border-radius:5; -fx-text-fill:white;  ");
	    	
	    	boolean check = AlertUtil.confirm("탈퇴", "정말 탈퇴하시겠습니까?");
	    	if(!check) {
	    		return;
	    	}
	    	
	    	  MemberVO vo = LoginVO.getCurrVo(); //로그인 정보를 불러와야한다.
	      	vo.getMem_id();
	    	
	    	int cnt = service.deleteMember(vo.getMem_id()); 
	    	if(cnt>0) {
	    		 try {
	    			 AlertUtil.infoMsg("작업결과", vo.getMem_id() + "님은 탈퇴하셨습니다. ");
	    			 topCtrl.backToBlack();
	    			 LoginVO.setCurrVo(null);
	    			   FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/G_center.fxml"));
	                   Parent main = loader.load();
	                   G_mediaViewController mainCtrl = loader.getController();
	                   mainCtrl.setTopCtrl(topCtrl);
	                   
	                   U_main.root.setCenter(main);
	                   topCtrl.backToBlack();
	              } catch (IOException e) {
	                 e.printStackTrace();
	              }
//	    		txtId.clear();
//	    		txtPw.clear();
//	    		txtName.clear();
//	    		txtTel.clear();
//	    		txtAddr.clear();
//	    		txtEmail.clear();
//	    		txtBir.clear();
//	    		txtAnswer.clear();
	    		
	    		
	    		
	    		
	    	}else {
	    		AlertUtil.warnMsg("작업결과", vo.getMem_id() + "회원탈퇴 실패 ");
	    	}
	    }

	    @FXML
	    void dataEdit(ActionEvent event) {
	    	
	    	txtTel.setPromptText("예) 010-1111-2222");
	    	txtEmail.setPromptText("예) aaa@naver.com");
	    	txtBir.setPromptText("예) 960826");
	    	txtPw.setPromptText("예) 소문자,숫자,특수문자조합 (8~12자리)");
	    	
	    	//TextField들을 편집 가능 상태로 만든다 
	    	//(txtId 수정제외 )
	    	txtId.setDisable(true);
	    	
	    	txtName.setEditable(true);
	    	txtPw.setEditable(true);
	    	txtTel.setEditable(true);
	    	txtAddr.setEditable(true);
	    	txtAnswer.setEditable(true);
	    	txtBir.setEditable(true);
	    	txtEmail.setEditable(true);
	    	//cmbHint.setEditable(true);
	    	
	    	//확인, 취소버튼 활성화 
	    	btnOk.setDisable(false);
	    	btnCancel.setDisable(false);
	    	
	    	txtName.setDisable(false);
	    	txtPw.setDisable(false);
	    	txtTel.setDisable(false);
	    	txtAddr.setDisable(false);
	    	txtAnswer.setDisable(false);
	    	txtBir.setDisable(false);
	    	txtEmail.setDisable(false);
	    	cmbHint.setDisable(false);
	    	radioMan.setDisable(false);
	    	radioWoman.setDisable(false);
	    	
	    	
	    	
	    	btnEdit.setDisable(true);
	    	
	    	txtName.requestFocus();
	    }

	    @FXML
	    void dataRun(ActionEvent event) throws RemoteException {
	    	String id = txtId.getText();
	    	String name = txtName.getText();
	    	String pw = txtPw.getText(); 
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
	    	
	    	HintVO temp = (HintVO) cmbHint.getValue(); 
	    	int hint = temp.getHint_no();
	    	
	    	String answer = txtAnswer.getText();
	    	
	    	//오류체크 
	    			
	    			if(name.isEmpty()) {
	    				AlertUtil.warnMsg("입력오류", "회원이름을 입력하세요 ");
	    				txtName.requestFocus();
	    				return;
	    			}
	    			if(addr.isEmpty()) {
	    				AlertUtil.warnMsg("입력오류", "회원의 주소를 입력하세요 ");
	    				txtAddr.requestFocus();
	    				return;
	    			}
	    	    	
	    	    	if(answer.isEmpty()) {
	    				AlertUtil.warnMsg("입력오류", "회원의 질문답을 입력하세요 ");
	    				txtAnswer.requestFocus();
	    				return;
	    			}
	    	    	if(gen.isEmpty()) {
	    				AlertUtil.warnMsg("입력오류", "성별을 클릭해주세요  ");
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
	    		    	  AlertUtil.warnMsg("입력오류", "전화번호를 양식에 맞게 입력해주세요 (예: 010-1111-2222)");
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
	    	    	memVo.setMem_name(name);
	    	    	memVo.setMem_pw(pw);
	    	    	memVo.setMem_gen(gen);
	    	    	memVo.setMem_tel(tel);
	    	    	memVo.setMem_email(email);
	    	    	memVo.setMem_bir(bir);
	    	    	memVo.setMem_addr(addr);
	    	    	memVo.setHint_no(hint);
	    	    	memVo.setMem_answer(answer);
	    	    	
	    	    	

	    	    	//추가버튼을 눌른 후의 작업인지 검사 
	    	    //	if("edit".equals(strWork)) {  //수정 작업
	    	    		
	    	    		//VO값을 DB에 저장 
	    	    		int cnt = service.updateMember(memVo);
	    	    		
	    	    		if(cnt>0) {
	    	    			AlertUtil.infoMsg("작업결과", "수정성공");
	    	    		}else {
	    	    			AlertUtil.warnMsg("작업결과", "수정실패...");
	    	    		}
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
	    	        	btnOk.setDisable(false);
	    	        	btnCancel.setDisable(false);
	    	        	
	    	        	// 수정, 삭제 , 테이블뷰를 비활성화
	    	        	btnEdit.setDisable(false);
	    	        	
	    	        	getAllMemberList();
	    	        	
	    	    		
	    }
	    private ObservableList<MemberVO> memberList;
	    private ObservableList<HintVO> hintList;
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
	            
	            MemberVO vo = LoginVO.getCurrVo(); //로그인 정보를 불러와야한다.
	        	txtId.setText(vo.getMem_id());
	        	txtPw.setText(vo.getMem_pw());
	    		txtName.setText(vo.getMem_name());
	    		txtTel.setText(vo.getMem_tel());
	    		txtAddr.setText(vo.getMem_addr());
	    		txtAnswer.setText(vo.getMem_answer());
	    		txtBir.setText(vo.getMem_bir().substring(2, 4)+vo.getMem_bir().substring(5, 7)
	    				+vo.getMem_bir().substring(8, 10));
	    		//txtBir.setText(vo.getMem_bir().substring(2, 10));
	    		txtEmail.setText(vo.getMem_email());
	    		
	    		if(vo.getMem_gen().equals("남자")) {
	    			radioMan.setSelected(true);
	    		}else {
	    			radioWoman.setSelected(true);
	    		}
	    		
	    		for(HintVO hint : cmbHint.getItems()) {
	    			if(hint.getHint_no()==vo.getHint_no()) {
	    				cmbHint.setValue(hint);
	    			}
	    		}
	            
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
	            
	         } catch (RemoteException e) {
	            e.printStackTrace();
	         } catch (NotBoundException e) {
	            e.printStackTrace();
	         }

	    }
	    private void getAllMemberList() throws RemoteException   {
	        List<MemberVO> list = service.getAllMemberList();
	        memberList = FXCollections.observableArrayList(list);
	    	
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
	    
	    private void changeColor() {
	    	btnMyCoupon.setStyle("-fx-border-color:#333; -fx-background-color:white; fx-border-width:1; -fx-border-radius:5;  ");
	    	btnMovieHistory.setStyle("-fx-border-color:#333; -fx-background-color:white; fx-border-width:1; -fx-border-radius:5;  ");
	    	btnMyInfo.setStyle("-fx-border-color:#333; -fx-background-color:white; fx-border-width:1; -fx-border-radius:5; -fx-text-fill:black;   ");
	    	btnSnackHistory.setStyle("-fx-border-color:#333; -fx-background-color:white; fx-border-width:1; -fx-border-radius:5;    ");
	    	btnDel.setStyle("-fx-border-color:#333; -fx-background-color:white; fx-border-width:1; -fx-border-radius:5;    ");
	    }
	    
	    
	}