package wg.saleCount.service;

import java.rmi.Remote;

public interface ISaleCountService extends Remote{
	
	public int getSaleCount(String pay_time);
	
}
