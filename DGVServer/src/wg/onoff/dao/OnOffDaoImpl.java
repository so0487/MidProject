package wg.onoff.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.MemberVO;
import wg.vo.OnOffVO;
import wg.vo.OnOffViewVO;

public class OnOffDaoImpl implements IOnOffDao {
	private static OnOffDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private OnOffDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static OnOffDaoImpl getInstance(){
		if(dao == null) dao = new OnOffDaoImpl();
		
		return dao;
	}

	@Override
	public int deleteOnOff(int onNo) {
		int cnt = 0;
		try {
			cnt= smc.delete("onoff.deleteOnOff", onNo);
			
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateOnOff(OnOffVO oovVo) {
		int cnt = 0;
		try {
			cnt= smc.update("onoff.updateOnOff", oovVo);
			
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		} 
		return cnt;
	}
	
	@Override
	public int updateOff(String mem_id) {
		int cnt = 0;
		try {
			cnt= smc.update("onoff.updateOff", mem_id);
			
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<OnOffViewVO> getAllOnOff() {
		List<OnOffViewVO> oo; 
		  
		try {
			oo = smc.queryForList("onoff.getAllOnOff");
			
		} catch (SQLException e) {
			oo= null;
			e.printStackTrace();
		}
		
		return oo;
	}

	@Override
	public int insertOnOff(String mem_id) {
		int cnt=0;
		
		try {
		Object obj= smc.insert("onoff.insertOnOff", mem_id);
		if(obj==null) cnt=1;   
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int getCountOnOff(String mem_id) {
		  int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("onoff.getCountOnOff",mem_id);
			
		} catch (SQLException e) {
			cnt= 0;
			e.printStackTrace();
		}
		
		return cnt;
	}


}
