package wg.bid.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.BidResultVO;
import wg.vo.BidVO;

public class BidDaoImpl implements IBidDao {
	private static BidDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private BidDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static BidDaoImpl getInstance(){
		if(dao == null) dao = new BidDaoImpl();
		
		return dao;
	}
	
	@Override
	public int bidPrice(int sseat_no) {
		int price = 0;
		try {
			price = (int) smc.queryForObject("bid.bidPrice",sseat_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return price;
	}

	@Override
	public int insertBid(BidVO bidvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("bid.insertBid",bidvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BidVO> getAllList() {
		List<BidVO> list;
		try {
			list = smc.queryForList("bid.getAllList");
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BidResultVO resultBid(int sseat_no) {
		BidResultVO vo;
		try {
			vo = (BidResultVO) smc.queryForObject("bid.resultBid","sseat_no");
			
		} catch (SQLException e) {
			vo = null;
			e.printStackTrace();
		}
		return vo;
		
	}

	@Override
	public String getMax() {
		String bid_id = null;
		try {
			bid_id = (String) smc.queryForObject("bid.getMax");
			int temp = Integer.parseInt(bid_id.substring(1))+1;
			bid_id = String.valueOf(temp);

			while (bid_id.length() < 4) {
				bid_id = "0" + bid_id;
			}
			bid_id = "A"+bid_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bid_id;
	}
}
