package wg.main;

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

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.AlertUtil;
import wg.movie.service.IMovieService;
import wg.seatSch.service.ISeatSchService;
import wg.theater.service.ITheaterService;
import wg.vo.LoginVO;
import wg.vo.SeatSchVO;
import wg.vo.TheaterVO;

public class U_bookMovie_seatChoiceMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox cBox;

    @FXML
    private HBox aBox;
    
    @FXML
    private Label lblTime;
    
    @FXML
    private Label lblMovie;
    
    @FXML
    private VBox rowBox;

    @FXML
    private VBox seatBox;
    
    @FXML
    private Button btnReset;
    
    @FXML
    private Label disPrice;

    @FXML
    private Label totalPrice;
    
    private U_bookMovieMainController mainCtrl;
    
    public U_bookMovieMainController getMainCtrl() {
		return mainCtrl;
	}

	public void setMainCtrl(U_bookMovieMainController mainCtrl) {
		this.mainCtrl = mainCtrl;
	}
    
	private ITheaterService thService;
    private ISeatSchService ssService;
    private IMovieService mService;
    
    private List<SeatSchVO> ssList;
    
    private List<CheckBox> open = new ArrayList<CheckBox>();
    private List<CheckBox> booked = new ArrayList<CheckBox>();
    
    private List<CheckBox> mSeat = new ArrayList<CheckBox>();
    private List<CheckBox> wSeat = new ArrayList<CheckBox>();
    
    private int aNum;
    private int cNum;
    private int ticketNum;
    private int selectNum;
    private List<Integer> selectedPrice;
    
    private List<String> selectedSeats;
    private List<Integer> selectedSseats;
    
    private int show_no;
    
    private int pay_price;
    
    private Map<String, String> infoMap;
    
    private Map<String, String> paramMap = new HashMap<String, String>();
    
    public void setInfoMap(Map<String, String> infoMap) {
    	this.infoMap = infoMap;
    }
    
    public String setShow_no(int show_no) {
    	this.show_no = show_no;
    	paramMap.put("show_no", show_no+"");
    	infoMap.put("show_no", show_no+"");
    	try {
			ssList = ssService.seatSchforAShow(show_no);
			String theater_id = ssList.get(0).getTheater_id();
			TheaterVO tvo = thService.getTheater(theater_id);
			String theater_name = tvo.getTheater_name();
			infoMap.put("theater_name", theater_name);
			setSeatBox();
			setInfo(infoMap);
			
			// 청소년 관람불가 영화이면
			String movie_name = infoMap.get("selectedMovie");
			try {
				String movie_adult = mService.movieAdultCheck(movie_name);
				if(movie_adult.equals("Y")){
					List<Node> cBoxList = cBox.getChildren(); 
					for (int i = 0; i < cBoxList.size(); i++) {
						cBoxList.get(i).setDisable(true);
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return infoMap.get("theater_name");
    }
    
    public int getShow_no() {
    	return show_no;
    }

    @FXML
    void btnResetClick(ActionEvent event) {
    	selectNum = 0;
    	mainCtrl.setCheck(0);
    	setAble();
    	btnReset.setDisable(true);
    	totalPrice.setText("0원");
    }
    
    private String dis_id;

    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			ssService = (ISeatSchService) reg.lookup("seatSchService");
			thService = (ITheaterService) reg.lookup("theaterService");	
			mService = (IMovieService) reg.lookup("movieService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	// 인원수 선택 버튼 세팅
    	for (int i = 0; i < 6; i++) {
			Button aBtn = new Button();
			aBtn.setPrefSize(20, 20);
			aBtn.setText(i+"");
			aBtn.setStyle("-fx-background-color: none; -fx-border-color: #827e7e;");
			aBox.getChildren().add(aBtn);
			aBox.setMargin(aBtn, new Insets(5));
			aBtn.setOnAction(e->{
				aNum = Integer.parseInt(aBtn.getText());
				ticketNum = aNum+cNum;
				if (ticketNum==0) {
					mainCtrl.setCheck(0);
					btnReset.setDisable(true);
					totalPrice.setText("0원");
					setDisable();
				}else {
					setAble();
					if(ticketNum>5) {
						AlertUtil.warnMsg("선택오류", "티켓은 한 번에 5매까지 예매가능합니다.");
						return;
					}
				}
				ObservableList<Node> aBoxList = aBox.getChildren();
				colorNone(aBoxList);
				colorGrey(aBtn);
			});
			
			Button cBtn = new Button();
			cBtn.setPrefSize(20, 20);
			cBtn.setText(i+"");
			cBtn.setStyle("-fx-background-color: none; -fx-border-color: #827e7e;");
			cBox.getChildren().add(cBtn);
			cBox.setMargin(cBtn, new Insets(5));
			cBtn.setOnAction(e->{
				cNum = Integer.parseInt(cBtn.getText());
				ticketNum = aNum+cNum;
				if (ticketNum==0) {
					mainCtrl.setCheck(0);
					btnReset.setDisable(true);
					totalPrice.setText("0원");
					setDisable();
				}else {
					setAble();
					// 할인금액 계산해서 띄우기
					if(ticketNum>5) {
						AlertUtil.warnMsg("선택오류", "티켓은 한 번에 5매까지 예매가능합니다.");
						return;
					}
					if(dis_id.equals("D0001")) {
						disPrice.setText((cNum*3000)+"원");
					}else {
						disPrice.setText("중복할인불가");
					}
				}
				ObservableList<Node> cBoxList = cBox.getChildren();
				colorNone(cBoxList);
				colorGrey(cBtn);
			});
		}
    	
    	
    	
    }
    
    public void colorNone(List<Node> btnList) {
    	for (int i = 0; i < btnList.size(); i++) {
			btnList.get(i).setStyle("-fx-background-color: none; -fx-border-color: #827e7e;");
		}
    }
    
    public void colorGrey(Node btn) {
    	btn.setStyle("-fx-background-color:#827e7e; -fx-border-color:#827e7e; -fx-text-fill:white");
    }
    
    public void qtyDisable(ObservableList<Node> qtyList) {
    	for (int i = 0; i < qtyList.size(); i++) {
			qtyList.get(i).setDisable(true);
		}
    }
    
    public void setDisable() {
    	for (int j = 0; j < open.size(); j++) {
    		open.get(j).setDisable(true);
		}
    }
    
    public void setAble() {
    	for (int j = 0; j < open.size(); j++) {
    		open.get(j).setSelected(false);
    		open.get(j).setDisable(false);
		}
    }
    
    public void setSeatBox() {
    	// SeatschVO에 예매 상태에 따라서 disable설정도 해주어야함
    	// 선택 명수에 따라서 좌석 상태를 disable설정
    	
    	// 상영좌석수
    	int seatNum = ssList.size();
    	
    	// for문의 실질적인 반복 횟수를 의미
    	int j = 0; 
    	
    	// row를 나타내기 위한 char형 변수
    	char c = 'A'; 
    	
    	VBox vbox = null;
    	HBox hbox = null; 
    	for (int i = 0; i < 11; i++) {
    		// j가 seatNum과 같아지면 for문 수행을 중단한다.
    		if(j==seatNum)	break;
    		
    		dis_id = infoMap.get("dis_id");
    		if(dis_id.equals("D0002")
					||dis_id.equals("D0003")
					||dis_id.equals("D0004")) { // 조조나 심야 또는 문화데이인경우
    			if(i==0) {
    				vbox = new VBox();
    				vbox.setId("5000");
    				vbox.setStyle("-fx-border-color:pink; -fx-border-width:3;");
    				
    				// 생성한 vbox를 seatBox에 추가 
    				seatBox.getChildren().add(vbox);
    			}
    			
    			if(i%10==0) { // i가 1 또는 10의 배수 일 때마다
        			// 새로운 hbox를 생성한다.
        			hbox = new HBox();
        			
        			// 좌석가격대를 나타내기 위하여 hbox를 vbox에 담는다.
    				vbox.getChildren().add(hbox);
    				
    				// 후에 선택한 좌석의 ID를 가져오기 위해 hbox에 id를 세팅한다.
    				hbox.setId(c+"");
    				
    				// rowBox에 알파벳라벨을 세팅(A~Z열의 수만큼)
    				Label row = new Label();
    				rowBox.setMargin(row, new Insets(0, 0, 12, 0));
    		    	row.setText((c++)+"");
    		    	rowBox.getChildren().add(row);
    		    	
    		    	// i는 좌석번호를 나타내기 위해 다시 0으로 만든다.
    				i = 0;
    			}
			}else {
				// 가격대를 나타내기 위한 스타일 설정
				if(j==0||j==20||j==50) {
					vbox = new VBox();
					seatBox.setMargin(vbox, new Insets(2,0,2,0));
					vbox.setPadding(new Insets(5, 0, 10, 0));
					
					if(j==0) {
						vbox.setId("8000");
						vbox.setStyle("-fx-border-color:orange; -fx-border-width:3;");
					}else if(j==20) {
						vbox.setId("9000");
						vbox.setStyle("-fx-border-color:green; -fx-border-width:3;");
					}else if(j==50) {
						vbox.setId("10000");
						vbox.setStyle("-fx-border-color:red; -fx-border-width:3;");
					}
					// 생성한 vbox를 seatBox에 추가 
					seatBox.getChildren().add(vbox);
				}
				
				if(i%10==0) { // i가 1 또는 10의 배수 일 때마다
	    			// 새로운 hbox를 생성한다.
	    			hbox = new HBox();
	    			
	    			// 좌석가격대를 나타내기 위하여 hbox를 vbox에 담는다.
					vbox.getChildren().add(hbox);
					
					// 후에 선택한 좌석의 ID를 가져오기 위해 hbox에 id를 세팅한다.
					hbox.setId(c+"");
					
					// rowBox에 알파벳라벨을 세팅(A~Z열의 수만큼)
					Label row = new Label();
					if(c=='C'||c=='F') {
						rowBox.setMargin(row, new Insets(25, 0, 5, 0));
					}else {
						rowBox.setMargin(row, new Insets(5, 0, 5, 0));
					}
			    	row.setText((c++)+"");
			    	rowBox.getChildren().add(row);
			    	
			    	// i는 좌석번호를 나타내기 위해 다시 0으로 만든다.
					i = 0;
				}
			}
    		
    		
    		// 한 좌석을 생성 후
    		CheckBox cbSeat = new CheckBox();
    		cbSeat.setPrefSize(20, 10);
    		cbSeat.setPadding(new Insets(4,7,4,7));
    		cbSeat.setDisable(true);
    		
    		// 점유여부를 확인
    		// 이미 예약된 좌석일 경우
    		if(ssList.get(j).getSseat_status().equals("Y")) {
    			// 체크되어 있는 상태로 출력
    			cbSeat.setSelected(true);
    			// 예매된 좌석은 따로 담아둔다.
    			booked.add(cbSeat);
    		}else {
    			// 특별관인지 확인
        		if(infoMap.get("theater_name").equals("5관(매칭관)")) {
        			// 수량 선택부분 성인 1명만 가능
        			ObservableList<Node> aBoxList = aBox.getChildren();
        			qtyDisable(aBoxList);
        			colorNone(aBoxList);
    				colorGrey(aBoxList.get(1));
    				aBoxList.get(1).setDisable(false);
    				
    				ObservableList<Node> cBoxList = cBox.getChildren();
    				qtyDisable(cBoxList);
    				colorNone(cBoxList);
        			
        			// 성별확인
        			if(LoginVO.getCurrVo().getMem_gen().equals("남자")) { // 남자이면
        				if(j%2!=0) { // 홀수좌석만 open
        					cbSeat.setDisable(false);
        					open.add(cbSeat);
        				}
        			}else { // 여자이면
        				if(j%2==0) { //짝수좌석만 선택
        					cbSeat.setDisable(false);
         					open.add(cbSeat);
        				}
        			}
        		}else { // 특별관아님
        			open.add(cbSeat);
        		}
    		}
    		
    		
    		// 좌석을 선택할 때마다 선택한 좌석수와 구매할 좌석수가 일치하는 지 체크 후
    		// 동일해 질 시 체크박스상태를 disable로 바꾼다.
    		cbSeat.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
						public void changed(javafx.beans.value.ObservableValue<? extends Boolean> observable, 
								Boolean oldValue, Boolean newValue) {
									int cnt = 0;
									if(infoMap.get("theater_name").equals("5관(매칭관)")) {
										ticketNum = 1;
									}else {
										ticketNum = aNum+cNum;
									}
									if(ticketNum==0) {
										AlertUtil.warnMsg("선택오류", "구매할 티켓수를 먼저 선택해주십시오.");
										return;
									}
									
									selectedPrice = new ArrayList<Integer>();
									selectedSeats = new ArrayList<String>();
									selectedSseats = new ArrayList<Integer>();
									for (int k = 0; k < open.size(); k++) {
										CheckBox chbx = open.get(k); 
										if(chbx.isSelected()) {
											// 가격계산
											selectedPrice.add(Integer.parseInt((chbx.getParent()).getParent().getId()));
											pay_price = 0;
											for (int l = 0; l < selectedPrice.size(); l++) {
												pay_price += selectedPrice.get(l);
											}
											if(dis_id.equals("D0001")) {
												pay_price -= (cNum*3000);
											}
											
											infoMap.put("pay_price", pay_price+"");
											
											totalPrice.setText(pay_price+"원");
											
											String seat = chbx.getParent().getId()+"";
											seat += chbx.getText();
											// 선택한 좌석을 리스트에 담는다.(selectedSeats)
											selectedSeats.add(seat);
											
											try {
												paramMap.put("seat_id", seat);
												int sseat_no = ssService.getSseat_no(paramMap);
												// 그에 해당하는 상영좌석을 리스트에 담는다. (selectedSseats)
												selectedSseats.add(sseat_no);
											} catch (RemoteException e) {
												e.printStackTrace();
											}
											cnt++;
										}
									}
									selectNum = cnt;
									
									// 결제선택창으로 보내기 위해 티켓의 장 수를 qty에 담는다.
									infoMap.put("book_qty", selectNum+"");
									
									// 좌석을 다 선택하면
									if(ticketNum==selectNum) {
										for (int k = 0; k < open.size(); k++) {
											btnReset.setDisable(false);
											open.get(k).setDisable(true);
										}
										mainCtrl.setCheck(1);
										mainCtrl.setInfoMap(infoMap);
										mainCtrl.setSelectedSseats(selectedSseats);
										mainCtrl.setSelectedSeats(selectedSeats);
									}
						};
				}
			);
    		
    		// i를 이용한 좌석번호 세팅
    		// 두 자리수로 통일시켜 콤보박스를 생성한다.
    		if((i+1)/10==0) { 
    			cbSeat.setText("0"+(i+1));
    		}else {
    			cbSeat.setText((i+1)+"");
    		}
    		
    		// checkBox가 선택될 때마다 선택된 좌석 수를 확인하고,
    		// 선택한 좌석 수만큼 선택했을 경우 모든 checkBox를 disable로 변경
			hbox.getChildren().add(cbSeat);
			
    		if(dis_id.equals("D0002")
					||dis_id.equals("D0003")
					||dis_id.equals("D0004")) { // 조조나 심야 또는 문화데이인경우
    			hbox.setMargin(cbSeat, new Insets(0,0,4,0));
    		}
			
			j++;
		}
    }
    
    public void setInfo(Map<String, String> infoMap) {
    	String movie = infoMap.get("selectedMovie")+"("+infoMap.get("theater_name")+")";
    	String date = infoMap.get("selectedDate");
    	String day = infoMap.get("selectedDay");
    	String time = infoMap.get("selectedTime");
    	lblMovie.setText(movie);
    	lblTime.setText(date+"("+day+")"+" "+time+"~");
    }
    

    
    
    

}
	