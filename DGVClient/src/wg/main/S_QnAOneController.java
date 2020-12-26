package wg.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import wg.answer.service.IAnswerService;
import wg.question.service.IQuestionService;
import wg.vo.AnswerVO;
import wg.vo.QuestionVO;

public class S_QnAOneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label_no;

    @FXML
    private Label label_title;

    @FXML
    private Label label_writer;

    @FXML
    private Label label_date;


    @FXML
    void click(MouseEvent event) {

    }

    
    
    private AnswerVO answerVo;
    private QuestionVO questionVo;
    
    public QuestionVO getQuestionVo() {
    	return questionVo;
    }
    
    
    public void setQuestionVO(QuestionVO questionVo) {
    	this.questionVo = questionVo;
    	label_no.setText(Integer.toString(questionVo.getQuestion_no()));
    	label_title.setText(questionVo.getQuestion_title());
    	label_writer.setText(questionVo.getMem_id());
    	label_date.setText(questionVo.getQuestion_time());
    }
    
    
    
    IQuestionService questionService;
    IAnswerService answerService;
    
    
    @FXML
    void initialize() {
    	
    }
}
