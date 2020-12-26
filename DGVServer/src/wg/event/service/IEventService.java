package wg.event.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.EventVO;

public interface IEventService extends Remote {
	public int insertEvent(EventVO eventVo) throws RemoteException;
	
	public int deleteEvent(int event_no) throws RemoteException;
	
	public List<EventVO> getAllEvent() throws RemoteException;
	
	public int updateEvent(EventVO eventVo) throws RemoteException;
}
