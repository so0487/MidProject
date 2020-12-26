package wg.main;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnchorPaneNode extends AnchorPane{
	
	// Date associated with this pane
		// 이 창과 관련된 날짜입니다.
	   	private LocalDate date;
	    
	    private Stage mainStage;

	    /**
	     * Create a anchor pane node. Date is not assigned in the constructor.
	     * @param children children of the anchor pane
	     */
	    public AnchorPaneNode(Node... children) {
	        super(children);
	        // Add action handler for mouse clicked
	        this.setOnMouseClicked(e ->{
	        	
	        	System.out.println("This pane's date is: " + date);
	        	//스케줄 모달창 띄우기
	        	try {
					mainStage = (Stage) this.getScene().getWindow();
					
					Stage secStage = new Stage(StageStyle.UTILITY);
					secStage.initModality(Modality.WINDOW_MODAL);
					secStage.initOwner(mainStage);
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/M_mscheduleReg.fxml"));
					Parent childRoot = loader.load();
					M_mscheduleRegController mscheduleRegController = loader.getController();
					mscheduleRegController.setDate(date);
					Scene childScene = new Scene(childRoot);
					secStage.setScene(childScene);
					secStage.setTitle("영화스케줄 등록");
					secStage.show();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	        });
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

}
