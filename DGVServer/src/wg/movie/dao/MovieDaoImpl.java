package wg.movie.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.MovieVO;
import wg.vo.MovieViewVO;
import wg.vo.SeatVO;

public class MovieDaoImpl implements IMovieDao {
	private static MovieDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private MovieDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static MovieDaoImpl getInstance(){
		if(dao == null) dao = new MovieDaoImpl();
		
		return dao;
	}

	@Override
	public int insertMovie(MovieVO mvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("movie.insertMovie",mvo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMovie(MovieVO mvo) {
		int cnt = 0;
		try {
			cnt = smc.update("movie.updateMovie",mvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMovie(String movie_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("movie.deleteMovie", movie_id);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MovieVO> getAllMovie() {
		List<MovieVO> mList = null;
		try {
			mList = smc.queryForList("movie.getAllMovie");
		} catch (SQLException e) {
			mList = null;
			e.printStackTrace();
		}
		return mList;
	}

	@Override
	public String getMaxMovie_id() {
		String movie_id = null;
		try {
			movie_id = (String) smc.queryForObject("movie.getMaxMovie_id");
			int temp = Integer.parseInt(movie_id.substring(1))+1;
			movie_id = String.valueOf(temp);

			while (movie_id.length() < 4) {
				movie_id = "0" + movie_id;
			}
			movie_id = "M"+movie_id;
		} catch (SQLException e) {
			movie_id = null;
			e.printStackTrace();
		}
		return movie_id;
	}

	@Override
	public int getMovieCount(Map<String, String> searchMap) {
		int cnt = 0;
		try {
			cnt = (int)smc.queryForObject("movie.getMovieCount", searchMap);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MovieViewVO> listView(Map<String, String> searchMap) {
		List<MovieViewVO> list = null;
		try {
			list = smc.queryForList("movie.getMovieView",searchMap);
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MovieVO> umovNameList(String movie_name) {
		List<MovieVO> list = null;
		try {
			list = smc.queryForList("movie.uMovieName", movie_name);
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}

	@Override
	public List<MovieVO> movieSchList() {
		List<MovieVO> list = null;
		try {
			list = smc.queryForList("movie.movieSchList");
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}

	@Override
	public int umovNameList2(String movie_id) {
		int count = 0;
		try {
			count = (int) smc.queryForObject("movie.uMovieName2", movie_id);
		} catch (SQLException e) {
			count = 0;
		}
		return count;
	}

	@Override
	public String movieAdultCheck(String movie_name) {
		String movie_adult = null;
		try {
			movie_adult = (String) smc.queryForObject("movie.movieAdultCheck", movie_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie_adult;
	}


}
