package wg.pay.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.PayVO;

public class PayDaoImpl implements IPayDao {
	private static PayDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private PayDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static PayDaoImpl getInstance(){
		if(dao == null) dao = new PayDaoImpl();
		
		return dao;
	}

	@Override
	public int payBookMovie(PayVO pvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("pay.insertPay",pvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int refundBookMovie(int pay_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("pay.deletePay", pay_no);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getPay_no(String book_id) {
		int pay_no = 0;
		try {
			pay_no = (int) smc.queryForObject("pay.getPay_no",book_id);
		} catch (SQLException e) {
			pay_no = 0;
			e.printStackTrace();
		} 
		return pay_no;
	}

	@Override
	public int updatePayRefund(String book_id) {
		int cnt = 0;
		try {
			cnt= smc.update("pay.updatePayRefund", book_id);
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateSnackPayRefund(String buy_id) {
		int cnt = 0;
		try {
			cnt= smc.update("pay.updateSnackPayRefund", buy_id);
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int paySnack(PayVO pvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("pay.insertPayBuy", pvo);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertPayBid(PayVO pvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("pay.insertPayBid", pvo);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}


	
	

}
