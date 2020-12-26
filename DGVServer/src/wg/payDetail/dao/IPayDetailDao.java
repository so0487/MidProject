package wg.payDetail.dao;

import java.rmi.RemoteException;

import wg.vo.PayDetailVO;
import wg.vo.PayVO;

public interface IPayDetailDao {
	
	/**
	 * 
	 * @author 선미
	 * @param pdvo
	 * @return
	 */
	public int payDetBookMovie(PayDetailVO pdvo);
	
	
	/**
	 * 
	 * @author 선미	
	 * @param paydet_no
	 * @return
	 */
	public int refundDetBookMovie(int paydet_no);
}
