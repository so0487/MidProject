package wg.saleCount.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISaleCountService extends Remote{
	
	public int getSaleCount(String pay_time) throws RemoteException ;
	
}
