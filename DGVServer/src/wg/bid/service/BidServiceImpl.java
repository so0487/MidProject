package wg.bid.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.bid.dao.BidDaoImpl;
import wg.bid.dao.IBidDao;
import wg.vo.BidResultVO;
import wg.vo.BidVO;

public class BidServiceImpl extends UnicastRemoteObject implements IBidService {
	private static BidServiceImpl service;
	private IBidDao dao;
	
	
	private BidServiceImpl()  throws RemoteException  {
		dao = BidDaoImpl.getInstance();
	}
	
	public static BidServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new BidServiceImpl();
		return service;
	}

	@Override
	public int bidPrice(int sseat_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.bidPrice(sseat_no);
	}

	@Override
	public int insertBid(BidVO bidvo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertBid(bidvo);
	}

	@Override
	public List<BidVO> getAllList() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllList();
	}

	@Override
	public BidResultVO resultBid(int sseat_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.resultBid(sseat_no);
	}

	@Override
	public String getMax() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getMax();
	}
}
