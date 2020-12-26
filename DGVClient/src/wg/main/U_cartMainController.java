package wg.main;

import java.io.IOException;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.quartz.impl.jdbcjobstore.HSQLDBDelegate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.AlertUtil;
import wg.snackSet.service.ISnackSetService;
import wg.vo.CartSnackSetVO;
import wg.vo.LoginVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_cartMainController {
	
    private Map<String, String> infoCart;
    
    public void setInfoCart(Map<String, String> infoCart) {
    	this.infoCart = infoCart;
    }
    
    private List<CartSnackSetVO> selectedSnack;
    
	public void setSelectedSnack(List<CartSnackSetVO> selectedSnack) {
		this.selectedSnack = selectedSnack;
	}
	
	public List<CartSnackSetVO> getSelectedSnack(){
		return selectedSnack;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label cart_price;
    
    @FXML
    private BorderPane outerBox;
    
    @FXML
    private Button buy;

    @FXML
    void goBuy(ActionEvent event) {
    	if(U_buySnackBarController.cartList.size()==0) {
    		AlertUtil.warnMsg("작업 오류", "장바구니에 담긴 상품이 없습니다.");
    		return;
    	}
    	try {
    		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_paySnackBar.fxml"));
			AnchorPane center = loader.load();	
			U_paySnackBarController psc = loader.getController();
			//List<SnackSetVO> list = service.getAllSnackSetList();
			psc.setMainCart(this);
			System.out.println("-------------");
			System.out.println(infoCart.get("pay_price"));
			System.out.println("-------------");
			psc.setInfoCart(infoCart);
			
//			up.setLbl_pay_price(infoCart.get("pay_price")+"원");
			U_main.root.setCenter(center);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
    
    @FXML
    private VBox vbox;
    ISnackSetService service;
    @FXML
    void initialize() {
    	Registry reg = null;
    	infoCart = new HashMap<String, String>();
    	int cartPrice = 0;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ISnackSetService) reg.lookup("snackService");
			
	    	List<CartSnackSetVO> list =U_buySnackBarController.cartList;
	    	
	    	String mem_id ="";
	    	
	    	if(list.size() != 0) {
	    		mem_id = list.get(0).getMem_id();
	    	}
	    	
	    	if(mem_id.equals(LoginVO.getCurrVo().getMem_id())) {
	    		for(CartSnackSetVO snackVo : list) {
	    			FXMLLoader loader1 = new FXMLLoader(U_cartMainController.class.getResource("../fxml/U_cartSnackSet.fxml"));
	    			Parent center = loader1.load();
	    			final CartSnackSetVO tt = snackVo;
	    			U_cartOneController setOneController = loader1.getController();
	    			setOneController.setCartSnackSetVO(tt);
	    			vbox.getChildren().add(center);
	    			System.out.println(snackVo.getSet_id());
	    			infoCart.put("set_id",snackVo.getSet_id());
	    			infoCart.put("set_num", String.valueOf(snackVo.getNum()));
	    		}
	    		for (int i = 0; i < U_buySnackBarController.cartList.size(); i++) {
	    			cartPrice += U_buySnackBarController.cartList.get(i).getSet_price();
	    		}
	    		
	    	}else {
	    		list.clear();
	    	}
	    	
//	    	for (int j = 0; j < list.size(); j++) {
	    		
	    		cart_price.setText(cartPrice+"원");
	        	infoCart.put("pay_price",cartPrice+"원");
//	    	}
	    	
	    	
	    		
	    	
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
    
//    void payInfo() {
//    	infoCart.put("pay_price",cartP+"");
//    }
}
