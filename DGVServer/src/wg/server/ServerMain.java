package wg.server;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Provider.Service;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wg.answer.service.AnswerServiceImpl;
import wg.answer.service.IAnswerService;
import wg.auction.dao.AuctionDaoImpl;
import wg.auction.service.AuctionServiceImpl;
import wg.auction.service.IAuctionService;
import wg.bid.service.BidServiceImpl;
import wg.bid.service.IBidService;
import wg.bookGenderCnt.service.BookGenderCntServiceImpl;
import wg.bookGenderCnt.service.IBookGenderCntService;
import wg.bookMemberCnt.service.BookMemberCntServiceImpl;
import wg.bookMemberCnt.service.IBookMemberCntService;
import wg.book_detail.dao.Book_DetailDaoImpl;
import wg.book_detail.dao.IBook_DetailDao;
import wg.book_detail.service.Book_DetailServiceImpl;
import wg.book_detail.service.IBook_DetailService;
import wg.bookmovie.service.BookMovieServiceImpl;
import wg.bookmovie.service.IBookMovieService;
import wg.buy_detail.dao.Buy_DetailDaoImpl;
import wg.buy_detail.service.Buy_DetailServiceImpl;
import wg.buy_detail.service.IBuy_DetailService;
import wg.buysnack.service.BuySnackServiceImpl;
import wg.buysnack.service.IBuySnackService;
import wg.coupon.service.CouponServiceImpl;
import wg.coupon.service.ICouponService;
import wg.discount.service.DiscountServiceImpl;
import wg.discount.service.IDiscountService;
import wg.event.service.EventServiceImpl;
import wg.event.service.IEventService;
import wg.genre.service.GenreServiceImpl;
import wg.genre.service.IGenreService;
import wg.hint.service.HintServiceImpl;
import wg.hint.service.IHintService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.issueCoupon.service.IssueCouponServiceImpl;
import wg.lnf.service.ILnFService;
import wg.lnf.service.LnFServiceImpl;
import wg.member.service.IMemberService;
import wg.member.service.MemberServiceImpl;
import wg.movie.service.IMovieService;
import wg.movie.service.MovieServiceImpl;
import wg.mschedule.service.IMScheduleService;
import wg.mschedule.service.MScheduleServiceImpl;
import wg.notice.service.INoticeService;
import wg.notice.service.NoticeServiceImpl;
import wg.onoff.service.IOnOffService;
import wg.onoff.service.OnOffServiceImpl;
import wg.pay.service.IPayService;
import wg.pay.service.PayServiceImpl;
import wg.payDetail.service.IPayDetailService;
import wg.payDetail.service.PayDetailServiceImpl;
import wg.payMethod.service.IpayMethodService;
import wg.payMethod.service.PayMethodServiceImpl;
import wg.preview.service.IPreviewService;
import wg.preview.service.PreviewServiceImpl;
import wg.question.service.IQuestionService;
import wg.question.service.QuestionServiceImpl;
import wg.review.dao.ReviewDaoImpl;
import wg.review.service.IReviewService;
import wg.review.service.ReviewServiceImpl;
import wg.saleCount.service.ISaleCountService;
import wg.saleCount.service.SaleCountServiceImpl;
import wg.seat.service.ISeatService;
import wg.seat.service.SeatServiceImpl;
import wg.seatSch.service.ISeatSchService;
import wg.seatSch.service.SeatSchServiceImpl;
import wg.smovie.service.ISMovieService;
import wg.smovie.service.SMovieServiceImpl;
import wg.snackSet.service.ISnackSetService;
import wg.snackSet.service.SnackSetServiceImpl;
import wg.theater.service.ITheaterService;
import wg.theater.service.TheaterServiceImpl;
import wg.vo.AuctionVO;

public class ServerMain extends Application  {
	
	public static void main(String[] args) {
		
		try {

			IAnswerService answerService = AnswerServiceImpl.getInstance();
			IAuctionService auctionService = AuctionServiceImpl.getInstance();
			IBidService bidService = BidServiceImpl.getInstance();
			IBook_DetailService book_detailService = Book_DetailServiceImpl.getInstance();
			IBookMovieService bookMovieService = BookMovieServiceImpl.getInstance();
			IBuy_DetailService buy_detailService = Buy_DetailServiceImpl.getInstance();
			IBuySnackService buySnackService = BuySnackServiceImpl.getInstance();
			ICouponService couponService = CouponServiceImpl.getInstance();
			IDiscountService discountService = DiscountServiceImpl.getInstance();
			IEventService eventService = EventServiceImpl.getInstance();
			IGenreService genreService = GenreServiceImpl.getInstance();
			IHintService hintService = HintServiceImpl.getInstance();
			IIssueCouponService issueCouponService = IssueCouponServiceImpl.getInstance();
			ILnFService lnfService = LnFServiceImpl.getInstance();
			IMemberService memberService = MemberServiceImpl.getInstance();
			IMovieService movieService = MovieServiceImpl.getInstance();
			IMScheduleService mScheduleService = MScheduleServiceImpl.getInstance();
			INoticeService noticeService = NoticeServiceImpl.getInstance();
			IOnOffService onOffService = OnOffServiceImpl.getInstance();
			IPayService payService = PayServiceImpl.getInstance();
			IPayDetailService payDetailService = PayDetailServiceImpl.getInstance();
			IpayMethodService payMethodService = PayMethodServiceImpl.getInstance();
			IPreviewService previewService = PreviewServiceImpl.getInstance();
			IQuestionService questionService = QuestionServiceImpl.getInstance();
			IReviewService reviewService = ReviewServiceImpl.getInstance();
			ISeatService seatService = SeatServiceImpl.getInstance();
			ISeatSchService seatSchService = SeatSchServiceImpl.getInstance();
			ISMovieService sMovieService = SMovieServiceImpl.getInstance();
			ITheaterService theaterService = TheaterServiceImpl.getInstance();
			ISnackSetService snackService = SnackSetServiceImpl.getInstance();
			IBookMemberCntService bookmemberCntService = BookMemberCntServiceImpl.getInstance();
			ISaleCountService saleCountService = SaleCountServiceImpl.getInstance();
			IBookGenderCntService bookGenderCntService = BookGenderCntServiceImpl.getInstance();
			Registry reg = LocateRegistry.createRegistry(9988);

			reg.rebind("answerService", answerService);
			reg.rebind("auctionService", auctionService);
			reg.rebind("bidService", bidService);
			reg.rebind("book_detailService", book_detailService);
			reg.rebind("bookMovieService", bookMovieService);
			reg.rebind("buy_detailService", buy_detailService);
			reg.rebind("buySnackService", buySnackService);
			reg.rebind("couponService", couponService);
			reg.rebind("discountService", discountService);
			reg.rebind("eventService", eventService);
			reg.rebind("genreService", genreService);
			reg.rebind("hintService", hintService);
			reg.rebind("issueCouponService", issueCouponService);
			reg.rebind("lnfService", lnfService);
			reg.rebind("memberService", memberService);
			reg.rebind("movieService", movieService);
			reg.rebind("mScheduleService", mScheduleService);
			reg.rebind("noticeService", noticeService);
			reg.rebind("onOffService", onOffService);
			reg.rebind("payService", payService);
			reg.rebind("payDetailService", payDetailService);
			reg.rebind("payMethodService", payMethodService);
			reg.rebind("previewService", previewService);
			reg.rebind("questionService", questionService);
			reg.rebind("reviewService", reviewService);
			reg.rebind("seatService", seatService);
			reg.rebind("seatSchService", seatSchService);
			reg.rebind("sMovieService", sMovieService);
			reg.rebind("theaterService", theaterService);
			reg.rebind("snackService", snackService);
			reg.rebind("bookmemberCntService", bookmemberCntService);
			reg.rebind("saleCountService", saleCountService);
			reg.rebind("bookGenderCntService", bookGenderCntService);

			System.out.println("서버 준비 완료...");
			launch(args);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("ServerMain.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("채팅 - Server");
		primaryStage.setScene(scene);
		//primaryStage.show();
	}

	
}