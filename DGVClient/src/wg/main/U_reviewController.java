package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import wg.review.service.IReviewService;
import wg.vo.ReviewViewVO2;

public class U_reviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_mem_name;

    @FXML
    private Label lbl_rev_cont;

    @FXML
    private Label lbl_rev_date;

    @FXML
    private HBox starHbox;

    @FXML
    private ImageView w0;

    @FXML
    private ImageView w1;

    @FXML
    private ImageView w2;

    @FXML
    private ImageView w3;

    @FXML
    private ImageView w4;

    @FXML
    private ImageView w5;

    @FXML
    private ImageView w6;

    @FXML
    private ImageView w7;

    @FXML
    private ImageView w8;

    @FXML
    private ImageView w9;

    @FXML
    private ImageView y0;

    @FXML
    private ImageView y2;

    @FXML
    private ImageView y4;

    @FXML
    private ImageView y6;

    @FXML
    private ImageView y8;

    @FXML
    private ImageView y1;

    @FXML
    private ImageView y3;

    @FXML
    private ImageView y5;

    @FXML
    private ImageView y7;

    @FXML
    private ImageView y9;
    
    List<ImageView> wList;
    List<ImageView> yList;

    private IReviewService service;
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	
    	try {
            reg = LocateRegistry.getRegistry("localhost",9988);
            service = (IReviewService) reg.lookup("reviewService");
            
         } catch (RemoteException e) {
            e.printStackTrace();
         } catch (NotBoundException e) {
            e.printStackTrace();
         }
    	
    	wList = new ArrayList<ImageView>();
    	
    	wList.add(w0);
    	wList.add(w1);
    	wList.add(w2);
    	wList.add(w3);
    	wList.add(w4);
    	wList.add(w5);
    	wList.add(w6);
    	wList.add(w7);
    	wList.add(w8);
    	wList.add(w9);
    	
    	yList = new ArrayList<ImageView>();
    	
    	yList.add(y0);
    	yList.add(y1);
    	yList.add(y2);
    	yList.add(y3);
    	yList.add(y4);
    	yList.add(y5);
    	yList.add(y6);
    	yList.add(y7);
    	yList.add(y8);
    	yList.add(y9);
    	
    	for (int i = 0; i < wList.size(); i++) {
    		String text = wList.get(i).getId();
    		int score = Integer.parseInt(text.substring(1));
			wList.get(i).setOnMouseClicked(e->{
				setYellow(score);
			});
			yList.get(i).setOnMouseClicked(e->{
				setYellow(score);
			});
			
		}
    }
    
    public void setAllWhite() {
    	for (int i = 0; i < wList.size(); i++) {
    		// 노란별은 안보이게
    		yList.get(i).setVisible(false);
    		// 흰별은 보이게
			wList.get(i).setVisible(true);
		}
    }
    
    public void setYellow(int score) {
    	setAllWhite();
    	for (int i = 0; i <= score; i++) {
    		wList.get(i).setVisible(false);
			yList.get(i).setVisible(true);
		}
    	for (int i = score+1; i < wList.size(); i++) {
			wList.get(i).setVisible(false);
		}
    }
    
    public void setReview(ReviewViewVO2 rvvo2) {
    	lbl_mem_name.setText(rvvo2.getMem_name());
    	lbl_rev_cont.setText(rvvo2.getRev_content());
    	lbl_rev_date.setText(rvvo2.getRev_date());
    	int score = (int) (rvvo2.getRev_rating()/0.5)-1;
    	setYellow(score);
    	for (int i = 0; i < wList.size(); i++) {
			wList.get(i).setDisable(true);
			yList.get(i).setDisable(true);
		}
    }
}


