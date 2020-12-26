package wg.event.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.discount.dao.DiscountDaoImpl;
import wg.vo.EventVO;
import wg.vo.MemberVO;
import wg.vo.OnOffViewVO;
//
public class EventDaoImpl implements IEventDao {
	private static EventDaoImpl dao;  
	private SqlMapClient smc;
	
	
	private EventDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	
	
	public static EventDaoImpl getInstance(){
		if(dao == null) dao = new EventDaoImpl();
		
		return dao;
	}

	@Override
	public int deleteEvent(int event_no) {
		int cnt = 0;
		try {
			cnt= smc.delete("event.deleteEvent", event_no);
			
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateEvent(EventVO eventVo) {
		int cnt = 0;
		try {
			cnt= smc.update("event.updateEvent", eventVo);
		} catch (SQLException e) {
			cnt = 0;                                                                                                                                                                                                 
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<EventVO> getAllEvent() {
		List<EventVO> ev; 
		  
		try {
			ev = smc.queryForList("event.getAllEvent");
			
		} catch (SQLException e) {
			ev= null;
			e.printStackTrace();
		}
		
		return ev;
	}

	@Override
	public int insertEvent(EventVO eventVo) {
		int cnt=0;
		
		try {
		Object obj= smc.insert("event.insertEvent", eventVo);
		if(obj==null) cnt=1;   
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}


}
