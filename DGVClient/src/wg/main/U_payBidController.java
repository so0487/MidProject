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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import util.AlertUtil;
import wg.bid.service.IBidService;
import wg.coupon.service.ICouponService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.pay.service.IPayService;
import wg.vo.BidVO;
import wg.vo.CouponUserViewVO;
import wg.vo.LoginVO;
import wg.vo.PayVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_payBidController {

	@FXML
    private ResourceBundle resources;
	
	BidVO vo;
	

    public BidVO getVo() {
		return vo;
	}

	public void setVo(BidVO vo) {
		this.vo = vo;
		
	}
	public static BidVO bidvo;


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
    
    
    private Stage mainStage;
    

    
    @FXML
    void goPay(ActionEvent event) throws IOException {
    	if(!radioCard.isSelected()&&!radioTel.isSelected()&&!radioTransfer.isSelected()){
    		AlertUtil.errorMsg("결제 실패", "결제 수단을 선택하세요.");
    	}else if(method==1&&U_bidCardMethodController.card==null) {
			AlertUtil.warnMsg("카드선택", "카드종류를 선택하십시오.");
			return;
		}else {
			BidVO bidvo2 = new BidVO();
			bidvo2.setBid_id(bidService.getMax());
			bidvo2.setAuc_no(bidvo.getAuc_no());
			bidvo2.setMem_id(LoginVO.getCurrVo().getMem_id());
			bidvo2.setBid_time(bidvo.getBid_time());
			bidvo2.setBid_price(bidvo.getBid_price());
			int cnt = bidService.insertBid(bidvo2);
			
			
			PayVO vo = new PayVO();
			vo.setBid_id(bidvo2.getBid_id());
			vo.setPay_price(bidvo.getBid_price());
			vo.setPaymet_no(method);
			System.out.println(bidvo2.getBid_id());
			System.out.println(bidvo.getBid_price());
			System.out.println(method);
			int cnt2 = payService.insertPayBid(vo);
			
			if(cnt>0 && cnt2>0) {
				AlertUtil.infoMsg("작업결과","성공");
				
				
			}else {
				AlertUtil.infoMsg("작업결과","실패");
				return;
			}
			
			
//			mainStage = (Stage) pay.getScene().getWindow();
//			
//			// 2. 부모자식창 관계설정
//			Stage secStage = new Stage(StageStyle.UTILITY);
//			secStage.initModality(Modality.WINDOW_MODAL);
//			secStage.initOwner(mainStage);
//			
//			// 3. 자식창 생성
//			FXMLLoader loader = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_payConfirm.fxml"));
//			Parent childRoot = loader.load();
//			
//			// 4. 부모컨트롤러, infoMap 넘겨주기
//			U_payConfirmController ctrl3 = loader.getController();
//			
		}
    	

    }
   

    @FXML
    private ComboBox<CouponUserViewVO> couponCombo;

    @FXML
    private Label couTime;
    
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
//    	infoMap.put("pay_met", "신용카드");
    	
    	setPaymet_no("신용카드");
//    	infoMap.put("paymet_no", getPaymet_no()+"");
    	
    	outerBox.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_bidCardMethod.fxml"));
    	Parent pay = loader.load();
    	U_bidCardMethodController ctrl = loader.getController();
    	outerBox.setCenter(pay);
    }

    @FXML
    void radioTelClick(ActionEvent event) throws IOException {
    	method=2;
    	lbl_pay_met.setText("휴대폰");
//    	infoMap.put("pay_met", "휴대폰");
    	
    	setPaymet_no("휴대폰");
//    	infoMap.put("paymet_no", getPaymet_no()+"");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_bidPhoneMethod.fxml"));
    	Parent phonePay = loader.load();
    	U_bidPhoneMethodController phoneCtrl = loader.getController();
    	phoneCtrl.setPay_price(lbl_final_price.getText());
    	
    	outerBox.getChildren().clear();
    	outerBox.setCenter(phonePay);
    	
    	
    }

    @FXML
    void transferClick(ActionEvent event) throws IOException {
    	method=3;
    	lbl_pay_met.setText("계좌이체");
//    	infoMap.put("pay_met", "계좌이체");
    	
    	setPaymet_no("계좌이체");
//    	infoMap.put("paymet_no", getPaymet_no()+"");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_transferMethod.fxml"));
    	Parent transferPay = loader.load();
    	
    	outerBox.getChildren().clear();
    	outerBox.setCenter(transferPay);
    	
    }
    private IBidService bidService;
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
			bidService = (IBidService) reg.lookup("bidService");
			List<CouponUserViewVO> uList = couService.userCouponList(LoginVO.getCurrVo().getMem_id());
			couList = FXCollections.observableArrayList(uList);
			payService = (IPayService) reg.lookup("payService");
			
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
    			lbl_cou_sale.setText(Integer.toString(userCou.getCou_sale())+"원");
    			couSaleText.setText(Integer.toString(userCou.getCou_sale())+"원");
    			
    			int pay_price = 0;
    			int final_price = 0;
    			int len = lbl_pay_price.getText().length();
    			
    			pay_price = Integer.parseInt(lbl_pay_price.getText().substring(0,len-1));
    			final_price = pay_price - userCou.getCou_sale();
    			int issueNo = couponCombo.getSelectionModel().getSelectedItem().getIssue_no();
//    			infoMap.put("issueNo", issueNo+"");
    			
    			lbl_final_price.setText(final_price+"원");
//    			infoMap.put("final_price", final_price+"");
    			
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
//		    			infoMap.put("final_price", final_price+"");
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
