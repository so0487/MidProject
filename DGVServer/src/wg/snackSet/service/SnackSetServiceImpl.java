package wg.snackSet.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.snackSet.dao.ISnackSetDao;
import wg.snackSet.dao.SnackSetDaoImpl;
import wg.vo.SnackSetVO;

public class SnackSetServiceImpl extends UnicastRemoteObject implements ISnackSetService {
	private static SnackSetServiceImpl service;
	private ISnackSetDao dao;

	// 생성자
	private SnackSetServiceImpl()  throws RemoteException{
		//dao = new MemberDaoImpl();
		dao=SnackSetDaoImpl.getInstance();
	}
	
	
	public static SnackSetServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new SnackSetServiceImpl();
		return service;
	}


	@Override
	public int insertSnackSet(SnackSetVO snackSetVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertSnackSet(snackSetVo);
	}


	@Override
	public int deleteSnackSet(String set_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deleteSnackSet(set_id);
	}


	@Override
	public int updateSnackSet(SnackSetVO snackSetVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateSnackSet(snackSetVo);
	}


	@Override
	public List<SnackSetVO> getAllSnackSetList() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllSnackSetList();
	}


	@Override
	public String getMaxSetId() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getMaxSetId();
	}
	
	





	



	
}
