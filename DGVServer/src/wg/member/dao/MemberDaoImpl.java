package wg.member.dao;

import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.HintVO;
import wg.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;  
	private SqlMapClient smc;
	Reader rd;
	Charset charset;
	
	private MemberDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static MemberDaoImpl getInstance(){
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}

	@Override
	public MemberVO loginMember(String mem_id) {
		MemberVO vo = null;
		try {
			vo = (MemberVO)smc.queryForObject("member.loginMember",mem_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
		
	}

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt=0;
		
		try {
		Object obj= smc.insert("member.insertMember", memVo);
		if(obj==null) cnt=1;   
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt= smc.update("member.deleteMember",memId);
			
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt= smc.update("member.updateMember", memVo);
			//if(obj==null) cnt=1; 
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> mem; 
		  
		try {
			mem = smc.queryForList("member.getAllMember");
			
		} catch (SQLException e) {
			mem= null;
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	public MemberVO findMember(String mem_tel) {
		MemberVO vo = null;
		try {
			vo = (MemberVO)smc.queryForObject("member.findMember",mem_tel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
		
	}

	@Override
	public List<MemberVO> getAllStaff() {
		List<MemberVO> mem; 
		  
		try {
			mem = smc.queryForList("member.getAllStaff");
			
		} catch (SQLException e) {
			mem= null;
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	public List<MemberVO> searchMember(Map<String, String> searchMap) {
		List<MemberVO> mList = null;
		try {
			mList = smc.queryForList("member.searchMember", searchMap);
		} catch (SQLException e) {
			mList = null;
			e.printStackTrace();
		}
		return mList;
	}

	@Override
	public int idChkMember(String mem_id) {
		int mem; 
		  
		try {
			mem = (int) smc.queryForObject("member.idChkMember", mem_id);
			
		} catch (SQLException e) {
			mem= 0;
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	public int telChkMember(String mem_tel) {
		int mem; 
		  
		try {
			mem = (int) smc.queryForObject("member.telChkMember", mem_tel);
			
		} catch (SQLException e) {
			mem= 0;
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	public int emailChkMember(String mem_email) {
		int mem; 
		  
		try {
			mem = (int) smc.queryForObject("member.emailChkMember", mem_email);
			
		} catch (SQLException e) {
			mem= 0;
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	public HintVO findHint(int no) {
		HintVO vo = null;
		try {
			vo = (HintVO)smc.queryForObject("member.findHint",no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int resetPassword(MemberVO vo) {
		int cnt = 0;
		try {
			cnt= smc.update("member.resetPw", vo);
			//if(obj==null) cnt=1; 
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		}
		
		return cnt;
	}
	

}
