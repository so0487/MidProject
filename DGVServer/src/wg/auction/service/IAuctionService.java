package wg.auction.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.AuctionTTVO;
import wg.vo.AuctionVO;
import wg.vo.SeatSchVO;

public interface IAuctionService extends Remote {
	public List<AuctionVO> getAllAuctionList() throws RemoteException;
	
	public int insertAuction(AuctionVO aucvo) throws RemoteException;
	
	public int updateAuction(AuctionVO aucvo) throws RemoteException;
	
	public int deleteAuction(int auc_no) throws RemoteException;
	
	public String aucPoster(int sseat_no) throws RemoteException;
	
	public int findSseat(int sseat_no) throws RemoteException;
	
	public List<AuctionTTVO> searchtt(AuctionTTVO vo)  throws RemoteException;
	
	public List<SeatSchVO>searchseat(Map<String, String> paramMap) throws RemoteException;
}
