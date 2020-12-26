package wg.bookmovie.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.bookmovie.dao.BookMovieDaoImpl;
import wg.bookmovie.dao.IBookMovieDao;
import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;

public class BookMovieServiceImpl extends UnicastRemoteObject implements IBookMovieService{
	private static BookMovieServiceImpl service;
	private IBookMovieDao dao;
	
	
	private BookMovieServiceImpl()  throws RemoteException  {
		dao = BookMovieDaoImpl.getInstance();
	}
	
	public static BookMovieServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new BookMovieServiceImpl();
		return service;
	}

	@Override
	public List<BookMovieViewVO> getAllBookMovieView(String mem_id) throws RemoteException {
		return dao.getAllBookMovieView(mem_id);
	}

	@Override
	public List<String> getSeat_idList(String book_id) throws RemoteException {
		return dao.getSeat_idList(book_id);
	}

	@Override
	public int insertBookMovie(BookMovieVO bvo) throws RemoteException{
		return dao.insertBookMovie(bvo);
	}

	@Override
	public int deleteBookMovie(String book_id) throws RemoteException {
		return dao.deleteBookMovie(book_id);
	}

	@Override
	public String getMaxBook_id() throws RemoteException {
		return dao.getMaxBook_id();
	}

	@Override
	public List<BookMovieViewVO> getAllBook() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllBook();
	}

}
