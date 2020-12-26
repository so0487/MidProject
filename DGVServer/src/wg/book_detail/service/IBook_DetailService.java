package wg.book_detail.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.Book_DetailVO;

public interface IBook_DetailService extends Remote {
	/**
	 * 
	 * @author 선미
	 * @param bdvo
	 * @return
	 */
	public int insertBookDet(Book_DetailVO bdvo) throws RemoteException;
	
	
	/**
	 * 
	 * @author 선미
	 * @param bdet_no
	 * @return
	 */
	public int deleteBookDet(int bdet_no) throws RemoteException;
	
	public List<Book_DetailVO> getBookDetail() throws RemoteException;
}
