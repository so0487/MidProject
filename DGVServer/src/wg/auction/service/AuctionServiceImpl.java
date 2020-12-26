package wg.auction.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.auction.dao.AuctionDaoImpl;
import wg.auction.dao.IAuctionDao;
import wg.vo.AuctionTTVO;
import wg.vo.AuctionVO;
import wg.vo.SeatSchVO;

public class AuctionServiceImpl extends UnicastRemoteObject implements IAuctionService {
	private static AuctionServiceImpl service;
	private IAuctionDao dao;
	
	
	private AuctionServiceImpl()  throws RemoteException  {
		dao = AuctionDaoImpl.getInstance();
	}
	
	public static AuctionServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new AuctionServiceImpl();
		return service;
	}

	@Override
	public List<AuctionVO> getAllAuctionList() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllAuctionList();
	}

	@Override
	public int insertAuction(AuctionVO aucvo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertAuction(aucvo);
	}

	@Override
	public int updateAuction(AuctionVO aucvo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateAuction(aucvo);
	}

	@Override
	public int deleteAuction(int auc_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deleteAuction(auc_no);
	}

	@Override
	public String aucPoster(int sseat_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.aucPoster(sseat_no);
	}

	@Override
	public int findSseat(int sseat_no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.findSseat(sseat_no);
	}

	@Override
	public List<AuctionTTVO> searchtt(AuctionTTVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.searchtt(vo);
	}

	@Override
	public List<SeatSchVO> searchseat(Map<String, String> paramMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.searchseat(paramMap);
	}
}
