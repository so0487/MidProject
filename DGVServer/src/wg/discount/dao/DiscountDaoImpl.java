package wg.discount.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.coupon.dao.CouponDaoImpl;
import wg.vo.DiscountVO;

public class DiscountDaoImpl implements IDiscountDao {
	private static DiscountDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private DiscountDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static DiscountDaoImpl getInstance(){
		if(dao == null) dao = new DiscountDaoImpl();
		
		return dao;
	}

	@Override
	public int insertDiscount(DiscountVO dvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("discount.insertDiscount",dvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateDiscount(DiscountVO dvo) {
		int cnt = 0;
		try {
			cnt = smc.update("discount.updateDiscount",dvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteDiscount(String dis_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("discount.deleteDiscount",dis_id);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<DiscountVO> getAllDiscount() {
		List<DiscountVO> dList = null;
		try {
			dList = smc.queryForList("discount.getAllDiscount");
		} catch (SQLException e) {
			dList = null;
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public String getMaxDis_id() {
		String dis_id = null;
		try {
			dis_id = (String) smc.queryForObject("genre.getMaxGen_id");
			int temp = Integer.parseInt(dis_id.substring(1))+1;
			dis_id = String.valueOf(temp);

			while (dis_id.length() < 4) {
				dis_id = "0" + dis_id;
			}
			dis_id = "D"+dis_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dis_id;
	}
}
