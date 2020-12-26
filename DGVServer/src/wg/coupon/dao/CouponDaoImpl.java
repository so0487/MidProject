package wg.coupon.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.buysnack.dao.BuySnackDaoImpl;
import wg.vo.CouponUserViewVO;
import wg.vo.CouponVO;

public class CouponDaoImpl implements ICouponDao {
	private static CouponDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private CouponDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static CouponDaoImpl getInstance(){
		if(dao == null) dao = new CouponDaoImpl();
		return dao;
	}

	@Override
	public int insertCoupon(CouponVO cvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("coupon.insertCoupon",cvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateCoupon(CouponVO cvo) {
		int cnt = 0;
		try {
			cnt = smc.update("coupon.updateCoupon",cvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteCoupon(String cou_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("coupon.deleteCoupon",cou_id);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<CouponVO> getAllCoupon() {
		List<CouponVO> cList = null;
		try {
			cList = smc.queryForList("coupon.getAllCoupon");
		} catch (SQLException e) {
			cList = null;
			e.printStackTrace();
		}
		return cList;
	}

	@Override
	public String getMaxCou_id() {
		String cou_id = null;
		try {
			cou_id = (String) smc.queryForObject("coupon.getMaxCou_id");
			int temp = Integer.parseInt(cou_id.substring(1))+1;
			cou_id = String.valueOf(temp);

			while (cou_id.length() < 4) {
				cou_id = "0" + cou_id;
			}
			cou_id = "C"+cou_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cou_id;
	}

	@Override
	public List<CouponUserViewVO> userCouponList(String mem_id) {
		List<CouponUserViewVO> list = null;
		try {
			list = smc.queryForList("coupon.userCoupon", mem_id);
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}
}
