package wg.onoff.dao;

import java.util.List;

import wg.vo.MemberVO;
import wg.vo.OnOffVO;
import wg.vo.OnOffViewVO;

public interface IOnOffDao {
	
	public int deleteOnOff(int onNo);
	
	public int updateOnOff(OnOffVO oovVo);
	
	public List<OnOffViewVO> getAllOnOff();
	
	public int insertOnOff(String mem_id);
	
	public int updateOff(String mem_id);
	
	public int getCountOnOff(String mem_id);
	
	

}
