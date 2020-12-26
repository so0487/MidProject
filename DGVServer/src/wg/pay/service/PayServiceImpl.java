package wg.pay.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import wg.pay.dao.IPayDao;
import wg.pay.dao.PayDaoImpl;
import wg.vo.PayVO;


public class PayServiceImpl extends UnicastRemoteObject implements IPayService {
	private static PayServiceImpl service;
	private IPayDao dao;
	
	
	private PayServiceImpl()  throws RemoteException  {
		dao = PayDaoImpl.getInstance();
	}
	
	public static PayServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new PayServiceImpl();
		return service;
	}

	@Override
	public int payBookMovie(PayVO pvo) throws RemoteException {
		return dao.payBookMovie(pvo);
	}

	@Override
	public int refundBookMovie(int pay_no) throws RemoteException {
		return dao.refundBookMovie(pay_no);
	}

	@Override
	public int getPay_no(String book_id) throws RemoteException {
		return dao.getPay_no(book_id);
	}

	@Override
	public int updatePayRefund(String book_id) throws RemoteException {
		return dao.updatePayRefund(book_id);
	}

	@Override
	public int updateSnackPayRefund(String buy_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateSnackPayRefund(buy_id);
	}

	@Override
	public int paySnack(PayVO pvo) throws RemoteException {
		return dao.paySnack(pvo);
	}

	@Override
	public int insertPayBid(PayVO pvo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertPayBid(pvo);
	}


}
