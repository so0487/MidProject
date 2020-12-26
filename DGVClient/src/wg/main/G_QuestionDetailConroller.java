package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.AlertUtil;
import wg.question.service.IQuestionService;
import wg.vo.QuestionVO;

public class G_QuestionDetailConroller {

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
	private TextArea txtArea;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDel;

	@FXML
	void btnAddClick(MouseEvent event) {
		int question_no = 0;
		String writer = "s005";			//나중에 MemberVO와 연동해서 id 가져오기
		String today = "";
		//String writer = member.getMem_id();



		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));



		try {
			question_no = service.getMaxQuestionNo();
			txtField_no.setText(String.valueOf(question_no));
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
			//txtField_date.clear();



			btnOK.setDisable(false);
			btnCancel.setDisable(false);
			strWords="add";




			//btnAdd.setDisable(true);
			//btnEdit.setDisable(true);
			//btnDel.setDisable(true);


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


		//btnOK.setDisable(true);
		//btnCancel.setDisable(true);
	}

	@FXML
	void btnDelClick(MouseEvent event) {
		String question_no = txtField_no.getText();

		int cnt;

		try {
			cnt = service.deleteQuestion(question_no);

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


				//btnOK.setDisable(true);
				//btnCancel.setDisable(true);

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
		String question_no = txtField_no.getText();

		txtField_title.setDisable(false);
		txtArea.setDisable(false);

		txtField_no.setDisable(true);
		txtField_date.setDisable(true);
		txtField_writer.setDisable(true);



		//btnAdd.setDisable(true);
		//btnEdit.setDisable(true);
		//btnDel.setDisable(true);


		//btnOK.setDisable(false);
		//btnCancel.setDisable(false);



		strWords = "edit";	
	}

	@FXML
	void btnListClick(MouseEvent event) {

	}

	@FXML
	void btnOKClick(MouseEvent event) {
		String question_title = txtField_title.getText();
		String question_cont = txtArea.getText();
		String question_no = txtField_no.getText();
		String question_writer = txtField_writer.getText();
		String question_date = txtField_date.getText();

		if(question_title.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "질문제목을 입력하세요");
			return;
		}


		QuestionVO questionVo = new QuestionVO();
		
		questionVo.setQuestion_no(Integer.parseInt(question_no));
		questionVo.setQuestion_title(question_title);
		questionVo.setMem_id(question_writer);
		questionVo.setQuestion_title(question_title);
		questionVo.setQuestion_content(question_cont);
		questionVo.setQuestion_time(question_title);

		if("add".equals(strWords)) {
			try {

				int	cnt = service.insertQuestion(questionVo);

				if(cnt>0) {
					AlertUtil.warnMsg("작업결과", "추가 성공");

				}else {
					AlertUtil.warnMsg("작업결과", "추가실패");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("edit".equals(strWords)) {

			try {

				int	cnt = service.updateQuestion(questionVo);


				if(cnt>0) {

					AlertUtil.warnMsg("작업결과", "수정 성공");


				}else {
					AlertUtil.warnMsg("작업결과", "수정실패");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		//getAllQuestionList();


		//txtField_title.clear();
		//txtField_writer.clear();
		//txtField_date.clear();



		//txtField_title.setDisable(true);
		//txtField_writer.setDisable(true);
		//txtField_date.setDisable(true);



		//btnOK.setDisable(true);
		//btnCancel.setDisable(true);
	}



	private String strWords = "";
	private IQuestionService service;
	private ObservableList<QuestionVO>questionList;
	
	
	
	private QuestionVO vo;
	
	public QuestionVO getVo() {
		return vo;
	}
	
	public void setVo() {
		this.vo = vo;
		
		
		
		txtField_no.setText(String.valueOf(vo.getQuestion_no()));
		txtField_writer.setText(vo.getMem_id());
		txtField_title.setText(vo.getQuestion_title());
		txtArea.setText(vo.getQuestion_content());
		txtField_date.setText(vo.getQuestion_title());
	}
	

	@FXML
	void initialize() {
		Registry reg = null;


		try {
//			reg = LocateRegistry.getRegistry("192.168.31.32",9988);
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IQuestionService) reg.lookup("questionService");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	//작업 완료 후 이전화면(목록)화면을 load
	public void getAllQuestionList() {

	}
}



