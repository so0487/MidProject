package wg.main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import util.AlertUtil;
import wg.vo.EventVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_eventBigController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView eventImg;
    
    @FXML
    private Label txtDate;
    
    @FXML
    private Label txtName;
    
    @FXML
    private Label txtMemId;
    
    @FXML
    void click(MouseEvent event) {

    }
    

    private EventVO vo;
    
    public EventVO getVo() {
		return vo;
	}


	public void setVo(EventVO vo) {
		this.vo = vo;
		File file = new File(vo.getEvent_photo());
		Image image = new Image(file.toURI().toString());
		
		//Image image = new Image(U_eventOneController.class.getResourceAsStream("../img/"+vo.getEvent_photo()));
    	eventImg.setImage(image );
    	txtDate.setText(vo.getEvent_time());
    	txtName.setText(vo.getEvent_title());
    	txtMemId.setText(vo.getMem_id());
    	System.out.println(vo.getEvent_photo());
	}


	@FXML
    void initialize() {
		
    }
}
