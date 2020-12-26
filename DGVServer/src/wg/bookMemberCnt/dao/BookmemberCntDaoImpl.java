package wg.bookMemberCnt.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.BookMemberCntVO;
import wg.vo.MovieVO;

public class BookmemberCntDaoImpl implements IBookMemberCntDao{

	private static BookmemberCntDaoImpl dao;
	private SqlMapClient smc;

	private BookmemberCntDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}

	public static BookmemberCntDaoImpl getInstance() {
		if(dao==null)dao=new BookmemberCntDaoImpl();

		return dao;
	}


	MovieVO movieVo = new MovieVO();

	@Override
	public int getMaleCnt(String movie_id) {
		int cnt=0;

		try {
			cnt = (int) smc.queryForObject("bookmembercount.getMaleCnt",movie_id);

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int getFemaleCnt(String movie_id) {
		int cnt=0;

		try {
			cnt = (int) smc.queryForObject("bookmembercount.getFemaleCnt",movie_id);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}

		return cnt;
	}


	@Override
	public int getAgeCount10(String movie_id) {
		int cnt = 0;
		try {
			Object obj = smc.queryForObject("bookmembercount.getAge10",movie_id);
			if(obj==null) {
				cnt = 0;
			}else {
				cnt = (int) obj;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getAgeCount20(String movie_id) {
		int cnt=0;
		try {
			Object obj = smc.queryForObject("bookmembercount.getAge20",movie_id);
			if(obj==null) {
				cnt = 0;
			}else {
				cnt = (int) obj;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getAgeCount30(String movie_id) {
		int cnt=0;
		try {
			Object obj = smc.queryForObject("bookmembercount.getAge30",movie_id);
			if(obj==null) {
				cnt = 0;
			}else {
				cnt = (int) obj;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getAgeCount40(String movie_id) {
		int cnt=0;
		try {
			Object obj = smc.queryForObject("bookmembercount.getAge40",movie_id);
			if(obj==null) {
				cnt = 0;
			}else {
				cnt = (int) obj;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;

	}

	@Override
	public int getAllMaleCnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("bookmembercount.getAllMaleCnt");
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getAllFemaleCnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("bookmembercount.getAllFemaleCnt");
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
}