package wg.main;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import util.AlertUtil;
import wg.smovie.service.ISMovieService;
import wg.theater.service.ITheaterService;
import wg.vo.SMovieVO;
import wg.vo.SMovieViewVO;
import wg.vo.TheaterVO;

public class M_smovieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<TheaterVO> thCombo;

    @FXML
    private TextField th_id;

    @FXML
    private TextField mo_id;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancle;

    @FXML
    private TableView<SMovieViewVO> smovieTable;

    @FXML
    private TableColumn<?, ?> thNameCol;

    @FXML
    private TableColumn<?, ?> moNameCol;

    @FXML
    private TableColumn<?, ?> relCol;

    @FXML
    private TableColumn<?, ?> endCol;

    @FXML
    private Button btnReg;

//    @FXML
//    private Button btnUpdate;

    @FXML
    private Button btnDel;
    
    @FXML
    private Button btnSrc;

    @FXML
    void btnCancleClick(ActionEvent event) {
    	mo_id.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	btnReg.setDisable(false);
//    	btnUpdate.setDisable(false);
    	btnDel.setDisable(false);
    	smovieTable.setDisable(false);
    	
    	
    	if(!smovieTable.getSelectionModel().isEmpty()) {
    		SMovieViewVO smovie = smovieTable.getSelectionModel().getSelectedItem();
    		
    		mo_id.setText(smovie.getMovie_id());
    	}
    }

    @FXML
    void btnDelClick(ActionEvent event) throws RemoteException {
    	
    	if(smovieTable.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("상영영화선택", "삭제할 상영영화를 선택하십시오.");
    		return;
    	}
    	String thId = smovieTable.getSelectionModel().getSelectedItem().getTheater_id();
    	String mo_name = smovieTable.getSelectionModel().getSelectedItem().getMovie_name();
    	int no = smovieTable.getSelectionModel().getSelectedItem().getSmovie_no();
    	
    	int cnt = service.deletSmovie(no);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", mo_name+"를(을) 삭제했습니다." );
    		
    		mo_id.clear();
    		getSmovie(thId);
    		
    	}else {
    		AlertUtil.warnMsg("작업결과", "상영영화 삭제 실패");
    	}
    	
    	
    }
    
    private Stage mainStage;
    
    @FXML
    void btnSrcClick(ActionEvent event) {
    	try {
			mainStage = (Stage) btnSrc.getScene().getWindow();
			
			Stage secStage = new Stage(StageStyle.UTILITY);
			secStage.initModality(Modality.WINDOW_MODAL);
			secStage.initOwner(mainStage);
			
			FXMLLoader loader = new FXMLLoader(M_smovieController.class.getResource("../fxml/M_smovieSearch.fxml"));
			Parent childRoot = loader.load();
			
			M_smovieSearchController ctrl = loader.getController();
			ctrl.setMainCtrl(this);
			
			Scene childScene = new Scene(childRoot);
			secStage.setScene(childScene);
			secStage.setTitle("영화검색");
			secStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnOkClick(ActionEvent event) throws RemoteException {
    	String thId=th_id.getText();
    	String moId=mo_id.getText();
    	
    	if(moId.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "영화ID를 입력하세요.");
    		return;
    	}
    	SMovieVO smo = new SMovieVO();
    	smo.setTheater_id(thId);
    	smo.setMovie_id(moId);
    	
    	if("add".equals(strWord)) {
    		int cnt = service.insertSmovie(smo);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "상영영화 등록 완료");
    			getSmovie(thId);
    		}else {
    			AlertUtil.warnMsg("작업결과", "상영영화 등록 실패");
    			mo_id.setText("");
    		}
    	}else if("eddit".equals(strWord)) {
    		int no = smovieTable.getSelectionModel().getSelectedItem().getSmovie_no();
    		smo.setSmovie_no(no);
    		int cnt = service.updateSmovie(smo);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "수정 완료");
    			getSmovie(thId);
    		}else {
    			AlertUtil.warnMsg("작업결과", "상영영화 수정 실패");
    			mo_id.setText("");
    		}
    	}
    	
    	mo_id.clear();
    	
    	mo_id.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	btnReg.setDisable(false);
//    	btnUpdate.setDisable(false);
    	btnDel.setDisable(false);
    	smovieTable.setDisable(false);
    	
    	strWord="";
    }

    @FXML
    void btnRegClick(ActionEvent event) {
    	
    	btnOk.setDisable(false);
    	btnCancle.setDisable(false);
    	
    	btnReg.setDisable(true);
//    	btnUpdate.setDisable(true);
    	btnDel.setDisable(true);
    	smovieTable.setDisable(true);
    	
    	strWord="add";
    }

//    @FXML
//    void btnUpClick(ActionEvent event) {
//    	if(smovieTable.getSelectionModel().isEmpty()) {
//    		AlertUtil.warnMsg("상영영화선택", "수정할 영화를 선택해주십시오.");
//    		return;
//    	}
//    	
//    	mo_id.setEditable(true);
//    	
//    	btnOk.setDisable(false);
//    	btnCancle.setDisable(false);
//    	
//    	btnReg.setDisable(true);
//    	btnUpdate.setDisable(true);
//    	btnDel.setDisable(true);
//    	smovieTable.setDisable(true);
//    	
//    	mo_id.requestFocus();
//    	strWord="eddit";
//    }

    @FXML
    void tableClick(MouseEvent event) {
    	if(smovieTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	SMovieViewVO smovie = smovieTable.getSelectionModel().getSelectedItem();
    	
    	th_id.setText(smovie.getTheater_id());
    	mo_id.setText(smovie.getMovie_id());
    	
    	
    }
    
    private ObservableList<SMovieViewVO> smvList;
    private ObservableList<TheaterVO> thList;
    private ISMovieService service;
    private ITheaterService service2;
    private String strWord="";

    @FXML
    void initialize() {
      
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ISMovieService) reg.lookup("sMovieService");
			service2 = (ITheaterService) reg.lookup("theaterService");
			thNameCol.setCellValueFactory(new PropertyValueFactory<>("theater_name"));
			moNameCol.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
			relCol.setCellValueFactory(new PropertyValueFactory<>("movie_release"));
			endCol.setCellValueFactory(new PropertyValueFactory<>("movie_end"));
			
			List<TheaterVO> list = service2.getAllTheaterList();
			thList = FXCollections.observableArrayList(list);
			
			//getList();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	for (int i = 0; i < thList.size(); i++) {
			thCombo.getItems().add(thList.get(i));
		}
    	
    	thCombo.setCellFactory(new Callback<ListView<TheaterVO>, ListCell<TheaterVO>>() {
			
			@Override
			public ListCell<TheaterVO> call(ListView<TheaterVO> param) {

				return new ListCell<TheaterVO>() {
					@Override
					protected void updateItem(TheaterVO item, boolean empty) {
						super.updateItem(item, empty);
						if(item==null || empty) {
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
    			List<SMovieViewVO> smovie=new ArrayList<>();
    			try {
					smovie = service.viewList2(theater.getTheater_id());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
    			smvList = FXCollections.observableArrayList(smovie);
    			smovieTable.setItems(smvList);
    			th_id.setText(thCombo.getValue().getTheater_id());
    			mo_id.setText("");
    			
    		}
		});
    	
    	thCombo.setValue(thList.get(0));
    	
    	mo_id.setEditable(false);
    	btnOk.setDisable(true);
    	btnCancle.setDisable(true);
    	
    	
    	
    	
    	
    	
    }
    private void getSmovie(String th_id) throws RemoteException {
    	List<SMovieViewVO> smovie = new ArrayList<>();
    	smovie=service.viewList2(th_id);
    	smvList=FXCollections.observableArrayList(smovie);
    	smovieTable.setItems(smvList);
    }
    
    public void setMovie_id(String movie_id) {
    	mo_id.setText(movie_id);
    }
    
}
