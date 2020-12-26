package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import wg.notice.service.INoticeService;
import wg.vo.NoticeVO;

public class S_noticeOneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label_no;

    @FXML
    private Label label_writer;

    @FXML
    private Label label_title;

    @FXML
    private Label label_date;
    
    
	private NoticeVO noticeVo;
	
	public NoticeVO getNoticeVo() {
		return noticeVo;
	}
	
	
	public void setNoticeVo(NoticeVO noticeVo) {
		this.noticeVo = noticeVo;
		label_no.setText(Integer.toString(noticeVo.getNot_no()));
		label_writer.setText(noticeVo.getNot_writer());
		label_title.setText(noticeVo.getNot_title());
		label_date.setText(noticeVo.getNot_time());
		
	}
    
    
    
    
    INoticeService service;
    
    
    
    
    
    
    
    
    
    

    @FXML
    void click(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(S_noticeOneController.class.getResource("../fxml/S_NoticeDetail.fxml"));
    	Pane root;
    	
    	try {
			root = loader.load();
			Scene scene = new Scene(root);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    
    

    @FXML
    void initialize() throws RemoteException {
    	
    }
}
