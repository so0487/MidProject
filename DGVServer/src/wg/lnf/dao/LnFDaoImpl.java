package wg.lnf.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.LnFVO;

public class LnFDaoImpl implements ILnFDao {
	private static LnFDaoImpl dao;  
	private SqlMapClient smc;


	private LnFDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	

	public static LnFDaoImpl getInstance(){
		if(dao == null) dao = new LnFDaoImpl();

		return dao;
	}

	@Override
	public int insertLnf(LnFVO lnfVo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("lnf.insertLnf",lnfVo);
			if(obj==null) cnt=1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}






	@Override
	public int deleteLnf(String lnf_no) {
		int cnt = 0;

		try {
			cnt = smc.delete("lnf.deleteLnf",lnf_no);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}

	
	
	@Override
	public int updateLnf(LnFVO lnfvo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("lnf.updateLnf",lnfvo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		
		
		return cnt;
	}

	@Override
	public List<LnFVO> getAllLnfList(Map<String, String> searchMap) {
		List<LnFVO> lnfList = null;
		
		try {
			lnfList = smc.queryForList("lnf.getAllLnf",searchMap);
		} catch (SQLException e) {
			lnfList = null;
			e.printStackTrace();
		}
		
		return lnfList;
	}


	@Override
	public int getMax_lnf_no() {
		int lnf_no = 0;
		
		try {
			lnf_no = (int) smc.queryForObject("lnf.getMaxLnfNo");
			int temp = lnf_no+1;
			lnf_no = temp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lnf_no;
	}

	@Override
	public int getLnfCount(Map<String, String> searchMap) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("lnf.getLnfNoCount",searchMap);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}


}
