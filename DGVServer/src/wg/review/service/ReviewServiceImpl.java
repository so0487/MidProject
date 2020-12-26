package wg.review.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.review.dao.IReviewDao;
import wg.review.dao.ReviewDaoImpl;
import wg.vo.ReviewVO;
import wg.vo.ReviewViewVO;
import wg.vo.ReviewViewVO2;

public class ReviewServiceImpl extends UnicastRemoteObject implements IReviewService{
	private  static ReviewServiceImpl service;
	private IReviewDao dao;
	
	
	
	private ReviewServiceImpl() throws RemoteException{
		dao = ReviewDaoImpl.getInstance();
	}
	
	
	
	public static ReviewServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new ReviewServiceImpl();
		
		return service;
	}



	@Override
	public int deleteReview(String book_id) throws RemoteException {
		return dao.deleteReview(book_id);
	}



	@Override
	public List<ReviewViewVO> getAllReview() throws RemoteException {
		return dao.getAllReview();
	}



	@Override
	public int insertReview(ReviewVO reviewVo) throws RemoteException {
		return dao.insertReview(reviewVo);
	}



	@Override
	public ReviewVO getThisReview(String book_id) throws RemoteException {
		return dao.getThisReview(book_id);
	}



	@Override
	public int updateReview(ReviewVO rvo) throws RemoteException {
		return dao.updateReview(rvo);
	}



	@Override
	public int deleteReview2(int rev_no) throws RemoteException {
		return dao.deleteReview2(rev_no);
	}



	@Override
	public List<ReviewViewVO2> getReviewView(String movie_id) throws RemoteException {
		return dao.getReviewView(movie_id);
	}
	
}






