package wg.discount.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.discount.dao.DiscountDaoImpl;
import wg.discount.dao.IDiscountDao;
import wg.vo.DiscountVO;

public class DiscountServiceImpl extends UnicastRemoteObject implements IDiscountService{
	
	
	//adasdadasd
	
	
	private static DiscountServiceImpl service;
	private IDiscountDao dao;
	
	
	private DiscountServiceImpl()  throws RemoteException  {
		dao = DiscountDaoImpl.getInstance();
	}
	
	public static DiscountServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new DiscountServiceImpl();
		return service;
	}

	@Override
	public int insertDiscount(DiscountVO dvo) throws RemoteException {
		return dao.insertDiscount(dvo);
	}

	@Override
	public int updateDiscount(DiscountVO dvo) throws RemoteException {
		return dao.updateDiscount(dvo);
	}

	@Override
	public int deleteDiscount(String dis_id) throws RemoteException {
		return dao.deleteDiscount(dis_id);
	}

	@Override
	public List<DiscountVO> getAllDiscount() throws RemoteException {
		return dao.getAllDiscount();
	}

	@Override
	public String getMaxDis_id() throws RemoteException {
		return dao.getMaxDis_id();
	}
}
