package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import wg.notice.service.INoticeService;
import wg.vo.MemberVO;
import wg.vo.NoticeVO;

public class U_noticeDetailConroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtField_title;

    @FXML
    private Button btnList;

    @FXML
    private TextField txtField_date;

    @FXML
    private TextField txtField_writer;

    @FXML
    private TextField txtField_no;

    @FXML
    private TextArea txtArea;

    @FXML
    void btnListClick(MouseEvent event) {
    	getAllNoticeList();
    }
    
    
    private NoticeVO vo;

	public NoticeVO getVo() {
		return vo;
	}


	public void setVo(NoticeVO vo) {
		this.vo = vo;

		txtField_no.setText(String.valueOf(vo.getNot_no()));
		txtField_writer.setText(vo.getNot_writer());
		txtField_title.setText(vo.getNot_title());
		txtArea.setText(vo.getNot_content());
		txtField_date.setText(vo.getNot_time());
	}


	private String strWords="";
	private INoticeService service;
	private ObservableList<NoticeVO> noticeList;
	MemberVO member = new MemberVO();

	private Map<String, String> searchMap;

    @FXML
    void initialize() {
    	Registry reg = null;


		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (INoticeService) reg.lookup("noticeService");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    public void getAllNoticeList() {
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_NoticeMain.fxml"));
			Parent center = loader.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
    
}
