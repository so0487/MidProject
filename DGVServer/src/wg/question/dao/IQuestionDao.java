package wg.question.dao;

import java.util.List;
import java.util.Map;

import wg.vo.QuestionVO;

/**
 * 질문관리 CRUD
 * @author 정수영
 *
 */

public interface IQuestionDao {

	
	
	public int insertQuestion(QuestionVO questionVo);
	
	
	
	
	public int deleteQuestion(String question_no);
	
	
	
	public int updateQuestion(QuestionVO questionVo);
	
	
	
	public List<QuestionVO> getaAllQuestion(Map<String, String> searchMap);
	
	
	
	public int getMaxQuestionNo();
	
	
	
	public int getQuestionCount(Map<String, String>searchMap);
}
