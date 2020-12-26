package wg.member.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.HintVO;
import wg.vo.MemberVO;

public interface IMemberService extends Remote {
	
	public MemberVO loginMember(String mem_id) throws RemoteException;
	
		
		/**
		 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드 
		 * @author 다은이
		 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
		 * @return insert작업이 성공하면 1이상의 정수가 반환되고 실패하면 0이반환
		 */
		public int insertMember(MemberVO memVo) throws RemoteException;
		
		
		/**
		 * 회원ID를 매개변수로 받아서 해당회원정보를 삭제하는 메서드 
		 * @author 다은이
		 * @param memId 삭제할 회원ID
		 * @return 작업성공:1이상, 작업실패:0
		 */
		public int deleteMember(String memId) throws RemoteException;
		
		/**
		 * MemberVo자료를 이용하여 DB에 update하는 메서드
		 * @author 다은이
		 * @param memVo update할 회원정보가 들어있는 MemberVO객체
		 * @return 작업성공:1, 작업실패:0
		 */
		public int updateMember(MemberVO memVo) throws RemoteException;;
		
		/**
		 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환한다. 
		 * @author 다은이
		 * @return MemberVO객체를
		 *  담고있는 List
		 */
		public List<MemberVO> getAllMemberList() throws RemoteException;
		
		public List<MemberVO> getAllStaff() throws RemoteException;

	
		public MemberVO findMember(String mem_tel) throws RemoteException;
		
		/**
		 * DB의 회원테이블에서 관리자의 검색조건에 일치하는 레코드들을 List에 담아서 반환한다.
		 * @param searchMap 검색필드와 검색어를 담은 Map객체
		 * @return 작업성공:1, 작업실패:0
		 */
		public List<MemberVO> searchMember(Map<String, String> searchMap) throws RemoteException;
		
		public int idChkMember(String mem_id) throws RemoteException;
		
		public int telChkMember(String mem_tel) throws RemoteException;
		
		public int emailChkMember(String mem_email) throws RemoteException;
		
		public HintVO findHint(int no) throws RemoteException;
		
		public int resetPassword(MemberVO vo) throws RemoteException;
	
	
	
}
