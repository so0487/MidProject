package wg.preview.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;

public class PreviewDaoImpl implements IPreviewDao{
	private SqlMapClient smc;
	
	private static PreviewDaoImpl dao;
	
	private PreviewDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	
	
	
	public static PreviewDaoImpl getInstance() {
		if(dao == null) dao = new PreviewDaoImpl();
		
		return dao;
	}
}
