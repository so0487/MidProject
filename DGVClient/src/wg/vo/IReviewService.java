package wg.vo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.ReviewVO;
import wg.vo.ReviewViewVO;

public interface IReviewService extends Remote {
	
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
}
