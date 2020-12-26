package wg.movie.dao;

import java.util.List;
import java.util.Map;

import wg.vo.GenreVO;
import wg.vo.MovieVO;
import wg.vo.MovieViewVO;
import wg.vo.SeatVO;

public interface IMovieDao {
	
	/**
	 * MovieVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 *  
	 * @param mvo DB에 insert할 자료가 저장된 MovieVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertMovie(MovieVO mvo);
	
	/**
	 * MovieVO객체에 담겨진 자료를 DB에 update하는 메서드
	 * 
	 * @param mvo DB에 update할 자료가 저장된 MovieVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMovie(MovieVO mvo);
	
	/**
	 * 장르id를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param Movie_id 삭제할 장르id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMovie(String Movie_id);
	
	/**
	 * DB의 Movie테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return MovieVO객체를 담고 있는 List객체
	 */
	public List<MovieVO> getAllMovie();
	
	
	/**
	 * 장르id 생성을 위하여 DB의 Movie테이블의 Movie_id중 가장 큰 값을  반환하는 메서드
	 * @return 가장 큰 movie_id
	 */
	public String getMaxMovie_id();
	
	/**
	 * 전체 영화수를 구하는 메서드
	 * @param searchMap
	 * @return 
	 */
	public int getMovieCount(Map<String, String> searchMap);
	
	/**
	 * 영화 리스트를 구하는 메서드 
	 * @param searchMap
	 * @return 리스트 반환
	 */
	public List<MovieViewVO> listView(Map<String, String> searchMap);
	
	/**
	 * 검색한 영화명으로 리스트를 띄움
	 * @param movie_name 영화이름을 가져옴
	 * @return 리스트 반환
	 */
	public List<MovieVO> umovNameList(String movie_name);
	
	/**
	 * 상영예정인 영화 리스트를 가져오는 메서드
	 * @return 리트스 반환
	 */
	public List<MovieVO> movieSchList();
	
	/**
	 * 전체 영화 검색후 아이디를 가져와서 등록된 상영영화인지 아닌지 검사 하는 메서드
	 * @param movie_id
	 * @return
	 */
	public int umovNameList2(String movie_id);
	
	/**
	 * 영화이름을 가지고 그 영화가 성인영화인지 검사하는 메서드
	 * @author 선미
	 * @param movie_name
	 * @return "Y", "N"
	 */
	public String movieAdultCheck(String movie_name);
	
	
}
