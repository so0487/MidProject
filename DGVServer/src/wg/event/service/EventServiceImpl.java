package wg.event.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.event.dao.EventDaoImpl;
import wg.event.dao.IEventDao;
import wg.vo.EventVO;

public class EventServiceImpl extends UnicastRemoteObject implements IEventService {
	private static EventServiceImpl service;
	private IEventDao dao;
	
	
	private EventServiceImpl()  throws RemoteException  {
		dao = EventDaoImpl.getInstance();
	}
	
	public static EventServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new EventServiceImpl();
		return service;
	}

	@Override
	public int insertEvent(EventVO eventVo) throws RemoteException {
		return dao.insertEvent(eventVo);
	}

	@Override
	public int deleteEvent(int event_no) throws RemoteException {
		return dao.deleteEvent(event_no);
	}

	@Override
	public List<EventVO> getAllEvent() throws RemoteException {
		return dao.getAllEvent();
	}

	@Override
	public int updateEvent(EventVO eventVo) throws RemoteException {
		return dao.updateEvent(eventVo);
	}
}
