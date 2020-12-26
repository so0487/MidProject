package wg.vo;

import java.io.Serializable;

public class SelectEventVO implements Serializable{
	
	
	
	public static EventVO currEventVo;
		
	
	public static EventVO getCurrEventVo() {
		return currEventVo;
	}
	
	
	public static void setCurrEventVo(EventVO currEventVo) {
		SelectEventVO.currEventVo = currEventVo;
	}
}
