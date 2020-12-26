package wg.payDetail.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import wg.payDetail.dao.IPayDetailDao;
import wg.payDetail.dao.PayDetailDaoImpl;
import wg.vo.PayDetailVO;



public class PayDetailServiceImpl extends UnicastRemoteObject implements IPayDetailService {
	private static PayDetailServiceImpl service;
	private IPayDetailDao dao;
	
	
	private PayDetailServiceImpl()  throws RemoteException  {
		dao = PayDetailDaoImpl.getInstance();
	}
	
	public static PayDetailServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new PayDetailServiceImpl();
		return service;
	}

	@Override
	public int payDetBookMovie(PayDetailVO pdvo) throws RemoteException {
		return dao.payDetBookMovie(pdvo);
	}

	@Override
	public int refundDetBookMovie(int paydet_no) throws RemoteException {
		return dao.refundDetBookMovie(paydet_no);
	}



}
