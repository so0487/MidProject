package wg.vo;

public class SelectMovieVO {
	public static MovieVO currMovieVO;

	public static MovieVO getCurrMovieVO() {
		return currMovieVO;
	}

	public static void setCurrMovieVO(MovieVO currMovieVO) {
		SelectMovieVO.currMovieVO = currMovieVO;
	}
	
}
