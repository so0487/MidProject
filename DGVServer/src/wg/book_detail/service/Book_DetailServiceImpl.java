package wg.book_detail.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.book_detail.dao.Book_DetailDaoImpl;
import wg.book_detail.dao.IBook_DetailDao;
import wg.vo.Book_DetailVO;

public class Book_DetailServiceImpl extends UnicastRemoteObject implements IBook_DetailService {
	private static Book_DetailServiceImpl service;
	private IBook_DetailDao dao;
	
	
	private Book_DetailServiceImpl()  throws RemoteException  {
		dao = Book_DetailDaoImpl.getInstance();
	}
	
	public static Book_DetailServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new Book_DetailServiceImpl();
		return service;
	}

	@Override
	public int insertBookDet(Book_DetailVO bdvo) throws RemoteException {
		return dao.insertBookDet(bdvo);
	}

	@Override
	public int deleteBookDet(int bdet_no) throws RemoteException {
		return dao.deleteBookDet(bdet_no);
	}

	@Override
	public List<Book_DetailVO> getBookDetail() throws RemoteException {
		return dao.getBookDetail();
	}
}
