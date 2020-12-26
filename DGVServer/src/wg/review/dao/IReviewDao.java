package wg.review.dao;

import java.util.List;

import wg.vo.ReviewVO;
import wg.vo.ReviewViewVO;
import wg.vo.ReviewViewVO2;

public interface IReviewDao {
	
	public int deleteReview2(int rev_no);
	
	public List<ReviewViewVO> getAllReview();
	
	public int insertReview(ReviewVO reviewVo);
	

	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public ReviewVO getThisReview(String book_id);
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public int deleteReview(String book_id);
	
	/**
	 * 
	 * @author 선미
	 * @param rvo
	 * @return
	 */
	public int updateReview(ReviewVO rvo);
	
	/**
	 * 
	 * @author 선미
	 * @param movie_id
	 * @return
	 */
	public List<ReviewViewVO2> getReviewView(String movie_id);
	
}
