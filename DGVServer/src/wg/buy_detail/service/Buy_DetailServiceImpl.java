package wg.buy_detail.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.buy_detail.dao.Buy_DetailDaoImpl;
import wg.buy_detail.dao.IBuy_DetailDao;
import wg.vo.Buy_DetailVO;

public class Buy_DetailServiceImpl extends UnicastRemoteObject implements IBuy_DetailService {
	private static Buy_DetailServiceImpl service;
	private IBuy_DetailDao dao;
	
	
	private Buy_DetailServiceImpl()  throws RemoteException  {
		dao = Buy_DetailDaoImpl.getInstance();
	}
	
	public static Buy_DetailServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new Buy_DetailServiceImpl();
		return service;
	}

	@Override
	public int insertBuyDet(Buy_DetailVO bdvo) throws RemoteException {
		return dao.insertBuyDet(bdvo);
	}

	@Override
	public int deleteBuyDet(int buydet_no) throws RemoteException {
		return dao.deleteBuyDet(buydet_no);
	}

	@Override
	public List<Buy_DetailVO> getBuyDetail() throws RemoteException {
		return dao.getBuyDetail();
	}
}
