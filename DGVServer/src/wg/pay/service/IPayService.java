package wg.pay.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import wg.vo.PayVO;

public interface IPayService extends Remote {
	
	/**
	 * 
	 * @author 선미
	 * @param pvo
	 * @return
	 */
	public int payBookMovie(PayVO pvo) throws RemoteException;
	
	
	/**
	 * 
	 * @author 선미
	 * @param pay_no
	 * @return
	 */
	public int refundBookMovie(int pay_no) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public int getPay_no(String book_id) throws RemoteException;
	
	public int updatePayRefund(String book_id) throws RemoteException;
	
	/**
	 * @author 이명석
	 * @param buy_id
	 * @return
	 */
	public int updateSnackPayRefund(String buy_id) throws RemoteException;
	
	/**
	 * 
	 * @param pvo
	 * @return
	 */
	public int paySnack(PayVO pvo) throws RemoteException;
	
	public int insertPayBid(PayVO pvo) throws RemoteException;
}
