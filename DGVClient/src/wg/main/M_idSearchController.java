package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.AlertUtil;
import wg.member.service.IMemberService;
import wg.vo.MemberVO;

public class M_idSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_searchWord;

    @FXML
    private ComboBox<String> cmb_searchField;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<MemberVO> tb_memInfo;

    @FXML
    private TableColumn<?, ?> col_mem_id;

    @FXML
    private TableColumn<?, ?> col_mem_name;

    @FXML
    private TableColumn<?, ?> col_mem_tel;
    
    private M_issueController mainCtrl;

    public M_issueController getMainCtrl() {
		return mainCtrl;
	}

	public void setMainCtrl(M_issueController mainCtrl) {
		this.mainCtrl = mainCtrl;
	}

	@FXML
    void btnSearchClick(ActionEvent event) {
    	String selectedSearchField = cmb_searchField.getValue();
    	if (selectedSearchField==null) {
			AlertUtil.warnMsg("검색오류",	"검색 항목을 선택하세요");
			return;
		}
    	
    	String searchField = null;
    	switch (selectedSearchField) {
		case "회원ID": searchField = "mem_id"; break;
		case "회원이름":searchField ="mem_name"; break;
		case "휴대폰번호": searchField ="mem_tel"; break;
    	}
    	String searchWord = tf_searchWord.getText();
    	
    	searchMap.put("searchField", searchField);
    	searchMap.put("searchWord", searchWord);
    	
    	List<MemberVO> mData;
    	try {
			mData = service.searchMember(searchMap);
			if(mData.size()==0) {
				AlertUtil.warnMsg("작업결과", "검색결과가 없습니다.");
			}
			mList = FXCollections.observableArrayList(mData);
			tb_memInfo.setItems(mList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void tb_memInfoClick(MouseEvent event) {
    	String mem_id = tb_memInfo.getSelectionModel().getSelectedItem().getMem_id();
    	//부모창으로보내야함
    	mainCtrl.setTf_mem_id(mem_id);
    	
    	//닫기
    	Stage currentStage = (Stage) btnSearch.getScene().getWindow();
    	currentStage.close();
    }
    
    private IMemberService service;
    private Map<String, String> searchMap;
    private ObservableList<MemberVO> mList;
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
    		reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IMemberService) reg.lookup("memberService");
			searchMap = new HashMap<String, String>();
			
			// TableView의 각 컬럼 설정
			col_mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			col_mem_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
			col_mem_tel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
			
			// 검색용 콤보박스 초기값 설정
			cmb_searchField.getItems().addAll("회원ID","회원이름","휴대폰번호");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
}
