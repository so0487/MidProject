package wg.buysnack.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;

import wg.buy_detail.dao.IBuy_DetailDao;
import wg.vo.BookMovieViewVO;
import wg.vo.BuySnackVO;
import wg.vo.SnackUserViewVO;

public class BuySnackDaoImpl implements IBuySnackDao  {
	private static BuySnackDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private BuySnackDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static BuySnackDaoImpl getInstance(){
		if(dao == null) dao = new BuySnackDaoImpl();
		
		return dao;
	}

	@Override
	public List<SnackUserViewVO> getBuySnack(String mem_id) {
		List<SnackUserViewVO> bmList; 
		try {
			bmList = smc.queryForList("buysnack.getBuySnack", mem_id);
		} catch (SQLException e) {
			bmList= null;
			e.printStackTrace();
		}
		return bmList;
	}

	@Override
	public List<SnackUserViewVO> getBuyAllSnack() {
		List<SnackUserViewVO> bmList; 
		try {
			bmList = smc.queryForList("buysnack.getBuyAllSnack");
		} catch (SQLException e) {
			bmList= null;
			e.printStackTrace();
		}
		return bmList;
	}

	@Override
	public int insertBuysnack(BuySnackVO svo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("buysnack.insertBuysnack", svo);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public String getMaxBuy_id() {
		String buy_id = null;
		try {
			buy_id = (String) smc.queryForObject("buysnack.getMaxBuy_id");
			if(buy_id==null) return "S0001";
			int temp = Integer.parseInt(buy_id.substring(1))+1;
			buy_id = String.valueOf(temp);
			
			while(buy_id.length() < 4) {
				buy_id = "0"+buy_id;
			}
			buy_id = "S"+buy_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buy_id;
	}
}
