package wg.bookGenderCnt.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.BookGenderCntVO;
import wg.vo.BookMemberCntVO;
import wg.vo.MovieVO;

public class BookGenderCntDaoImpl implements IBookGenderCntDao{

	private static BookGenderCntDaoImpl dao;
	private SqlMapClient smc;
	
	
	
	private BookGenderCntDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static BookGenderCntDaoImpl getInstance() {
		if(dao==null)
			dao=new BookGenderCntDaoImpl();
		return dao;
	}


	@Override
	public int getbookcount(String movie_name) {
		int cnt=0;
		
		try {
			cnt = (int) smc.queryForObject("bookgenCnt.getbookcount",movie_name);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return cnt;
	}

	@Override
	public List<String> getbookgenCntList() {
		List<String> bookgenList = null;
		
		
		try {
			bookgenList = smc.queryForList("bookgenCnt.bookgenCnt");
		} catch (SQLException e) {
			bookgenList = null;
			e.printStackTrace();
		}
		
		return bookgenList;
	}
	
	



}