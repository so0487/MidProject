package wg.bookMemberCnt.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import wg.bookMemberCnt.dao.BookmemberCntDaoImpl;
import wg.bookMemberCnt.dao.IBookMemberCntDao;

public class BookMemberCntServiceImpl extends UnicastRemoteObject implements IBookMemberCntService {

	private static BookMemberCntServiceImpl service;
	private IBookMemberCntDao dao;



	private BookMemberCntServiceImpl() throws RemoteException{
		dao = BookmemberCntDaoImpl.getInstance();
	}

	public static BookMemberCntServiceImpl getInstance() throws RemoteException{
		if(service==null)
			service = new BookMemberCntServiceImpl();
		return service;
	}

	@Override
	public int getMaleCnt(String movie_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getMaleCnt(movie_id);
	}

	@Override
	public int getFemaleCnt(String movie_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getFemaleCnt(movie_id);
	}


	@Override
	public int getAgeCount10(String movie_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAgeCount10(movie_id);
	}

	@Override
	public int getAgeCount20(String movie_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAgeCount20(movie_id);
	}

	@Override
	public int getAgeCount30(String movie_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAgeCount30(movie_id);
	}

	@Override
	public int getAgeCount40(String movie_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAgeCount40(movie_id);
	}

	@Override
	public int getAllMaleCnt() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllMaleCnt();
	}

	@Override
	public int getAllFemaleCnt()  throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllFemaleCnt();
	}

}
