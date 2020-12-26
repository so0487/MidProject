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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import wg.bookMemberCnt.service.IBookMemberCntService;
import wg.review.service.IReviewService;
import wg.vo.BookMemberCntVO;
import wg.vo.MovieVO;
import wg.vo.ReviewViewVO2;

public class U_movieInfoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label movName;

	@FXML
	private ImageView movPos;

	@FXML
	private Button btnBook;

	@FXML
	private Label movDir;

	@FXML
	private Label movActor;

	@FXML
	private Label movLevle;

	@FXML
	private Label movRun;

	@FXML
	private Label movRel;

	@FXML
	private MediaView movTra;

	@FXML
	private TextArea movSummary;

	@FXML
	private VBox reviewBox;

	@FXML
	private PieChart pieChart;

	@FXML
	private BarChart<String, Number> barChart;

	@FXML
	void btnBookClick(ActionEvent event) {

	}


	private MovieVO vo;

	public MovieVO getVo() {
		return vo;
	}

	public void setVo(MovieVO vo) {
		this.vo = vo;

		movName.setText(vo.getMovie_name());
		movSummary.setText(vo.getMovie_summary());
		movSummary.setEditable(false);
		movDir.setText(vo.getMovie_director());
		movActor.setText(vo.getMovie_actor());
		if(vo.getMovie_adult().equals("N")) {
			movLevle.setText("청소년관람가능");
		}else {
			movLevle.setText("청소년관람불가");
		}
		movRun.setText(vo.getMovie_runningtime()+"분");
		movRel.setText(vo.getMovie_release().substring(0, 11));

		String movie_id=vo.getMovie_id();

		File file = new File(vo.getMovie_poster());
		Image iamge = new Image(file.toURI().toString());
		movPos.setImage(iamge);
		File file2 = new File(vo.getMovie_trailer());
		Media media = new Media(file2.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		movTra.setMediaPlayer(mediaPlayer);

		mediaPlayer.setOnReady(new Runnable() {		
			@Override
			public void run() {
				mediaPlayer.setAutoPlay(true);

			}
		});

		try {
			List<ReviewViewVO2> rvvList = rService.getReviewView(vo.getMovie_id());
			if(rvvList.size()>0) {
				reviewBox.getChildren().clear();
				for (int i = 0; i < rvvList.size(); i++) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/U_review.fxml"));
					Parent review = loader.load();
					U_reviewController rCtrl = loader.getController();
					rCtrl.setReview(rvvList.get(i));
					reviewBox.getChildren().add(review);
				}
			}


		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}







		//chart
		try {
			int maleCnt;
			maleCnt = service.getMaleCnt(movie_id);
			int femaleCnt = service.getFemaleCnt(movie_id);
			//pie차트
			pieChart.setData(FXCollections.observableArrayList(
					new PieChart.Data("남자 ("+ maleCnt + "명)",maleCnt),
					new PieChart.Data("여자("+femaleCnt+"명)", femaleCnt)
					));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//barChart
		XYChart.Series<String, Number> age10 = new XYChart.Series<String, Number>();
		age10.setName("10대");
		XYChart.Series<String, Number> age20 = new XYChart.Series<String, Number>();
		age20.setName("20대");
		XYChart.Series<String, Number> age30 = new XYChart.Series<String, Number>();
		age30.setName("30대");
		XYChart.Series<String, Number> age40 = new XYChart.Series<String, Number>();
		age40.setName("40대");

		try {
			age10.getData().add(new Data<String, Number>("10대", service.getAgeCount10(movie_id)));
			age20.getData().add(new Data<String, Number>("20대", service.getAgeCount20(movie_id)));
			age30.getData().add(new Data<String, Number>("30대", service.getAgeCount30(movie_id)));
			age40.getData().add(new Data<String, Number>("40대", service.getAgeCount40(movie_id)));

			System.out.println(service.getAgeCount20(movie_id));

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		barChart.getData().addAll(age10,age20,age30,age40);
	}



	private IBookMemberCntService service;
	private ObservableList<BookMemberCntVO> bookMemCnt;
	private IReviewService rService;

	MovieVO movieVo;
	@FXML
	void initialize() {
		Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IBookMemberCntService) reg.lookup("bookmemberCntService");
			rService = (IReviewService) reg.lookup("reviewService");
		}
		catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}

