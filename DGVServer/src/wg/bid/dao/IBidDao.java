package wg.bid.dao;

import java.util.List;

import wg.vo.BidResultVO;
import wg.vo.BidVO;

public interface IBidDao {
	
	public int bidPrice(int sseat_no);
	
	public int insertBid(BidVO bidvo);
	
	public List<BidVO> getAllList();
	
	public BidResultVO resultBid(int sseat_no);
	
	public String getMax();
}
