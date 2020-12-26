package wg.hint.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.HintVO;
import wg.vo.MemberVO;

public class HintDaoImpl implements IHintDao {
	private static HintDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private HintDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static HintDaoImpl getInstance(){
		if(dao == null) dao = new HintDaoImpl();
		
		return dao;
	}

	@Override
	public List<HintVO> getAllHint() {
		List<HintVO> hint; 
		  
		try {
			hint = smc.queryForList("hint.getAllHint");
			
		} catch (SQLException e) {
			hint= null;
			e.printStackTrace();
		}
		
		return hint;
	}

}
