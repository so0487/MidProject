package wg.review.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.OnOffViewVO;
import wg.vo.ReviewVO;
import wg.vo.ReviewViewVO;
import wg.vo.ReviewViewVO2;

public class ReviewDaoImpl implements IReviewDao{
	private static ReviewDaoImpl dao;
	private SqlMapClient smc;
	
	
	
	private ReviewDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	
	public static ReviewDaoImpl getInstance() {
		if(dao==null) dao = new ReviewDaoImpl();
		
		return dao;
	}


	@Override
	public int deleteReview(String book_id) {
		int cnt = 0;
		try {
			cnt= smc.delete("review.deleteReview", book_id);
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		} 
		return cnt;
	}


	@Override
	public List<ReviewViewVO> getAllReview() {
		List<ReviewViewVO> oo; 
		  
		try {
			oo = smc.queryForList("review.getAllReview");
			
		} catch (SQLException e) {
			oo= null;
			e.printStackTrace();
		}
		
		return oo;
	}


	@Override
	public int insertReview(ReviewVO reviewVo) {
		int cnt=0;
		try {
		Object obj= smc.insert("review.insertReview", reviewVo);
		if(obj==null) cnt=1;   
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}


	@Override
	public ReviewVO getThisReview(String book_id) {
		ReviewVO rvo = null;
		try {
			rvo = (ReviewVO) smc.queryForObject("review.getThisReview", book_id);
		} catch (SQLException e) {
			rvo = null;
			e.printStackTrace();
		}
		return rvo;
	}


	@Override
	public int updateReview(ReviewVO rvo) {
		int cnt = 0;
		try {
			cnt = smc.update("review.updateReview", rvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int deleteReview2(int rev_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("review.deleteReview2", rev_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public List<ReviewViewVO2> getReviewView(String movie_id) {
		List<ReviewViewVO2> rvvo2 = null;
		try {
			rvvo2 = smc.queryForList("review.getReviewView",movie_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rvvo2;
	}
}
