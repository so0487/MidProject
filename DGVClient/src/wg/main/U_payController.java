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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import wg.bid.service.IBidService;
import wg.coupon.service.ICouponService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.pay.service.IPayService;
import wg.vo.CouponUserViewVO;
import wg.vo.LoginVO;

public class U_payController {
	
	private U_bookMovieMainController mainCtrl;
	
	public U_bookMovieMainController getMainCtrl() {
		return mainCtrl;
	}

	public void setMainCtrl(U_bookMovieMainController mainCtrl) {
		this.mainCtrl = mainCtrl;
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
    private Label lbl_cou_sale;

    @FXML
    private Label lbl_pay_price;
    
    @FXML
    private Label couSaleText;
    
    @FXML
    private Button pay;
   

    @FXML
    private ComboBox<CouponUserViewVO> couponCombo;

    @FXML
    private Label couTime;
    
    private String selectedCard;
    
    public void setSelectedCard(String selectedCard) {
		this.selectedCard = selectedCard;
	}
    
    private Map<String, String> infoMap;
    void setInfoMap(Map<String, String> infoMap) {
    	this.infoMap = infoMap;
    }
    
    private int paymet_no;
    private String pay_met;
    
    @FXML
    private Button btnCxl;

    @FXML
    void btnCxlClick(ActionEvent event) {
    	couponCombo.setValue(null);
    	//couponCombo.setSelectionModel(null);
    	couTime.setText("");
		return;
    }
    
    @FXML
    void radioCardClick(ActionEvent event) throws IOException {
    	method=1;
    	lbl_pay_met.setText("신용카드");
    	infoMap.put("pay_met", "신용카드");
    	
    	setPaymet_no("신용카드");
    	infoMap.put("paymet_no", getPaymet_no()+"");
    	mainCtrl.setInfoMap(infoMap);
    	
    	outerBox.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_cardMethod.fxml"));
    	Parent pay = loader.load();
    	U_cardMethodController ctrl = loader.getController();
    	ctrl.setMainCtrl(this);
    	mainCtrl.setGrandChild(ctrl);
    	
    	outerBox.setCenter(pay);
    }

    @FXML
    void radioTelClick(ActionEvent event) throws IOException {
    	method=2;
    	lbl_pay_met.setText("휴대폰");
    	infoMap.put("pay_met", "휴대폰");
    	
    	setPaymet_no("휴대폰");
    	infoMap.put("paymet_no", getPaymet_no()+"");
    	mainCtrl.setInfoMap(infoMap);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_phoneMethod.fxml"));
    	Parent phonePay = loader.load();
    	U_phoneMethodController phoneCtrl = loader.getController();
    	
    	phoneCtrl.setPay_price(infoMap.get("final_price")+"원");
    	
    	outerBox.getChildren().clear();
    	outerBox.setCenter(phonePay);
    	
    	
    }

    @FXML
    void transferClick(ActionEvent event) throws IOException {
    	method=3;
    	lbl_pay_met.setText("계좌이체");
    	infoMap.put("pay_met", "계좌이체");
    	
    	setPaymet_no("계좌이체");
    	infoMap.put("paymet_no", getPaymet_no()+"");
    	mainCtrl.setInfoMap(infoMap);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_transferMethod.fxml"));
    	Parent transferPay = loader.load();
    	
    	outerBox.getChildren().clear();
    	outerBox.setCenter(transferPay);
    	
    }
    private ICouponService couService;
    private ObservableList<CouponUserViewVO> couList;
    private IPayService payService;
    private int method = 0;
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			couService = (ICouponService) reg.lookup("couponService");
			
			List<CouponUserViewVO> uList = couService.userCouponList(LoginVO.getCurrVo().getMem_id());
			couList = FXCollections.observableArrayList(uList);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	// 사용하지 않은 쿠폰만 출력
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
    	
    	// 쿠폰 선택
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
    				infoMap.put("issueNo", issueNo+"");
    			}
    			
    			lbl_final_price.setText(final_price+"원");
    			infoMap.put("final_price", final_price+"");
    			mainCtrl.setInfoMap(infoMap);
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
		    			infoMap.put("final_price", final_price+"");
		    			mainCtrl.setInfoMap(infoMap);
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
