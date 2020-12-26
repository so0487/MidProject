package wg.snackSet.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.SnackSetVO;

public class SnackSetDaoImpl implements ISnackSetDao{
	//mvc패턴 작업
	private static SnackSetDaoImpl dao;
	private SqlMapClient smc;

	private SnackSetDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}

	public static SnackSetDaoImpl getInstance() {
		if(dao==null)dao = new SnackSetDaoImpl();

		return dao;
	}
	
	
	
	
	
	
	//insert
	@Override
	public int insertSnackSet(SnackSetVO snackSetVo) {
		int cnt = 0;


		try {
			Object obj = smc.insert("snackSet.insertSnackSet",snackSetVo);
			if(obj==null) cnt=1;
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}

		return cnt;
	}

	
	
	
	//delete
	@Override
	public int deleteSnackSet(String setId) {
		int cnt=0;
		
		try {
			cnt = smc.delete("snackSet.deleteSnackSet", setId);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		
		
		return cnt;
	}

	
	
	
	
	
	//update
	@Override
	public int updateSnackSet(SnackSetVO snackSetVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("snackSet.updateSnackSet",snackSetVo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
				
		return cnt;
	}
	
	
	//모든 스낵세드리스트의 정보를 가져오기
	@Override
	public List<SnackSetVO> getAllSnackSetList() {


		List<SnackSetVO>snackSetList = null;
		
		try {
			snackSetList = smc.queryForList("snackSet.getAllSnackSetList");
		} catch (SQLException e) {
			snackSetList = null;
			e.printStackTrace();
		}
		return snackSetList;
	}

	

	@Override
	public String getMaxSetId() {
		String set_id = null;
		
		try {
			set_id = (String) smc.queryForObject("snackSet.getMaxSetId");
			int temp = Integer.parseInt(set_id.substring(1))+1;
			set_id = String.valueOf(temp);
			
			
			while(set_id.length()<4) {
				set_id = "0"+set_id;
			}
			
			set_id = "N"+set_id;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return set_id;
	}

	
	
	

}
