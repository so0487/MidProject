package wg.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class U_payResultController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_movie_name;

    @FXML
    private Label lbl_show_time;

    @FXML
    private Label lbl_theater_name;

    @FXML
    private Label lbl_seat_id;

    @FXML
    private Label lbl_pay_time;

    @FXML
    private Label lbl_pay_price;
    
    @FXML
    private Label lbl_pay_met;

    @FXML
    private Button btnMyBookMovie;

    @FXML
    private Button goHome;
    
    @FXML
    private ImageView moviePos;

    
    private G_topController topCtrl;
    public void setTopCtrl(G_topController topCtrl) {
    	this.topCtrl=topCtrl;
    }
    
    private Map<String, String> infoMap;
    public void setInfoMap(Map<String, String> infoMap) {
    	this.infoMap = infoMap;
    }
    
    private List<String> selectedSeats;
    public void setSelectedSeats(List<String> selectedSeats) {
    	this.selectedSeats= selectedSeats;
    }
    public List<String> getSelectedSeats() {
    	return selectedSeats; 
    }

    @FXML
    void GoMyBookMovieClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(G_topController.class.getResource("../fxml/U_info.fxml"));
			StackPane info = loader.load();
			U_infoController infoCtrl = loader.getController();
			infoCtrl.setTopCtrl(topCtrl); 
			
			infoCtrl.getBtnMovieHistory().setStyle(
					"-fx-border-color:#333; -fx-background-color: #333; -fx-border-width:1; -fx-border-radius:5; -fx-text-fill:white;  ");

			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../fxml/U_myBookMovieMain.fxml"));
			Parent bookMovieHis = loader1.load();
			infoCtrl.getOuterBox().setCenter(bookMovieHis);
			
			U_main.root.setCenter(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void goHomeClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(G_loginController.class.getResource("../fxml/G_center.fxml"));
			Parent main= loader.load();
			G_mediaViewController mainCtrl = loader.getController();
			mainCtrl.setTopCtrl(topCtrl);
			
			topCtrl.backToBlack();
			U_main.root.setCenter(main);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
    
    public void setPage(Map<String, String> infoMap) {
    	String dateTime = infoMap.get("selectedDate")+"("+infoMap.get("selectedDay")+") "+infoMap.get("selectedTime");
		String movie = infoMap.get("selectedMovie");
		String pay_met = infoMap.get("pay_met");
		String pos = infoMap.get("moviePos");
		int payPrice = Integer.parseInt(infoMap.get("pay_price"));
		String theater_name = infoMap.get("theater_name");
		int qty = Integer.parseInt(infoMap.get("book_qty"));
		String seats = "";
		for (int i = 0; i < selectedSeats.size(); i++) {
			if(!seats.equals("")) {
				seats +=", ";
			}
			seats += selectedSeats.get(i);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date now = new Date();
		String time = format.format(now);
		
		lbl_movie_name.setText(movie);
		lbl_pay_price.setText(payPrice+"원");
		lbl_show_time.setText(dateTime);
		lbl_seat_id.setText(seats);
		lbl_theater_name.setText(theater_name);
		lbl_pay_time.setText(time);
		lbl_pay_met.setText(pay_met);
		
		try {
			InputStream is = new FileInputStream(pos);
			Image image = new Image(is);
			this.moviePos.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
    }
}
