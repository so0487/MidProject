package wg.lnf.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.lnf.dao.ILnFDao;
import wg.lnf.dao.LnFDaoImpl;
import wg.vo.LnFVO;


public class LnFServiceImpl extends UnicastRemoteObject implements ILnFService {
	private static LnFServiceImpl service;
	private ILnFDao dao;
	
	
	private LnFServiceImpl()  throws RemoteException  {
		dao = LnFDaoImpl.getInstance();
	}
	
	public static LnFServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new LnFServiceImpl();
		return service;
	}

	@Override
	public int insertLnf(LnFVO lnfVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertLnf(lnfVo);
	}

	@Override
	public int deleteLnf(String lnf_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deleteLnf(lnf_no);
	}

	@Override
	public int updateLnf(LnFVO lnfvo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateLnf(lnfvo);
	}

	@Override
	public List<LnFVO> getAllLnfList(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllLnfList(searchMap);
	}

	@Override
	public int getMax_lnf_no() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getMax_lnf_no();
	}

	@Override
	public int getLnfCount(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getLnfCount(searchMap);
	}

}
