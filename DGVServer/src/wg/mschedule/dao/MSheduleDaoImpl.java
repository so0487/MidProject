package wg.mschedule.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.MScheduleVO;
import wg.vo.MscheduleViewVO;
import wg.vo.ShowInfoVO;

public class MSheduleDaoImpl implements IMScheduleDao {
	private static MSheduleDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private MSheduleDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static MSheduleDaoImpl getInstance(){
		if(dao == null) dao = new MSheduleDaoImpl();
		
		return dao;
	}

	@Override
	public List<MscheduleViewVO> viewList(Map<String, String> param) {
		List<MscheduleViewVO> list = null;
		try {
			list = smc.queryForList("mschedule.msView",param);
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}

	@Override
	public List<MscheduleViewVO> viewMoname(String theater_name) {
		List<MscheduleViewVO> list = null;
		try {
			list = smc.queryForList("mschedule.viewMoname",theater_name);
		} catch (SQLException e) {
			list=null;
		}
		return list;
	}

	@Override
	public int insertMs(MScheduleVO msvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("mschedule.insertMs",msvo);
			
			if(obj==null) {
				cnt=1;
			}
			
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public int deletMs(int show_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("mschedule.deletMs", show_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<ShowInfoVO> getShowInfo(Map<String, String> selectedMap) {
		List<ShowInfoVO> siList = null;
		try {
			siList = smc.queryForList("mschedule.getShowInfo", selectedMap);
		} catch (SQLException e) {
			siList = null;
			e.printStackTrace();
		}
		return siList;
	}

	@Override
	public int getShow_no(Map<String, String> selectedMap) {
		int show_no = 0;
		try {
			show_no = (int) smc.queryForObject("mschedule.getShow_no", selectedMap);
		} catch (SQLException e) {
			show_no=0;
			e.printStackTrace();
		}
		return show_no;
	}

	@Override
	public String getDis_id(int show_no) {
		String dis_id = null;
		try {
			dis_id = (String) smc.queryForObject("mschedule.getDis_id", show_no);
		} catch (SQLException e) {
			dis_id = null;
			e.printStackTrace();
		}
		return dis_id;
	}

	@Override
	public int deletMsSeat(int show_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("mschedule.deletMsSeat", show_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

}
