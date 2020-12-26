package wg.theater.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;

import util.BuildSqlMapClient;
import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public class TheaterDaoImpl implements ITheaterDao{
	private static TheaterDaoImpl dao;
	private SqlMapClient smc;
	
	private TheaterDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static TheaterDaoImpl getInstance() {
		if(dao==null)dao = new TheaterDaoImpl();
		
		return dao;
	}

	@Override
	public int insertTheater(TheaterVO thVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("theater.insertTheater", thVo);
			
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<TheaterVO> getAllTheaterList() {
		List<TheaterVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("theater.selectAllTheater");
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}

	@Override
	public int updateTheater(TheaterVO thVo) {
		int cnt = 0;
		try {
			cnt = smc.update("theater.updateTheater", thVo);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public int deleteTheater(String thId) {
		int cnt = 0;
		try {
			cnt = smc.delete("theater.deleteTheater", thId);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public TheaterVO getTheater(String theater_id) {
		TheaterVO vo = null;
		try {
			vo =(TheaterVO) smc.queryForObject("theater.selectTheater",theater_id);
		} catch (SQLException e) {
			vo=null;
		}
		return vo;
	}

	@Override
	public int updateSeat(String theater_id) {
		int cnt = 0;
		try {
			cnt = smc.update("theater.updateSeatNum",theater_id);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<String> theaterNameListforADay(Map<String, String> selectedMap) {
		List<String> tnList = null;
		try {
			tnList = smc.queryForList("theater.theaterNameListforADay", selectedMap);
		} catch (SQLException e) {
			tnList = null;
			e.printStackTrace();
		} 
		return tnList;
	}

	@Override
	public int insertMovSeat(SeatVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("theater.insertTheSeat",vo);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return cnt;
	}

	@Override
	public String getMaxTh_id() {
		String theater_id = null;
		try {
			theater_id = (String) smc.queryForObject("theater.getMaxTh_id");
			if(theater_id == null) return "T0001";
			int temp = Integer.parseInt(theater_id.substring(1))+1;
			theater_id = String.valueOf(temp);
			
			while(theater_id.length() < 4) {
				theater_id = "0" + theater_id;
			}
			theater_id = "T"+theater_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theater_id;
	}

	@Override
	public int deleteThSeat(String theater_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("theater.deleteThSeat", theater_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
