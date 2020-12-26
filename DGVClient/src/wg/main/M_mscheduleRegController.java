package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sound.sampled.DataLine;
import javax.swing.text.DateFormatter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import util.AlertUtil;
import wg.discount.service.IDiscountService;
import wg.mschedule.service.IMScheduleService;
import wg.seatSch.service.ISeatSchService;
import wg.theater.service.ITheaterService;
import wg.vo.DiscountVO;
import wg.vo.MScheduleVO;
import wg.vo.MscheduleViewVO;
import wg.vo.SMovieViewVO;
import wg.vo.SeatSchVO;
import wg.vo.TheaterVO;

public class M_mscheduleRegController {
	static Map<String, String> param = new HashMap<>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<MscheduleViewVO> sMoCombo;

    @FXML
    private ComboBox<String> hourCombo;

    @FXML
    private ComboBox<String> minCombo;
    
    @FXML
    private ComboBox<DiscountVO> disCombo;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancle;

    @FXML
    private Label dateLabel;

    @FXML
    private ComboBox<TheaterVO> thCombo;

    @FXML
    private TableView<MscheduleViewVO> mTable;

    @FXML
    private TableColumn<?, ?> ThCol;

    @FXML
    private TableColumn<?, ?> moCol;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> endCol;
    
    @FXML
    private TableColumn<?, ?> disCol;

    @FXML
    private Button btnReg;

    @FXML
    private Button btnDel;
    
    @FXML
    private Button BtnSseat;

    @FXML
    void BtnSseatClick(ActionEvent event) throws RemoteException {
    	
    	if(mTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("영화선택", "상영좌석을 추가할 영화를 선택하십시오.");
    		return;
    	}
    	
    	MscheduleViewVO ms = mTable.getSelectionModel().getSelectedItem();	
    	//여기서 상영좌석 추가
		char c = 'A';
		int b = 1;
		int x = 10;
		int cnt = 0;
		
		SeatSchVO svo = new SeatSchVO();
		
		int seatNum = thCombo.getValue().getTheater_numOfSeat();
		
		if(seatNum%10 == 0) {
			for (int i = 0; i < seatNum/10; i++) {
				for (int j = 0; j < 10; j++) {
					String seat = c+"0"+b;
					if(b == 10) {
						seat = c+"10";
					}
					svo.setShow_no(ms.getShow_no());
					svo.setTheater_id(thCombo.getValue().getTheater_id());
					svo.setSeat_id(seat);
					svo.setSseat_status("N");
					
					cnt = seatService.insertSseat(svo);
					++b;
				}
				c++;
				b = 1;
			}
		} else{
			for (int i = 0; i < seatNum/10+1; i++) {
				if(i==seatNum/10) {
					x = seatNum%10;
				}
				for (int j = 0; j < x; j++) {
					String seat = c+"0"+b;
					if(b == 10) {
						seat = c+"10";
					}
					svo.setShow_no(ms.getShow_no());
					svo.setTheater_id(thCombo.getValue().getTheater_id());
					svo.setSeat_id(seat);
					svo.setSseat_status("N");
					cnt = seatService.insertSseat(svo);
					++b;
				}
				c++;
				b = 1;
			}
		}
		if(cnt>0) {
			AlertUtil.infoMsg("작업결과", "상영좌석 추가 완료");
		}else {
			AlertUtil.warnMsg("작업결과", "상영좌석 추가 실패");
		}
    }

    @FXML
    void btnCancleClick(ActionEvent event) {
    	thCombo.setDisable(false);
    	mTable.setDisable(false);
    	
    	btnOk.setDisable(true);
		btnCancle.setDisable(true);
		sMoCombo.setDisable(true);
		hourCombo.setDisable(true);
		minCombo.setDisable(true);
		disCombo.setDisable(true);
    	
    	btnReg.setDisable(false);
    	btnDel.setDisable(false);
    	
    	sMoCombo.setPromptText("상영영화선택");
    	hourCombo.setPromptText(null);
    	minCombo.setPromptText(null);
    	disCombo.setPromptText("할인선택");
    	
    }

    @FXML
    void btnDelClick(ActionEvent event) throws RemoteException, ParseException {
    	if(mTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("스케줄선택", "삭제할 스케줄을 선택하십시오.");
    		return;
    	}
    	String moName = mTable.getSelectionModel().getSelectedItem().getMovie_name();
    	int no = mTable.getSelectionModel().getSelectedItem().getShow_no();
    	
    	service.deletMsSeat(no);
    	int cnt = service.deletMs(no);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", moName+"를(을) 삭제했습니다.");
    		
    		getMs(param);
    		
    	}else {
    		AlertUtil.warnMsg("작업결과", "스케줄 삭제 실패");
    	}
    	
    }

    @FXML
    void btnOkClick(ActionEvent event) throws RemoteException, ParseException {
    	
    	if("".equals(strWord)) {
    		AlertUtil.warnMsg("선택오류", "등록 수정을 선택하세요." );
    		return;
    	}
    	
    	if(sMoCombo.getValue() == null) {
    		AlertUtil.warnMsg("선택오류", "영화를 선택해주세요.");
    		return;
    	}
    	
    	int smNO = sMoCombo.getSelectionModel().getSelectedItem().getSmovie_no();
    	if(hourCombo.getValue() == null || minCombo.getValue() == null) {
    		AlertUtil.warnMsg("선택오류", "상영시간을 선택해주세요.");
    		return;
    	}
    	String dateReg = dateLabel.getText().concat(" "+hourCombo.getSelectionModel().getSelectedItem()+":"+minCombo.getSelectionModel().getSelectedItem()+":00");
    	if(disCombo.getValue() == null) {
    		AlertUtil.warnMsg("선택오류", "할인을 선택해주세요.");
    		return;
    	}
    	String disNo = disCombo.getSelectionModel().getSelectedItem().getDis_id();
    	
    	//시간이 겹치는지 검사
    	List<MscheduleViewVO> list = mTable.getItems();
    	for (int i = 0; i < list.size(); i++) {
    		String endTime = list.get(i).getShow_end();
    		
    		//문자열을 시간으로 변경
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date dateSet = format.parse(endTime);
	    	Date dateset2 = format.parse(dateReg);
	    	
	    	if(dateSet.getTime() > dateset2.getTime()) {
	    		AlertUtil.warnMsg("중복", "스케줄 등록을 할 수 없습니다. 시간을 맞게 입력해주세요.");
	    		return;
	    	}	    	
	    	
		}
    	  	
    	
    	MScheduleVO msv = new MScheduleVO();
    	msv.setSmovie_no(smNO);
    	msv.setShow_beginTime(dateReg);
    	msv.setDis_no(disNo);
    	
    	if("add".equals(strWord)) {
    		int cnt = service.insertMs(msv);
    		
        	MscheduleViewVO ms = mTable.getSelectionModel().getSelectedItem();	
//        	//여기서 상영좌석 추가
//    		char c = 'A';
//    		int b = 1;
//    		int x = 10;
//
//    		
//    		SeatSchVO svo = new SeatSchVO();
//    		
//    		int seatNum = thCombo.getValue().getTheater_numOfSeat();
//    		
//    		if(seatNum%10 == 0) {
//    			for (int i = 0; i < seatNum/10; i++) {
//    				for (int j = 0; j < 10; j++) {
//    					String seat = c+"0"+b;
//    					if(b == 10) {
//    						seat = c+"10";
//    					}
//    					svo.setShow_no(ms.getShow_no());
//    					svo.setTheater_id(thCombo.getValue().getTheater_id());
//    					svo.setSeat_id(seat);
//    					svo.setSseat_status("N");
//    					
//    					seatService.insertSseat(svo);
//    					++b;
//    				}
//    				c++;
//    				b = 1;
//    			}
//    		} else{
//    			for (int i = 0; i < seatNum/10+1; i++) {
//    				if(i==seatNum/10) {
//    					x = seatNum%10;
//    				}
//    				for (int j = 0; j < x; j++) {
//    					String seat = c+"0"+b;
//    					if(b == 10) {
//    						seat = c+"10";
//    					}
//    					svo.setShow_no(ms.getShow_no());
//    					svo.setTheater_id(thCombo.getValue().getTheater_id());
//    					svo.setSeat_id(seat);
//    					svo.setSseat_status("N");
//    					seatService.insertSseat(svo);
//    					++b;
//    				}
//    				c++;
//    				b = 1;
//    			}
//    		}
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "스케줄등록완료");
    			getMs(param);
    			mTable.setDisable(false);
    		}else {
    			AlertUtil.warnMsg("작업결과", "스케줄등록실패");
    		}
    	}
    	thCombo.setDisable(false);
    	
    }

    @FXML
    void btnRegClick(ActionEvent event) {
    	if(thCombo.getValue() == null) {
    		AlertUtil.warnMsg("작업오류", "상영관을 선택해주십시오.");
    		return;
    	}
    	thCombo.setDisable(true);
    	mTable.setDisable(true);
    	btnOk.setDisable(false);
		btnCancle.setDisable(false);
		sMoCombo.setDisable(false);
		hourCombo.setDisable(false);
		minCombo.setDisable(false);
		disCombo.setDisable(false);
    	strWord="add";
    }

    @FXML
    void mTableClick(MouseEvent event) {
    	if(mTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	MscheduleViewVO ms = mTable.getSelectionModel().getSelectedItem();
    	
    }
    
    private LocalDate date;
    private ObservableList<MscheduleViewVO> msList;
    private ObservableList<TheaterVO> thList;
    private ObservableList<DiscountVO> disList;
    private IMScheduleService service;
    private ITheaterService service2;
    private IDiscountService service3;
    private ISeatSchService seatService;
    private String strWord="";
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        dateLabel.setText(String.valueOf(date));
        //여기 맨처음 디폴트로 띄우기
        thCombo.setValue(thList.get(0));
        String namemm = thCombo.getValue().getTheater_name();
        String datemm = dateLabel.getText();
        System.out.println(datemm);
        param.put("namemm", namemm);
        param.put("datemm", datemm);
        try {
        	getMs(param);
        } catch (RemoteException e1) {
        	e1.printStackTrace();
        } catch (ParseException e1) {
        	e1.printStackTrace();
        }
        	
    }
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	List<TheaterVO> list = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IMScheduleService) reg.lookup("mScheduleService");
			service2 = (ITheaterService) reg.lookup("theaterService");
			service3 = (IDiscountService) reg.lookup("discountService");
			seatService = (ISeatSchService) reg.lookup("seatSchService");
			ThCol.setCellValueFactory(new PropertyValueFactory<>("theater_name"));
			moCol.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
			startCol.setCellValueFactory(new PropertyValueFactory<>("show_time"));
			endCol.setCellValueFactory(new PropertyValueFactory<>("show_end"));
			disCol.setCellValueFactory(new PropertyValueFactory<>("dis_name"));
			
			list = service2.getAllTheaterList();
			thList = FXCollections.observableArrayList(list);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	for (int i = 0; i < thList.size(); i++) {
			thCombo.getItems().add(thList.get(i));
		}
    	
    	//여기서 헷갈림 수정이 필요함 첫페이지 1관에 있는 스케줄목록 출력
    	
    	
    	
    	thCombo.setCellFactory(new Callback<ListView<TheaterVO>, ListCell<TheaterVO>>() {
			
			@Override
			public ListCell<TheaterVO> call(ListView<TheaterVO> param) {
				return new ListCell<TheaterVO>() {
					@Override
					protected void updateItem(TheaterVO item, boolean empty) {
						super.updateItem(item, empty);
						if(item==null||empty) {
							setText(null);
						}else {
							setText(item.getTheater_name());
						}
					}
				};
			}
		});
    	
    	thCombo.setButtonCell(new ListCell<TheaterVO>() {
    		@Override
    		protected void updateItem(TheaterVO item, boolean empty) {
    			super.updateItem(item, empty);
    			if(item==null||empty) {
    				setText(null);
    			}else {
    				setText(item.getTheater_name());
    			}
    		}
    	});
    	
    	
    	//콤보박스 변경
    		thCombo.valueProperty().addListener(new ChangeListener<TheaterVO>() {
	    			
	    		@Override
	    		public void changed(ObservableValue<? extends TheaterVO> observable, TheaterVO oldValue,
	    				TheaterVO newValue) {
	    			
	    			TheaterVO theater = thCombo.getSelectionModel().getSelectedItem();
	    			List<MscheduleViewVO> ms = new ArrayList<>();
	    			
	    			param.put("date", dateLabel.getText());
	    			param.put("theater_name", theater.getTheater_name());
	    			
	    			
	    			
	    			try {
						ms = service.viewList(param);
						String end = null;
						
						for (int i = 0; i < ms.size(); i++) {
							String dateReg = ms.get(i).getShow_time();
							
							//문자열을 시간으로 변경
					    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	Date dateSet = format.parse(dateReg);
					    	
					    	Calendar cal = Calendar.getInstance();
					    	cal.setTime(dateSet);
					    	
					    	cal.add(Calendar.MINUTE, (int)Math.ceil(ms.get(i).getMovie_runningtime()/10)*10+30);
					    	
					    	end = format.format(cal.getTime());
					    	ms.get(i).setShow_end(end);
					    	
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
	    			
	    			msList = FXCollections.observableArrayList(ms);
	    			mTable.setItems(msList);
	    			
	    			List<MscheduleViewVO> ms2 = new ArrayList<>();
	    			
	    			try {
						ms2 = service.viewMoname(theater.getTheater_name());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
	    			msList = FXCollections.observableArrayList(ms2);
	    			
	    			sMoCombo.getItems().clear();
	    			
	    			for (int i = 0; i < msList.size(); i++) {
	    				sMoCombo.getItems().add(msList.get(i));						
					}
	    		}
		});
    		
    		List<DiscountVO> dis = new ArrayList<>();
    		try {
				dis = service3.getAllDiscount();
				disList = FXCollections.observableArrayList(dis);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    		
    		for (int i = 0; i < disList.size(); i++) {
				disCombo.getItems().add(disList.get(i));
			}
    		
    		disCombo.setCellFactory(new Callback<ListView<DiscountVO>, ListCell<DiscountVO>>() {
    			
    			//할인
				@Override
				public ListCell<DiscountVO> call(ListView<DiscountVO> param) {
					return new ListCell<DiscountVO>() {
						@Override
						protected void updateItem(DiscountVO item, boolean empty) {
							super.updateItem(item, empty);
							if(item==null || empty) {
								setText(null);
							}else {
								setText(item.getDis_name());
							}
						}
					};
				}
    			
			});
    		
    		disCombo.setButtonCell(new ListCell<DiscountVO>() {
    			@Override
    			protected void updateItem(DiscountVO item, boolean empty) {
    				super.updateItem(item, empty);
    				if(item==null || empty) {
						setText(null);
					}else {
						setText(item.getDis_name());
					}
    			}
    		});
    		
    		//상영영화
    		sMoCombo.setCellFactory(new Callback<ListView<MscheduleViewVO>, ListCell<MscheduleViewVO>>() {
    			
    			@Override
    			public ListCell<MscheduleViewVO> call(ListView<MscheduleViewVO> param) {
    				return new ListCell<MscheduleViewVO>() {
    					@Override
    					protected void updateItem(MscheduleViewVO item, boolean empty) {
    						super.updateItem(item, empty);
    						if(item==null||empty) {
    							setText(null);
    						}else {
    							setText(item.getMovie_name());
    						}
    					}
    				};
    			}
			});
    		
    		sMoCombo.setButtonCell(new ListCell<MscheduleViewVO>() {
    			@Override
    			protected void updateItem(MscheduleViewVO item, boolean empty) {
    				super.updateItem(item, empty);
    				if(item==null||empty) {
    					setText(null);
    				}else {
    					setText(item.getMovie_name());
    				}
    			}
    		});
    		
    		//시간콤보박스
    		hourCombo.getItems().addAll("8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","00");
    		minCombo.getItems().addAll("00","10","20","30","40","50");
    		
    		btnOk.setDisable(true);
    		btnCancle.setDisable(true);
    		sMoCombo.setDisable(true);
    		hourCombo.setDisable(true);
    		minCombo.setDisable(true);
    		disCombo.setDisable(true);
    		
//    		//여기 맨처음 디폴트로 띄우기
//    		thCombo.setValue(thList.get(0));
//    		String namemm = thCombo.getValue().getTheater_name();
//    		String datemm = dateLabel.getText();
//    		param.put("namemm", namemm);
//    		param.put("datemm", datemm);
//    		//System.out.println(param.get("datemm"));
//    		try {
//    			getMs(param);
//    		} catch (RemoteException e1) {
//    			e1.printStackTrace();
//    		} catch (ParseException e1) {
//    			e1.printStackTrace();
//    		}
    		
    }
    
    
    
    private void getMs(Map<String, String> param) throws RemoteException, ParseException {
    	List<MscheduleViewVO> list = new ArrayList<>();
    	list = service.viewList(param);
    	String end = null;
		
		for (int i = 0; i < list.size(); i++) {
			String dateReg = list.get(i).getShow_time();
			
			//문자열을 시간으로 변경
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date dateSet = format.parse(dateReg);
	    	
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(dateSet);
	    	
	    	cal.add(Calendar.MINUTE, (int)Math.ceil(list.get(i).getMovie_runningtime()/10)*10+30);
	    	
	    	end = format.format(cal.getTime());
	    	list.get(i).setShow_end(end);
	    	
		}
    	msList=FXCollections.observableArrayList(list);
    	mTable.setItems(msList);
    }
    
}

