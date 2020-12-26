package wg.vo;

import java.io.Serializable;

public class SelectNoticeVO implements Serializable{
	
	
	
	public static NoticeVO currNoticeVo;
		
	
	public static NoticeVO getCurrNoticeVo() {
		return currNoticeVo;
	}
	
	
	public static void setCurrNoticeVo(NoticeVO currNoticeVo) {
		SelectNoticeVO.currNoticeVo = currNoticeVo;
	}
}
