package wg.smovie.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.smovie.dao.ISMovieDao;
import wg.smovie.dao.SMovieDaoImpl;
import wg.vo.SMovieVO;
import wg.vo.SMovieViewVO;
import wg.vo.SMovieViewVO2;
import wg.vo.SmovieShortInfoVO;

public class SMovieServiceImpl extends UnicastRemoteObject implements ISMovieService{
	
	private static SMovieServiceImpl service;
	private ISMovieDao dao;
	
	// 생성자
	private SMovieServiceImpl()  throws RemoteException{
		//dao = new MemberDaoImpl();
		dao = SMovieDaoImpl.getInstance();
	}
	
	public static SMovieServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new SMovieServiceImpl();
		return service;
	}

	@Override
	public int insertSmovie(SMovieVO smo) throws RemoteException {
		return dao.insertSmovie(smo);
	}

	@Override
	public List<SMovieVO> getAllSmovie() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateSmovie(SMovieVO smo) throws RemoteException {
		return dao.updateSmovie(smo);
	}

	@Override
	public int deletSmovie(int smovie_no) throws RemoteException {
		return dao.deletSmovie(smovie_no);
	}

	@Override
	public List<SMovieViewVO> viewList() throws RemoteException {
		return dao.viewList();
	}

	@Override
	public List<SMovieViewVO> viewList2(String th_id) throws RemoteException {
		return dao.viewList2(th_id);
	}

	@Override
	public List<SmovieShortInfoVO> sMovieNameList() throws RemoteException {
		return dao.sMovieNameList();
	}

	@Override
	public List<SMovieViewVO2> viewList3(Map<String, String> param) throws RemoteException {
		return dao.viewList3(param);
	}
	
	
	

}
