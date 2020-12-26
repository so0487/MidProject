package wg.seatSch.dao;

import java.util.List;
import java.util.Map;

import wg.vo.SeatSchVO;

public interface ISeatSchDao {
	
	/**
	 * 특정 상영영화에 대한 상영좌석 리스트를 반환하는 메서드
	 * @author 선미
	 * @return 특정 상영영화에 대한 상영좌석 리스트(리스트의 길이는 좌석 수를 의미한다.) 
	 */
	public List<SeatSchVO> seatSchforAShow(int show_no);
	
	
	/**
	 * 
	 * @author 선미
	 * @param paramMap
	 * @return
	 */
	public int getSseat_no(Map<String, String> paramMap);
	
	/**
	 * 
	 * @author 선미
	 * @param show_no
	 * @return
	 */
	public int getOpenSeatNum(int show_no);
	
	/**
	 * 
	 * @author 선미
	 * @param show_no
	 * @return
	 */
	public int getSeatNum(int show_no);
	
	/**
	 * 
	 * @author 선미
	 * @param sseat_no
	 * @return
	 */
	public int updateToY(int sseat_no);
	
	/**
	 * 상영좌석 추가 메서드
	 * @param svo 
	 * @return
	 */
	public int insertSseat(SeatSchVO svo);
	
	public int updateToN(String book_id);
	
}
