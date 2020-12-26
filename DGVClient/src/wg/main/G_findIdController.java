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
import javafx.scene.control.TextField;
import util.AlertUtil;
import wg.member.service.IMemberService;
import wg.vo.MemberVO;

public class G_findIdController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private Button btnGoFindId;

    @FXML
    private Button btnGoFindPw;

    @FXML
    private TextField tfTel;

    @FXML
    private Button btnFindId;
    
    private IMemberService service;

    @FXML
    void findId(ActionEvent event) throws IOException {
    	
    	if(tfName.getText().isEmpty()||tfTel.getText().isEmpty()) {
    		
    	}
    	MemberVO vo = service.findMember(tfTel.getText());
    	if(vo==null) {
    		AlertUtil.infoMsg("아이디", "일치하는 정보가 없습니다.");
    		return;
    	}
    	
    	if(tfName.getText().equals(vo.getMem_name())) {
    		AlertUtil.infoMsg("아이디", vo.getMem_id().substring(0,vo.getMem_id().length()-3)+"***");
    		FXMLLoader loader = new FXMLLoader(G_findIdController.class.getResource("../fxml/G_top.fxml"));
			Parent top = loader.load();
			U_main.root.setTop(top);
			FXMLLoader loader1 = new FXMLLoader(G_findIdController.class.getResource("../fxml/G_top.fxml"));
			Parent center = loader.load();
			U_main.root.setCenter(center);
    	}else {
    		AlertUtil.warnMsg("아이디 찾기 실패", "입력하신 정보와 일치하는 회원이 없습니다.");
    		
    	}
    }

    @FXML
    void goFindId(ActionEvent event) {
    	
    }

    @FXML
    void goFindPw(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(G_findIdController.class.getResource("../fxml/G_findPw.fxml"));
			Parent center = loader.load();
			U_main.root.setCenter(center);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    @FXML
    void initialize() {
    	Registry reg = null;
		try {
			//reg = LocateRegistry.getRegistry("192.168.31.32",9988);
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IMemberService) reg.lookup("memberService");
			
			
	    	
	    	
	    	
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
