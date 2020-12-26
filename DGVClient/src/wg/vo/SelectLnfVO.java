package wg.vo;

import java.io.Serializable;

public class SelectLnfVO implements Serializable{
	
	
	public static LnFVO currLnfVo;
	
	public static LnFVO getCurrentLnfVo() {
		return currLnfVo;
	}
	
	
	public static void setCurrLnfVo(LnFVO currLnfVo) {
		SelectLnfVO.currLnfVo = currLnfVo;
	}
	
}
