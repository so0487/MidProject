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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.member.service.IMemberService;
import wg.vo.LoginVO;
import wg.vo.MemberVO;

public class G_loginController {

    @FXML
    private ResourceBundle resources;

    @FXML   
    private URL location;

    @FXML
    private TextField mem_id;

    @FXML
    private PasswordField mem_pw;
    
    @FXML
    private Button btnRegister;
    
    @FXML
    void btnRegClick(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/G_register.fxml"));
         StackPane center = loader.load();
         U_main.root.setCenter(center);
      } catch (IOException e) {
         e.printStackTrace();
      }
    }
    
    
    @FXML
    private Button loginbtn;
    
    private IMemberService service;
    
    private G_topController con;
    
    private G_mediaViewController meCon;
    

   private BorderPane bp;
    
    
   public BorderPane getBp() {
      return bp;
   }

   public void setBp(BorderPane bp) {
      this.bp = bp;
   }
   
   public G_mediaViewController getMeCon() {
      return meCon;
   }
   public void setMecon(G_mediaViewController meCon) {
      this.meCon = meCon;
   }
   
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   private G_mediaViewController centerCtrl;
   
   public void setCenterCtrl(G_mediaViewController centerCtrl) {
      this.centerCtrl = centerCtrl;
   }
   
   public G_mediaViewController getCenterCtrl() {
      return centerCtrl;
   }
   
    private G_topController topCtrl;
    
    
    
    public G_topController getTopCtrl() {
       return topCtrl;
    }
    public void setTopCtrl(G_topController topCtrl) {
       this.topCtrl = topCtrl;
    }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////

   @FXML
    void findId(MouseEvent event) {
       try {
         FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/G_findId.fxml"));
         Parent selectedSnack = loader.load();
         U_main.root.setCenter(selectedSnack);
         } catch (IOException e) {
            e.printStackTrace();
         }
    }
    
    @FXML
    void rePw(MouseEvent event) {
       try {
         FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/G_findPw.fxml"));
         Parent rePw = loader.load();
         U_main.root.setCenter(rePw);
         } catch (IOException e) {
            e.printStackTrace();
         }
    }
    
    public G_topController getCon() {
      return con;
   }

   public void setCon(G_topController con) {
      this.con = con;
   }
   
   @FXML
   void login(ActionEvent event) throws RemoteException {
      MemberVO vo = service.loginMember(mem_id.getText());
      if (vo == null) {
         AlertUtil.warnMsg("로그인 오류", "일치하는 정보가 없습니다.");
         return;
      }
      if (!(mem_pw.getText().equals(vo.getMem_pw()))) {
         AlertUtil.warnMsg("로그인 오류", "일치하는 정보가 없습니다.");
      } else {
         AlertUtil.infoMsg("로그인 완료", vo.getMem_name() + "님 환영합니다!");
         LoginVO.setCurrVo(vo);
         if (vo.getMem_lev() == 1) { // 관리자
            try {
               FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/M_top_white.fxml"));
               AnchorPane login1 = loader.load();
               U_main.root.setTop(login1);
               
               FXMLLoader loader3 = new FXMLLoader(
                     G_loginController.class.getResource("../fxml/M_centerWhite.fxml"));
                  
               Parent manager = loader3.load();
               U_main.root.setCenter(manager);
               //U_main.root.setLeft(null);

            } catch (IOException e) {
               e.printStackTrace();
            }
         } else if (vo.getMem_lev() == 3) { // 회원
            try {
               FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/G_center.fxml"));
               Parent main = loader.load();
               G_mediaViewController mainCtrl = loader.getController();
               mainCtrl.setTopCtrl(topCtrl);
               
               U_main.root.setCenter(main);
               topCtrl.backToBlack();
            } catch (IOException e) {
               e.printStackTrace();
            }
         } else { // 직원
            try {
               // 직원모드 탑 변경
               FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/S_top.fxml"));
               Parent staffTop = loader.load();
               S_topController s_topCtrl = loader.getController();
               U_main.root.setTop(staffTop);
               s_topCtrl.setBp(bp);
               
               // 직원모드 센터 변경
               FXMLLoader loader2 = new FXMLLoader(G_loginController.class.getResource("../fxml/S_center.fxml"));
               Parent staffCenter = loader2.load();
               U_main.root.setCenter(staffCenter);

            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   }

    @FXML
    void initialize() {
       Registry reg = null;
      try {
//         reg = LocateRegistry.getRegistry("192.168.31.32",9988);
         reg = LocateRegistry.getRegistry("localhost",9988);
         service = (IMemberService) reg.lookup("memberService");
      } catch (RemoteException e) {
         e.printStackTrace();
      } catch (NotBoundException e) {
         e.printStackTrace();
      }
    }

    
	private U_infoController topCtrl2;

	public U_infoController getTopCtrl2() {
		return topCtrl2;
	}

	public void setTopCtrl2(U_infoController topCtrl2) {
		this.topCtrl2 = topCtrl2;
	}
    
    
  
}