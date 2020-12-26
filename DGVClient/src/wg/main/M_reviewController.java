package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.AlertUtil;
import wg.review.service.IReviewService;
import wg.vo.ReviewViewVO;

public class M_reviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtRevId;

    @FXML
    private TextField txtBidId;

    @FXML
    private TextField txtRevDate;

    @FXML
    private TextField txtbookId;
    @FXML
    private TextField txtMemId;

    @FXML
    private TextField txtRevRa;

    @FXML
    private TextArea txtRevContent;

    @FXML
    private TableView<ReviewViewVO> ReviewTable;

    @FXML
    private TableColumn<?, ?> revIdCol;

    @FXML
    private TableColumn<?, ?> bookIdCol;

    @FXML
    private TableColumn<?, ?> bidIdCol;
    
    @FXML
    private TableColumn<?, ?> revMemIdCol;

    @FXML
    private TableColumn<?, ?> revContentCol;

    @FXML
    private TableColumn<?, ?> revRatCol;

    @FXML
    private TableColumn<?, ?> revDateCol;

    @FXML
    private Button btnDel;
    
    private IReviewService service;


    @FXML
    void dataDel(ActionEvent event) throws RemoteException {
       if(ReviewTable.getSelectionModel().isEmpty()) {
          AlertUtil.warnMsg("선택", "삭제할 리뷰를 선택한 후 사용하세요! ");
          return; 
       }
       
       int revNo = ReviewTable.getSelectionModel().getSelectedItem().getRev_no();
       //String name = ReviewTable.getSelectionModel().getSelectedItem().getmem;
       
       try {
         int cnt = service.deleteReview2(revNo);

         if(cnt>0) {
            AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");


            getAllReview();


            txtRevId.clear();
            txtbookId.clear();
            txtBidId.clear();
            txtRevContent.clear();
            txtRevDate.clear();
            txtMemId.clear();
            txtRevRa.clear();
            
            
//            txtEventId.setDisable(true);
//            txtWriterId.setDisable(true);
//            txtMemId.setDisable(true);
//            txtEventName.setDisable(true);
//            txtEventDate.setDisable(true);



         }else {
            AlertUtil.warnMsg("작업결과", "삭제를 실패했습니다.");
         }
      } catch (RemoteException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
    }
    
    
    
    

    @FXML
    void tableClick(MouseEvent event) {
       if(ReviewTable.getSelectionModel().isEmpty()) {
          return; 
       }
       //선택한 데이터 가져오기 
       ReviewViewVO rvvo = ReviewTable.getSelectionModel().getSelectedItem();
       
       txtRevId.setText(Integer.toString(rvvo.getRev_no()));
       txtbookId.setText(rvvo.getBook_id());
      txtBidId.setText(rvvo.getBid_id());
      txtRevRa.setText(Integer.toString(rvvo.getRev_rating()));
      txtRevContent.setText(rvvo.getRev_content());
      txtRevDate.setText(rvvo.getRev_date());
      txtMemId.setText(rvvo.getMem_id());

    }
    private ObservableList<ReviewViewVO> reviewList;

    @FXML
    void initialize() {
       Registry reg = null;
       
       try {
            reg = LocateRegistry.getRegistry("localhost",9988);
            service = (IReviewService) reg.lookup("reviewService");
            
            //TableView의 컬럼들과 VO를 연결한다.
            revIdCol.setCellValueFactory(new PropertyValueFactory<>("rev_no"));
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));
            bidIdCol.setCellValueFactory(new PropertyValueFactory<>("bid_id"));
            revContentCol.setCellValueFactory(new PropertyValueFactory<>("rev_content"));
            revRatCol.setCellValueFactory(new PropertyValueFactory<>("rev_rating"));
            revDateCol.setCellValueFactory(new PropertyValueFactory<>("rev_date"));
            revMemIdCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
            
            getAllReview();
            
            
         } catch (RemoteException e) {
            e.printStackTrace();
         } catch (NotBoundException e) {
            e.printStackTrace();
         }

    }
   private void getAllReview() throws RemoteException {
      List<ReviewViewVO> reviewData;

      try {
         reviewData = service.getAllReview();
         reviewList = FXCollections.observableArrayList(reviewData);
         ReviewTable.setItems(reviewList);
      } catch (RemoteException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}