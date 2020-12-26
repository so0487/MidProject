package wg.issueCoupon.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.issueCoupon.dao.IIssueCouponDao;
import wg.issueCoupon.dao.IssueCouponDaoImpl;
import wg.vo.IssueCouponVO;
import wg.vo.IssueViewVO;


public class IssueCouponServiceImpl extends UnicastRemoteObject implements IIssueCouponService {
	private static IssueCouponServiceImpl service;
	private IIssueCouponDao dao;
	
	
	private IssueCouponServiceImpl()  throws RemoteException  {
		dao = IssueCouponDaoImpl.getInstance();
	}
	
	public static IssueCouponServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new IssueCouponServiceImpl();
		return service;
	}

	@Override
	public int insertIssueCoupon(IssueCouponVO ivo) throws RemoteException{
		return dao.insertIssueCoupon(ivo);
	}

	@Override
	public int deleteIssueCoupon(int issue_no) throws RemoteException{
		return dao.deleteIssueCoupon(issue_no);
	}

	@Override
	public List<IssueViewVO> getAllIssueCoupon() throws RemoteException{
		return dao.getAllIssueCoupon();
	}

	@Override
	public int updateCou(int cou_no) throws RemoteException {
		return dao.updateCou(cou_no);
	}

	

}
