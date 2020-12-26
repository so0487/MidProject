package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.AlertUtil;
import wg.review.service.IReviewService;
import wg.vo.BookMovieViewVO;
import wg.vo.ReviewVO;
import wg.vo.ReviewViewVO;

public class U_reviewController2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    @FXML
    private Label lblBookId;

    @FXML
    private Button btnOk;
    
    @FXML
    private Button btnCxl;

    @FXML
    private TextArea txtRevContent;
    
    @FXML
    private Label lbl_score;
    
    @FXML
    private Label lbl_movie_name;
    
    List<ImageView> wList;
    List<ImageView> yList;
    
    private BookMovieViewVO bookMovieViewVo;
    
    private IReviewService service;

    public BookMovieViewVO getBookMovieViewVo() {
      return bookMovieViewVo;
   }
    
    private U_myBookMovieOneController mainCtrl;
    public void setMainCtrl(U_myBookMovieOneController mainCtrl) {
       this.mainCtrl = mainCtrl;
    }


   public void setBookMovieViewVo(BookMovieViewVO bookMovieViewVo) {
      this.bookMovieViewVo = bookMovieViewVo;
      lbl_movie_name.setText(bookMovieViewVo.getMovie_name());
      lblBookId.setText(bookMovieViewVo.getBook_id());
   }
   
    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;
    
    private String func = "";
    
    @FXML
    void btnDelClick(ActionEvent event) {
       // 삭제 메서드 호출
       try {
          String book_id = lblBookId.getText();
          int cnt = service.deleteReview(book_id);
          if(cnt>0) {
             AlertUtil.infoMsg("작업결과", "리뷰삭제완료");
             // 창 닫기
             Stage currStage = (Stage) lbl_score.getScene().getWindow();
             currStage.close();
              // 배경화면 리셋
              mainCtrl.getParentCtrl().setBookMovieList();
          }else {
             AlertUtil.warnMsg("작업결과", "리뷰삭제실패");
          }
      } catch (RemoteException e) {
         e.printStackTrace();
      }
    }

    @FXML
    void btnModClick(ActionEvent event) {
       func = "mod";
       // 리뷰 내용 수정가능상태로 만들기
       txtRevContent.setEditable(true);
       //별점 수정가능상태로 만들기
       for (int i = 0; i < wList.size(); i++) {
         wList.get(i).setDisable(false);
         yList.get(i).setDisable(false);
      }
       btnOk.setDisable(false);
       btnCxl.setDisable(false);
    }

    @FXML
    void btnCxlClick(ActionEvent event) {
       if(func.equals("mod")) {
          btnCxl.setVisible(false);
          btnOk.setVisible(false);
       }else {
          // 창 닫기
          Stage currStage = (Stage) lbl_score.getScene().getWindow();
          currStage.close();
       }
    }
    
    // 확인버튼 클릭 시
    @FXML
    void dataRun(ActionEvent event) throws RemoteException {
       if(txtRevContent.getText().isEmpty()) {
          AlertUtil.warnMsg("입력오류", "리뷰 내용은 필수사항입력 사항입니다.");
          return;
       }
       if(lbl_score.getText().isEmpty()) {
          AlertUtil.warnMsg("입력오류", "리뷰 별점을 선택해주십시오.");
          return;
       }
       String rev_content = txtRevContent.getText().trim();
       double rev_rating = Double.parseDouble(lbl_score.getText());
       String movie_name = lbl_movie_name.getText(); 
       String book_id = lblBookId.getText();
       
       ReviewVO rvo = new ReviewVO();
       rvo.setRev_content(rev_content);
       rvo.setRev_rating(rev_rating);
       rvo.setBook_id(book_id);
       if(func.equals("mod")) {
          // 수정하려고 확인버튼을 누를 때 
          // 확인, 취소버튼 활성화
          btnCxl.setDisable(false);
          btnOk.setDisable(false);
          int cnt = service.updateReview(rvo);
          if(cnt>0) {
             AlertUtil.infoMsg("작업결과", "리뷰수정완료");
             txtRevContent.setEditable(false);
             btnMod.setVisible(false);
             btnDel.setVisible(false);
             btnOk.setDisable(true);
             btnCxl.setDisable(true);
             func="";
          }else {
             AlertUtil.warnMsg("작업결과", "리뷰수정실패");
          }
       }else {
          if(rev_content.isEmpty()) {
             AlertUtil.warnMsg("입력오류", "리뷰내용을 입력해주세요 ");
             txtRevContent.requestFocus();
             return;
          }
          //VO값을 DB에 저장 
          int cnt = service.insertReview(rvo);
          
          if(cnt>0) {
             AlertUtil.infoMsg("작업결과", "리뷰작성완료");
             // 창 닫기
             Stage currStage = (Stage) lbl_score.getScene().getWindow();
             currStage.close();
              // 배경화면 리셋
              mainCtrl.getParentCtrl().setBookMovieList();
             
          }else {
             AlertUtil.warnMsg("작업결과", "추가실패..");
          }
       }
    }

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
            float point = (score+1)*0.5f;
            lbl_score.setText(point+"");
         });
         yList.get(i).setOnMouseClicked(e->{
            setYellow(score);
            float point = (score+1)*0.5f;
            lbl_score.setText(point+"");
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
         wList.get(i).setVisible(true);
      }
    }
    
    
    public void setMovieName(String movie_name) {
       this.lbl_movie_name.setText(movie_name);
    }
    
    public void setReview(String cont, double score) {
       // 내용 설정
       txtRevContent.setText(cont);
       txtRevContent.setEditable(false);
       
       // 평점 설정
       lbl_score.setText(score+"");
       
       // 별점 설정
       int rating = (int) (score/0.5)-1;
       setYellow(rating);
       for (int i = 0; i < wList.size(); i++) {
         wList.get(i).setDisable(true);
         yList.get(i).setDisable(true);
      }
       // 수정, 삭제버튼 보이기
       btnDel.setVisible(true);
       btnMod.setVisible(true);
       
       // 확인, 취소버튼 비활성화
       btnOk.setDisable(true);
       btnCxl.setDisable(true);
    }
}