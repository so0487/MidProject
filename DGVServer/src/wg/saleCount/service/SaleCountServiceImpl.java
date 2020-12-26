package wg.saleCount.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import wg.saleCount.dao.ISaleCountDao;
import wg.saleCount.dao.SaleCountDaoImpl;

public class SaleCountServiceImpl extends UnicastRemoteObject implements ISaleCountService {

	
	private static SaleCountServiceImpl service;
	private ISaleCountDao dao;
	
	
	private SaleCountServiceImpl() throws RemoteException{
		dao = SaleCountDaoImpl.getInstance();
	}
	
	public static SaleCountServiceImpl getInstance()throws RemoteException{
		if(service==null)
			service = new SaleCountServiceImpl();
		return service;
	}

	@Override
	public int getSaleCount(String pay_time) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.getSaleCount(pay_time);
	}

}
