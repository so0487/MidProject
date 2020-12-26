package wg.genre.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.event.dao.EventDaoImpl;
import wg.vo.GenreVO;

public class GenreDaoImpl implements IGenreDao {
	private static GenreDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private GenreDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static GenreDaoImpl getInstance(){
		if(dao == null) dao = new GenreDaoImpl();
		
		return dao;
	}

	@Override
	public int insertGenre(GenreVO gvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("genre.insertGenre",gvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateGenre(GenreVO gvo) {
		int cnt = 0;
		try {
			cnt = smc.update("genre.updateGenre",gvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteGenre(String gen_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("genre.deleteGenre",gen_id);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<GenreVO> getAllGenre() {
		List<GenreVO> gList = null;
		try {
			gList = smc.queryForList("genre.getAllGenre");
		} catch (SQLException e) {
			gList = null;
			e.printStackTrace();
		}
		return gList;
	}

	@Override
	public String getMaxGen_id() {
		String gen_id = null;
		try {
			gen_id = (String) smc.queryForObject("genre.getMaxGen_id");
			int temp = Integer.parseInt(gen_id.substring(1))+1;
			gen_id = String.valueOf(temp);

			while (gen_id.length() < 4) {
				gen_id = "0" + gen_id;
			}
			gen_id = "G"+gen_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gen_id;
	}
}
