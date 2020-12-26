package wg.pay.dao;

import wg.vo.MemberVO;
import wg.vo.PayVO;

public interface IPayDao {
	
	/**
	 * 
	 * @author 선미
	 * @param pvo
	 * @return
	 */
	public int payBookMovie(PayVO pvo);
	
	public int insertPayBid(PayVO pvo);
	
	
	/**
	 * 
	 * @author 선미
	 * @param pay_no
	 * @return
	 */
	public int refundBookMovie(int pay_no);
	
	
	/**
	 * 
	 * @author 선미
	 * @param book_id
	 * @return
	 */
	public int getPay_no(String book_id);
	
	
	/**
	 * @author 다은
	 * 
	 * 
	 */
	public int updatePayRefund(String book_id);
	/**
	 * @author 이명석
	 * @param buy_id
	 * @return
	 */
	public int updateSnackPayRefund(String buy_id);
	
	/**
	 * 
	 * @param pvo
	 * @return
	 */
	public int paySnack(PayVO pvo);
	
	
	
}
