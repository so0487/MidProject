package wg.bookMemberCnt.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.BookMemberCntVO;

public interface IBookMemberCntService extends Remote  {

	/**
	 *	male값 list 반환
	 * @param male
	 * @return
	 */
	public int getMaleCnt(String movie_id) throws RemoteException;



	/**
	 * female값 list 반환
	 * @param female
	 * @return
	 */
	public int getFemaleCnt(String movie_id) throws RemoteException;


	/**
	 * 연령대별 해당하는 갯수 반납
	 * @param movie_id
	 * @return
	 */

	public int getAgeCount10(String movie_id) throws RemoteException;


	public int getAgeCount20(String movie_id) throws RemoteException;


	public int getAgeCount30(String movie_id) throws RemoteException;



	public int getAgeCount40(String movie_id) throws RemoteException;




	/**
	 * 남자회원의 전체 예매율을 반환
	 * @param movie_id
	 * @return
	 */
	public int getAllMaleCnt() throws RemoteException;




	/**
	 * 여성회원 전체의 예매율을 반환
	 * @param movie_id
	 * @return
	 */
	public int getAllFemaleCnt() throws RemoteException;
	
	

}
