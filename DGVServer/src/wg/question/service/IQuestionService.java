package wg.question.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.QuestionVO;

public interface IQuestionService extends Remote{
	

	public int insertQuestion(QuestionVO questionVo) throws RemoteException;
	
	
	
	
	public int deleteQuestion(String question_no) throws RemoteException;
	
	
	
	public int updateQuestion(QuestionVO questionVo) throws RemoteException;
	
	
	
	public List<QuestionVO> getaAllQuestion(Map<String, String> searchMap) throws RemoteException;
	
	
	
	public int getMaxQuestionNo() throws RemoteException;
	
	
	
	public int getQuestionCount(Map<String, String>searchMap) throws RemoteException;
	
	
}
