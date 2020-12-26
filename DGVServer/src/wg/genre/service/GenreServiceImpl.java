package wg.genre.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import wg.genre.dao.GenreDaoImpl;
import wg.genre.dao.IGenreDao;
import wg.vo.GenreVO;

public class GenreServiceImpl extends UnicastRemoteObject implements IGenreService {
	private static GenreServiceImpl service;
	private IGenreDao dao;
	
	
	private GenreServiceImpl()  throws RemoteException  {
		dao = GenreDaoImpl.getInstance();
	}
	
	public static GenreServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new GenreServiceImpl();
		return service;
	}

	@Override
	public int insertGenre(GenreVO gvo) throws RemoteException {
		return dao.insertGenre(gvo);
	}

	@Override
	public int updateGenre(GenreVO gvo) throws RemoteException {
		return dao.updateGenre(gvo);
	}

	@Override
	public int deleteGenre(String gen_id) throws RemoteException {
		return dao.deleteGenre(gen_id);
	}

	@Override
	public List<GenreVO> getAllGenre() throws RemoteException {
		return dao.getAllGenre();
	}

	@Override
	public String getMaxGen_id() throws RemoteException {
		return dao.getMaxGen_id();
	}
}
