package wg.theater.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.SeatVO;
import wg.vo.TheaterVO;

public interface ITheaterService extends Remote{
	/**
	 * 상영관을 추가하는 메서드
	 * @param thVo DB에 insert할 자료가 저장된 TheaterVO 객체
	 * @return 작업이 성공하면 1 실패하면 0반환
	 */
	public int insertTheater(TheaterVO thVo) throws RemoteException;
	
	/**
	 * 상영관을 조회 하는 메서드
	 * @return TheaterVO를 담고 있는 리스트
	 */
	public List<TheaterVO> getAllTheaterList() throws RemoteException;
	
	/**
	 * TheaterVO자료를 이용하여 수정하는 메서드
	 * @param thVo 수정할 회원정보가 들어있는 TheaterVO객체
	 * @return 작업성공 1 실패 0
	 */
	public int updateTheater(TheaterVO thVo) throws RemoteException;
	
	/**
	 * 상영관아이디를 받아서 해당 상영관 삭제하는 메서드
	 * @param thId 삭제할 상영관 아이디
	 * @return 성공1 실패0
	 */
	public int deleteTheater(String thId) throws RemoteException;
	
	/**
	 * 상영관아이디를 받아서 해당하는 상영관 정보를 얻오옴
	 * @param theater_id
	 * @return 해당 vo의 정보를 담고있음
	 */
	public TheaterVO getTheater(String theater_id) throws RemoteException;
	
	/**
	 * 좌석을 추가하면 맞게 증가하는 메서드
	 * @param theater_id
	 * @return 성공1 실패0
	 */
	public int updateSeat(String theater_id) throws RemoteException;
	
	/**
	 * 예매시 선택한 영화와 날짜에 해당하는 상영관리스트를 반환하는 메서드
	 * @author 선미
	 * @param selectedMap 선택한 영화와 날짜정보를 가지고 있는 Map객체(key값 selectedMovie, selected Date)
	 * @return 예매시 선택한 영화와 날짜에 해당하는 상영관리스트
	 */
	public List<String> theaterNameListforADay(Map<String, String> selectedMap) throws RemoteException;
	
	/**
	 * 상영관을 추가 후 상영수 좌석에 맞게 좌석 생성
	 * @param vo
	 * @return
	 */
	public int insertMovSeat(SeatVO vo) throws RemoteException;
	
	/**
	 * 관id 생성을 위하여 관id중 가장 큰 값을 반환하는 메서드
	 * @return 가장 큰 theater_id
	 */
	public String getMaxTh_id() throws RemoteException;
	
	/**
	 * 상영관을 삭제하면 상영관의 좌석도 삭제 하는 메서드
	 * @param theater_id
	 * @return
	 */
	public int deleteThSeat(String theater_id) throws RemoteException;
}
