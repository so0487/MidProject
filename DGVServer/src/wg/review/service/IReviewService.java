package wg.review.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.ReviewVO;
import wg.vo.ReviewViewVO;
import wg.vo.ReviewViewVO2;

public interface IReviewService extends Remote {
	
	public int deleteReview2(int rev_no) throws RemoteException;
	
	public List<ReviewViewVO> getAllReview() throws RemoteException;
	
	public int insertReview(ReviewVO reviewVo) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 * @throws RemoteException
	 */
	public ReviewVO getThisReview(String book_id) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public int deleteReview(String book_id) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param rvo
	 * @return
	 */
	public int updateReview(ReviewVO rvo) throws RemoteException;
	
	/**
	 * 
	 * @author 선미
	 * @param movie_id
	 * @return
	 */
	public List<ReviewViewVO2> getReviewView(String movie_id) throws RemoteException;
}
