package wg.genre.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import wg.vo.GenreVO;

public interface IGenreService extends Remote {
	/**
	 * GenreVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 *  
	 * @param gvo DB에 insert할 자료가 저장된 GenreVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertGenre(GenreVO gvo) throws RemoteException;
	
	/**
	 * GenreVO객체에 담겨진 자료를 DB에 update하는 메서드
	 * 
	 * @param gvo DB에 update할 자료가 저장된 GenreVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateGenre(GenreVO gvo) throws RemoteException;
	
	/**
	 * 장르id를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param gen_id 삭제할 장르id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteGenre(String gen_id) throws RemoteException;
	
	/**
	 * DB의 genre테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return GenreVO객체를 담고 있는 List객체
	 */
	public List<GenreVO> getAllGenre() throws RemoteException;
	
	
	/**
	 * 장르id 생성을 위하여 DB의 genre테이블의 gen_id중 가장 큰 값을  반환하는 메서드
	 * @return 가장 큰 gen_id
	 */
	public String getMaxGen_id() throws RemoteException;
}
