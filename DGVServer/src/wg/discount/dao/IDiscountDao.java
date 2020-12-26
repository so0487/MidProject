package wg.discount.dao;

import java.util.List;

import wg.vo.DiscountVO;


public interface IDiscountDao {
	/**
	 * DiscountVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 *  
	 * @param dvo DB에 insert할 자료가 저장된 DiscountVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertDiscount(DiscountVO dvo);
	
	/**
	 * DiscountVO객체에 담겨진 자료를 DB에 update하는 메서드
	 * 
	 * @param dvo DB에 update할 자료가 저장된 DiscountVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateDiscount(DiscountVO dvo);
	
	/**
	 * 할인id를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param dis_id 삭제할 할인id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteDiscount(String dis_id);
	
	/**
	 * DB의 Discount테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return DiscountVO객체를 담고 있는 List객체
	 */
	public List<DiscountVO> getAllDiscount();
	
	
	/**
	 * 할인id 생성을 위하여 DB의 Discount테이블의 dis_id중 가장 큰 값을  반환하는 메서드
	 * @return 가장 큰 dis_id
	 */
	public String getMaxDis_id();
}
