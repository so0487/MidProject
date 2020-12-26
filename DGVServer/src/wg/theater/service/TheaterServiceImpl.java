package wg.theater.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.theater.dao.ITheaterDao;
import wg.theater.dao.TheaterDaoImpl;
import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public class TheaterServiceImpl extends UnicastRemoteObject implements ITheaterService{
	private static TheaterServiceImpl service;
	private ITheaterDao dao;

	// 생성자
	private TheaterServiceImpl()  throws RemoteException{
		//dao = new MemberDaoImpl();
		dao = TheaterDaoImpl.getInstance();
	}

	public static TheaterServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new TheaterServiceImpl();
		return service;
	}

	@Override
	public int insertTheater(TheaterVO thVo) throws RemoteException {
		return dao.insertTheater(thVo);
	}

	@Override
	public List<TheaterVO> getAllTheaterList() throws RemoteException {
		return dao.getAllTheaterList();
	}

	@Override
	public int updateTheater(TheaterVO thVo) throws RemoteException {
		return dao.updateTheater(thVo);
	}

	@Override
	public int deleteTheater(String thId) throws RemoteException {
		return dao.deleteTheater(thId);
	}

	@Override
	public TheaterVO getTheater(String theater_id)throws RemoteException {
		return dao.getTheater(theater_id);
	}

	@Override
	public int updateSeat(String theater_id) throws RemoteException {
		return dao.updateSeat(theater_id);
	}

	@Override
	public List<String> theaterNameListforADay(Map<String, String> selectedMap) throws RemoteException {
		return dao.theaterNameListforADay(selectedMap);
	}

	@Override
	public int insertMovSeat(SeatVO vo) throws RemoteException {
		return dao.insertMovSeat(vo);
	}

	@Override
	public String getMaxTh_id() throws RemoteException {
		return dao.getMaxTh_id();
	}

	@Override
	public int deleteThSeat(String theater_id) throws RemoteException {
		return dao.deleteThSeat(theater_id);
	}

	

}
