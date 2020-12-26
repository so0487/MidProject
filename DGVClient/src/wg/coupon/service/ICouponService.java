package wg.coupon.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.CouponUserViewVO;
import wg.vo.CouponVO;

public interface ICouponService extends Remote {

	/**
	 * CouponVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 *  
	 * @param cvo DB에 insert할 자료가 저장된 CouponVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertCoupon(CouponVO cvo) throws RemoteException;
	
	/**
	 * CouponVO객체에 담겨진 자료를 DB에 update하는 메서드
	 * 
	 * @param cvo DB에 update할 자료가 저장된 CouponVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateCoupon(CouponVO cvo) throws RemoteException;
	
	/**
	 * 쿠폰id를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param cou_id 삭제할 쿠폰id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteCoupon(String cou_id) throws RemoteException;
	
	/**
	 * DB의 Coupon테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return CouponVO객체를 담고 있는 List객체
	 */
	public List<CouponVO> getAllCoupon() throws RemoteException;
	
	
	/**
	 * 쿠폰id 생성을 위하여 DB의 Coupon테이블의 cou_id중 가장 큰 값을  반환하는 메서드
	 * @return 가장 큰 cou_id
	 */
	public String getMaxCou_id() throws RemoteException;
	
	/**
	 * 회원이 보유한 쿠폰 메서드
	 * @param mem_id 로그인한 회원 아이디
	 * @return 리스트반환
	 */
	public List<CouponUserViewVO> userCouponList(String mem_id) throws RemoteException;
}
