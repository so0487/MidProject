package wg.payDetail.dao;

import java.sql.SQLException;

import org.apache.log4j.net.SMTPAppender;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.PayDetailVO;

public class PayDetailDaoImpl implements IPayDetailDao {
	private static PayDetailDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private PayDetailDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static PayDetailDaoImpl getInstance(){
		if(dao == null) dao = new PayDetailDaoImpl();
		
		return dao;
	}

	@Override
	public int payDetBookMovie(PayDetailVO pdvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("paydetail.insertPayDetail", pdvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int refundDetBookMovie(int paydet_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("paydetail.deletePayDetail", paydet_no);
		} catch (SQLException e) {
			cnt =0;
			e.printStackTrace();
		}
		return cnt;
	}


}
