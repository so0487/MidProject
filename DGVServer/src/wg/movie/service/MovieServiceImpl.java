package wg.movie.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.movie.dao.IMovieDao;
import wg.movie.dao.MovieDaoImpl;
import wg.vo.MovieVO;
import wg.vo.MovieViewVO;
import wg.vo.SeatVO;

public class MovieServiceImpl extends UnicastRemoteObject implements IMovieService {
	private static MovieServiceImpl service;
	private IMovieDao dao;
	
	
	private MovieServiceImpl()  throws RemoteException  {
		dao = MovieDaoImpl.getInstance();
	}
	
	public static MovieServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new MovieServiceImpl();
		return service;
	}

	@Override
	public int insertMovie(MovieVO mvo) throws RemoteException {
		return dao.insertMovie(mvo);
	}

	@Override
	public int updateMovie(MovieVO mvo) throws RemoteException {
		return dao.updateMovie(mvo);
	}

	@Override
	public int deleteMovie(String Movie_id) throws RemoteException {
		return dao.deleteMovie(Movie_id);
	}

	@Override
	public List<MovieVO> getAllMovie() throws RemoteException {
		return dao.getAllMovie();
	}

	@Override
	public String getMaxMovie_id() throws RemoteException {
		return dao.getMaxMovie_id();
	}

	@Override
	public int getMovieCount(Map<String, String> searchMap) throws RemoteException {
		return dao.getMovieCount(searchMap);
	}

	@Override
	public List<MovieViewVO> listView(Map<String, String> searchMap) throws RemoteException {
		return dao.listView(searchMap);
	}

	@Override
	public List<MovieVO> umovNameList(String movie_name) throws RemoteException {
		return dao.umovNameList(movie_name);
	}

	@Override
	public List<MovieVO> movieSchList() throws RemoteException {
		return dao.movieSchList();
	}

	@Override
	public int umovNameList2(String movie_id) throws RemoteException {
		return dao.umovNameList2(movie_id);
	}

	@Override
	public String movieAdultCheck(String movie_name) throws RemoteException {
		return dao.movieAdultCheck(movie_name);
	}

}
