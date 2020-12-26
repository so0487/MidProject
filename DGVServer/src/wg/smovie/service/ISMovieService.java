package wg.smovie.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import wg.vo.SMovieVO;
import wg.vo.SMovieViewVO;
import wg.vo.SMovieViewVO2;
import wg.vo.SmovieShortInfoVO;

public interface ISMovieService extends Remote{
	
	/**
	 * 상영영화를 추가하는 메서드
	 * @param SMovieVO의 정보를 담음
	 * @return 성공1 실패0
	 */
	public int insertSmovie(SMovieVO smo) throws RemoteException;
	
	/**
	 * 상영영화 전체 조회
	 * @return 상영영화 리스트 반환
	 */
	public List<SMovieVO> getAllSmovie() throws RemoteException;
	
	/**
	 * 상영영화 정보 수정
	 * @param  해당vo를 가져옴
	 * @return 성공1 실패0
	 */
	public int updateSmovie(SMovieVO smo) throws RemoteException;
	
	/**
	 * 상영영화 정보 삭제
	 * @param smovie_no 값을 담음
	 * @return 성공1 실패0
	 */
	public int deletSmovie(int smovie_no) throws RemoteException;
	
	/**
	 * 상영영화 정보 view
	 * @param param
	 * @return
	 */
	public List<SMovieViewVO> viewList() throws RemoteException; 
	
	/**
	 * 해당 상영관의 상영영화를 가져옴
	 * @param th_id 상영관id를 담음
	 * @return 해당관의 상영영화 리스트 반환
	 */
	public List<SMovieViewVO> viewList2(String th_id) throws RemoteException;
	
	/**
	 * DB에서 현재 상영중인 영화의 제목, 청불여부를 가져와 List에 담아 반환하는 메서드
	 * @author 선미
	 * @return 현재 상영 중인 영화의 제목, 청불여부 List
	 */
	public List<SmovieShortInfoVO> sMovieNameList() throws RemoteException;
	
	/**
	 * 장르와 맞는 영화를 가져옴
	 * @return 리스트반환
	 */
	public List<SMovieViewVO2> viewList3(Map<String, String> param) throws RemoteException;
}
