package wg.issueCoupon.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.IssueCouponVO;
import wg.vo.IssueViewVO;

public interface IIssueCouponService extends Remote {

	/**
	 * IssueCouponVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 *  
	 * @param ivo DB에 insert할 자료가 저장된 IssueCouponVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertIssueCoupon(IssueCouponVO ivo) throws RemoteException;
	
	/**
	 * 발급id를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param issue_no 삭제할 발급id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteIssueCoupon(int issue_no) throws RemoteException;
	
	/**
	 * DB의 IssueCoupon테이블과  Coupon테이블의 조인쿼리의 결과 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return IssueViewVO객체를 담고 있는 List객체
	 */
	public List<IssueViewVO> getAllIssueCoupon() throws RemoteException;
	
	/**
	 * 쿠폰을 사용여부 메서드
	 * @param cou_no 기본키인 쿠폰 발급번호를 가져온다.
	 * @return
	 */
	public int updateCou(int cou_no) throws RemoteException;
}

