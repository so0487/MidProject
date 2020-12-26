package wg.book_detail.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.bid.dao.BidDaoImpl;
import wg.vo.Book_DetailVO;
import wg.vo.OnOffViewVO;

public class Book_DetailDaoImpl implements IBook_DetailDao {
	private static Book_DetailDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private Book_DetailDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static Book_DetailDaoImpl getInstance(){
		if(dao == null) dao = new Book_DetailDaoImpl();
		
		return dao;
	}

	@Override
	public int insertBookDet(Book_DetailVO bdvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("book_detail.insertBookDeatil", bdvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBookDet(int bdet_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("book_detail.deleteBookDetail",bdet_no);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Book_DetailVO> getBookDetail() {
		List<Book_DetailVO> bd; 
		  
		try {
			bd = smc.queryForList("book_detail.getBookDetail");
			
		} catch (SQLException e) {
			bd= null;
			e.printStackTrace();
		}
		
		return bd;
	}
}
