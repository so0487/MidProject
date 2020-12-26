package wg.seatSch.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.seatSch.dao.ISeatSchDao;
import wg.seatSch.dao.SeatSchDaoImpl;
import wg.vo.SeatSchVO;

public class SeatSchServiceImpl extends UnicastRemoteObject implements ISeatSchService{
	
	
	private static SeatSchServiceImpl service;
	private ISeatSchDao dao;
	
	// 생성자
	private SeatSchServiceImpl()  throws RemoteException{
		//dao = new MemberDaoImpl();
		dao = SeatSchDaoImpl.getInstance();
	}
	
	public static SeatSchServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new SeatSchServiceImpl();
		return service;
	}

	@Override
	public List<SeatSchVO> seatSchforAShow(int show_no) throws RemoteException{
		return dao.seatSchforAShow(show_no);
	}

	@Override
	public int getSseat_no(Map<String, String> paramMap) throws RemoteException {
		return dao.getSseat_no(paramMap);
	}

	@Override
	public int getOpenSeatNum(int show_no) throws RemoteException {
		return dao.getOpenSeatNum(show_no);
	}

	@Override
	public int getSeatNum(int show_no) throws RemoteException {
		return dao.getSeatNum(show_no);
	}

	@Override
	public int updateToY(int sseat_no) throws RemoteException {
		return dao.updateToY(sseat_no);
	}

	@Override
	public int insertSseat(SeatSchVO svo) throws RemoteException {
		return dao.insertSseat(svo);
	}

	@Override
	public int updateToN(String book_id) throws RemoteException {
		return dao.updateToN(book_id);
	}
}
