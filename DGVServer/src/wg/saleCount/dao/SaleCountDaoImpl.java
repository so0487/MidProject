package wg.saleCount.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;

public class SaleCountDaoImpl implements ISaleCountDao{
	private static SaleCountDaoImpl dao;
	private SqlMapClient smc;

	private SaleCountDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}

	public static SaleCountDaoImpl getInstance() {
		if(dao==null)dao=new SaleCountDaoImpl();

		return dao;
	}

	@Override
	public int getSaleCount(String pay_time) {
		int cnt = 0;

		try {
			cnt = (int) smc.queryForObject("saleCount.getSaleCountMonth",pay_time);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}
}
