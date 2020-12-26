package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.AlertUtil;
import wg.member.service.IMemberService;
import wg.vo.HintVO;
import wg.vo.MemberVO;

public class G_findPwController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfId;

    @FXML
    private PasswordField tfTel;

    @FXML
    private Button btnGoFindId;

    @FXML
    private Button btnGoFindPw;

    @FXML
    private Button btnSearch;

    @FXML
    private Label lbQuestion;

    @FXML
    private Button btnReSet;

    @FXML
    private Button btnCer;

    @FXML
    private TextField tfNewPw;

    @FXML
    private TextField tfNewPwChk;

    @FXML
    private TextField tfAnswer;
    private IMemberService service;
    

    @FXML
    void certification(ActionEvent event) {
    	if(vo.getMem_answer().equals(tfAnswer.getText())){
    		AlertUtil.infoMsg("인증 결과","인증 성공하셨습니다.");
    		tfNewPw.setEditable(true);
    		tfNewPwChk.setEditable(true);
    	}else {
    		AlertUtil.warnMsg("인증 결과","인증 실패하셨습니다.");
    	}
    	
    }

    @FXML
    void goFindId(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_findId.fxml"));
			Parent center = loader.load();
			U_main.root.setCenter(center);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void goFindPw(ActionEvent event) {
    	btnGoFindPw.setDisable(true);
    }

    @FXML
    void reSetting(ActionEvent event) throws RemoteException {
    	if(tfNewPw.getText().equals(tfNewPwChk.getText())) {
    		vo.setMem_pw(tfNewPw.getText());
    		service.resetPassword(vo);
;    		AlertUtil.infoMsg("재설정 결과", "비밀번호가 재설정 되었습니다.");
    	}else {
    		AlertUtil.warnMsg("재설정 결과", "비밀번호와 비밀번호 확인 입력값이 같지 않습니다.");
    	}

    }
    MemberVO vo = null;
    @FXML
    void search(ActionEvent event) throws RemoteException {
    	 vo = service.findMember(tfTel.getText());
    	if(!(tfId.getText().equals(vo.getMem_id()))) {
    		AlertUtil.errorMsg("회원 조회 오류", "입력하신 정보와 일치하는 회원이 없습니다.");
    		return;
    	}else {
    		int no =vo.getHint_no();
    		HintVO hv = service.findHint(no);
    		lbQuestion.setText(hv.getHint_question());
    		tfAnswer.setEditable(true);
    		
    	}
    }
    @FXML
    void initialize() {
    	Registry reg = null;
		try {
//			reg = LocateRegistry.getRegistry("192.168.31.32",9988);
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IMemberService) reg.lookup("memberService");
			tfAnswer.setEditable(false);
			tfNewPw.setEditable(false);
			tfNewPwChk.setEditable(false);
			
			
			
	    	
	    	
	    	
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
