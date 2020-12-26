package wg.main;

import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.AlertUtil;
import wg.auction.service.IAuctionService;
import wg.vo.AuctionVO;

public class U_auctionOneController {
	
	private AuctionVO vo;
	

    public AuctionVO getVo() {
		return vo;
	}
    private String poster;
    
    public String getPoster() {
		return poster;
	}
    
    @FXML
    private Button btnEnter;
    
    public Button getBtnEnter() {
    	return btnEnter;
    }


	public void setPoster(String poster) throws RemoteException {
		this.poster = poster;
		
		File file = new File(getPoster());
		if(file==null) {
			AlertUtil.infoMsg("허허", "허허");
		}else {
			Image image = new Image(file.toURI().toString());
//			Image image = new Image(U_auctionOneController.class.getResourceAsStream(getPoster()));
			img.setImage(image );
			
		}
		
	}
	private IAuctionService service;
	public void setVo(AuctionVO vo) throws RemoteException {
		this.vo = vo;
//		File file = new File(service.aucPoster(vo.getSseat_no()));
//		Image image = new Image(file.toURI().toString());
//		Image image = new Image(U_auctionOneController.class.getResourceAsStream(getPoster()));
//    	img.setImage(image );
		title.setText(vo.getAuc_title());
		seat.setText(Integer.toString(vo.getSseat_no()));
		start.setText(vo.getAuc_startTime());
		end.setText(vo.getAuc_endTime());
		
		
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label seat;

    @FXML
    private Label start;

    @FXML
    private Label end;

    @FXML
    private Label title;
    
    @FXML
    private ImageView img;

    @FXML
    void initialize() {
    	Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IAuctionService) reg.lookup("auctionService");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
