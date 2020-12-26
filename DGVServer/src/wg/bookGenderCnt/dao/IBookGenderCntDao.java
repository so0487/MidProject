package wg.bookGenderCnt.dao;

import java.rmi.RemoteException;
import java.util.List;

import wg.vo.BookGenderCntVO;
import wg.vo.BookMemberCntVO;
import wg.vo.MovieVO;

public interface IBookGenderCntDao {

	


	
	
	public int getbookcount(String movie_name);
	
	
	public List<String> getbookgenCntList();
}
