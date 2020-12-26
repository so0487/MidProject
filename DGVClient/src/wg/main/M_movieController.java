package wg.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import util.AlertUtil;
import wg.genre.service.IGenreService;
import wg.movie.service.IMovieService;
import wg.vo.GenreVO;
import wg.vo.HintVO;
import wg.vo.MovieVO;
import wg.vo.MovieViewVO;

public class M_movieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_movie_id;

    @FXML
    private TextField tf_movie_name;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCxl;
    
    @FXML
    private Button btnPos;

    @FXML
    private Button btnTra;
    
    @FXML
    private DatePicker movRel;

    @FXML
    private DatePicker movEnd;

    @FXML
    private TextField tf_movie_summary;

    @FXML
    private TextField tf_mobi;
    
    @FXML
    private TextField tf_tra;

    @FXML
    private TextField tf_running;

    @FXML
    private ComboBox<GenreVO> cmb_gen_name;
    
    @FXML
    private ComboBox<String> tf_adult;

    @FXML
    private ImageView imgView;
    
    @FXML
    private TextField tf_dir;

    @FXML
    private TextField tf_actor;

    @FXML
    private TableView<MovieViewVO> tb_movie;

    @FXML
    private TableColumn<?, ?> col_gen_id;

    @FXML
    private TableColumn<?, ?> col_movie_name;

    @FXML
    private TableColumn<?, ?> col_movie_summary;

    @FXML
    private TableColumn<?, ?> col_movie_runningTime;

    @FXML
    private TableColumn<?, ?> col_movie_adult;

    @FXML
    private TableColumn<?, ?> col_movie_release;

    @FXML
    private TableColumn<?, ?> col_movie_end;

    @FXML
    private Pagination pagination;

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<String> scrCombo;

    @FXML
    private TextField scrText;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;
    
    private int rowPerPage = 10;	//한 화면에 보여줄 레코드 수		
    private int totalRowCount;		// 전체 레코드 수	 	
    private int totalPageCount;		// 전체 페이지 수 	
    
    // 검색 데이터와 페이징 처리에 필요한 값을 구성할 Map객체 변수
    private Map<String, String> searchMap;

    @FXML
    void btnAddClick(ActionEvent event) throws RemoteException {
    	tf_movie_id.setText(movService.getMaxMovie_id());
    	
    	tf_movie_name.setEditable(true);
    	tf_movie_summary.setEditable(true);
    	tf_running.setEditable(true);
    	tf_dir.setEditable(true);
    	tf_actor.setEditable(true);
    	
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	
    	cmb_gen_name.setDisable(false);
    	tf_adult.setDisable(false);
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	btnPos.setDisable(false);
    	btnTra.setDisable(false);
    	tb_movie.setDisable(true);
    	movRel.setDisable(false);
    	movEnd.setDisable(false);
    	
    	tf_adult.setValue("청불선택");
    	
    	strWord="add";
    	
    	tf_mobi.clear();
    	tf_actor.clear();
    	tf_dir.clear();
    	tf_movie_name.clear();
    	tf_movie_summary.clear();
    	tf_running.clear();
    	tf_tra.clear();
    	movRel.getEditor().clear();
    	movEnd.getEditor().clear();
    	imgView.setImage(null);
    }
    
    @FXML
    void btnCxlClick(ActionEvent event) {
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	tb_movie.setDisable(false);
    	cmb_gen_name.setDisable(true);
    	tf_adult.setDisable(true);
    	btnPos.setDisable(true);
    	btnTra.setDisable(true);
    	movRel.setDisable(true);
    	movEnd.setDisable(true);
    	cmb_gen_name.setDisable(true);
    	tf_adult.setDisable(true);
    	
    	tf_actor.setEditable(false);
    	tf_dir.setEditable(false);
    	tf_movie_name.setEditable(false);
    	tf_movie_summary.setEditable(false);
    	tf_running.setEditable(false);
    	
    	tb_movie.requestFocus();
    	tf_movie_id.clear();
    	imgView.setImage(null);
    	
    	
    	if(!tb_movie.getSelectionModel().isEmpty()) {
    		MovieViewVO movie = tb_movie.getSelectionModel().getSelectedItem();
    		
    		tf_movie_id.setText(movie.getMovie_id());
        	tf_movie_name.setText(movie.getMovie_name());
        	tf_movie_summary.setText(movie.getMovie_summary());
        	tf_mobi.setText(movie.getMovie_poster());
        	tf_tra.setText(movie.getMovie_trailer());
        	tf_running.setText(String.valueOf(movie.getMovie_runningtime()));
        	tf_adult.setValue(movie.getMovie_adult());
        	tf_dir.setText(movie.getMovie_director());
        	tf_actor.setText(movie.getMovie_actor());
        	String path = movie.getMovie_poster();
        	
        	File file = new File(path);
        	Image image = new Image(file.toURI().toString());
        	
        	imgView.setImage(image);
        	
    	}
    }

    @FXML
    void btnDelClick(ActionEvent event) throws RemoteException {
    	if(tb_movie.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("영화선택", "삭제할 영화를 선택하십시오.");
    		return;
    	}
    	String movieId = tb_movie.getSelectionModel().getSelectedItem().getMovie_id();
    	String movieName = tb_movie.getSelectionModel().getSelectedItem().getMovie_name();
    	
    	int cnt = movService.deleteMovie(movieId);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", movieName+"를(을) 삭제했습니다.");
    		tf_movie_id.clear();
        	tf_mobi.clear();
        	tf_actor.clear();
        	tf_dir.clear();
        	tf_adult.setValue("청불선택");
        	tf_movie_name.clear();
        	tf_movie_summary.clear();
        	tf_running.clear();
        	tf_tra.clear();
        	cmb_gen_name.getItems().clear();
        	movRel.getEditor().clear();
        	movEnd.getEditor().clear();
        	imgView.setImage(null);
        	
        	setPagination();
    	}else {
    		AlertUtil.warnMsg("작업결과", "영화 삭제 실패");
    	}
    	
    	tf_movie_id.clear();
    	tf_mobi.clear();
    	tf_actor.clear();
    	tf_dir.clear();
    	tf_adult.setValue("청불선택");
    	tf_movie_name.clear();
    	tf_movie_summary.clear();
    	tf_running.clear();
    	tf_tra.clear();
    	movRel.getEditor().clear();
    	movEnd.getEditor().clear();
    }

    @FXML
    void btnModClick(ActionEvent event) {
    	if(tb_movie.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("영화선택", "수정할 영화를 선택해주십시오.");
    		return;
    	}
    	btnOk.setDisable(false);
    	btnCxl.setDisable(false);
    	btnPos.setDisable(false);
    	btnTra.setDisable(false);
    	cmb_gen_name.setDisable(false);
    	tf_adult.setDisable(false);
    	movRel.setDisable(false);
    	movEnd.setDisable(false);
    	
    	btnAdd.setDisable(true);
    	btnMod.setDisable(true);
    	btnDel.setDisable(true);
    	tb_movie.setDisable(true);
    	
    	tf_movie_name.setEditable(true);
    	tf_movie_summary.setEditable(true);
    	tf_running.setEditable(true);
    	tf_dir.setEditable(true);
    	tf_actor.setEditable(true);
    	
    	strWord="eddit";
    	
    	
    	
    }
    
    @FXML
    void btnOkClick(ActionEvent event) throws RemoteException {
    	
    	
    	String movId = tf_movie_id.getText();
    	if(cmb_gen_name.getValue() == null) {
    		AlertUtil.warnMsg("입력오류", "장르를 선택해주십시오.");
    		return;
    	}
    	String genId = cmb_gen_name.getValue().getGen_id();
    	String movName = tf_movie_name.getText();
    	String movSumm = tf_movie_summary.getText();
    	String poster = tf_mobi.getText();
    	String tra = tf_tra.getText();
    	if(tf_running.getText().equals("")) {
    		AlertUtil.warnMsg("입력오류", "러닝타임을 입력하십시오.");
    		return;
    	}
    	int running = Integer.parseInt(tf_running.getText());
    	if(tf_adult.getValue().equals("청불선택")) {
    		AlertUtil.warnMsg("입력오류", "청불여부를 선택하십시오.");
    		return;
    	}
    	String adult = tf_adult.getValue();
    	if(movRel.getValue() == null) {
    		AlertUtil.warnMsg("입력오류", "개봉일을 선택하십시오.");
    		return;
    	}
    	if(movEnd.getValue() == null) {
    		AlertUtil.warnMsg("입력오류", "종료일을 선택하십시오.");
    		return;
    	}
    	String rel = movRel.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String end = movEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String dir = tf_dir.getText();
    	String actor = tf_actor.getText();
    	
    	if(movName.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "영화명을 입력하십시오.");
    		return;
    	}
    	if(movSumm.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "영화 줄거리를 입력하십시오.");
    		return;
    	}
    	if(dir.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "감독명을 입력하십시오.");
    		return;
    	}
    	if(actor.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "배우를 입력하십시오.");
    		return;
    	}
    	
    	MovieVO mvo = new MovieVO();
    	mvo.setGen_id(genId);
    	mvo.setMovie_id(movId);
    	mvo.setMovie_name(movName);
    	mvo.setMovie_summary(movSumm);
    	mvo.setMovie_poster(poster);
    	mvo.setMovie_trailer(tra);
    	mvo.setMovie_runningtime(running);
    	mvo.setMovie_adult(adult);
    	mvo.setMovie_release(rel);
    	mvo.setMovie_end(end);
    	mvo.setMovie_director(dir);
    	mvo.setMovie_actor(actor);
    	
    	if("add".equals(strWord)) {
    		int cnt = movService.insertMovie(mvo);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "영화 등록 완료");
    			setPagination();
    		}else {
    			AlertUtil.warnMsg("작업결과", "영화 등록 실패");
    		} 
    	}else if("eddit".equals(strWord)) {
    		String movieId = tb_movie.getSelectionModel().getSelectedItem().getMovie_id();
    		
    		mvo.setMovie_id(movieId);
    		int cnt = movService.updateMovie(mvo);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", movName+" 수정완료");
    			setPagination();
    		}else {
    			AlertUtil.warnMsg("작업결과", "수정실패");
    		}
    	}
    	
    	tf_movie_id.clear();
    	tf_mobi.clear();
    	tf_actor.clear();
    	tf_dir.clear();
    	tf_adult.setValue("청불선택");
    	tf_movie_name.clear();
    	tf_movie_summary.clear();
    	tf_running.clear();
    	tf_tra.clear();
    	movRel.getEditor().clear();
    	movEnd.getEditor().clear();
    	
    	tb_movie.setDisable(false);
    	btnAdd.setDisable(false);
    	btnMod.setDisable(false);
    	btnDel.setDisable(false);
    	tf_dir.setEditable(false);
    	tf_actor.setEditable(false);
    	tf_movie_name.setEditable(false);
    	tf_movie_summary.setEditable(false);
    	tf_running.setEditable(false);
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	cmb_gen_name.setDisable(true);
    	tf_adult.setDisable(true);
    	movRel.setDisable(true);
    	movEnd.setDisable(true);
    	btnPos.setDisable(true);
    	btnTra.setDisable(true);
    }
    
    @FXML
    void btnPosClick(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("포스터 선택");
    	fc.setInitialDirectory(new File("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img"));
    	
    	ExtensionFilter imgType = new ExtensionFilter("img file", "*.jpg","*.gif","*.png");
    	
    	fc.getExtensionFilters().add(imgType);
    	
    	File path = fc.showOpenDialog(null);
    	
    	try {
    		FileInputStream fis = new FileInputStream(path);
    		BufferedInputStream bis = new BufferedInputStream(fis);
    		tf_mobi.setText(path.getAbsolutePath());
    		
    		Image img = new Image(bis);
    		imgView.setImage(img);
    		
    		try {
    			bis.close();
    			fis.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    } 	
    

    @FXML
    void btnTraClick(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("트레일러 선택");
    	fc.setInitialDirectory(new File("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/trailer"));
    	
    	ExtensionFilter traType = new ExtensionFilter("tra file", "*.mp4", "*.wav", "*.wma", "*.mp3");
    	fc.getExtensionFilters().add(traType);
    	
    	File path = fc.showOpenDialog(null);
    	
    	try {
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			tf_tra.setText(path.getAbsolutePath());
			
			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void btnSearchClick(ActionEvent event) throws RemoteException {
    	String selectSearchField = scrCombo.getValue();
    	if(selectSearchField == null) {
    		AlertUtil.warnMsg("검색오류", "검색 항목을 선택하세요");
    		return;
    	}
    	
    	String searchField = null;
    	switch(selectSearchField) {
    	case "영화명": searchField = "movie_name"; break;
    	case "장르명": searchField = "gen_name"; break;
    	}
    	
    	String searchWord = scrText.getText();
    	searchMap.put("searchField", searchField);
    	searchMap.put("searchWord", searchWord);
    	
    	setPagination();
    	
    }
    
    @FXML
    void tableClick(MouseEvent event) {
    	if(tb_movie.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	MovieViewVO movie = tb_movie.getSelectionModel().getSelectedItem();
    	
    	tf_movie_id.setText(movie.getMovie_id());
    	tf_movie_name.setText(movie.getMovie_name());
    	tf_movie_summary.setText(movie.getMovie_summary());
    	tf_mobi.setText(movie.getMovie_poster());
    	tf_tra.setText(movie.getMovie_trailer());
    	tf_running.setText(String.valueOf(movie.getMovie_runningtime()));
    	tf_adult.setValue(movie.getMovie_adult());
    	tf_dir.setText(movie.getMovie_director());
    	tf_actor.setText(movie.getMovie_actor());
    	
    	//수정할부분 8월15일 선택하면 날짜 적용
//    	movRel.setPromptText(movie.getMovie_release());
//    	movEnd.setPromptText(movie.getMovie_end());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDate dateRel = LocalDate.parse(movie.getMovie_release(),formatter);
    	LocalDate dateEnd = LocalDate.parse(movie.getMovie_end(),formatter);
    	movRel.setValue(dateRel);
    	movEnd.setValue(dateEnd);
    	
    	String path = tf_mobi.getText();
    	
    	if(tf_mobi.getText() == null) {
    		path = "c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/NoImage.jpg";
    	}
    	
    	File file = new File(path);
    	Image image = new Image(file.toURI().toString());
    	
    	imgView.setImage(image);
    	
    	
    	for(GenreVO genre : cmb_gen_name.getItems()) {
    		if(genre.getGen_id().equals(movie.getGen_id())) {
    			cmb_gen_name.setValue(genre);
    			
    		}
    	}
    	
    }
    
    private ObservableList<GenreVO> genList;
    private ObservableList<MovieViewVO> movList;
    private IGenreService genService;
    private IMovieService movService;
    private String strWord ="";
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	
    	searchMap = new HashMap<String, String>();
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			genService = (IGenreService) reg.lookup("genreService");
			movService = (IMovieService) reg.lookup("movieService");
			
			col_gen_id.setCellValueFactory(new PropertyValueFactory<>("gen_name"));
			col_movie_name.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
			col_movie_summary.setCellValueFactory(new PropertyValueFactory<>("movie_summary"));
			col_movie_runningTime.setCellValueFactory(new PropertyValueFactory<>("movie_runningtime"));
			col_movie_adult.setCellValueFactory(new PropertyValueFactory<>("movie_adult"));
			col_movie_release.setCellValueFactory(new PropertyValueFactory<>("movie_release"));
			col_movie_end.setCellValueFactory(new PropertyValueFactory<>("movie_end"));
			
			List<GenreVO> gen = genService.getAllGenre();
			genList = FXCollections.observableArrayList(gen);
			
			for (int i = 0; i < genList.size(); i++) {
				cmb_gen_name.getItems().add(gen.get(i));
			}
			
			setPagination();
    		
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	cmb_gen_name.setCellFactory(new Callback<ListView<GenreVO>, ListCell<GenreVO>>() {
			
			@Override
			public ListCell<GenreVO> call(ListView<GenreVO> param) {
				return new ListCell<GenreVO>() {
					@Override
					protected void updateItem(GenreVO item, boolean empty) {
						super.updateItem(item, empty);
						if(item==null || empty) {
							setText(null);
						}else {
							setText(item.getGen_name());
						}
					}
				};
			}
		});
    	cmb_gen_name.setButtonCell(new ListCell<GenreVO>() {
    		@Override
    		protected void updateItem(GenreVO item, boolean empty) {
    			super.updateItem(item, empty);
    			if(item==null || empty) {
					setText(null);
				}else {
					setText(item.getGen_name());
				}
    		}
    	});
    	
    	scrCombo.getItems().addAll("영화명","장르명");
    	tf_adult.getItems().addAll("Y","N");
    	
    	pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
    		@Override
    		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    			try {
					changeTableView(newValue.intValue());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
    			
    		}
		});
    	
    	btnOk.setDisable(true);
    	btnCxl.setDisable(true);
    	btnPos.setDisable(true);
    	btnTra.setDisable(true);
    	movRel.setDisable(true);
    	movEnd.setDisable(true);
    	movRel.setEditable(false);
    	movEnd.setEditable(false);
    	tf_dir.setEditable(false);
    	tf_actor.setEditable(false);
    	
    	
    }
    
    public void setPagination() throws RemoteException {
    	totalRowCount = movService.getMovieCount(searchMap);
    	
    	totalPageCount = (int)Math.ceil((double)totalRowCount/rowPerPage);
    	
    	pagination.setPageCount(totalPageCount);
    	
    	pagination.setCurrentPageIndex(0);
    	
    	pagination.setMaxPageIndicatorCount(10);
    	
    	changeTableView(pagination.getCurrentPageIndex());
    	
    }
    
    public void changeTableView(int index) throws RemoteException {
    	int start = index*rowPerPage;
    	int end = Math.min(start+rowPerPage, totalRowCount);
    	
    	searchMap.put("start", String.valueOf(start));
    	searchMap.put("end", String.valueOf(end));
    	
    	tb_movie.setItems(FXCollections.observableArrayList(movService.listView(searchMap)));
    }
    
    
    
}
