package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import wg.notice.service.INoticeService;
import wg.vo.NoticeVO;

public class S_noticeMainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane outerbox;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Label label_no;

	@FXML
	private Label label_date;

	@FXML
	private Label label_title;

	@FXML
	private Label label_writer;

	@FXML
	private VBox main_vbox;

	@FXML
	private Pagination pagination;

	@FXML
	private Button btnadd;

	@FXML
	void btnaddClick(MouseEvent event) {
		//입력화면 실행

		try {

			FXMLLoader loader = new FXMLLoader(S_noticeMainController.class.getResource("../fxml/S_NoticeDetail.fxml"));
			Parent seletedNotice = loader.load();
			outerbox.setCenter(seletedNotice);
			S_noticeDetailConroller detailCon = loader.getController();
			//detailCon.setVo(noticeVo);

			
			
			detailCon.btnAddClick(event);	
			
			
			


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}



	private INoticeService service;

	private int rowPerPage = 10;
	private int totalRowCount;
	private int totalPageCount;


	private Map<String, String> searchMap;


	private String strWords = "";
	private ObservableList<NoticeVO> noticeList;

	private NoticeVO noticeVo;



	@FXML
	void initialize() throws RemoteException {

		Registry reg = null;

		searchMap = new HashMap<String, String>();

		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (INoticeService) reg.lookup("noticeService");
			//List<NoticeVO> list = service.getAllNoticeList(searchMap);

			//페이징 처리
			pagination.currentPageIndexProperty().addListener(	
					new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
							try {
								changeVBox(newValue.intValue());
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
					);

			setPagination();


		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public void setPagination() throws RemoteException {
		totalRowCount = service.getNoticeCount(searchMap);


		totalPageCount = (int)Math.ceil((double)totalRowCount/rowPerPage);

		pagination.setPageCount(totalPageCount);

		pagination.setCurrentPageIndex(0);

		pagination.setMaxPageIndicatorCount(10);

		changeVBox(pagination.getCurrentPageIndex());
	}





	public void changeVBox(int index) throws RemoteException {
		int start = index*rowPerPage;
		int end = Math.min(start+rowPerPage,totalRowCount);


		searchMap.put("start", String.valueOf(start));
		searchMap.put("end", String.valueOf(end));

		List<NoticeVO> list = service.getAllNoticeList(searchMap);
		dataDisplay(list);
	}


	public void dataDisplay(List<NoticeVO> list) {
		if(list==null) {
			return;
		}
		main_vbox.getChildren().clear();


		for(NoticeVO noticeVo : list) {
			try {
				FXMLLoader loader1 = new FXMLLoader(M_main.class.getResource("../fxml/S_Notice.fxml"));
				Parent center = loader1.load();
				S_noticeOneController setOneController = loader1.getController();
				setOneController.setNoticeVo(noticeVo);

				center.setOnMouseClicked(e->{
					try {

						FXMLLoader loader = new FXMLLoader(S_noticeMainController.class.getResource("../fxml/S_NoticeDetail.fxml"));
						Parent seletedNotice = loader.load();
						outerbox.setCenter(seletedNotice);
						S_noticeDetailConroller detailCon = loader.getController();
						detailCon.setVo(noticeVo);


					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				});


				main_vbox.getChildren().add(center);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}



	}

}
