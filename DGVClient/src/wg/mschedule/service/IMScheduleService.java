package wg.mschedule.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.MScheduleVO;
import wg.vo.MscheduleViewVO;
import wg.vo.ShowInfoVO;

public interface IMScheduleService extends Remote {
	/**
	 * 해당 날짜에 관한 상영스케줄정보를 담은 리스트
	 * @param param 상영날짜, 상영관 필요
	 * @return
	 */
	public List<MscheduleViewVO> viewList(Map<String, String> param) throws RemoteException;
	
	/**
	 * 해당 관의 영화명 리스트
	 * @param theater_name 상영관명 필요
	 * @return
	 */
	public List<MscheduleViewVO> viewMoname(String theater_name) throws RemoteException;
	
	/**
	 * 스케줄 등록 
	 * @param msvo 등록정보가 필요
	 * @return 성공1 실패0
	 */
	public int insertMs(MScheduleVO msvo) throws RemoteException;
	
	/**
	 * 스케줄 삭제
	 * @param show_no 기본키인 스케줄번호를 가져옴
	 * @return 성공 1 실패0
	 */
	public int deletMs(int show_no) throws RemoteException;
	
	/**
	 * 예매시 선택한 영화, 날짜, 상영관에 해당하는 상영스케줄스트를 반환하는 메서드
	 * @author 선미
	 * @param selectedMap 선택한 영화, 날짜, 상영관정보를 가지고 있는 Map객체(key값 selectedMovie, selectedDate, selectedTheater)
	 * @return 예매시 선택한 영화, 날짜, 관에 해당하는 상영스케줄스트
	 */
	public List<ShowInfoVO> getShowInfo(Map<String, String> selectedMap) throws RemoteException;
	
	/**
	 * 예매시 선택한 영화, 시간에 해당하는 상영스케줄스트를 반환하는 메서드
	 * @author 선미
	 * @param selectedMap 선택한 영화, 날짜, 상영관정보를 가지고 있는 Map객체(key값 selectedMovie, selectedTime)
	 * @return 예매시 선택한 영화, 시간에 해당하는 상영스케줄번호
	 */
	public int getShow_no(Map<String, String> selectedMap) throws RemoteException;
	
	
	/**
	 * 선택한 상영스케줄의 할인ID를 반환하는 메서드
	 * @author 선미
	 * @param show_no
	 * @return 선택한 상영스케줄의 할인ID
	 */
	public String getDis_id(int show_no) throws RemoteException;
	
	/**
	 * 상영등록된 상영좌석을 삭제하는 메서드
	 * @param show_no 해당 상영번호를 가져옴
	 * @return
	 */
	public int deletMsSeat(int show_no) throws RemoteException;
}
