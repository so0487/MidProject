package wg.buy_detail.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.Buy_DetailVO;

public interface IBuy_DetailService extends Remote {
	
	/**
	 * 스낵바 구매 디테일
	 * @param bdvo
	 * @return
	 */
	public int insertBuyDet(Buy_DetailVO bdvo) throws RemoteException;
	
	/**
	 * 
	 * @param buydet_no
	 * @return
	 */
	public int deleteBuyDet(int buydet_no) throws RemoteException;
	
	/**
	 * 
	 * @return
	 */
	public List<Buy_DetailVO> getBuyDetail() throws RemoteException;

}
