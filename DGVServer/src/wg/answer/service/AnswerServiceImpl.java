package wg.answer.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.answer.dao.AnswerDaoImpl;
import wg.answer.dao.IAnswerDao;
import wg.vo.AnswerVO;

public class AnswerServiceImpl extends UnicastRemoteObject implements IAnswerService {
	private static AnswerServiceImpl service;
	private IAnswerDao dao;
	
	
	private AnswerServiceImpl()  throws RemoteException  {
		dao = AnswerDaoImpl.getInstance();
	}
	
	public static AnswerServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new AnswerServiceImpl();
		return service;
	}

	@Override
	public int insertAnswer(AnswerVO answerVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertAnswer(answerVo);
	}

	@Override
	public int deleteAnswer(String answer_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deleteAnswer(answer_no);
	}

	@Override
	public int updateAnswer(AnswerVO answerVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateAnswer(answerVo);
	}

	@Override
	public List<AnswerVO> getAllAnswerList(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllAnswerList(searchMap);
	}

	@Override
	public int getMaxAnswerNo() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getMaxAnswerNo();
	}

	@Override
	public int getAnswerCount(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAnswerCount(searchMap);
	}

}
