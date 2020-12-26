package wg.notice.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.NoticeVO;

public interface INoticeService extends Remote{



	/**
	 * noticeVO DB자료 insert
	 * @param noticeVo
	 * @return	작업성공1	/	작업실패1
	 */
	public int insertNotice(NoticeVO noticeVo) throws RemoteException;


	/**
	 * not_no를 매개변수로 받아 해당하는 공지 번호를 삭제
	 * @param not_no
	 * @return	작업성공 1	/	작업실패0
	 */
	public int deleteNotice(String not_no) throws RemoteException;




	/**
	 * noticeVO 정보를 담고 있는 noticeVo로 DB update
	 * @param noticeVo
	 * @return	작업성공 1	/	작업실패0
	 */
	public int updateNotice(NoticeVO noticeVo) throws RemoteException;






	/**
	 * noticeVO전체 정보를 가져와서 List에 담아 반환
	 * @return	작업성공1	/	작업실패0
	 */
	public List<NoticeVO> getAllNoticeList(Map<String, String> searchMap) throws RemoteException;





	/**
	 * 가장 큰 not_no를 반환하는 메서드
	 * @return	가장 큰 not_no
	 */
	public int getMaxNot_no() throws RemoteException;

	
	
	
	/**
	 * 조건에 해당하는 공지 갯수 반환
	 * @param serachMap
	 * @return
	*/
	public  int getNoticeCount(Map<String, String> serachMap) throws RemoteException;

	
	
	


}
