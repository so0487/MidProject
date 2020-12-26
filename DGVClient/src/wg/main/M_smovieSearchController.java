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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.AlertUtil;
import wg.smovie.service.ISMovieService;
import wg.vo.SMovieViewVO2;

public class M_smovieSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> scrCombo;

    @FXML
    private TextField scrText;

    @FXML
    private Button btnScr;

    @FXML
    private TableView<SMovieViewVO2> mTable;

    @FXML
    private TableColumn<?, ?> genreCol;

    @FXML
    private TableColumn<?, ?> movieCol;
    
    private M_smovieController mainCtrl;
    
    public M_smovieController getMainCtrl() {
    	return mainCtrl;
    }
    public void setMainCtrl(M_smovieController mainCtrl) {
    	this.mainCtrl = mainCtrl;
    }

    @FXML
    void btnScrClick(ActionEvent event) {
    	String select = scrCombo.getValue();
    	if(select == null) {
    		AlertUtil.warnMsg("검색오류", "검색항목을 선택하세요");
    		return;
    	}
    	
    	String searchField = null;
    	switch (select) {
		case "영화명": searchField = "movie_name"; break;
		case "장르명": searchField = "gen_name"; break;
		}
    	
    	String searchWord = scrText.getText();
    	
    	searchMap.put("searchField", searchField);
    	searchMap.put("searchWord", searchWord);
    	
    	List<SMovieViewVO2> smList2 = new ArrayList<>();
    	
    	try {
			smList2 = service.viewList3(searchMap);
			
			if(smList2.size() == 0) {
				AlertUtil.warnMsg("작업결과", "검색결과가 없습니다");
			}
			
			smList = FXCollections.observableArrayList(smList2);
			mTable.setItems(smList);
			
		} catch (RemoteException e) {
			// TODO: handle exception
		}
    }

    @FXML
    void mTableClick(MouseEvent event) {
    	String movie_id =  mTable.getSelectionModel().getSelectedItem().getMovie_id();
    	
    	mainCtrl.setMovie_id(movie_id);
    	
    	Stage currentStage = (Stage) btnScr.getScene().getWindow();
    	currentStage.close();
    	
    }
    private ISMovieService service;
    private Map<String, String> searchMap;
    private List<SMovieViewVO2> smvList;
    private ObservableList<SMovieViewVO2> smList;

    @FXML
    void initialize() {
    	
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ISMovieService) reg.lookup("sMovieService");
			searchMap=new HashMap<String, String>();
			
			
			genreCol.setCellValueFactory(new PropertyValueFactory<>("gen_name"));
			movieCol.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
			
			scrCombo.getItems().addAll("영화명","장르명");
			
			setPage();
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void setPage() throws RemoteException {
    	mTable.setItems(FXCollections.observableArrayList(service.viewList3(searchMap)));
    }
}

