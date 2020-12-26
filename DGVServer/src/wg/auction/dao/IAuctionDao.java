

package wg.auction.dao;

import java.util.List;
import java.util.Map;

import wg.vo.AuctionTTVO;
import wg.vo.AuctionVO;
import wg.vo.SeatSchVO;
import wg.vo.TheaterVO;




public interface IAuctionDao{
	public List<AuctionVO> getAllAuctionList();
	
	public int insertAuction(AuctionVO aucvo);
	
	public int updateAuction(AuctionVO aucvo);
	
	public int deleteAuction(int auc_no);
	
	public String aucPoster(int sseat_no);
	
	public int findSseat(int sseat_no);
	
	public List<AuctionTTVO> searchtt(AuctionTTVO vo);
	
	public List<SeatSchVO>searchseat(Map<String, String> paramMap);
	
	
}
