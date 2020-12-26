package wg.seat.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.seat.dao.ISeatDao;
import wg.seat.dao.SeatDaoImpl;
import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public class SeatServiceImpl extends UnicastRemoteObject implements ISeatService {
	private static SeatServiceImpl service;
	private ISeatDao dao;
	
	
	private SeatServiceImpl () throws RemoteException{
		dao = SeatDaoImpl.getInstance();
	}
	
	
	public static SeatServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new SeatServiceImpl();
		return service;
	}


	@Override
	public List<TheaterVO> getThNameList() throws RemoteException {
		return dao.getThNameList();
	}


	@Override
	public List<SeatVO> getSeatList(String th_name) throws RemoteException {
		return dao.getSeatList(th_name);
	}


	@Override
	public int insertSeat(SeatVO seatVo) throws RemoteException {
		return dao.insertSeat(seatVo);
	}


	@Override
	public int updateSeat(SeatVO seatVo) throws RemoteException {
		return dao.updateSeat(seatVo);
	}


	@Override
	public int deletSeat(Map<String, String> param) throws RemoteException {
		return dao.deletSeat(param);
	}


	@Override
	public List<SeatVO> getAllseatList() throws RemoteException {
		return dao.getAllseatList();
	}
}
