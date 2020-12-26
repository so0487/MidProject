package wg.notice.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.NoticeVO;

public class NoticeDaoImpl implements INoticeDao {
	private static NoticeDaoImpl dao;  
	private SqlMapClient smc;


	private NoticeDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	

	public static NoticeDaoImpl getInstance(){
		if(dao == null) dao = new NoticeDaoImpl();

		return dao;
	}

	@Override
	public int insertNotice(NoticeVO noticeVo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("notice.insertNotice",noticeVo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}






	@Override
	public int deleteNotice(String not_no) {
		int cnt = 0;

		try {
			cnt = smc.delete("notice.deleteNotice",not_no);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	

	@Override
	public int updateNotice(NoticeVO noticeVo) {
		int cnt = 0;

		try {
			cnt = smc.update("notice.updateNotice",noticeVo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}

		return cnt;
	}
	
	
	

	@Override
	public List<NoticeVO> getAllNoticeList(Map<String, String> searchMap) {
		List<NoticeVO> noticeList = null;
		
		try {
			noticeList = smc.queryForList("notice.getAllNotice",searchMap);
		} catch (SQLException e) {
			noticeList = null;
			e.printStackTrace();
		}
		
		
		return noticeList;
	}

	@Override
	public int getMaxNot_no() {
		
		/*
		String not_no = null;
		
		
		try {
			not_no = (String) smc.queryForObject("notice.getMaxNoticeNo");
			int temp = Integer.parseInt(not_no.substring(1))+1;
			not_no = String.valueOf(temp);
			
			
		*/	
			
			
			int not_no = 0;
			
			
			try {
				not_no = (int) smc.queryForObject("notice.getMaxNoticeNo");
				int temp = not_no+1;
				not_no = temp;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return not_no;
	}

	@Override
	public int getNoticeCount(Map<String, String> searchMap) {
		int cnt = 0;

		try {
			cnt = (int) smc.queryForObject("notice.getNoticeCount",searchMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}

}
