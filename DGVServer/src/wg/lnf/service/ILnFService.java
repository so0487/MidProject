package wg.lnf.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.LnFVO;
import wg.vo.NoticeVO;

public interface ILnFService extends Remote {
	
	
	/**
	 * lntVO DB자료 insert
	 * @param lnfVo
	 * @return	작업성공1	실패0
	 */
	
	
	public int insertLnf(LnFVO lnfVo) throws RemoteException;
	
	
	
	
	
	/**
	 * lnf_no를 매개변수로 받아 해당하는 lnf의 정보 삭제
	 * @param lnf_no
	 * @return
	 */
	public int deleteLnf(String lnf_no) throws RemoteException;
	
	
	
	/**
	 * LnFVO 정보를 담고 있는 lnfvo로 DB update
	 * @param lnfvo
	 * @return
	 */
	public int updateLnf(LnFVO lnfvo) throws RemoteException;
	
	
	
	
	/**
	 * lnfVO의 전체 정보를 list로 반환
	 * @param searchMap
	 * @return
	 */
	public List<LnFVO> getAllLnfList(Map<String, String>searchMap) throws RemoteException;
	
	
	
	
	
	/**
	 * 가장 큰 lnf_no를 반환
	 * @return
	 */
	public int getMax_lnf_no() throws RemoteException;
	
	
	
	
	
	/**
	 * 해당하는 lnf번호 반환
	 * @param seraMap
	 * @return
	 */
	public int getLnfCount(Map<String, String>seraMap) throws RemoteException;

}
