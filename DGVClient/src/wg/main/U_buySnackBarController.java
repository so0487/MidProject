package wg.main;

import java.io.File;

import java.io.IOException;
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.snackSet.service.ISnackSetService;
import wg.vo.CartSnackSetVO;
import wg.vo.LoginVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_buySnackBarController {
   
   public static List<CartSnackSetVO> cartList = new ArrayList();
   

    public static List<CartSnackSetVO> getCartList() {
      return cartList;
   }

   public static void setCartList(List<CartSnackSetVO> cartList) {
      U_buySnackBarController.cartList = cartList;
   }
   
   //맵 리스트 담아서 옮기기
    private List<Map<String, String>> payList;
    private Map<String, String> sanckInfo=new HashMap<String, String>();
 
    U_cardMethodController grandChildCtrl;
    public void setGrandChild(U_cardMethodController grandChildCtrl) {
       this.grandChildCtrl = grandChildCtrl;
    }

   @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane scroll;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Button desc;

   @FXML
    private Button asc;

    @FXML
    private Label sum;

    @FXML
    private Button cart;
    
    @FXML
    private Button addCart;
    
    @FXML
    void addCart(ActionEvent event) {
       if(LoginVO.getCurrVo()==null) {
          AlertUtil.warnMsg("비 로그인 상태", "로그인 해주세요.");
          return;
       }
       CartSnackSetVO cvo = new CartSnackSetVO();
       cvo.setMem_id(LoginVO.getCurrVo().getMem_id());
       cvo.setSet_id(vo.getSet_id());
       cvo.setSet_name(vo.getSet_name());
       cvo.setNum(Integer.parseInt(num.getText()));
       cvo.setSet_photo(vo.getSet_photo());
       cvo.setSet_price(Integer.parseInt(sum.getText().substring(0, sum.getText().length()-1)));
//       for (int i = 0; i < cartList.size(); i++) {
//         if(cvo.getSet_id().equals(cartList.get(i).getSet_id())) {
//            cartList.get(i).setNum(cartList.get(i).getNum()+cvo.getNum());
//            cartList.get(i).setSet_price(cartList.get(i).getSet_price()+cvo.getSet_price());
//         }else {
//            cartList.add(cvo);
//         }
//      }
      cartList.add(cvo);
       AlertUtil.infoMsg("작업결과", "장바구니에 추가하였습니다.");

    }
    
    @FXML
    private ImageView img;
    
    @FXML
    private Button buy;
    
    @FXML
    private Label id;
    
    @FXML
    private Label num;
    ISnackSetService service;
    
    @FXML
    void asc(ActionEvent event) {
       int n =Integer.parseInt(num.getText());
       n++;
       num.setText(Integer.toString(n));
       sum.setText((Integer.parseInt(num.getText())*(vo.getSet_price()))+"원");
    }

    @FXML
    void desc(ActionEvent event) {
       int n =Integer.parseInt(num.getText());
       if(n==1) {
          AlertUtil.infoMsg("안내", "최소 1개 이상 구매해야 합니다.");
       }else {
          n--;
       }
       num.setText(Integer.toString(n));
       sum.setText((Integer.parseInt(num.getText())*(vo.getSet_price()))+"원");
    }
    
    @FXML
    void goBuy(ActionEvent event) {
       if(LoginVO.getCurrVo()==null) {
          AlertUtil.warnMsg("비 로그인 상태", "로그인 해주세요.");
          try {
             FXMLLoader loader = new FXMLLoader(U_buySnackBarController.class.getResource("../fxml/G_login.fxml"));
             StackPane center = loader.load();
             U_main.root.setCenter(center);
             
             } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
          
       }else {
          try {
             FXMLLoader loader = new FXMLLoader(U_buySnackBarController.class.getResource("../fxml/U_paySnackBar.fxml"));
             U_paySnackBarController pay = loader.getController();
//             SnackSetVO tt =null;
             List<SnackSetVO> list = service.getAllSnackSetList();
             
//             AlertUtil.infoMsg(vo.getSet_name(),"gg");
//             for (int i = 0; i <list.size(); i++) {
//               if(vo.getSet_id().equals(list.get(i).getSet_id())) {
////                  tt = list.get(i);
////                  up.setVo(list.get(i));
//                  AlertUtil.infoMsg("ss", list.get(i).getSet_name());
////                  if(list.get(i)==null) {
////                     AlertUtil.infoMsg("aa","허허");
////                  }else {
//                     up.setVo(list.get(i));
////                  }
//                  
//               }
//            }
             
             
             AnchorPane center = loader.load();
             U_main.root.setCenter(center);
             
             } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
       }
    }

    @FXML
    void goCart(ActionEvent event) {
       if(LoginVO.getCurrVo()==null) {
          AlertUtil.warnMsg("비 로그인 상태", "로그인 해주세요.");
          return;
       }
       try {
         FXMLLoader loader = new FXMLLoader(U_buySnackBarController.class.getResource("../fxml/U_cartSnackSetMain.fxml"));
         U_paySnackBarController up = loader.getController();
         StackPane center = loader.load();
         U_main.root.setCenter(center);
         
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
       
    }

    private SnackSetVO vo;
    
    public SnackSetVO getVo() {
      return vo;
   }


   public void setVo(SnackSetVO vo) {
      this.vo = vo;
      name.setText(vo.getSet_name());
      File file = new File(vo.getSet_photo());
      Image image = new Image(file.toURI().toString());
      img.setImage(image );
      id.setText(vo.getSet_id());
       price.setText(Integer.toString(vo.getSet_price())+"원");
       sum.setText((Integer.parseInt(num.getText())*(vo.getSet_price()))+"원");
       
   }


   @FXML
    void initialize() {
      num.setText("1");
      Registry reg = null;
      try {
         reg = LocateRegistry.getRegistry("localhost",9988);
         service = (ISnackSetService) reg.lookup("snackService");
         
      } catch (RemoteException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (NotBoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      
      
    }
}