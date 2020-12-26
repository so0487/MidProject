package wg.buy_detail.dao;

import java.util.List;

import wg.vo.Buy_DetailVO;

public interface IBuy_DetailDao {
	
	/**
	 * 스낵바 구매 디테일
	 * @param bdvo
	 * @return
	 */
	public int insertBuyDet(Buy_DetailVO bdvo);
	
	/**
	 * 
	 * @param buydet_no
	 * @return
	 */
	public int deleteBuyDet(int buydet_no);
	
	/**
	 * 
	 * @return
	 */
	public List<Buy_DetailVO> getBuyDetail();

}
