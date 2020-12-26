package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import util.AlertUtil;
import wg.bid.service.IBidService;
import wg.buy_detail.service.IBuy_DetailService;
import wg.buysnack.service.IBuySnackService;
import wg.coupon.service.ICouponService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.pay.service.IPayService;
import wg.vo.BuySnackVO;
import wg.vo.Buy_DetailVO;
import wg.vo.CartSnackSetVO;
import wg.vo.CouponUserViewVO;
import wg.vo.LoginVO;
import wg.vo.PayVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_paySnackBarController {
	
//	private SelectSnackSetVO vo;
//	
//	
//
//    public SelectSnackSetVO getVo() {
//		return vo;
//	}
//
//	public void setVo(SelectSnackSetVO vo) {
//		this.vo = vo;
////		lbl_pay_price.setText(vo.getSet_price()+"");
//		
//	}
	
	private U_cartMainController mainCart;
	
	public U_cartMainController getMainCart() {
		return mainCart;
	}
	public void setMainCart(U_cartMainController mainCart) {
		this.mainCart = mainCart;
	}
	String set_id;
	int buydet_qty;
	private Map<String, String> infoCart;
	public void setInfoCart(Map<String, String> infoCart) {
		this.infoCart = infoCart;
		lbl_pay_price.setText(infoCart.get("pay_price"));
		set_id = infoCart.get("set_id");
		buydet_qty = Integer.parseInt(infoCart.get("set_num"));
		
		
	}
	
	//장바구니 있는 스낵셋vo 가져오기
	private List<CartSnackSetVO> selectedSnack;
	
	public void setSelectedSnack(List<CartSnackSetVO> selectedSnack) {
		this.selectedSnack = selectedSnack;
		
	}
	
	public List<CartSnackSetVO> getSelectedSnack(){
		return selectedSnack;
	}
	
	
	private SnackSetVO vo;
	
	public SnackSetVO getVo() {
		return vo;
	}

	public void setVo(SnackSetVO vo) {
		this.vo = vo;
		lbl_pay_price.setText(vo.getSet_price()+"");
	}


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox aBox;
    
    @FXML
    private Label lbl_pay_met;

    @FXML
    private Label lbl_final_price;

    @FXML
    private RadioButton radioCard;

    @FXML
    private ToggleGroup payMet;

    @FXML
    private RadioButton radioTel;

    @FXML
    private RadioButton radioTransfer;

    @FXML
    private BorderPane outerBox;

    @FXML
    private ComboBox<String> selectCardCombo;
    
    @FXML
    private Button btnCxl;
     
    @FXML
    private Label lbl_cou_sale;

    @FXML
    private Label lbl_pay_price;
    
    @FXML
    private Label couSaleText;
    
    @FXML
    private Button pay;
    
    @FXML
    private Button buy;
    
    @FXML
    void btnCxlClick(ActionEvent event) {
    	couponCombo.setValue(null);
    	//couponCombo.setSelectionModel(null);
    	couTime.setText("");
    	lbl_final_price.setText(lbl_pay_price.getText());
    	try {
    		if(radioTel.isSelected()) {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_snackBarPhoneMethod.fxml"));
    			Parent phonePay = loader.load();
    			U_sanckBarPhoneMethodController phoneCtrl = loader.getController();
    			phoneCtrl.setPay_price(infoCart.get("pay_price"));
    			outerBox.getChildren().clear();
    			outerBox.setCenter(phonePay);	
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
    }
    

    @FXML
    void goBuy(ActionEvent event) {

    }

    @FXML
    private ComboBox<CouponUserViewVO> couponCombo;

    @FXML
    private Label couTime;
    
    @FXML
    private Button btnPay;

    @FXML
    void btnPayClick(ActionEvent event) {
       	if(!radioCard.isSelected()&&!radioTel.isSelected()&&!radioTransfer.isSelected()){
    		AlertUtil.errorMsg("결제 실패", "결제 수단을 선택하세요.");
    		return;
    	}else if(method==1&&U_snackBarcardMethodController.card==null) {
			AlertUtil.warnMsg("카드선택", "카드종류를 선택하십시오.");
			return;
		}
       	
       	BuySnackVO svo = new BuySnackVO();
       	PayVO pvo = new PayVO();
       	try {
       		String buy_id = snaService.getMaxBuy_id();
			svo.setBuy_id(buy_id);
			svo.setMem_id(LoginVO.getCurrVo().getMem_id());
			
			int cnt = snaService.insertBuysnack(svo);
			if(cnt > 0) {
				AlertUtil.infoMsg("결제", "결제완료되었습니다.");
				
				int len = lbl_final_price.getText().length();
				pvo.setBuy_id(buy_id);
				pvo.setPay_price(Integer.parseInt(infoCart.get("final_price").substring(0,len-1)));
				pvo.setPaymet_no(Integer.parseInt(infoCart.get("paymet_no")));
				
				System.out.println(buy_id);
				System.out.println(Integer.parseInt(infoCart.get("final_price").substring(0,len-1)));
				System.out.println(Integer.parseInt(infoCart.get("paymet_no")));
				
				int cnt2 = payService.paySnack(pvo);
				System.out.println("===============");
				System.out.println(cnt2);
				System.out.println("===============");
				
				U_buySnackBarController.cartList.clear();
				
				if(couponCombo.getSelectionModel().getSelectedItem()!=null) {
					issueService.updateCou(Integer.parseInt(infoCart.get("issueNo")));					
				}
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_snackSetMain.fxml"));
				Parent center = loader.load();
				U_main.root.setCenter(center);
				
				
			}else {
				AlertUtil.warnMsg("결제실패", "결제실패입니다.");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
       	           
       	
    }
    
    private String selectedCard;
    
    public void setSelectedCard(String selectedCard) {
		this.selectedCard = selectedCard;
	}
    
//    private Map<String, String> infoMap;
//    void setInfoMap(Map<String, String> infoMap) {
//    	this.infoMap = infoMap;
//    }
    
    private int paymet_no;
    private String pay_met;
    
    @FXML
    void radioCardClick(ActionEvent event) throws IOException {
    	method=1;
    	lbl_pay_met.setText("신용카드");
    	infoCart.put("pay_met", "신용카드");
    	
    	setPaymet_no("신용카드");
    	infoCart.put("paymet_no", getPaymet_no()+"");
    	
    	outerBox.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_snackBarcardMethod.fxml"));
    	Parent pay = loader.load();
    	U_snackBarcardMethodController ctrl = loader.getController();
    	outerBox.setCenter(pay);
    }

    @FXML
    void radioTelClick(ActionEvent event) throws IOException {
    	method=2;
    	lbl_pay_met.setText("휴대폰");
    	infoCart.put("pay_met", "휴대폰");
    	
    	setPaymet_no("휴대폰");
    	infoCart.put("paymet_no", getPaymet_no()+"");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_snackBarPhoneMethod.fxml"));
    	Parent phonePay = loader.load();
    	U_sanckBarPhoneMethodController phoneCtrl = loader.getController();
    	
    	phoneCtrl.setPay_price(infoCart.get("final_price"));
    	
    	outerBox.getChildren().clear();
    	outerBox.setCenter(phonePay);
    	
    	
    }

    @FXML
    void transferClick(ActionEvent event) throws IOException {
    	method=3;
    	lbl_pay_met.setText("계좌이체");
    	//infoCart.put("pay_met", "계좌이체");
    	
    	setPaymet_no("계좌이체");
    	//infoCart.put("paymet_no", getPaymet_no()+"");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_transferMethod.fxml"));
    	Parent transferPay = loader.load();
    	
    	outerBox.getChildren().clear();
    	outerBox.setCenter(transferPay);
    	
    }
    private ICouponService couService;
    private IBuySnackService snaService;
    private IIssueCouponService issueService;
    private IBuy_DetailService bdService;
    private ObservableList<CouponUserViewVO> couList;
    private IPayService payService;
    private int method = 0;
    
    @FXML
    void initialize() {
    	Registry reg = null;
//    	lbl_pay_price.setText((infoCart.get("pay_price"))+"원");
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			couService = (ICouponService) reg.lookup("couponService");
			snaService = (IBuySnackService) reg.lookup("buySnackService");
			issueService = (IIssueCouponService) reg.lookup("issueCouponService");
			bdService = (IBuy_DetailService) reg.lookup("buy_detailService");
			payService = (IPayService) reg.lookup("payService");
			
			List<CouponUserViewVO> uList = couService.userCouponList(LoginVO.getCurrVo().getMem_id());
			couList = FXCollections.observableArrayList(uList);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	for (int i = 0; i < couList.size(); i++) {
    		if(couList.get(i).getIssue_use().equals("N")) {
    			couponCombo.getItems().add(couList.get(i));    			
    		}
		}
    	
    	couponCombo.setCellFactory(new Callback<ListView<CouponUserViewVO>, ListCell<CouponUserViewVO>>() {
			
			@Override
			public ListCell<CouponUserViewVO> call(ListView<CouponUserViewVO> param) {
				return new ListCell<CouponUserViewVO>() {
					@Override
					protected void updateItem(CouponUserViewVO item, boolean empty) {
						super.updateItem(item, empty);
						if(item==null||empty) {
							setText(null);
						}else {
							setText(item.getCou_name());
						}
					}
				};
			}
		});
    	
    	couponCombo.setButtonCell(new ListCell<CouponUserViewVO>() {
    		@Override
    		protected void updateItem(CouponUserViewVO item, boolean empty) {
    			super.updateItem(item, empty);
    			if(item==null||empty) {
					setText(null);
				}else {
					setText(item.getCou_name());
				}
    		}
    	});
    	
    	couponCombo.valueProperty().addListener(new ChangeListener<CouponUserViewVO>() {
    		@Override
    		public void changed(ObservableValue<? extends CouponUserViewVO> observable, CouponUserViewVO oldValue,
    				CouponUserViewVO newValue) {
    			CouponUserViewVO userCou = couponCombo.getSelectionModel().getSelectedItem();
    			if(userCou==null) {
    				lbl_cou_sale.setText("0원");
    				couSaleText.setText("0원");
    			}else {
        			lbl_cou_sale.setText(Integer.toString(userCou.getCou_sale())+"원");
        			couSaleText.setText(Integer.toString(userCou.getCou_sale())+"원");
        			
        			//여기서 유효기간 구해야함
        			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        			try {
    					Date exp = format.parse(userCou.getIssue_time());
    					Calendar cal = Calendar.getInstance();
    					cal.setTime(exp);
    					cal.add(Calendar.DATE, Integer.parseInt(userCou.getCou_time()));
    					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    					String to = fm.format(cal.getTime());
    					couTime.setText(to);
    				} catch (ParseException e) {
    					e.printStackTrace();
    				}
    			}
    			
    			int pay_price = 0;
    			int final_price = 0;
    			int len = lbl_pay_price.getText().length();
    			
    			pay_price = Integer.parseInt(lbl_pay_price.getText().substring(0,len-1));
    			final_price = pay_price - Integer.parseInt(lbl_cou_sale.getText().substring(0, lbl_cou_sale.getText().length()-1));
    			if(couponCombo.getSelectionModel().getSelectedItem()!=null) {
    				int issueNo = couponCombo.getSelectionModel().getSelectedItem().getIssue_no();
    				infoCart.put("issueNo", issueNo+"");
    			}
    			
    			if(couponCombo.getSelectionModel().getSelectedItem()!=null) {
    				if(Integer.parseInt(lbl_pay_price.getText().substring(0,len-1)) < userCou.getCou_sale()) {
    					AlertUtil.warnMsg("금액오류", "쿠폰 사용 불가능");
    					couponCombo.setValue(null);
    					couTime.setText("");
    					return;
    				}
    			}
    			
//    			pay_price = Integer.parseInt(lbl_pay_price.getText().substring(0,len-1));
//    			final_price = pay_price - userCou.getCou_sale();	
//    			int issueNo = couponCombo.getSelectionModel().getSelectedItem().getIssue_no();
//    			infoMap.put("issueNo", issueNo+"");
    			
    			lbl_final_price.setText(final_price+"원");
//    			infoMap.put("final_price", final_price+"");
    			
    			//여기서 유효기간 구해야함
//    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    			try {
//					Date exp = format.parse(userCou.getIssue_time());
//					Calendar cal = Calendar.getInstance();
//					cal.setTime(exp);
//					cal.add(Calendar.DATE, Integer.parseInt(userCou.getCou_time()));
//					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String to = fm.format(cal.getTime());
//					couTime.setText(to);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
    			if(radioTel.isSelected()) {
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_snackBarPhoneMethod.fxml"));
    				Parent phonePay;
    				try {
    					phonePay = loader.load();
    					U_sanckBarPhoneMethodController phoneCtrl = loader.getController();
    					phoneCtrl.setPay_price(final_price+"원");
    					outerBox.getChildren().clear();
    					outerBox.setCenter(phonePay);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		}
    		
		});
    	
    	method=1;
    	
    	outerBox.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_cardMethod.fxml"));
    	Parent pay;
		try {
			pay = loader.load();
			outerBox.setCenter(pay);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		payMet.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(javafx.beans.value.ObservableValue<? extends Toggle> observable, 
							Toggle oldValue, Toggle newValue) {
						int pay_price = 0;
		    			int final_price = 0;
		    			int len = lbl_pay_price.getText().length();
		    			
		    			int cou_len = lbl_cou_sale.getText().length();
		    			int cou_sale = Integer.parseInt(lbl_cou_sale.getText().substring(0,cou_len-1));
		    			
		    			pay_price = Integer.parseInt(lbl_pay_price.getText().substring(0,len-1));
		    			final_price = pay_price - cou_sale;
		    			
		    			lbl_final_price.setText(final_price+"원");
		    			
		    			infoCart.put("final_price", final_price+"원");
					}
				}
			);
		
		
    }
    
    public void setPaymet_no(String pay_met) {
    	switch (pay_met) {
    	case "신용카드": paymet_no = 1;
    	break;
    	case "휴대폰": paymet_no = 2;
    	break;
    	case "계좌이체": paymet_no = 3;
    	break;
    	}
    }
    
    public int getPaymet_no() {
    	return paymet_no;
    }
    
    public String getPay_met() {
    	return pay_met;
    }
    
    public void setLbl_pay_price(String lbl_pay_price) {
    	this.lbl_pay_price.setText(lbl_pay_price);
    }
    
    public void setLbl_final_price(String lbl_final_price) {
    	this.lbl_final_price.setText(lbl_final_price);
    }
    
}
