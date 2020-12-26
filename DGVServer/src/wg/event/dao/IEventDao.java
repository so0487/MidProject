package wg.event.dao;

import java.util.List;
import java.util.Map;

import wg.vo.EventVO;

public interface IEventDao {

	public int deleteEvent(int event_no);
	
	public int updateEvent(EventVO eventVo);
	
	public List<EventVO> getAllEvent();
	
	public int insertEvent(EventVO eventVo);
}
