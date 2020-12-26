package wg.bookGenderCnt.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.bookGenderCnt.dao.BookGenderCntDaoImpl;
import wg.bookGenderCnt.dao.IBookGenderCntDao;
import wg.bookMemberCnt.dao.BookmemberCntDaoImpl;
import wg.bookMemberCnt.dao.IBookMemberCntDao;
import wg.vo.BookGenderCntVO;
import wg.vo.MovieVO;

public class BookGenderCntServiceImpl extends UnicastRemoteObject implements IBookGenderCntService {

	private static BookGenderCntServiceImpl service;
	private IBookGenderCntDao dao;
	
	private BookGenderCntServiceImpl() throws RemoteException{
		dao = BookGenderCntDaoImpl.getInstance();
	}
	
	public static BookGenderCntServiceImpl getInstance() throws RemoteException{
		if(service==null) 
			service = new BookGenderCntServiceImpl();
		
		return service;
	}


	@Override
	public int getbookcount(String movie_name) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getbookcount(movie_name);
	}

	@Override
	public List<String> getbookgenCntList() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getbookgenCntList();
	}


	
	
	
	

}
