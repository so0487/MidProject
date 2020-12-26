package wg.snackSet.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.SnackSetVO;

public interface ISnackSetService extends Remote {



	/**
	 * SnackSetVO DB자료를 insert 
	 * 
	 * @param snackSetVo(VO에)
	 * @return 작업성공1 실패0
	 */
	public int insertSnackSet(SnackSetVO snackSetVo) throws RemoteException;




	/**
	 * set_id를 매개변수로 받아 해당하는 스낵세트를 삭제하는 메서드
	 * @param set_id
	 * @return	작업성공1 실패0
	 */
	public int deleteSnackSet(String set_id) throws RemoteException;





	/**
	 * snacksetVO정보를 담고있는 snacksetVo로 DB update
	 * @param snackSetVo
	 * @return	작업성공1 실패0
	 * @throws RemoteException
	 */
	public int updateSnackSet(SnackSetVO snackSetVo) throws RemoteException;





	/**
	 * SnackSetVO의 전체 정보를 가져와서 List에 담아 반환
	 * @return 작업성공1 실패0
	 */
	public List<SnackSetVO> getAllSnackSetList() throws RemoteException;





	/**
	 * 가장 큰 set_id를 반환하는 메서드
	 * @return	가장 큰 set_id
	 */
	public String getMaxSetId() throws RemoteException;


}
