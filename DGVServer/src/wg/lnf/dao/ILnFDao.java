package wg.lnf.dao;

import java.util.List;
import java.util.Map;

import wg.vo.LnFVO;

/**
 * 분실물 관리
 * 
 * 분실물 추가/수정/조회/삭제
 * 
 * 
 * @author 정수영
 *
 */

public interface ILnFDao {
	
	/**
	 * lntVO DB자료 insert
	 * @param lnfVo
	 * @return	작업성공1	실패0
	 */
	
	
	public int insertLnf(LnFVO lnfVo);
	
	
	
	
	
	/**
	 * lnf_no를 매개변수로 받아 해당하는 lnf의 정보 삭제
	 * @param lnf_no
	 * @return
	 */
	public int deleteLnf(String lnf_no);
	
	
	
	/**
	 * LnFVO 정보를 담고 있는 lnfvo로 DB update
	 * @param lnfvo
	 * @return
	 */
	public int updateLnf(LnFVO lnfvo);
	
	
	
	
	/**
	 * lnfVO의 전체 정보를 list로 반환
	 * @param searchMap
	 * @return
	 */
	public List<LnFVO> getAllLnfList(Map<String, String>searchMap);
	
	
	
	
	
	/**
	 * 가장 큰 lnf_no를 반환
	 * @return
	 */
	public int getMax_lnf_no();
	
	
	
	
	
	/**
	 * 해당하는 lnf번호 반환
	 * @param seraMap
	 * @return
	 */
	public int getLnfCount(Map<String, String>seraMap);

}
