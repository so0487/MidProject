package wg.vo;

import java.io.Serializable;

public class SelectQnAVO implements Serializable {
	public static QuestionVO currentQuestionVo;
	
	public static QuestionVO getCurrQuestionVo() {
		return currentQuestionVo;
	}
	
	public static void setCurrQuestionVo(QuestionVO questionVo) {
		SelectQnAVO.currentQuestionVo = currentQuestionVo;
	}
	
	
	
}
