package wg.book_detail.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.bid.dao.BidDaoImpl;
import wg.vo.Book_DetailVO;

public interface IBook_DetailDao {
	
	/**
	 * 
	 * @author 선미
	 * @param bdvo
	 * @return
	 */
	public int insertBookDet(Book_DetailVO bdvo);
	
	
	/**
	 * 
	 * @author 선미
	 * @param bdet_no
	 * @return
	 */
	public int deleteBookDet(int bdet_no);
	
	public List<Book_DetailVO> getBookDetail();
}
