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
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import wg.lnf.service.ILnFService;
import wg.vo.LnFVO;

public class U_lnfMainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane outerBox;
	
	
    @FXML
    private AnchorPane anchor;
    

    @FXML
    private BorderPane LnfBorderPane;

    @FXML
    private VBox vbox;

    @FXML
    private HBox LnfHbox;

	
	
	
    @FXML
    private Pagination LnfPage;

	private ILnFService service;


	private int rowPerPage = 1;
	private int totalRowCount;
	private int totalPageCount;


	private Map<String, String> searchMap;


	private String strWords="";
	private ObservableList<LnFVO> lnfList;

	private LnFVO LnFVo;



	@FXML
	void initialize() {
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

	}



	public void setPagination() throws RemoteException{
		totalRowCount = service.getLnfCount(searchMap);



		totalPageCount = (int)Math.ceil((double)totalRowCount/rowPerPage);


		LnfPage.setPageCount(totalPageCount);

		LnfPage.setCurrentPageIndex(0);


		LnfPage.setMaxPageIndicatorCount(3);


		changeHBox(LnfPage.getCurrentPageIndex());

	}



	public void changeHBox(int index) throws RemoteException{
		int start = index*rowPerPage;
		int end = Math.min(start+rowPerPage,totalRowCount);


		searchMap.put("start", String.valueOf(start));
		searchMap.put("end", String.valueOf(end));

		List<LnFVO> list = service.getAllLnfList(searchMap);
		dataDisplay(list);



	}




	public void dataDisplay(List<LnFVO> list) {
		if(list==null) {
			return;
		}

		LnfHbox.getChildren().clear();




		for(LnFVO lnfVo : list) {




			try {


				FXMLLoader loader1 = new FXMLLoader(U_lnfMain.class.getResource("../fxml/U_lnf.fxml"));
				Parent lnf = loader1.load();
				U_lnfOneController setOneController = loader1.getController();
				setOneController.setLnfVo(lnfVo);

				lnf.setOnMouseClicked(e->{
					try {

						FXMLLoader loader = new FXMLLoader(U_lnfMainController.class.getResource("../fxml/U_lnfDetail.fxml"));
						Parent selectedLnf = loader.load();
						//BorderPane border = null;
						//border.setCenter(selectedLnf);
						LnfBorderPane.setCenter(selectedLnf);
						U_lnfDetailController detailCon = loader.getController();
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
}
