package wg.buysnack.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.BuySnackVO;
import wg.vo.SnackUserViewVO;

public interface IBuySnackService extends Remote {
	
	public List<SnackUserViewVO> getBuySnack(String mem_id) throws RemoteException;
	
	public List<SnackUserViewVO> getBuyAllSnack() throws RemoteException; 
	
	/**
	 * 스낵바 구매하는 메서드
	 * @param svo
	 * @return
	 */
	public int insertBuysnack(BuySnackVO svo) throws RemoteException;
	
	/**
	 * 구매id 생성을 위하여 가장 큰 값을 반환하는 메서드
	 * @return
	 */
	public String getMaxBuy_id() throws RemoteException;

}
