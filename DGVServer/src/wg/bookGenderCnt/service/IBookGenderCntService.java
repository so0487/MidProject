package wg.bookGenderCnt.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.BookGenderCntVO;
import wg.vo.MovieVO;

public interface IBookGenderCntService extends Remote  {





	
	
	public int getbookcount(String movie_name) throws RemoteException;
	
	public List<String> getbookgenCntList() throws RemoteException;
	

}
