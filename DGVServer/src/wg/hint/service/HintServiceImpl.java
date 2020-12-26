package wg.hint.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.hint.dao.HintDaoImpl;
import wg.hint.dao.IHintDao;
import wg.vo.HintVO;

public class HintServiceImpl extends UnicastRemoteObject implements IHintService {
	private static HintServiceImpl service;
	private IHintDao dao;
	
	
	private HintServiceImpl()  throws RemoteException  {
		dao = HintDaoImpl.getInstance();
	}
	
	public static HintServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new HintServiceImpl();
		return service;
	}

	@Override
	public List<HintVO> getAllHint() throws RemoteException {
		return dao.getAllHint();
	}

}
