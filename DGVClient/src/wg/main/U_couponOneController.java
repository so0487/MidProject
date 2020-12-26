package wg.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import wg.bookmovie.service.IBookMovieService;
import wg.coupon.service.ICouponService;
import wg.movie.service.IMovieService;
import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;
import wg.vo.CouponUserViewVO;

public class U_couponOneController {
	private CouponUserViewVO couponUserViewVo;

    public CouponUserViewVO getCouponUserViewVo() {
		return couponUserViewVo;
	}


	public void setCouponUserViewVo(CouponUserViewVO couponUserViewVo) {
		this.couponUserViewVo = couponUserViewVo;
		
    	lblCouponName.setText(couponUserViewVo.getCou_name());
    	lblCouponPrice.setText(String.valueOf(couponUserViewVo.getCou_sale()));
    	lblCouponDate.setText(couponUserViewVo.getCou_time());
    	lblCouId.setText(couponUserViewVo.getCou_id());
    	lblIssueTime.setText(couponUserViewVo.getIssue_time());
	}

	ICouponService service;
	
	 private Stage mainStage;
	
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label lblCouponName;

	    @FXML
	    private Label lblCouponPrice;

	    @FXML
	    private Label lblCouponDate;

	    @FXML
	    private Label lblIssueTime;

	    @FXML
	    private Label lblCouId;
    
    @FXML
    void initialize() throws RemoteException {
    	
    }
    
}
