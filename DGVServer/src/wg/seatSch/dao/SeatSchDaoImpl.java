package wg.seatSch.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.SeatSchVO;

public class SeatSchDaoImpl implements ISeatSchDao{
	private static SeatSchDaoImpl dao;
	private SqlMapClient smc;
	
	
	private SeatSchDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static SeatSchDaoImpl getInstance() {
		if(dao==null)dao = new SeatSchDaoImpl();
		
		return dao;
	}

	@Override
	public List<SeatSchVO> seatSchforAShow(int show_no) {
		List<SeatSchVO> ssList = null;
		try {
			ssList = smc.queryForList("seatsch.seatSchforAShow",show_no);
		} catch (SQLException e) {
			ssList = null;
			e.printStackTrace();
		}
		return ssList;
	}

	@Override
	public int getSseat_no(Map<String, String> paramMap) {
		int sseat_no = 0;
		try {
			sseat_no = (int) smc.queryForObject("seatsch.getSseat_no", paramMap);
		} catch (SQLException e) {
			sseat_no = 0;
		}
		return sseat_no;
	}

	@Override
	public int getOpenSeatNum(int show_no) {
		int openSeatNum = 0;
		try {
			openSeatNum = (int) smc.queryForObject("seatsch.getOpenSeatNum",show_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return openSeatNum;
	}

	@Override
	public int getSeatNum(int show_no) {
		int seatNum = 0;
		try {
			seatNum = (int) smc.queryForObject("seatsch.getSeatNum",show_no);
		} catch (SQLException e) {
			seatNum = 0;
			e.printStackTrace();
		}
		return seatNum;
	}

	@Override
	public int updateToY(int sseat_no) {
		int cnt = 0;
		try {
			cnt = smc.update("seatsch.updateToY",sseat_no);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertSseat(SeatSchVO svo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("seatsch.insertSseat",svo);
			if(obj==null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public int updateToN(String book_id) {
		int cnt = 0;
		try {
			cnt = smc.update("seatsch.updateToN",book_id);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
}
