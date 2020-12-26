package wg.vo;

import java.io.Serializable;

public class LoginVO implements Serializable {
	public static MemberVO currVo;

	public static MemberVO getCurrVo() {
		return currVo;
	}

	public static void setCurrVo(MemberVO currVo) {
		LoginVO.currVo = currVo;
	}
	
}
