package wg.answer.dao;

import java.util.List;
import java.util.Map;

import wg.vo.AnswerVO;

/**
 * 답변 C/R/U/D
 * @author 정수영
 *
 */
public interface IAnswerDao {
	
	/**
	 * answerVO DB자료 insert
	 * @param answerVo
	 * @return	작업성공1/실패0
	 */
	public int insertAnswer(AnswerVO answerVo);
	
	
	
	
	/**
	 * answer_no를 매개변수로 받아 해당흐는 answerVo정보 삭제
	 * @param answer_no
	 * @return	성공1/실패0
	 */
	public int deleteAnswer(String answer_no);
	
	
	
	/**
	 * answerVo정보 업데이트
	 * @param answerVo
	 * @return	성공1/실패0
	 */
	public int updateAnswer(AnswerVO answerVo);
	
	
	
	
	/**
	 * answerVo의 전체 정보를 list로 반환
	 * @param searchMap
	 * @return
	 */
	public List<AnswerVO>getAllAnswerList(Map<String, String> searchMap);
	
	
	
	/**
	 * 가장 큰 answer_no정보 반환
	 * @return
	 */
	public int getMaxAnswerNo();
	
	
	
	/**
	 * 해당하는 answer_no 갯수를 반환
	 * @param searchMap
	 * @return
	 */
	public int getAnswerCount(Map<String, String>searchMap);
	
}
