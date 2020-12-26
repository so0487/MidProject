package wg.bookmovie.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.book_detail.dao.Book_DetailDaoImpl;
import wg.vo.BookMovieVO;
import wg.vo.BookMovieViewVO;
import wg.vo.MemberVO;

public class BookMovieDaoImpl implements IBookMovieDao {
	private static BookMovieDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private BookMovieDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static BookMovieDaoImpl getInstance(){
		if(dao == null) dao = new BookMovieDaoImpl();
		
		return dao;
	}

	@Override
	public List<BookMovieViewVO> getAllBookMovieView(String mem_id) {
		List<BookMovieViewVO> bmList; 
		try {
			bmList = smc.queryForList("bookmovie.getAllBookMovieView", mem_id);
		} catch (SQLException e) {
			bmList= null;
			e.printStackTrace();
		}
		return bmList;
	}

	@Override
	public List<String> getSeat_idList(String book_id) {
		List<String> sidList;
		try {
			sidList = smc.queryForList("bookmovie.getSeat_idList",book_id);
		} catch (SQLException e) {
			sidList = null;
			e.printStackTrace();
		}
		return sidList;
	}

	@Override
	public int insertBookMovie(BookMovieVO bvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("bookmovie.insertBookMovie", bvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBookMovie(String book_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("bookmovie.deleteBookMovie", book_id);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String getMaxBook_id() {
		String book_id = null;
		try {
			book_id = (String) smc.queryForObject("bookmovie.getMaxBook_id");
			if(book_id==null) return "B0001";
			int temp = Integer.parseInt(book_id.substring(1))+1;
			book_id = String.valueOf(temp);

			while (book_id.length() < 4) {
				book_id = "0" + book_id;
			}
			book_id = "B"+book_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book_id;
	}

	@Override
	public List<BookMovieViewVO> getAllBook() {
		List<BookMovieViewVO> bmList; 
		try {
			bmList = smc.queryForList("bookmovie.getAllBook");
		} catch (SQLException e) {
			bmList= null;
			e.printStackTrace();
		}
		return bmList;
	}
	
	
}
