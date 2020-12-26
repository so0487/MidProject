package wg.buy_detail.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;

import wg.bookmovie.dao.BookMovieDaoImpl;
import wg.vo.Buy_DetailVO;

public class Buy_DetailDaoImpl implements IBuy_DetailDao {
	private static Buy_DetailDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private Buy_DetailDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static Buy_DetailDaoImpl getInstance(){
		if(dao == null) dao = new Buy_DetailDaoImpl();
		
		return dao;
	}

	@Override
	public int insertBuyDet(Buy_DetailVO bdvo) {
		int cnt = 0;
		try {
			Object obj = smc.queryForObject("buy_detail.insertBuyDeatil",bdvo);
			if(obj == null) {
				cnt =1;
			}
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public int deleteBuyDet(int buydet_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("buy_detail.deleteBuyDetail", buydet_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<Buy_DetailVO> getBuyDetail() {
		List<Buy_DetailVO> list = null;
		try {
			list = smc.queryForList("buy_detail.getBuyDetail");
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}
	
	
}
