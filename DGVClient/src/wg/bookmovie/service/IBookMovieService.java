package wg.bookmovie.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;

public interface IBookMovieService extends Remote {
	
	/**
	 * 
	 * @author 선미
	 * @param mem_id
	 * @return
	 */
	public List<BookMovieViewVO> getAllBookMovieView(String mem_id) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public List<String> getSeat_idList(String book_id) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param bvo
	 * @return
	 */
	public int insertBookMovie(BookMovieVO bvo) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public int deleteBookMovie(String book_id) throws RemoteException;
	
	/**
	 * 예매id 생성을 위하여 DB의 BookMovie테이블의 book_id중 가장 큰 값을  반환하는 메서드
	 * @author 선미
	 * @return 가장 큰 book_id
	 */
	public String getMaxBook_id() throws RemoteException;
	/**
	 * 관리자가 전체 예매 내역을 조회하는 매서드
	 * @author 이명석
	 * @return 전체 예매 내역
	 */
	public List<BookMovieViewVO> getAllBook() throws RemoteException;
}
