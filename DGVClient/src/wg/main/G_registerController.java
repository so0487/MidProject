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
import javafx.event.EventHandler;
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
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import util.AlertUtil;
import wg.hint.service.IHintService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.member.service.IMemberService;
import wg.vo.GenreVO;
import wg.vo.HintVO;
import wg.vo.IssueCouponVO;
import wg.vo.LoginVO;
import wg.vo.MemberVO;

public class G_registerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<HintVO> cmbHint;
    
    @FXML
    private Button btnIdChk;

    @FXML
    private RadioButton radioWoman;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;
    @FXML
	private ToggleGroup gender;

    
    @FXML
    private PasswordField txtPw;

    @FXML
    private PasswordField txtPwChk;


    @FXML
    private RadioButton radioMan;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtAddr;
    
    @FXML
    private TextField txtBir;

    @FXML
    private TextField txtHint;

    @FXML
    private TextField txtAnswer;

    @FXML
    private Button btnTelChk;

    @FXML
    private Button btnEmailChk;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
    
    private IMemberService service;
    private IHintService hintService;
    private IIssueCouponService iService;
    
    private G_topController topCtrl;
    
    public void setTopCtrl(G_topController topCtrl) {
    	this.topCtrl = topCtrl;
    }

    
    //가입하기 버튼
    @FXML
    void dataRun(ActionEvent event) throws RemoteException {
		
    	String id = txtId.getText();
    	String name = txtName.getText();
    	String pw = txtPw.getText();
    	String pwChk = txtPwChk.getText();
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
    	
    	//오류체크 
    			if(id.isEmpty()) {
    				AlertUtil.warnMsg("입력오류", "회원ID를 입력하세요 ");
    				txtId.requestFocus();
    				return;
    			}
    			if(name.isEmpty()) {
    				AlertUtil.warnMsg("입력오류", "회원이름을 입력하세요 ");
    				txtName.requestFocus();
    				return;
    			}
    			if(pw.isEmpty()) {
    				AlertUtil.warnMsg("입력오류", "비밀번호를 입력하세요 ");
    				txtPw.requestFocus();
    				return;
    			}
    			if(pwChk.isEmpty()) {
    				AlertUtil.warnMsg("입력오류", "비밀번호 재확인을 위하여 한번 더 입력하세요 ");
    				txtPwChk.requestFocus();
    				return;
    			}
    			if (!(txtPw.getText().equals(txtPwChk.getText()))) {
    				AlertUtil.warnMsg("입력오류", "비밀번호가 일치하지않습니다. ");
    				txtPwChk.requestFocus();
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
    	memVo.setMem_lev(3);
    	
    	if(idClick&&telClick&&emailClick) {   
			
		}else {
			if(!idClick) {
				AlertUtil.infoMsg("작업결과", "아이디 중복확인을 진행하십시오.");
			}else if(!telClick) {
				AlertUtil.infoMsg("작업결과", "전화번호 중복확인을 진행하십시오.");
			}else if(!emailClick) {
				AlertUtil.infoMsg("작업결과", "이메일 중복확인을 진행하십시오.");
			}return; 
		}
    	
    		
		// VO값을 DB에 저장
		int cnt = service.insertMember(memVo);
		
		// 가입 성공 시 쿠폰발행
		if (cnt > 0) {
			IssueCouponVO ivo = new IssueCouponVO();
			ivo.setCou_id("C0001"); 
			ivo.setMem_id(memVo.getMem_id());
			iService.insertIssueCoupon(ivo);
			AlertUtil.infoMsg("회원가입성공", memVo.getMem_name() + "님 감사합니다. 회원가입 축하쿠폰이 발행되었습니다.!");
			// 로그인화면으로 이동
			try {
				topCtrl.changeColor();
				FXMLLoader loader1 = new FXMLLoader(G_topController.class.getResource("../fxml/G_login.fxml"));
				Parent login = loader1.load();
				G_loginController loginCtrl = loader1.getController();

				// 로그인 페이지에 자기 객체, top 객체 넘겨주기
				loginCtrl.setTopCtrl(topCtrl);

				U_main.root.setCenter(login);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			AlertUtil.warnMsg("회원가입실패", "알 수 없는 오류로 회원가입에 실패하였습니다. 다시 시도해주시기 바랍니다.");
		}
    	
    }
    
    @FXML
    void dataCancel(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("./fxml/G_center.fxml"));
			StackPane center = loader.load();
			U_main.root.setCenter(center);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
    
   private boolean idClick; 
   private boolean telClick; 
   private boolean emailClick; 
    
    //아이디 중복체크 
    @FXML
    void dataIdChk(ActionEvent event) throws RemoteException {
    	idClick = true; 
    	
    	String mem_id = txtId.getText();
    	if(mem_id.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "아이디를 입력하세요");
    		return;  
    	}
		int cnt = service.idChkMember(mem_id);
		
		if(cnt==0) {
			AlertUtil.infoMsg("작업결과", "사용가능한 아이디입니다.");
		}else {
			AlertUtil.warnMsg("작업결과", "이미 사용중인 아이디입니다. 다.른 아이디를 입력해주십시오.");
			idClick = false; 
		}
    }
    
    
  //전화번호 중복체크
    @FXML
    void dataTelChk(ActionEvent event) throws RemoteException {
    	telClick = true; 
    	
    	String mem_tel = txtTel.getText();
    	if(mem_tel.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "전화번호를 입력하세요");
    		return; 
    	}
    	int cnt = service.telChkMember(mem_tel);
		
		if(cnt==0) {
			AlertUtil.infoMsg("작업결과", "사용가능한 전화번호입니다.");
		}else {
			AlertUtil.warnMsg("작업결과", "이미 등록된 휴대폰번호입니다.");
			telClick = false; 
		}
    }
    
  //이메일 중복체크
    @FXML
    void dataEmailChk(ActionEvent event) throws RemoteException {
    	emailClick = true; 
    	
    	String mem_email = txtEmail.getText();
    	if(mem_email.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "전화번호를 입력하세요");
    		return; 
    	}
    	int cnt = service.emailChkMember(mem_email);
		
		if(cnt==0) {
			AlertUtil.infoMsg("작업결과", "사용가능한 이메일입니다.");
		}else {
			AlertUtil.warnMsg("작업결과", "이미 등록된 이메일주소입니다.");
			emailClick = false; 
		}
    }
    
    
    
    
    
    private ObservableList<HintVO> hintList;
    
    @FXML
    void initialize() {
    	
    	//cmbHint.setValue("제 1호 보물은?");
    	Registry reg = null;
    	List<HintVO> hintData;
    	try {
         //   reg = LocateRegistry.getRegistry("192.168.31.32",9988);
    		reg = LocateRegistry.getRegistry("localhost",9988);
            service = (IMemberService) reg.lookup("memberService");
            hintService = (IHintService) reg.lookup("hintService");
            iService = (IIssueCouponService) reg.lookup("issueCouponService");
            
            
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
    		
    		//콤보박스의 선택한 데이터가 표시되는 영역을 버튼 영역이라한다.  
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
    		//콤보박스를 클릭했을때 이벤트 처리 
    	
            
         } catch (RemoteException e) {
            e.printStackTrace();
         } 
    	catch (NotBoundException e) {
            e.printStackTrace();
         }
    	//cmbHint.setValue("제 1호 보물은?");
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

