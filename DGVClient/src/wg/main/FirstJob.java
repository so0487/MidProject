package wg.main;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.AlertUtil;
import wg.auction.service.IAuctionService;
import wg.genre.service.IGenreService;
import wg.vo.BidVO;
import wg.vo.LoginVO;

public class FirstJob implements Job  {
	public static U_auctionRoomController ua = new U_auctionRoomController();

	public static U_auctionRoomController getUa() {
		return ua;
	}

	public static void setUa(U_auctionRoomController ua) {
		FirstJob.ua = ua;
	}
	
	
	IAuctionService service;
	@Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		Platform.runLater(() -> {
			AlertUtil.infoMsg("마감", "마감");
			ua.out();
			Registry reg = null;
	    	try {
				//reg = LocateRegistry.getRegistry("192.168.31.32",9988);
	    		reg = LocateRegistry.getRegistry("localhost",9988);
				service = (IAuctionService) reg.lookup("auctionService");
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
	 
			if(LoginVO.currVo.getMem_id().equals(ua.getMemid().getText())) {
				try {
					FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_payBid.fxml"));
					AnchorPane center = loader.load();
					U_main.root.setCenter(center);
					U_payBidController up = loader.getController();
					up.setLbl_pay_price(ua.getLbPrice().getText()+"원");
					up.setLbl_final_price(ua.getLbPrice().getText()+"원");
					BidVO vo = new BidVO();
					vo.setAuc_no(ua.acvo.getAuc_no());
					vo.setBid_id("b001");
					vo.setBid_price(Integer.parseInt(ua.getLbPrice().getText()));
					vo.setBid_time(ua.acvo.getAuc_endTime());
					U_payBidController.bidvo = vo;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_auctionMain.fxml"));
					StackPane center = loader.load();
					U_main.root.setCenter(center);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
        });
		
		
        
    }


}	