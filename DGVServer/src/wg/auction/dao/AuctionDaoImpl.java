package wg.auction.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.AuctionTTVO;
import wg.vo.AuctionVO;
import wg.vo.MemberVO;
import wg.vo.SeatSchVO;



public class AuctionDaoImpl implements IAuctionDao {
	private static AuctionDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private AuctionDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static AuctionDaoImpl getInstance(){
		if(dao == null) dao = new AuctionDaoImpl();
		
		return dao;
	}

	@Override
	public List<AuctionVO> getAllAuctionList() {
		List<AuctionVO> list; 
		  
		try {
			list = smc.queryForList("auction.getAllAuction");
			
		} catch (SQLException e) {
			list= null;
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insertAuction(AuctionVO aucvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("auction.insertAuction",aucvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateAuction(AuctionVO aucvo) {
		int cnt = 0;
		try {
			cnt = smc.update("auction.updateAuction", aucvo);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public int deleteAuction(int auc_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("auction.deleteAuction", auc_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public String aucPoster(int sseat_no) {
		String poster = null;
		try {
			poster = (String) smc.queryForObject("auction.aucPoster",sseat_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return poster;
		
	}

	@Override
	public int findSseat(int sseat_no) {
		int cnt = 0;
		try {
			cnt =  (int) smc.queryForObject("auction.findSseat", sseat_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<AuctionTTVO> searchtt(AuctionTTVO vo) {
		List<AuctionTTVO> list;
		try {
			list =smc.queryForList("auction.searchtt",vo);
		} catch (Exception e) {
			list = null;
		}
		return list;
		
	}

	@Override
	public List<SeatSchVO> searchseat(Map<String, String> paramMap) {
		List<SeatSchVO> list;
		try {
			list =smc.queryForList("auction.searchseat",paramMap);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	
}
