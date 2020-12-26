package wg.issueCoupon.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.IssueCouponVO;
import wg.vo.IssueViewVO;

public class IssueCouponDaoImpl implements IIssueCouponDao {
	private static IssueCouponDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private IssueCouponDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static IssueCouponDaoImpl getInstance(){
		if(dao == null) dao = new IssueCouponDaoImpl();
		
		return dao;
	}

	@Override
	public int insertIssueCoupon(IssueCouponVO ivo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("issueCoupon.insertIssue",ivo);
			if (obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteIssueCoupon(int issue_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("issueCoupon.deleteIssue",issue_no);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<IssueViewVO> getAllIssueCoupon() {
		List<IssueViewVO> iList = null;
		try {
			iList = smc.queryForList("issueCoupon.getAllIssue");
		} catch (SQLException e) {
			iList = null;
			e.printStackTrace();
		}
		return iList;
	}

	@Override
	public int updateCou(int cou_no) {
		int cnt = 0;
		try {
			cnt = smc.update("issueCoupon.updateCouUse", cou_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}



}
