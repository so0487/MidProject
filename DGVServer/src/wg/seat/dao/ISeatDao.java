package wg.seat.dao;

import java.util.List;
import java.util.Map;

import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public interface ISeatDao {
	
	/**
	 * 상영관 정보를 가져옴
	 * @return 상영관vo정보를 담은 리스트 반환
	 */
	public List<TheaterVO> getThNameList();
	
	/**
	 * 해당상영관에 대한 좌석을 가져온다.
	 * @param th_id 해당상영관을 아이디를 매개변수로 사용
	 * @return 좌석vo정보를 담은 리스트 반환
	 */
	public List<SeatVO> getSeatList(String th_name);
	
	/**
	 * 해당 상영관의 좌석을 추가하는 메서드
	 * @param seatVo 좌석의 정보를 담음
	 * @return 성공1 실패0
	 */
	public int insertSeat(SeatVO seatVo);
	
	/**
	 * 해당상영관의 좌석을 수정하는 메서드
	 * @param th_id 해당상영관아이디를 가져옴
	 * @return 성공1 실패0
	 */
	public int updateSeat(SeatVO seatVo);
	
	/**
	 * 해당상영관의 좌석을 삭제하는 메서드
	 * @param param 해당 상영관과 좌석을 가져옴
	 * @return 성공1 실패0
	 */
	public int deletSeat(Map<String, String> param);
	
	/**
	 * 좌석전체를 가져온다
	 * @return 좌석vo정보를 담은 리스트 반환
	 */
	public List<SeatVO> getAllseatList();
	

}
