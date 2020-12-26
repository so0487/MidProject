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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.xml.namespace.QName;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import wg.lnf.service.ILnFService;
import wg.question.service.IQuestionService;
import wg.vo.AnswerVO;
import wg.vo.LnFVO;
import wg.vo.QuestionVO;

public class U_centerController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private StackPane scroll;

	@FXML
	private AnchorPane anchor;

	@FXML
	private BorderPane QnABorderPane;

	@FXML
	private HBox QnAHbox;

	@FXML
	private Button btnAnswer;

	@FXML
	private Button btnLnfAdd;

	@FXML
	private Button btnFinish;

	@FXML
	private Button btnStart;

	@FXML
	private BorderPane LnfBorderPane;

	@FXML
	private HBox LnfHbox;

	@FXML
	private Pagination LnfPage;

	@FXML
	private Pagination QnAPage;


	@FXML
	void btnAnswerClick(MouseEvent event) {

	}

	@FXML
	void btnLnfAddClick(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(S_centerMain.class.getResource("../fxml/S_lnfDetail.fxml"));
			Parent selectedLnf;
			selectedLnf = loader.load();
			LnfBorderPane.setCenter(selectedLnf);
			S_lnfDetailController detailCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void dataFinish(ActionEvent event) {

	}

	@FXML
	void dataStart(ActionEvent event) {

	}

	private ILnFService service;
	private IQuestionService qnaservice;

	private int rowPerPage = 1;
	private int totalRowCount;
	private int totalPageCount;
	
	
	private int qnatotalRowCount;
	private int qnatotalPageCount;

	private Map<String, String> searchMap;


	private String strWords="";
	private ObservableList<LnFVO> lnfList;

	private LnFVO LnFVo;


	@FXML
	void initialize() throws RemoteException {
		Registry reg = null;


		searchMap = new HashMap<String, String>();

		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ILnFService) reg.lookup("lnfService");


			LnfPage.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					try {
						changeHBox(newValue.intValue());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

			setPagination();
			

		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Registry QnAreg = null;
		
		
		try {
			QnAreg = LocateRegistry.getRegistry("localhost",9988);
			qnaservice = (IQuestionService)QnAreg.lookup("questionService");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

	public void setPagination() throws RemoteException{
		totalRowCount = service.getLnfCount(searchMap);
		qnatotalRowCount = qnaservice.getQuestionCount(searchMap);
		

		totalPageCount = (int)Math.ceil((double)totalRowCount/rowPerPage);
		qnatotalPageCount = (int)Math.ceil((double)totalRowCount/rowPerPage);

		LnfPage.setPageCount(totalPageCount);
		QnAPage.setPageCount(totalPageCount);

		LnfPage.setCurrentPageIndex(0);
		QnAPage.setCurrentPageIndex(0);

		LnfPage.setMaxPageIndicatorCount(3);
		QnAPage.setMaxPageIndicatorCount(rowPerPage);

		changeHBox(LnfPage.getCurrentPageIndex());
		changeHBox(QnAPage.getCurrentPageIndex());
	}






	public void changeHBox(int index) throws RemoteException{
		int start = index*rowPerPage;
		int end = Math.min(start+rowPerPage,totalRowCount);


		searchMap.put("start", String.valueOf(start));
		searchMap.put("end", String.valueOf(end));

		List<LnFVO> list = service.getAllLnfList(searchMap);
		dataDisplay(list);
		
		
		
		List<QuestionVO> questionList = qnaservice.getaAllQuestion(searchMap);
		QnADataDispaly(questionList);
	}


	public void dataDisplay(List<LnFVO> list) {
		if(list==null) {
			return;
		}

		LnfHbox.getChildren().clear();




		for(LnFVO lnfVo : list) {




			try {


				FXMLLoader loader1 = new FXMLLoader(S_centerMain.class.getResource("../fxml/S_lnf.fxml"));
				Parent lnf = loader1.load();
				S_lnfOneController setOneController = loader1.getController();
				setOneController.setLnfVo(lnfVo);

				lnf.setOnMouseClicked(e->{
					try {

						FXMLLoader loader = new FXMLLoader(U_centerController.class.getResource("../fxml/S_lnfDetail.fxml"));
						Parent selectedLnf = loader.load();
						//BorderPane border = null;
						//border.setCenter(selectedLnf);
						LnfBorderPane.setCenter(selectedLnf);
						S_lnfDetailController detailCon = loader.getController();
						detailCon.setVo(lnfVo);




					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});


				LnfHbox.getChildren().add(lnf);





			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		}


	}
	
	
	
	public void QnADataDispaly(List<QuestionVO> questionList) {
		if(questionList==null) {
			return;
		}
		
		
		QnAHbox.getChildren().clear();
		
		for(QuestionVO questionVo : questionList) {
			try {
				FXMLLoader loader = new FXMLLoader(S_centerMain.class.getResource("../fxml/S_QnA.fxml"));
				Parent center = loader.load();
				S_QnAOneController setOneController = loader.getController();
				setOneController.setQuestionVO(questionVo);
				
				QnAHbox.getChildren().add(center);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}











}