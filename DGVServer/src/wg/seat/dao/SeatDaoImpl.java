package wg.seat.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public class SeatDaoImpl implements ISeatDao{
	private static SeatDaoImpl dao;
	private SqlMapClient smc;
	
	private SeatDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	
	public static SeatDaoImpl getInstance() {
		if(dao==null) dao = new SeatDaoImpl();
		return dao;
	}


	@Override
	public List<SeatVO> getSeatList(String th_name) {
		List<SeatVO> list = null;
		try {
			list = smc.queryForList("seat.getSeatList", th_name);
		} catch (SQLException e) {
			list=null;
		}
		return list;
	}


	@Override
	public int insertSeat(SeatVO seatVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("seat.insertSeat",seatVo);
			
			if(obj == null) {
				cnt =1;
			}
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}


	@Override
	public int updateSeat(SeatVO seatVo) {
		int cnt = 0;
		try {
			cnt = smc.update("seat.updateSeat", seatVo);
		} catch (SQLException e) {
			cnt =0;
		}
		return cnt;
	}


	@Override
	public int deletSeat(Map<String, String> param) {
		int cnt = 0;
		try {
			cnt = smc.delete("seat.deleteSeat",param);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}


	@Override
	public List<TheaterVO> getThNameList() {
		List<TheaterVO> list = null;
		try {
			list = smc.queryForList("seat.getThList");
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}


	@Override
	public List<SeatVO> getAllseatList() {
		List<SeatVO> list = null;
		try {
			list = smc.queryForList("seat.getAllSeat");
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}
}
