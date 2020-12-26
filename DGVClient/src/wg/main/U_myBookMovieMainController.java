package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import wg.book_detail.service.IBook_DetailService;
import wg.bookmovie.service.IBookMovieService;
import wg.pay.service.IPayService;
import wg.review.service.IReviewService;
import wg.seatSch.service.ISeatSchService;
import wg.snackSet.service.ISnackSetService;
import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;
import wg.vo.LoginVO;
import wg.vo.MemberVO;
import wg.vo.ReviewVO;
import wg.vo.SelectSnackSetVO;
import wg.vo.SnackSetVO;

public class U_myBookMovieMainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane outerBox;

	@FXML
	private VBox vbox;

	IBookMovieService service;
	IPayService payService;
	ISeatSchService seatSchService;
	IBook_DetailService book_detailService;
	IReviewService rService;

	@FXML
	void initialize() {
		Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry("localhost", 9988);
			service = (IBookMovieService) reg.lookup("bookMovieService");
			rService = (IReviewService) reg.lookup("reviewService");
			setBookMovieList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public void setBookMovieList() {
		try {
			vbox.getChildren().clear();
			MemberVO vo = LoginVO.getCurrVo(); // 로그인 정보를 불러와야한다.
			List<BookMovieViewVO> list = service.getAllBookMovieView(vo.getMem_id());
			int i = 0;
			VBox vb = null;
			for (BookMovieViewVO bmvVo : list) {
				if (i % 3 == 0) {
					vb = new VBox();
				}
				FXMLLoader loader1 = new FXMLLoader(
						U_myBookMovieMainController.class.getResource("../fxml/U_myBookMovie.fxml"));
				Parent center = loader1.load();
				U_myBookMovieOneController myOneController = loader1.getController();
				myOneController.setBookMovieViewVo(bmvVo);
				myOneController.setParentCtrl(this);
				
				// 환불된 예매건 인 경우
				if (bmvVo.getPay_refund().equals("Y")) {
					myOneController.setVisible();
				}
				// 리뷰를 작성한 경우
				String book_id = bmvVo.getBook_id();
				ReviewVO rvo = rService.getThisReview(book_id);
				if(rvo!=null) {
					myOneController.hasReview();
					myOneController.setRvo(rvo);
				}
				final BookMovieViewVO bmv = bmvVo;
				vb.getChildren().add(center);
				vbox.getChildren().add(vb);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}