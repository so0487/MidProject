package wg.payMethod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.GenreVO;
import wg.vo.PayMethod;

public class PayMethodDaoImpl implements PayMethodDao {
	private static PayMethodDaoImpl dao;
	private SqlMapClient smc;
	
	private PayMethodDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static PayMethodDaoImpl getInstance() {
		if(dao==null) dao = new PayMethodDaoImpl();
		return dao;
	}

	@Override
	public List<PayMethod> getPayMethod() {
		// TODO Auto-generated method stub
		List<PayMethod> list = null;
		try {
			list = smc.queryForList("genre.getAllGenre");
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}
}
