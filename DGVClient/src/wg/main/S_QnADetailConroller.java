package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import wg.answer.service.IAnswerService;
import wg.vo.AnswerVO;
import wg.vo.QuestionVO;

public class S_QnADetailConroller {

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
	private TextArea txtArea_Question;

	@FXML
	private TextArea txtArea_Answer;

	@FXML
	void btnAddClick(MouseEvent event) {

	}

	@FXML
	void btnCancelClick(MouseEvent event) {

	}

	@FXML
	void btnDelClick(MouseEvent event) {

	}

	@FXML
	void btnEditClick(MouseEvent event) {

	}

	@FXML
	void btnListClick(MouseEvent event) {

	}

	@FXML
	void btnOKClick(MouseEvent event) {

	}


	private AnswerVO vo;
	public AnswerVO getVo() {
		return vo;
	}

	private QuestionVO questionVo;
	public QuestionVO getQuestionVo() {
		return questionVo;
	}


	public void setVo(AnswerVO vo) {
		this.vo = vo;

		txtField_no.setText(String.valueOf(vo.getAnswer_no()));
		txtField_writer.setText(vo.getMem_id());
		txtField_title.setText(String.valueOf(vo.getQuestion_no()));
		txtArea_Question.setText(questionVo.getQuestion_title());
		txtArea_Answer.setText(vo.getAnswer_content());
		txtField_date.setText(vo.getAnswer_time());

	}

	
	IAnswerService service;
	

	@FXML
	void initialize() {
		Registry reg = null;


		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IAnswerService) reg.lookup("answerService");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
