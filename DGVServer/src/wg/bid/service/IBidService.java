package wg.bid.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.BidResultVO;
import wg.vo.BidVO;

public interface IBidService extends Remote {
	
	public int bidPrice(int sseat_no) throws RemoteException;
	public int insertBid(BidVO bidvo) throws RemoteException;
	public List<BidVO> getAllList() throws RemoteException;
	
	public BidResultVO resultBid(int sseat_no) throws RemoteException;
	
	public String getMax() throws RemoteException;

}
