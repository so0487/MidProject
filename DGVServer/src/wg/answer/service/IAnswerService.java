package wg.answer.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.AnswerVO;

public interface IAnswerService extends Remote {
	/**
	 * answerVO DB자료 insert
	 * @param answerVo
	 * @return	작업성공1/실패0
	 */
	public int insertAnswer(AnswerVO answerVo) throws RemoteException;
	
	
	
	
	/**
	 * answer_no를 매개변수로 받아 해당흐는 answerVo정보 삭제
	 * @param answer_no
	 * @return	성공1/실패0
	 */
	public int deleteAnswer(String answer_no) throws RemoteException;
	
	
	
	/**
	 * answerVo정보 업데이트
	 * @param answerVO
	 * @return	성공1/실패0
	 */
	public int updateAnswer(AnswerVO answerVO) throws RemoteException;
	
	
	
	
	/**
	 * answerVo의 전체 정보를 list로 반환
	 * @param searchMap
	 * @return
	 */
	public List<AnswerVO>getAllAnswerList(Map<String, String> searchMap) throws RemoteException;
	
	
	
	/**
	 * 가장 큰 answer_no정보 반환
	 * @return
	 */
	public int getMaxAnswerNo() throws RemoteException;
	
	
	
	/**
	 * 해당하는 answer_no 갯수를 반환
	 * @param searchMap
	 * @return
	 */
	public int getAnswerCount(Map<String, String>searchMap) throws RemoteException;
}
