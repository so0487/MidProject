package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import wg.bookmovie.service.IBookMovieService;
import wg.coupon.service.ICouponService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;
import wg.vo.CouponUserViewVO;
import wg.vo.LoginVO;
import wg.vo.MemberVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_couponMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane outerBox;

    @FXML
    private VBox vbox;
    
    ICouponService service;

    @FXML
    void initialize() {
       Registry reg = null;
       try {
         reg = LocateRegistry.getRegistry("localhost",9988);
         service = (ICouponService) reg.lookup("couponService");
         
         MemberVO vo = LoginVO.getCurrVo(); //로그인 정보를 불러와야한다.
         
         	List<CouponUserViewVO> list =service.userCouponList(vo.getMem_id());
         	
         	int i = 0;
	    	VBox vb = null;
	    	
	    	for(CouponUserViewVO bmvVo : list) {
	    		if(i%3 == 0 ) {
	    			vb = new VBox();
	    		}
	    		System.out.println(vb.getId());
	    		
    			FXMLLoader loader1 = new FXMLLoader(U_couponMainController.class.getResource("../fxml/U_couponOne.fxml"));
    			Parent center = loader1.load();
    			U_couponOneController myOneController = loader1.getController();
    			myOneController.setCouponUserViewVo(bmvVo);
    			final CouponUserViewVO bmv = bmvVo;
    			
    			vb.getChildren().add(center);
					vbox.getChildren().add(vb);
	    	}
          
       } catch (RemoteException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (NotBoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
    }
}