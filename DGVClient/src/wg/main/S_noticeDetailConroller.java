package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import util.AlertUtil;
import wg.notice.service.INoticeService;
import wg.vo.MemberVO;
import wg.vo.NoticeVO;

public class S_noticeDetailConroller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtField_title;

	@FXML
	private Button btnList;

	@FXML
	private TextField txtField_date;

	@FXML
	private TextField txtField_writer;

	@FXML
	private TextField txtField_no;

	@FXML
	private Button btnOK;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDel;

	@FXML
	private TextArea txtArea;

	@FXML
	void btnAddClick(MouseEvent event) {

		int notice_no = 0;
		String writer = "m001";			
		String today = "";



		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));




		try {
			System.out.println(service.getMaxNot_no());
			notice_no = service.getMaxNot_no();
			txtField_no.setText(String.valueOf(notice_no));
			txtField_writer.setText(writer);
			txtField_date.setText(today);






			txtField_no.setDisable(true);


			txtArea.setDisable(true);
			txtField_writer.setDisable(true);


			txtField_title.clear();
			txtField_title.setDisable(false);
			txtField_title.requestFocus();

			txtArea.setDisable(false);
			txtArea.clear();


			txtField_date.setDisable(true);
			txtField_date.clear();



			btnOK.setDisable(false);
			btnCancel.setDisable(false);
			strWords="add";


			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			btnDel.setDisable(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





	}

	@FXML
	void btnCancelClick(MouseEvent event) {
		txtField_title.setDisable(true);
		txtArea.setDisable(true);
		txtField_date.setDisable(true);


		btnAdd.setDisable(false);
		btnEdit.setDisable(false);
		btnDel.setDisable(false);


		btnOK.setDisable(true);
		btnCancel.setDisable(true);

	}

	@FXML
	void btnDelClick(MouseEvent event) {
		String not_no = txtField_no.getText();

		int cnt;
		try {
			cnt = service.deleteNotice(not_no);

			if(cnt>0) {
				AlertUtil.infoMsg("작업결과", "삭제를 완료했습니다.");

				txtField_no.clear();
				txtField_title.clear();
				txtField_writer.clear();
				txtArea.clear();
				txtField_date.clear();




				txtField_title.setDisable(true);
				txtArea.setDisable(true);
				txtField_date.setDisable(true);


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
	void btnEditClick(MouseEvent event) {
		String not_no = txtField_no.getText();

		txtField_title.setDisable(false);
		txtArea.setDisable(false);

		txtField_no.setDisable(true);
		txtField_date.setDisable(true);
		txtField_writer.setDisable(true);



		btnAdd.setDisable(true);
		btnEdit.setDisable(true);
		btnDel.setDisable(true);


		btnOK.setDisable(false);
		btnCancel.setDisable(false);



		strWords = "edit";	
	}

	@FXML
	void btnListClick(MouseEvent event) {
		try {
			FXMLLoader loader1 = new FXMLLoader(M_topController.class.getResource("../fxml/S_noticeMain.fxml"));
			Parent center = loader1.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	@FXML
	void btnOKClick(MouseEvent event) {
		String not_title = txtField_title.getText();
		String not_cont = txtArea.getText();
		String not_no = txtField_no.getText();
		String not_writer = txtField_writer.getText();
		String not_date = txtField_date.getText();
		
		




		if(not_title.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "공지제목을 입력하세요");
			return;
		}if(not_cont.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "공지 내용을 입력하세요");
			return;
		}



		NoticeVO noticeVo = new NoticeVO();

		noticeVo.setNot_title(not_title);
		noticeVo.setNot_content(not_cont);
		noticeVo.setNot_writer(not_writer);
		noticeVo.setNot_no(Integer.parseInt(not_no));
		noticeVo.setNot_content(not_cont);
		noticeVo.setNot_time(not_date);



		if("add".equals(strWords)) {
			try {

				int	cnt = service.insertNotice(noticeVo);

				if(cnt>0) {
					AlertUtil.warnMsg("작업결과", "추가 성공");
					
					btnOK.setDisable(true);
					btnCancel.setDisable(true);
					txtArea.setDisable(true);
					
					

				}else {
					AlertUtil.warnMsg("작업결과", "추가실패");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("edit".equals(strWords)) {

			try {

				int	cnt = service.updateNotice(noticeVo);


				if(cnt>0) {

					AlertUtil.warnMsg("작업결과", "수정 성공");
					
					btnOK.setDisable(true);
					btnCancel.setDisable(true);
					txtArea.setDisable(true);

				}else {
					AlertUtil.warnMsg("작업결과", "수정실패");

				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		//getAllNoticeList();


		//txtField_title.clear();
		//txtField_writer.clear();
		//txtField_date.clear();



		txtField_title.setDisable(true);
		txtField_writer.setDisable(true);
		txtField_date.setDisable(true);



		btnOK.setDisable(false);
		btnCancel.setDisable(false);

		btnEdit.setDisable(false);
		btnDel.setDisable(false);
	}

	private NoticeVO vo;

	public NoticeVO getVo() {
		return vo;
	}


	public void setVo(NoticeVO vo) {
		this.vo = vo;

		txtField_no.setText(String.valueOf(vo.getNot_no()));
		txtField_writer.setText(vo.getNot_writer());
		txtField_title.setText(vo.getNot_title());
		txtArea.setText(vo.getNot_content());
		txtField_date.setText(vo.getNot_time());
	}


	private String strWords="";
	private INoticeService service;
	private ObservableList<NoticeVO> noticeList;
	MemberVO member = new MemberVO();

	private Map<String, String> searchMap;

	@FXML
	void initialize()  throws RemoteException{

		Registry reg = null;


		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (INoticeService) reg.lookup("noticeService");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/*
	public void getAllNoticeList() {
    	try {
			FXMLLoader loader1 = new FXMLLoader(M_topController.class.getResource("../fxml/S_noticeMain.fxml"));
			Parent center = loader1.load();
			U_main.root.setCenter(center);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	 */	


}
