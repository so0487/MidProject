package wg.snackSet.dao;

import java.util.List;



import wg.vo.SnackSetVO;

/**
 * 스낵바 C/R/U/D
 * 
 * 
 * 스낵바 메뉴 C/R/U/D 관리
 * @author 정수영
 *
 */
public interface ISnackSetDao {




	/**
	 * SnackSetVO DB자료를 insert 
	 * 
	 * @param snackSetVo(VO에)
	 * @return 작업성공1 실패0
	 */
	public int insertSnackSet(SnackSetVO snackSetVo);




	/**
	 * setId를 매개변수로 받아 해당하는 스낵세트를 삭제하는 메서드
	 * @param setId
	 * @return	작업성공1 실패0
	 */
	public int deleteSnackSet(String setId);





	/**
	 * memVO정보를 담고있는 memVo로 DB update
	 * @param memVo
	 * @return	작업성공1 실패0
	 */
	public int updateSnackSet(SnackSetVO snackSetVo);






	/**
	 * SnackSetVO의 전체 정보를 가져와서 List에 담아 반환
	 * @return 작업성공1 실패0
	 */
	public List<SnackSetVO> getAllSnackSetList();





	/**
	 * 가장 큰 set_id를 반환하는 메서드
	 * @return	가장 큰 set_id
	 */
	public String getMaxSetId();

}
