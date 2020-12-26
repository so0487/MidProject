package wg.smovie.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.SMovieVO;
import wg.vo.SMovieViewVO;
import wg.vo.SMovieViewVO2;
import wg.vo.SmovieShortInfoVO;

public class SMovieDaoImpl implements ISMovieDao{
	private static SMovieDaoImpl dao;
	private SqlMapClient smc;
	
	private SMovieDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static SMovieDaoImpl getInstance() {
		if(dao==null)dao = new SMovieDaoImpl();
		
		return dao;
	}

	@Override
	public int insertSmovie(SMovieVO smo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("smovie.insertSmovie", smo);
			
			if(obj==null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<SMovieVO> getAllSmovie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateSmovie(SMovieVO smo) {
		int cnt = 0;
		try {
			cnt = smc.update("smovie.updateSmovie", smo);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public int deletSmovie(int smvoie_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("smovie.deletSmovie", smvoie_no);
		} catch (SQLException e) {
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<SMovieViewVO> viewList() {
		List<SMovieViewVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("smovie.viewList");
		} catch (SQLException e) {
			list=null;
		}
		return list;
	}

	@Override
	public List<SMovieViewVO> viewList2(String th_id) {
		List<SMovieViewVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("smovie.viewList2", th_id);
		} catch (SQLException e) {
			list=null;
		}
		return list;
	}

	@Override
	public List<SmovieShortInfoVO> sMovieNameList() {
		List<SmovieShortInfoVO> smnList = null;
		try {
			smnList = smc.queryForList("smovie.sMovieNameList");
		} catch (SQLException e) {
			smnList = null;
			e.printStackTrace();
		}
		return smnList;
	}

	@Override
	public List<SMovieViewVO2> viewList3(Map<String, String> param) {
		List<SMovieViewVO2> list = new ArrayList<>();
		try {
			list = smc.queryForList("smovie.viewList3", param);
		} catch (SQLException e) {
			list=null;
		}
		return list;
	}
}
