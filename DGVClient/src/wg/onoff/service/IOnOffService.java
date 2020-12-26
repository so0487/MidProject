package wg.onoff.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.OnOffVO;
import wg.vo.OnOffViewVO;

public interface IOnOffService extends Remote {
	
	
	public int deleteOnOff(int onNo) throws RemoteException;
	
	public int updateOnOff(OnOffVO oovVo) throws RemoteException;
	
	
	public int updateOff(String mem_id) throws RemoteException;
	
	public List<OnOffViewVO> getAllOnOff() throws RemoteException;
	
	public int insertOnOff(String mem_id) throws RemoteException;
	
	public int getCountOnOff(String mem_id) throws RemoteException;
}

