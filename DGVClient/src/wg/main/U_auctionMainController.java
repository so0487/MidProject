package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import util.AlertUtil;
import wg.auction.service.IAuctionService;
import wg.vo.AuctionVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_auctionMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private VBox vBox;
    
    IAuctionService service;
    

	private Stage mainStage; 
	@FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IAuctionService) reg.lookup("auctionService");
	    	List<AuctionVO> list =service.getAllAuctionList();
	    	int i = 0;
	    	for(AuctionVO vo : list) {
    			FXMLLoader loader1 = new FXMLLoader(U_auctionMainController.class.getResource("../fxml/U_auction.fxml"));
    			Parent center = loader1.load();
    			U_auctionOneController aucOneController = loader1.getController();
    			aucOneController.setVo(vo);
    			aucOneController.setPoster(service.aucPoster(vo.getSseat_no()));
    			vBox.getChildren().add(center);
    			final AuctionVO tt = vo;
    			aucOneController.getBtnEnter().setOnMouseClicked(e->{
    				try {
    					Date date = new Date();
    					String nowDate =(date.getYear()+1900)+("0"+(date.getMonth()+1)+"")+(date.getDate()+"");
    					String minutes = date.getMinutes()+"";
    					if(minutes.length()==1) {
    						minutes = "0"+minutes;
    					}
    					String nowTime = (date.getHours()+"")+minutes+(date.getSeconds()+"");
    					String aucStartDate = tt.getAuc_startTime().substring(0, 4)+tt.getAuc_startTime().substring(5, 7)+tt.getAuc_startTime().substring(8, 10);
    					String aucStartTime = tt.getAuc_startTime().substring(11, 13)+tt.getAuc_startTime().substring(14, 16)+tt.getAuc_startTime().substring(17, 19);
    					String aucEndDate = tt.getAuc_endTime().substring(0, 4)+tt.getAuc_endTime().substring(5, 7)+tt.getAuc_endTime().substring(8, 10);
    					String aucEndTime = tt.getAuc_endTime().substring(11, 13)+tt.getAuc_endTime().substring(14, 16)+tt.getAuc_endTime().substring(17, 19);
    					
//    					
    					
    					
    					if(Integer.parseInt(nowDate)>Integer.parseInt(aucEndDate)) {
    						AlertUtil.warnMsg("작업 오류", "이미 종료된 경매입니다.");
    						return;
    					}else if(Integer.parseInt(nowTime)>Integer.parseInt(aucEndTime)) {
    						AlertUtil.warnMsg("작업 오류", "이미 종료된 경매입니다.");
    						return;
    					}
    					
    					if(Integer.parseInt(nowDate)<Integer.parseInt(aucStartDate)) {
    						AlertUtil.warnMsg("작업 오류", "아직 경매시작 전입니다.");
    						return;
    					}else if(Integer.parseInt(nowTime)<Integer.parseInt(aucStartTime)) {
    						AlertUtil.warnMsg("작업 오류", "아직 경매시작 전입니다.");
    						return;
    					}
    					
    					
    					mainStage = (Stage) vBox.getScene().getWindow();
    					// 새로운 Stage객체 생성
    					Stage dialog = new Stage(StageStyle.DECORATED);
    					U_auctionRoomController.acvo = tt;
    					dialog.initOwner(mainStage);
    					dialog.initModality(Modality.WINDOW_MODAL);
    					FXMLLoader loader = new FXMLLoader(U_auctionMainController.class.getResource("../fxml/U_auctionRoom.fxml"));
    					Parent childRoot = loader.load();
    					U_auctionRoomController ua = loader.getController();
    					ua.setVo(tt);
    					
    					// 자식 창 Stage에 읽어온 Fxml문서 내용을 나타낸다.
    					Scene childScene = new Scene(childRoot);
    					dialog.setScene(childScene);
    					dialog.setTitle("경매방");
    					dialog.show();
    					
    					}catch(IOException e2) {
    						e2.printStackTrace();
    					}
    			});
				i++;
	    	}
	    	
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
