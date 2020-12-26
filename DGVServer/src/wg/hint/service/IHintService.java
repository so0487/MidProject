package wg.hint.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.HintVO;

public interface IHintService extends Remote {
	
	public List<HintVO> getAllHint() throws RemoteException;


}
