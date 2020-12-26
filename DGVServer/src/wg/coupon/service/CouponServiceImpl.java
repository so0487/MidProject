package wg.coupon.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.coupon.dao.CouponDaoImpl;
import wg.coupon.dao.ICouponDao;
import wg.vo.CouponUserViewVO;
import wg.vo.CouponVO;

public class CouponServiceImpl extends UnicastRemoteObject implements ICouponService{
	private static CouponServiceImpl service;
	private ICouponDao dao;
	
	
	private CouponServiceImpl()  throws RemoteException  {
		dao = CouponDaoImpl.getInstance();
	}
	
	public static CouponServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new CouponServiceImpl();
		return service;
	}

	@Override
	public int insertCoupon(CouponVO cvo) throws RemoteException {
		return dao.insertCoupon(cvo);
	}

	@Override
	public int updateCoupon(CouponVO cvo) throws RemoteException {
		return dao.updateCoupon(cvo);
	}

	@Override
	public int deleteCoupon(String cou_id) throws RemoteException {
		return dao.deleteCoupon(cou_id);
	}

	@Override
	public List<CouponVO> getAllCoupon() throws RemoteException {
		return dao.getAllCoupon();
	}

	@Override
	public String getMaxCou_id() throws RemoteException {
		return dao.getMaxCou_id();
	}

	@Override
	public List<CouponUserViewVO> userCouponList(String mem_id) throws RemoteException {
		return dao.userCouponList(mem_id);
	}
	
	
}
