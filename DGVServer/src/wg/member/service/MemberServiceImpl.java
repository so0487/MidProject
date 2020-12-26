package wg.member.service;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.member.dao.IMemberDao;
import wg.member.dao.MemberDaoImpl;
import wg.vo.HintVO;
import wg.vo.MemberVO;


public class MemberServiceImpl extends UnicastRemoteObject implements IMemberService {
	private static MemberServiceImpl service;
	private IMemberDao dao;
	
	
	private MemberServiceImpl()  throws RemoteException  {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public MemberVO loginMember(String mem_id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.loginMember(mem_id);
	}

	@Override
	public int insertMember(MemberVO memVo) throws RemoteException {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) throws RemoteException {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) throws RemoteException {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() throws RemoteException {
		return dao.getAllMemberList();
	}

	@Override
	public MemberVO findMember(String mem_tel) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.findMember(mem_tel);
	}

	@Override
	public List<MemberVO> getAllStaff() throws RemoteException {
		return dao.getAllStaff();
	}

	@Override
	public List<MemberVO> searchMember(Map<String, String> searchMap) throws RemoteException {
		return dao.searchMember(searchMap);
	}

	@Override
	public int idChkMember(String mem_id) throws RemoteException {
		return dao.idChkMember(mem_id);
	}

	@Override
	public int telChkMember(String mem_tel) throws RemoteException {
		return dao.telChkMember(mem_tel);
	}

	@Override
	public int emailChkMember(String mem_email) throws RemoteException {
		return dao.emailChkMember(mem_email);
	}

	@Override
	public HintVO findHint(int no) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.findHint(no);
	}

	@Override
	public int resetPassword(MemberVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.resetPassword(vo);
	}



	

}
