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
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.AlertUtil;
import wg.snackSet.service.ISnackSetService;
import wg.vo.SnackSetVO;

public class M_snackSetController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDel;

	@FXML
	private Label lbl_id;

	@FXML
	private TextField txtfield_id;

	@FXML
	private TextField txtfield_name;

	@FXML
	private Label lbl_name;

	@FXML
	private Label lbl_price;

	@FXML
	private TextField txtfield_price;

	@FXML
	private Button btnOK;

	@FXML
	private Button btnCancel;

	@FXML
	private ImageView imgView;

	@FXML
	private TextField txtfield_img;

	@FXML
	private Button btnImg;

	@FXML
	private Label lbl_img;

	@FXML
	private TableView<SnackSetVO> tableView;

	@FXML
	private TableColumn<?, ?> idCol;

	@FXML
	private TableColumn<?, ?> nameCol;

	@FXML
	private TableColumn<?, ?> priceCol;

	@FXML
	void btnAddClick(ActionEvent event) {
		String set_id = "";
		try {
			set_id = service.getMaxSetId();
			txtfield_id.setText(set_id);
			txtfield_name.clear();
			txtfield_name.setDisable(false);
			txtfield_name.requestFocus();
			txtfield_price.setDisable(false);
			txtfield_price.clear();
			txtfield_img.setDisable(false);
			txtfield_img.clear();



			txtfield_img.setDisable(true);
			btnOK.setDisable(false);
			btnCancel.setDisable(false);
			strWords="add";


			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			btnDel.setDisable(true);
			btnImg.setDisable(false);

		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
	}

	@FXML
	void btnDelClick(ActionEvent event) {
		if(tableView.getSelectionModel().isEmpty()) {
			AlertUtil.warnMsg("스낵바 세트 선택", "삭제할 스낵바 세트 메뉴를 선택하세요");
			return;
		}


		String set_id  = tableView.getSelectionModel().getSelectedItem().getSet_id();

		try {
			int cnt = service.deleteSnackSet(set_id);

			if(cnt>0) {
				AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");


				getAllSnackSetList();


				txtfield_id.clear();
				txtfield_name.clear();
				txtfield_price.clear();
				txtfield_img.clear();



				txtfield_name.setDisable(true);
				txtfield_price.setDisable(true);
				txtfield_img.setDisable(true);


				btnOK.setDisable(true);
				btnCancel.setDisable(true);

			}else {
				AlertUtil.warnMsg("작업결과", "삭제를 실패했습니다.");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void btnEditClick(ActionEvent event) {
		if(tableView.getSelectionModel().isEmpty()) {
			AlertUtil.warnMsg("장르선택", "수정할 장르를 선택하세요");
			return;
		}



		String set_id  = tableView.getSelectionModel().getSelectedItem().getSet_id();

		txtfield_name.setDisable(false);
		txtfield_price.setDisable(false);
		txtfield_img.setDisable(false);



		btnAdd.setDisable(true);
		btnEdit.setDisable(true);
		btnDel.setDisable(true);

		btnImg.setDisable(false);
		btnOK.setDisable(false);
		btnCancel.setDisable(false);


		strWords="edit";
	}

	@FXML
	void btnImgClick(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("이미지 선택");
		fc.setInitialDirectory(new File("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img"));

		ExtensionFilter imgType = new ExtensionFilter("img file", "*.jpg","*.gif","*.png");

		fc.getExtensionFilters().add(imgType);

		File path = fc.showOpenDialog(null);

		//System.out.println(path);



		try {
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			txtfield_img.setText(path.getAbsolutePath());


			Image img = new Image(bis);
			imgView.setImage(img);




			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void dataCancelClick(ActionEvent event) {
		txtfield_name.setDisable(true);
		txtfield_price.setDisable(true);
		txtfield_img.setDisable(true);


		btnAdd.setDisable(false);
		btnEdit.setDisable(false);
		btnDel.setDisable(false);

		btnImg.setDisable(true);
		btnOK.setDisable(true);
		btnCancel.setDisable(true);


		if(!tableView.getSelectionModel().isEmpty()) {
			SnackSetVO snacksetvo = tableView.getSelectionModel().getSelectedItem();


			txtfield_id.setText(snacksetvo.getSet_id());
			txtfield_name.setText(snacksetvo.getSet_name());
			txtfield_price.setText(Integer.toString(snacksetvo.getSet_price()));
			txtfield_img.setText(snacksetvo.getSet_photo());
		}




		strWords="";

	}

	@FXML
	void dataOKClick(ActionEvent event)  {
		
		//가격정규식
		String pricePattern = "[0-9]";
		
		
		
		
		
		String set_id = txtfield_id.getText();
		String set_name = txtfield_name.getText();
		String price = txtfield_price.getText();
		String img_path = txtfield_img.getText();
		Matcher priceMatcher =Pattern.compile(pricePattern).matcher(price);
		


		try {
			if(set_name.isEmpty()) {
				AlertUtil.warnMsg("입력오류", "스낵바 세트 명을 입력하세요");
				return;
			}else if(price.isEmpty()) {
				AlertUtil.warnMsg("입력오류", "스낵바 세트 가격을 입력하세요");
				return;
			}else if(img_path.isEmpty()) {
				AlertUtil.warnMsg("입력오류", "스낵바 세트 경로를 입력하세요");
				return;
			}else if(Integer.parseInt(price)<0) {
				AlertUtil.warnMsg("입력오류", "정확한 금액을 입력하세요");
				return;
			}
			
		}catch (NumberFormatException e) {
			AlertUtil.warnMsg("입력오류", "정확한 금액을 입력하세요");
			return;
		}

		SnackSetVO snacksetvo = new SnackSetVO();
		snacksetvo.setSet_id(set_id);
		snacksetvo.setSet_name(set_name);
		snacksetvo.setSet_price(Integer.parseInt(price));
		snacksetvo.setSet_photo(img_path);

		if("add".equals(strWords)) {
			try {
				int cnt = service.insertSnackSet(snacksetvo);

				if(cnt>0) {

					AlertUtil.infoMsg("작업결과", "스낵세트 추가 완료");

				}

				else {

					AlertUtil.infoMsg("작업결과", "스낵세트 추가 실패");
				}

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("edit".equals(strWords)) {

			try {

				int cnt = service.updateSnackSet(snacksetvo);

				if(cnt>0) {

					AlertUtil.infoMsg("작업결과", "스낵 세트 수정 완료");
				}else {
					AlertUtil.infoMsg("작업결과", "스낵세트 수정 실패");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		getAllSnackSetList();

		txtfield_id.clear();
		txtfield_name.clear();
		txtfield_price.clear();
		txtfield_img.clear();


		txtfield_name.setDisable(true);
		txtfield_price.setDisable(true);
		txtfield_img.setDisable(true);

		btnAdd.setDisable(false);
		btnEdit.setDisable(false);
		btnDel.setDisable(false);


		btnOK.setDisable(true);
		btnCancel.setDisable(true);
		btnImg.setDisable(true);

	}

	@FXML
	void tableViewClick(MouseEvent event)  {




		if(tableView.getSelectionModel().isEmpty()) {
			return;
		}

		SnackSetVO snacksetvo = tableView.getSelectionModel().getSelectedItem();


		txtfield_id.setText(snacksetvo.getSet_id());
		txtfield_name.setText(snacksetvo.getSet_name());
		txtfield_price.setText(Integer.toString(snacksetvo.getSet_price()));
		txtfield_img.setText(snacksetvo.getSet_photo());


		String path = txtfield_img.getText();


		//System.out.println(path);

		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		imgView.setImage(image);
	}




	private String strWords="";
	private ISnackSetService service;
	private ObservableList<SnackSetVO> snacksetList;




	@FXML
	void initialize() {





		Registry reg = null;


		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ISnackSetService) reg.lookup("snackService");
			idCol.setCellValueFactory(new PropertyValueFactory<>("set_id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<>("set_name"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("set_price"));

			getAllSnackSetList();




		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}




	private void getAllSnackSetList() {
		List<SnackSetVO> snacksetData;

		try {
			snacksetData = service.getAllSnackSetList();
			snacksetList = FXCollections.observableArrayList(snacksetData);
			tableView.setItems(snacksetList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
