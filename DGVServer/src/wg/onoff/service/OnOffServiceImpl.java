package wg.onoff.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.onoff.dao.IOnOffDao;
import wg.onoff.dao.OnOffDaoImpl;
import wg.vo.OnOffVO;
import wg.vo.OnOffViewVO;

public class OnOffServiceImpl extends UnicastRemoteObject implements IOnOffService {
	private static OnOffServiceImpl service;
	private IOnOffDao dao;
	
	
	private OnOffServiceImpl()  throws RemoteException  {
		dao = OnOffDaoImpl.getInstance();
	}
	
	public static OnOffServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new OnOffServiceImpl();
		return service;
	}

	
	
	@Override
	public int deleteOnOff(int onNo) throws RemoteException {
		return dao.deleteOnOff(onNo);
	}

	@Override
	public int updateOnOff(OnOffVO oovVo) throws RemoteException {
		return dao.updateOnOff(oovVo);
	}
	
	@Override
	public int updateOff(String mem_id) throws RemoteException {
		return dao.updateOff(mem_id);
	}

	@Override
	public List<OnOffViewVO> getAllOnOff() throws RemoteException {
		return dao.getAllOnOff();
	}

	@Override
	public int insertOnOff(String mem_id) throws RemoteException {
		return dao.insertOnOff(mem_id);
	}

	@Override
	public int getCountOnOff(String mem_id) throws RemoteException {
		return dao.getCountOnOff(mem_id);
	}
	
	


}
