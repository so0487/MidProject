package wg.buysnack.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.buysnack.dao.BuySnackDaoImpl;
import wg.buysnack.dao.IBuySnackDao;
import wg.vo.BuySnackVO;
import wg.vo.SnackUserViewVO;

public class BuySnackServiceImpl extends UnicastRemoteObject implements IBuySnackService {
	private static BuySnackServiceImpl service;
	private IBuySnackDao dao;
	
	
	private BuySnackServiceImpl()  throws RemoteException  {
		dao = BuySnackDaoImpl.getInstance();
	}
	
	public static BuySnackServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new BuySnackServiceImpl();
		return service;
	}

	@Override
	public List<SnackUserViewVO> getBuySnack(String mem_id) throws RemoteException {
		return dao.getBuySnack(mem_id);
	}

	@Override
	public List<SnackUserViewVO> getBuyAllSnack() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getBuyAllSnack();
	}

	@Override
	public int insertBuysnack(BuySnackVO svo) throws RemoteException {
		return dao.insertBuysnack(svo);
	}

	@Override
	public String getMaxBuy_id() throws RemoteException {
		return dao.getMaxBuy_id();
	}
}
