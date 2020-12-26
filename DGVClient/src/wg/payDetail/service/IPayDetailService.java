package wg.payDetail.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import wg.vo.PayDetailVO;

public interface IPayDetailService extends Remote {
	/**
	 * 
	 * @author 선미
	 * @param pdvo
	 * @return
	 */
	public int payDetBookMovie(PayDetailVO pdvo) throws RemoteException;
	
	
	/**
	 * 
	 * @author 선미	
	 * @param paydet_no
	 * @return
	 */
	public int refundDetBookMovie(int paydet_no) throws RemoteException;
}
