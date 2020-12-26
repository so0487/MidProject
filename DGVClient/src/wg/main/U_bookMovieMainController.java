package wg.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.AlertUtil;
import wg.mschedule.service.IMScheduleService;
import wg.seatSch.service.ISeatSchService;
import wg.smovie.service.ISMovieService;
import wg.theater.service.ITheaterService;
import wg.vo.LoginVO;
import wg.vo.ShowInfoVO;
import wg.vo.SmovieShortInfoVO;

public class U_bookMovieMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane outerBox;
    
    @FXML
    private VBox movieBox;

    @FXML
    private Label lblYear;

    @FXML
    private Label lblMonth;

    @FXML
    private VBox dateBox;

    @FXML
    private VBox scheduleBox;
    
    @FXML
    private Button btnNext;
    
    @FXML
    private Label lblMovieCheck;

    @FXML
    private Label lblTimeCheck;

    @FXML
    private Label lblSeatCheck;
    
    @FXML
    private Label lblPayCheck;
    
    @FXML
    private AnchorPane bottom;
    
    private ISMovieService smService;
    private IMScheduleService mSchService;
    private ITheaterService thService;
    private ISeatSchService ssService;
    
    private Map<String, String> selectedMap;
    
    private String theater_name = "";
    
    private G_topController topCtrl;
    public void setTopCtrl(G_topController topCtrl) {
    	this.topCtrl=topCtrl;
    }
    
    //예매 페이지가 아닌 바깥경로로 예매를 진행하려 할 때 사용할 메서드
    public int picker(String movieName, String moviePos) {
    	int count = 0;
    	// 선택영화 선택해놓기
    	for (int i = 0; i < btnMovieDateCtrl.size(); i++) {
			if(btnMovieDateCtrl.get(i).btnMovieGetText().equals(movieName)) {
				int cnt = adultCheck(btnMovieDateCtrl.get(i));
				if(cnt>0) {
					count = cnt;
					return count;
				}
				btnMovieDateCtrl.get(i).colorGrey();
			}
		}
    	// 맵에 담아두기
    	selectedMap.put("selectedMovie", movieName);
    	selectedMap.put("moviePos", moviePos);
    	
    	// 오늘날짜 세팅
    	String selectedDate = "";
    	String selectedDay = "";
    	
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH);
		int date = cal.get(cal.DATE);
		String strMonth = (month+1)+"";
		if(strMonth.length()==1) strMonth="0"+strMonth;
		
		// 선택날짜가 한 자리일 시 앞에 0을 붙여 형식을 맞춘다.
		String strDate = date+"";
		if(strDate.length()==1) strDate="0"+strDate;
		selectedDate = year+"-"+strMonth+"-"+strDate;
		
		int tmp_day = cal.get(Calendar.DAY_OF_WEEK);
		switch (tmp_day) {
			case 1: selectedDay = "일"; break;
			case 2: selectedDay = "월"; break;
			case 3: selectedDay = "화"; break;
			case 4: selectedDay = "수"; break;
			case 5: selectedDay = "목"; break;
			case 6: selectedDay = "금"; break;
			case 7: selectedDay = "토"; break;
		}
    	
		btnDateCtrlList.get(0).colorGrey(selectedDay);
    	
    	// 맵에 담아두기
    	selectedMap.put("selectedDate",selectedDate);
    	selectedMap.put("selectedDay",selectedDay);
    	setScheduleBox(selectedMap);
    	return count;
    }
    
    private List<U_bookMovie_btnMovieController> btnMovieDateCtrl;
    private List<U_bookMovie_btnDateController> btnDateCtrlList; 
    private List<Node> btnTimeList;
    private int show_no;
    
    private Stage mainStage;
    
    private int check;
    void setCheck(int check) {
    	this.check = check;
    }
    
    private String selectedCard;
    void setSelectedCard(String selectedCard) {
    	this.selectedCard = selectedCard;
    }
    
    private Map<String, String> infoMap;
    void setInfoMap(Map<String, String> infoMap) {
    	this.infoMap = infoMap;
    }
    
    private List<Integer> selectedSseats;
    public void setSelectedSseats(List<Integer> selectedSseats) {
    	this.selectedSseats = selectedSseats;
    }
    public List<Integer> getSelectedSseats() {
    	return selectedSseats; 
    }
    
    private List<String> selectedSeats;
    public void setSelectedSeats(List<String> selectedSeats) {
    	this.selectedSeats= selectedSeats;
    }
    public List<String> getSelectedSeats() {
    	return selectedSeats; 
    }
    
    U_cardMethodController grandChildCtrl;
    public void setGrandChild(U_cardMethodController grandChildCtrl) {
    	this.grandChildCtrl = grandChildCtrl;
    }
    
    // 다음버튼 클릭 시 
    @FXML
    void btnNextClick(ActionEvent event) {
    	try {
    		if(selectedMap.get("selectedMovie")!=null
    				&&selectedMap.get("selectedDate")!=null
    				&&selectedMap.get("selectedTime")!=null) {
    			String func = btnNext.getText();
    			
    			// 좌석선택 창 세팅
    			FXMLLoader loader = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_bookMovie_seatChoiceMain.fxml"));
    			Parent seatChoice = loader.load();
    			U_bookMovie_seatChoiceMainController ctrl = loader.getController();
    			ctrl.setMainCtrl(this);
    			ctrl.setInfoMap(selectedMap);
    			theater_name = ctrl.setShow_no(show_no);
    			if(func.equals("좌석선택")) {
    				// 좌석선택으로 창변경
    				outerBox.setCenter(seatChoice);
    				
    				btnNext.setText("결제선택");
    				btnNextNormal();
    			}else if(func.equals("결제선택")) {
    				if(check<1) {
    					AlertUtil.warnMsg("선택오류", "좌석을 선택해주십시오.");
    					return;
    				}
    				lblSeatCheck.setStyle("-fx-text-fill:red;");
    				
    				// 결제선택으로 창변경
    				FXMLLoader loader2 = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_pay.fxml"));
    				Parent payChoice = loader2.load();
    				U_payController ctrl4 = loader2.getController();
    				ctrl4.setMainCtrl(this);
    				ctrl4.setInfoMap(infoMap);
    				ctrl4.setLbl_pay_price(infoMap.get("pay_price")+"원");
    				ctrl4.setLbl_final_price(infoMap.get("pay_price")+"원");
    				
    				btnNext.setText("결제하기");
    				outerBox.setCenter(payChoice);
    			}else if(func.equals("결제하기")) {
    				if(infoMap.get("pay_met")==null) {
    					AlertUtil.warnMsg("결제수단선택", "결제수단을 선택하십시오.");
    					return;
    				}
    				if(infoMap.get("pay_met").equals("신용카드")) {
    					selectedCard = grandChildCtrl.getSelectedCard();
    					if(selectedCard == null) {
    						AlertUtil.warnMsg("카드선택", "카드종류를 선택하십시오.");
    						return;
    					}
    				}
    				
    				lblPayCheck.setStyle("-fx-text-fill:red;");
    				// 결제창 모달로 띄우기
    				
    				// 1. 부모창 객체얻기
    				mainStage = (Stage) btnNext.getScene().getWindow();
    				
    				// 2. 부모자식창 관계설정
    				Stage secStage = new Stage(StageStyle.UTILITY);
    				secStage.initModality(Modality.WINDOW_MODAL);
    				secStage.initOwner(mainStage);
    				
    				// 3. 자식창 생성
    				FXMLLoader loader3 = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_payConfirm.fxml"));
    				Parent childRoot = loader3.load();
    				
    				// 4. 부모컨트롤러, infoMap 넘겨주기
    				U_payConfirmController ctrl3 = loader3.getController();
    				ctrl3.setMainCtrl(this);
    				ctrl3.setInfoMap(infoMap);
    				ctrl3.setSelectedSeats(selectedSeats);
    				ctrl3.setSelectedSseats(selectedSseats);
    				ctrl3.setAb(1);
    				ctrl3.setTopCtrl(topCtrl);
    				
    				// 5. 자식창에 데이터 세팅
    				String dateTime = infoMap.get("selectedDate")+"("+infoMap.get("selectedDay")+") "+infoMap.get("selectedTime");
    				String movie = infoMap.get("selectedMovie");
    				String pay_met = infoMap.get("pay_met");
    				String pos = infoMap.get("moviePos");
    				int pay_price = Integer.parseInt(infoMap.get("pay_price"));
    				String theater = infoMap.get("theater_name");
    				int qty = Integer.parseInt(infoMap.get("book_qty"));
    				String seats = "";
    				for (int i = 0; i < selectedSeats.size(); i++) {
    					if(!seats.equals("")) {
    						seats +=", ";
    					}
						seats += selectedSeats.get(i);
					}
    				
    				ctrl3.setDateTime(dateTime);
    				ctrl3.setMovieName(movie);
    				ctrl3.setPayMet(pay_met);
    				ctrl3.setPayPrice(pay_price);
    				ctrl3.setTheaterName(theater);
    				ctrl3.setQty(qty);
    				ctrl3.setSeatId(seats);
    				ctrl3.setMoviePos(pos);
    				
    				// 5. 자식창 띄우기
    				Scene childScene = new Scene(childRoot);
    				secStage.setScene(childScene);
    				secStage.setTitle("결제창");
    				secStage.show();
    			}
    		}else {
    			AlertUtil.warnMsg("선택오류", "예매하실 영화정보를 선택해주십시오.");
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	selectedMap = new HashMap<String, String>();
    	btnMovieDateCtrl = new ArrayList<>();
    	btnDateCtrlList = new ArrayList<>();
    	btnTimeList = new ArrayList<>();
    	
    	try {
    		reg = LocateRegistry.getRegistry("localhost",9988);
    		smService = (ISMovieService) reg.lookup("sMovieService");
    		thService = (ITheaterService) reg.lookup("theaterService");
    		mSchService = (IMScheduleService) reg.lookup("mScheduleService");
    		ssService = (ISeatSchService) reg.lookup("seatSchService");
    		
    		
			List<SmovieShortInfoVO> smList = smService.sMovieNameList();

			// movieBox List 세팅
			for (int i = 0; i < smList.size(); i++) {
				String smovie_name = smList.get(i).getMovie_name();
				FXMLLoader loader = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_bookMovie_btnMovie.fxml"));
				Parent btnMovie = loader.load();
				U_bookMovie_btnMovieController btnMovieCtrl = loader.getController();
				
				// 버튼 클릭 시 효과를 주기 위해 생성한 버튼을 List에 저장해둔다.
				btnMovieDateCtrl.add(btnMovieCtrl);
				
				// 청소년관람불가 영화인 경우 청불표시를 띄운다.
				if(smList.get(i).getMovie_adult().equalsIgnoreCase("Y")) {
					btnMovieCtrl.setVisible();
				}
				
				btnMovieCtrl.btnMovieSetText(smovie_name);
				btnMovieCtrl.getBtnMovie().setOnMouseClicked(e -> {
					try {
						// 청소년이 청소년 관람불가 영화를 선택한 경우
						if(btnMovieCtrl.isVisible()) {
							// 날짜비교를 위한 양식설정
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							
							// 생년월일
							Date mem_birth = (Date) format.parse(LoginVO.currVo.getMem_bir());
							Calendar cal = Calendar.getInstance();
							cal.setTime(mem_birth);
							// +18
							cal.add(Calendar.YEAR, 18);
							
							// 비교할 날짜(생년월일+18년)
							Date birth = new Date(cal.getTimeInMillis());
							// 오늘날짜
							Date today = new Date();
							
							if(birth.getTime()>today.getTime()) {
								AlertUtil.warnMsg("알림", "본 영화는 청소년 관람불가 영화입니다.");
								selectedMap.put("selectedMovie", null);
								// 선택된 영화가 있다면 색을 초기화
								for (int j = 0; j < btnMovieDateCtrl.size(); j++) {
									btnMovieDateCtrl.get(j).colorNone();				
								}
								// 선택된 날짜가 있다면 색 초기화
								for (int j = 0; j < btnDateCtrlList.size(); j++) {
									String temp = btnDateCtrlList.get(j).btnDateGetText();
									temp = temp.substring(temp.length()-1);
									
									btnDateCtrlList.get(j).colorNone(temp);
								}
								// 스케줄박스 초기화
								scheduleBox.getChildren().clear();
								return;
							}
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					String selectedMovie = btnMovieCtrl.btnMovieGetText();
					// DB에 보내기 위해 Map에 담는다.
					selectedMap.put("selectedMovie", selectedMovie);
					for (int j = 0; j < smList.size(); j++) {
						if(smList.get(j).getMovie_name().equals(selectedMovie)) {
							selectedMap.put("moviePos",smList.get(j).getMovie_poster());
						}
					}
					
					// 클릭시 다른 버튼의 컬러를 없애고,
					for (int j = 0; j < btnMovieDateCtrl.size(); j++) {
						btnMovieDateCtrl.get(j).colorNone();				
					}
					// 해당 버튼의 컬러를 변경한다.
					btnMovieCtrl.colorGrey();
					
					// 아래 진행단계 라벨 컬러도 변경
					lblMovieCheck.setStyle("-fx-text-fill:red;");
						
					
					if(selectedMap.get("selectedDate")==null) {
						// 오늘날짜가 디폴트로 세팅
						// 오늘날짜 세팅
						String selectedDate = "";
						String selectedDay = "";
						
						Calendar cal = Calendar.getInstance();
						int year = cal.get(cal.YEAR);
						int month = cal.get(cal.MONTH);
						int date = cal.get(cal.DATE);
						String strMonth = (month+1)+"";
						if(strMonth.length()==1) strMonth="0"+strMonth;
						
						// 선택날짜가 한 자리일 시 앞에 0을 붙여 형식을 맞춘다.
						String strDate = date+"";
						if(strDate.length()==1) strDate="0"+strDate;
						selectedDate = year+"-"+strMonth+"-"+strDate;
						
						int tmp_day = cal.get(Calendar.DAY_OF_WEEK);
						switch (tmp_day) {
						case 1: selectedDay = "일"; break;
						case 2: selectedDay = "월"; break;
						case 3: selectedDay = "화"; break;
						case 4: selectedDay = "수"; break;
						case 5: selectedDay = "목"; break;
						case 6: selectedDay = "금"; break;
						case 7: selectedDay = "토"; break;
						}
						
						btnDateCtrlList.get(0).colorGrey(selectedDay);
						
						// 맵에 담아두기
						selectedMap.put("selectedDate",selectedDate);
						selectedMap.put("selectedDay",selectedDay);
						setScheduleBox(selectedMap);
					}
					
					
					// 영화를 변경하여 클릭하는 경우를 대비하여 scheduleBox 세팅
					if(selectedMap.get("selectedDate")!=null) {
						scheduleBox.getChildren().clear();
						setScheduleBox(selectedMap);
					}
				});
				movieBox.getChildren().add(btnMovie);
			}
			
			setDateBox();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
    }
    
    // 데이트 박스 세팅
    public void setDateBox() {
    	Calendar cal = Calendar.getInstance();
		// 시작날짜(오늘) 구하기
		int year = cal.get(cal.YEAR);
		lblYear.setText(year+"");
		int month = cal.get(cal.MONTH);
		lblMonth.setText((month+1)+"");
		int date = cal.get(cal.DATE);
		int tmp_day = cal.get(Calendar.DAY_OF_WEEK);
		String day = "";
		String style = "";
		
		// dateBox List 세팅
		for (int i = 0; i < 7; i++) {
			FXMLLoader loader = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_bookMovie_btnDate.fxml"));
			Parent btnDate = null;
			try {
				btnDate = loader.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			U_bookMovie_btnDateController btnDateCtrl = loader.getController();
			
			// 날짜 클릭시 색상변경을 위해 List에 담아준다.
			btnDateCtrlList.add(btnDateCtrl);
			
			switch (tmp_day) {
			case 1: day = "일";
				style = "-fx-text-fill:red; -fx-background-color: none;";
				btnDateCtrl.btnDateSetStyle(style);
				break;
			case 2: day = "월"; break;
			case 3: day = "화"; break;
			case 4: day = "수"; break;
			case 5: day = "목"; break;
			case 6: day = "금"; break;
			case 7: day = "토";
				style = "-fx-text-fill:blue; -fx-background-color: none;";
				btnDateCtrl.btnDateSetStyle(style);
				break;
			}
			String dnd = date+" "+day;
			
			// 버튼에 날짜&요일 입력 예:14 금
			btnDateCtrl.btnDateSetText(dnd);
			
			// 날짜 버튼 추가
			dateBox.getChildren().add(btnDate);
			date++;
			tmp_day++;
			if(tmp_day>7) tmp_day-=7;
			
			// 날짜를 선택했을 때 
			btnDate.setOnMouseClicked(e->{
				if(selectedMap.get("selectedMovie")==null) {
					AlertUtil.warnMsg("선택오류", "영화를 먼저 선택해주세요.");
					return;
				}
				
				// 다른날짜는 배경색 없애고,
				for (int j = 0; j < btnDateCtrlList.size(); j++) {
					String temp = btnDateCtrlList.get(j).btnDateGetText();
					temp = temp.substring(temp.length()-1);
					
					btnDateCtrlList.get(j).colorNone(temp);
				}
				// 해당날짜만 배경색 입힌다.
				String temp_day = btnDateCtrl.btnDateGetText().substring(btnDateCtrl.btnDateGetText().length()-1);
				btnDateCtrl.colorGrey(temp_day);
				
				
				// scheduleBox 세팅
				scheduleBox.getChildren().clear();
				
				// 선택날짜
				String strMonth = (month+1)+"";
				if(strMonth.length()==1) strMonth="0"+strMonth;
				
				// 선택날짜가 한 자리일 시 앞에 0을 붙여 형식을 맞춘다.
				String strDate = btnDateCtrl.btnDateGetText().substring(0,2);
				int tempParsing = Integer.parseInt(strDate);
				String temp = tempParsing+"";
				if(temp.length()==1) strDate="0"+strDate;
				
				
				// 선택요일
				int	len = btnDateCtrl.btnDateGetText().length()-1;
				String selectedDay = btnDateCtrl.btnDateGetText().substring(len,len+1);
				selectedMap.put("selectedDay", selectedDay);
				
				// DB에 보낼 형태로 날짜를 세팅 예:2020-08-19
				String selectedDate = year+"-"+strMonth+"-"+strDate;
				
				// DB에 보내기 위해 맵에 담는다.
				selectedMap.put("selectedDate", selectedDate);
				setScheduleBox(selectedMap);
			});
		}
    }
    
    
    // 스케줄 박스 세팅
    public void setScheduleBox(Map<String, String> selectedMap) {
    	try {
			List<String> tnList = thService.theaterNameListforADay(selectedMap);
			for (int j = 0; j < tnList.size(); j++) {
				String theaterName = tnList.get(j);
				FXMLLoader loader2 = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_bookMovie_theaterBox.fxml"));
				Parent theaterBox = loader2.load();
				U_bookMovie_theaterBoxController thBoxCtrl = loader2.getController();
				thBoxCtrl.SetLblTheaterName(theaterName);
				/////////////////////////////////////////////////////////////////////////////////////////////////////////
				theaterBox.setId(theaterName);
				
				selectedMap.put("selectedTheater", theaterName);
				List<ShowInfoVO> showList = mSchService.getShowInfo(selectedMap);
				
				HBox hbox = null;
				for (int l = 0; l < showList.size(); l++) {
					if(l%2==0) hbox = new HBox();
					hbox.setId("hbox"+l);
					
					FXMLLoader loader3 = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_bookMovie_timeBox.fxml"));
					Parent timeBox = loader3.load();
					U_bookMovie_timeBoxController tbCtrl = loader3.getController();
					btnTimeList.add(tbCtrl.getBtn());
					
					String org = showList.get(l).getShow_time();
					String time = showList.get(l).getShow_time().substring(11,16);
					tbCtrl.btnTimeSetText(time);
					
					// 날짜비교를 위한 양식설정
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					
					// 상영시간
					Date show_time = (Date) format.parse(org);
					
					// 현재시간
					Date now = new Date();
					
					// 상영시간이 지났을 경우
					if(show_time.getTime()<now.getTime()) {
						tbCtrl.setDisable();
					}
					
					// 해당관의 잔여좌석수 표시를 위해 메서드 호출
					int tmp_show_no = showList.get(l).getShow_no();
					int seatNum = ssService.getSeatNum(tmp_show_no);
					int openSeatNum = ssService.getOpenSeatNum(tmp_show_no);
					
					// 결과를설정
					tbCtrl.lblSeatNum(seatNum);
					tbCtrl.lblOpenSeatNum(openSeatNum);
					
					// 심야 또는 조조 인지 검사 후 아이콘을 설정
					String dis_id = showList.get(l).getDis_id();
					
					if(dis_id.equals("D0002")) { //조조
						InputStream is = new FileInputStream("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/sun_icon.png");								
						tbCtrl.setImg(is);
					}else if(dis_id.equals("D0003")) { // 심야
						InputStream is = new FileInputStream("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/star_icon.png");								
						tbCtrl.setImg(is);
					}else { // 일반
						InputStream is = new FileInputStream("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/none.png");								
						tbCtrl.setImg(is);
					}
					
					// 시간버튼 클릭 시
					tbCtrl.getBtn().setOnMouseClicked(e->{
						int cnt = specialCheck(tbCtrl);
						if(cnt>0) return;
						// 클릭시 다른 버튼의 컬러를 없애고,
						for (int i = 0; i < btnTimeList.size(); i++) {
							colorNone(btnTimeList);			
						}
						// 해당 버튼의 컬러를 변경한다.
						colorGrey(tbCtrl.getBtn());
						
						// 선택한 시간을 Map에 담는다.
						String selectedTime = tbCtrl.btnTimeGetText();
						selectedMap.put("selectedTime", selectedTime);
						
						try {
							// 상영스케줄 번호를 받아 전역변수에 저장한다.
							show_no = mSchService.getShow_no(selectedMap);
							
							// 할인ID를 받아 맵에 저장한다.
							String temp = mSchService.getDis_id(show_no);
							selectedMap.put("dis_id", temp);
							
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						// 좌석선택버튼 컬러 변경
						if(selectedMap.get("selectedMovie")!=null
			    				&&selectedMap.get("selectedDate")!=null
			    				&&selectedMap.get("selectedTime")!=null) {
							btnNextBold();
							
							// 라벨컬러 변경
							lblTimeCheck.setStyle("-fx-text-fill:red;");
						}else {
							btnNextNormal();
						}
					});
					hbox.getChildren().add(timeBox);
					if(l%2==0) thBoxCtrl.addHbox(hbox);
				}
				
				scheduleBox.getChildren().add(theaterBox);
				
				FXMLLoader loader3 = new FXMLLoader(U_bookMovieMainController.class.getResource("../fxml/U_bookMovie_line.fxml"));
				Parent line = loader3.load();
				U_bookMovie_lineController lineCtrl = loader3.getController();
				scheduleBox.getChildren().add(line);
			}
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    }
    
    public void colorNone(List<Node> btnList) {
    	for (int i = 0; i < btnList.size(); i++) {
			btnList.get(i).setStyle("-fx-background-color: none; -fx-border-color: #827e7e;");
		}
    }
    
    public void colorGrey(Node btn) {
    	btn.setStyle("-fx-background-color:#c2baba; -fx-border-color:#827e7e; -fx-text-fill:white");
    }
    
    public void btnNextBold() {
    	btnNext.setStyle("-fx-border-color:white; -fx-border-width:7; -fx-border-radius:20; -fx-background-color:none;");
    }
    
    public void btnNextNormal() {
    	btnNext.setStyle("-fx-border-color:white; -fx-border-radius:20; -fx-background-color:none;");
    }
    
    public void setResult(Node center) {
    	bottom.getChildren().clear();
    	bottom.setStyle("-fx-background-color:white;");
    	outerBox.setCenter(center);
    }
    
    public int specialCheck(U_bookMovie_timeBoxController tbBox) {
    	int cnt = 0;
    	String theater_name = tbBox.getBtn().getParent().getParent().getParent().getId();
    	selectedMap.put("theater_name", theater_name);
    	try {
    		// 5관인 경우 청소년 이용불가 
    		if(theater_name.equals("5관(매칭관)")) {
    			// 날짜비교를 위한 양식설정
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    			
    			// 생년월일
    			Date mem_birth = (Date) format.parse(LoginVO.currVo.getMem_bir());
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(mem_birth);
    			// +18
    			cal.add(Calendar.YEAR, 18);
    			
    			// 비교할 날짜(생년월일+18년)
    			Date birth = new Date(cal.getTimeInMillis());
    			// 오늘날짜
    			Date today = new Date();
    			
    			if(birth.getTime()>today.getTime()) {
    				AlertUtil.warnMsg("알림", "해당관은 성인만 이용이 가능합니다.");
    				cnt = 1;
    			}
    		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return cnt;
    }
    
    public int adultCheck(U_bookMovie_btnMovieController bmCtrl) {
    	int cnt = 0;
		try {
			// 청소년이 청소년 관람불가 영화를 선택한 경우
			if(bmCtrl.isVisible()) {
				// 날짜비교를 위한 양식설정
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				// 생년월일
				Date mem_birth = (Date) format.parse(LoginVO.currVo.getMem_bir());
				Calendar cal = Calendar.getInstance();
				cal.setTime(mem_birth);
				// +18
				cal.add(Calendar.YEAR, 18);
				
				// 비교할 날짜(생년월일+18년)
				Date birth = new Date(cal.getTimeInMillis());
				// 오늘날짜
				Date today = new Date();
				
				if(birth.getTime()>today.getTime()) {
					AlertUtil.warnMsg("알림", "본 영화는 청소년 관람불가 영화입니다.");
					cnt = 1;
					// 선택된 영화 초기화
					for (int j = 0; j < btnMovieDateCtrl.size(); j++) {
						btnMovieDateCtrl.get(j).colorNone();				
					}
					// 선택된 날짜 초기화
					for (int j = 0; j < btnDateCtrlList.size(); j++) {
						String temp = btnDateCtrlList.get(j).btnDateGetText();
						temp = temp.substring(temp.length()-1);
						
						btnDateCtrlList.get(j).colorNone(temp);
					}
					// 선택된 시간 초기화
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return cnt;
    }
    
    
}
