package wg.buysnack.dao;

import java.util.List;

import wg.vo.BuySnackVO;
import wg.vo.SnackUserViewVO;

public interface IBuySnackDao {
	
	public List<SnackUserViewVO> getBuySnack(String mem_id); 
	
	public List<SnackUserViewVO> getBuyAllSnack(); 
	
	/**
	 * 스낵바 구매하는 메서드
	 * @param svo
	 * @return
	 */
	public int insertBuysnack(BuySnackVO svo);
	
	/**
	 * 구매id 생성을 위하여 가장 큰 값을 반환하는 메서드
	 * @return
	 */
	public String getMaxBuy_id();
	
	

}
