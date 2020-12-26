package wg.question.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.question.dao.IQuestionDao;
import wg.question.dao.QuestionDaoImpl;
import wg.vo.QuestionVO;

public class QuestionServiceImpl extends UnicastRemoteObject implements IQuestionService {
	private static QuestionServiceImpl service;
	private IQuestionDao dao;
	
	
	private QuestionServiceImpl() throws RemoteException{
		dao = QuestionDaoImpl.getInstance();
	}
	
	public static QuestionServiceImpl getInstance() throws RemoteException{
		if(service == null) service = new QuestionServiceImpl();
		return service;
	}

	@Override
	public int insertQuestion(QuestionVO questionVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertQuestion(questionVo);
	}

	@Override
	public int deleteQuestion(String question_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deleteQuestion(question_no);
	}

	@Override
	public int updateQuestion(QuestionVO questionVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateQuestion(questionVo);
	}

	@Override
	public List<QuestionVO> getaAllQuestion(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getaAllQuestion(searchMap);
	}

	@Override
	public int getMaxQuestionNo() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getMaxQuestionNo();
	}

	@Override
	public int getQuestionCount(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getQuestionCount(searchMap);
	}
}
