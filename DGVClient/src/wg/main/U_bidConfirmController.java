package wg.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.AlertUtil;
import wg.bid.service.IBidService;
import wg.book_detail.service.IBook_DetailService;
import wg.bookmovie.service.IBookMovieService;
import wg.issueCoupon.service.IIssueCouponService;
import wg.pay.service.IPayService;
import wg.payDetail.service.IPayDetailService;
import wg.seatSch.service.ISeatSchService;
import wg.vo.BidResultVO;
import wg.vo.BookMovieVO;
import wg.vo.Book_DetailVO;
import wg.vo.LoginVO;
import wg.vo.PayVO;

public class U_bidConfirmController {
	private U_payController cou;
	public U_payController getCou() {
		return cou;
	}
	public void setCou(U_payController cou) {
		this.cou = cou;
	}

    @FXML
    private Button btnGo;

    @FXML
    private Button btnCxl;

    @FXML
    private ImageView ImgPoster;

    @FXML
    private Label lbl_movie_name;

    @FXML
    private Label lbl_theater_name;

    @FXML
    private Label lbl_date_time;

    @FXML
    private Label lbl_qty;

    @FXML
    private Label lbl_seat_id;

    @FXML
    private Label lbl_pay_price;
    
    

    @FXML
    private Label lbl_pay_met;
    
    @FXML
    private CheckBox allCheck;

    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check3;

    @FXML
    private CheckBox check2;
    
    BidResultVO brvo;
    

    public BidResultVO getBrvo() {
		return brvo;
	}
	public void setBrvo(BidResultVO brvo) {
		this.brvo = brvo;
	}

	@FXML
    private CheckBox check4;
    private IBidService bidService;
    private IBook_DetailService bdService;
    private IPayService pService;
    private IPayDetailService pdService;
    private ISeatSchService ssService;
    
    private U_bookMovieMainController mainCtrl;
    
    public U_bookMovieMainController getMainCtrl() {
    	return mainCtrl;
    }
    
    public void setMainCtrl(U_bookMovieMainController mainCtrl) {
    	this.mainCtrl = mainCtrl;
    }
    
    private Map<String, String> infoMap;
    public void setInfoMap(Map<String, String> infoMap) {
    	this.infoMap = infoMap;
    }
    
    private List<Integer> selectedSseats;
    public void setSelectedSseats(List<Integer> selectedSseats) {
    	this.selectedSseats = selectedSseats;
    }
    public List<Integer> getSelectedSseats() {
    	return selectedSseats; 
    }
    
    private List<String> selectedSeats;
    public void setSelectedSeats(List<String> selectedSeats) {
    	this.selectedSeats= selectedSeats;
    }
    public List<String> getSelectedSeats() {
    	return selectedSeats; 
    }
    
    @FXML
    void btnCxlClick(ActionEvent event) {
    	Stage currStage = (Stage) btnCxl.getScene().getWindow();
    	currStage.close();
    }
    
    @FXML
    void allCheckClick(ActionEvent event) {
    	check1.setSelected(true);
    	check2.setSelected(true);
    	check3.setSelected(true);
    	check4.setSelected(true);
    	btnGo.setDisable(false);
    	
    	if(!allCheck.isSelected()) {
    		check1.setSelected(false);
    		check2.setSelected(false);
    		check3.setSelected(false);
    		check4.setSelected(false);
    		btnGo.setDisable(true);
    	}
    }
    
    @FXML
    void check1Click(ActionEvent event) {
    	setCheck();
    }

    @FXML
    void check2Click(ActionEvent event) {
    	setCheck();
    	
    }

    @FXML
    void check3Click(ActionEvent event) {
    	setCheck();
    	
    }

    @FXML
    void check4Click(ActionEvent event) {
    	setCheck();
    
    }

    
    // 1은 예매 2는 경매
    
    private int ab;
    
	
	public int getAb() {
		return ab;
	}
	public void setAb(int ab) {
		this.ab = ab;
	}
	@FXML
    void btnGoClick(ActionEvent event) throws IOException {
    	if(ab==1) {

        	// 성공 시 부모창 변경
        	FXMLLoader loader = new FXMLLoader(U_bidConfirmController.class.getResource("../fxml/U_payResult.fxml"));
        	Parent result = loader.load();
        	mainCtrl.setResult(result);
        	
        	// 현재창 닫기
        	Stage currStage = (Stage) btnGo.getScene().getWindow();
        	currStage.close();
        	
        	Registry reg = null;
        	int cnt = 0;
        	try {
    			reg = LocateRegistry.getRegistry("localhost",9988);
    			bdService = (IBook_DetailService) reg.lookup("book_detailService");
    			pService = (IPayService) reg.lookup("payService");
    			pdService = (IPayDetailService) reg.lookup("payDetailService");
    			ssService = (ISeatSchService) reg.lookup("seatSchService");
    			
    			
        	}catch (Exception e) {
				// TODO: handle exception
			}
    
    	}
    			
    			// BookMovie테이블 insert
    			
    	
    	
    	
    	
    	
    	
    }
    
    private IIssueCouponService service;
    
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost", 9988);
			service = (IIssueCouponService) reg.lookup("issueCouponService");
			bidService = (IBidService) reg.lookup("bidService");
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	setCheck();
    	btnGo.setDisable(true);
    }
    
    public void setMovieName(String movie) {
    	lbl_movie_name.setText(movie);
    }
    public void setTheaterName(String theater) {
    	lbl_theater_name.setText(theater);
    }
    public void setDateTime(String dateTime) {
    	lbl_date_time.setText(dateTime);
    }
    public void setQty(int qty) {
    	lbl_qty.setText(qty+"명");
    }
    public void setSeatId(String seats) {
    	lbl_seat_id.setText(seats);
    }
    public void setPayPrice(int pay_price) {
    	lbl_pay_price.setText(pay_price+"원");
    }
    public void setPayMet(String pay_met) {
    	lbl_pay_met.setText(pay_met);
    }
    
    public void setMoviePos(String poster) {
    	File file = new File(poster);
    	Image image = new Image(file.toURI().toString());
    	ImgPoster.setImage(image);
    }
    
    public void setCheck() {
    	if(check1.isSelected() && check2.isSelected() && check3.isSelected() && check4.isSelected()) {
    		allCheck.setSelected(true);
    	}
    	if(!check1.isSelected() || !check2.isSelected() || !check3.isSelected() || !check4.isSelected()) {
    		allCheck.setSelected(false);
    	}
    	if(allCheck.isSelected()) {
    		btnGo.setDisable(false);
    	}
    	if(!allCheck.isSelected()) {
    		btnGo.setDisable(true);
    	}
    }
    
    public void makePDF(Map<String, String> infoMap) {
    	//새로운 document 객체 생성
    	Document doc = new Document();
    	
    	try {
    		BaseFont bfKorean_ = BaseFont.createFont("HYGoThic-Medium",
    				"UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
    	} catch (DocumentException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	
    	try {
    		PdfWriter.getInstance(doc, new FileOutputStream("test.pdf"));
    		System.out.println("파일이 생성되었습니다.");
    	}catch (Exception e) {
    		// TODO: handle exception
    		System.out.println("pdf파일 생성 실패");
    		e.printStackTrace();
    	}
    	
    	
    	doc.open();//pdf 열기
    	
    	//pdf파일 내용 작성
    	try {
    		//PDF내용 생성
    		//사진파일 생성
    		//이미지 파일경로 지정
    		String logoPath = "c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/src/wg/img/dgbLogo.jpg";   
    		
    		//image를 처리하는 getInstance
    		com.itextpdf.text.Image dgvlogo = com.itextpdf.text.Image.getInstance(logoPath);
    		
    		//로고 이미지 크기 지정
    		dgvlogo.scaleAbsolute(180, 100);
    		
    		//로고 이미지 정렬
    		dgvlogo.setAlignment(Paragraph.ALIGN_CENTER);
    		
    		//이지미 추가
    		doc.add(dgvlogo);
    		
    		String dateTime = infoMap.get("selectedDate")+"("+infoMap.get("selectedDay")+") "+infoMap.get("selectedTime");
			String movie = infoMap.get("selectedMovie");
			String pay_met = infoMap.get("pay_met");
			String pos = infoMap.get("moviePos");
			int payPrice = Integer.parseInt(infoMap.get("pay_price"));
			String theater_name = infoMap.get("theater_name");
			int qty = Integer.parseInt(infoMap.get("book_qty"));
			String seats = "";
			for (int i = 0; i < selectedSeats.size(); i++) {
				if(!seats.equals("")) {
					seats +=", ";
				}
				seats += selectedSeats.get(i);
			}
			String moviePos = infoMap.get("moviePos");
    		
    		
    		PdfPTable book_no = new PdfPTable(1);
    		PdfPCell book_no_title;
    		book_no_title = new PdfPCell(new Phrase("예매번호 : B0001"));
    		book_no_title.setPadding(5f);
    		book_no_title.setBorder(1);
    		book_no_title.setBorderColor(BaseColor.GRAY);
    		//book_no_title.setExtraParagraphSpace(15);
    		book_no.addCell(book_no_title);
    		doc.add(book_no_title);
    		doc.add(book_no);
    		
    		
    		PdfPTable bookinfo = new PdfPTable(1);
    		PdfPCell bookinfo_title;
    		bookinfo_title = new PdfPCell(new Phrase("[예매정보]"));
    		bookinfo_title.setPadding(5f);
    		bookinfo_title.setBorder(1);
    		bookinfo_title.setBorderColor(BaseColor.GRAY);
    		bookinfo.addCell(bookinfo_title);
    		doc.add(bookinfo_title);
    		doc.add(bookinfo);
    		
    		
    		PdfPTable table = new PdfPTable(2);
    		//사진파일 생성
    		String posterPath = moviePos;   //이미지 파일경로 지정
    		
    		//image를 처리하는 getInstance
    		com.itextpdf.text.Image posterImg = com.itextpdf.text.Image.getInstance(posterPath);
    		
    		//포스터 이미지 크기 지정
    		posterImg.scaleAbsolute(150, 200);
    		PdfPCell movie_poster = new PdfPCell(posterImg);
    		movie_poster.setPaddingTop(5f);
    		movie_poster.setPaddingBottom(5f);
    		movie_poster.setRowspan(6);
    		movie_poster.setBorder(0);
    		movie_poster.setVerticalAlignment(Element.ALIGN_CENTER);
    		table.addCell(movie_poster);
    		
    		PdfPCell movie_name = new PdfPCell(new Phrase("관람영화 : "+movie));
    		movie_name.setBorder(0);
    		movie_name.setPaddingTop(30);
    		movie_name.setPaddingBottom(15);
    		table.addCell(movie_name);
    		
    		PdfPCell movie_date =new PdfPCell(new Phrase("관람일시 : "+dateTime));
    		movie_date.setBorder(0);
    		movie_date.setPaddingBottom(15);
    		table.addCell(movie_date);
    		
    		PdfPCell theater = new PdfPCell(new Phrase("관람관 : "+theater_name));
    		theater.setBorder(0);
    		theater.setPaddingBottom(15);
    		table.addCell(theater);
    		
    		PdfPCell seat = new PdfPCell(new Phrase("좌석 : "+seats));
    		seat.setBorder(0);
    		seat.setPaddingBottom(15);
    		table.addCell(seat);
    		
    		// 날짜
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		Date now = new Date();
    		String time = format.format(now);
    		
    		//PdfPTable pay_info = new PdfPTable(1);
    		PdfPCell pay_date;
    		pay_date = new PdfPCell(new Phrase("결제일시 : "+time));
    		pay_date.setBorder(0);
    		pay_date.setPaddingBottom(15);
    		table.addCell(pay_date);
    		
    		PdfPCell pay_price;
    		pay_price = new PdfPCell(new Paragraph("결제금액 : "+payPrice+"원"));
    		pay_price.setPaddingBottom(15);
    		pay_price.setBorder(0);
    		table.addCell(pay_price);
    		
    		doc.add(table);
    		
    	}catch(DocumentException e){
    		System.out.println("내용 작성 실패");
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	doc.close();
    }
    
    
    public void sendEmail(){
        final String user = "so0487@naver.com"; // 발신인의 naver 계정
        final String password = "so6725so6725*"; // 패스워드
        final String[] sendTo = {LoginVO.getCurrVo().getMem_email()};
        
        Properties prop = new Properties(); //프로퍼티스 객체 생성
        //prop에 메일을 보내기 위한 기본 설정 값을 입력
          prop.put("mail.smtp.host", "smtp.naver.com");
          prop.put("mail.smtp.port", "587");
          prop.put("mail.smtp.auth", "true");
        
        //naver 계정 인증
        Authenticator auth = new Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(user, password);
           }
        };
        
        //세션객체 생성
        Session session = Session.getDefaultInstance(prop, auth);
        
        //세션객체를 가지고 MimeMessage 객체 생성
        Message msg = new MimeMessage(session);
        InternetAddress[] addTo = new InternetAddress[sendTo.length]; //단체 메일을 보내기 위해 수신자의 이메일 주소를 저장할 InternetAddress형 배열
        Multipart multi = new MimeMultipart(); //mbody를 추가할 Multipart
        
        try {
           msg.setSubject("[DGV]"+LoginVO.getCurrVo().getMem_name()+"님께서 예매하신 내역입니다."); //메일 제목 설정
           msg.setFrom(new InternetAddress(user)); //발신인 이메일 주소 set
           //단체 메일 보내기
           for (int i = 0; i < sendTo.length; i++) {
              addTo[i] = new InternetAddress(sendTo[i]); //sendTo배열의 주소를 InternetAddress 객체로 addTo배열에 넣어준다
           }
           msg.setRecipients(Message.RecipientType.TO, addTo);
        } catch (AddressException e) {
           System.out.println("이메일 주소를 확인해주세요.");
           e.printStackTrace();
           return;
        } catch (MessagingException e) {
           System.out.println("메시지를 제대로 입력해주세요.");
           e.printStackTrace();
           return;
        }
        
        BodyPart mbody = new MimeBodyPart(); //메시지 몸체 생성
        try {
           mbody.setText("메일의 내용입니다.");
           multi.addBodyPart(mbody); //Multipart에 내용 추가
        } catch (MessagingException e) {
           System.out.println("메일의 내용을 제대로 입력해주세요.");
           e.printStackTrace();
           return;
        }
        
        mbody = new MimeBodyPart(); //파일을 첨부할 mbody
        File file = new File("c:/soo//A_TeachingMaterial/4.MiddleProject/workspace/DGVClient/test.pdf"); //첨부 할 파일 경로
        FileDataSource fds = new FileDataSource(file); //file를 이용해 FileDataSource(파일을 캡슐화 해준다) 객체 생성
        
        try {
           mbody.setDataHandler(new DataHandler(fds));
           mbody.setFileName(file.getName()); //파일 이름
           multi.addBodyPart(mbody);
        } catch (MessagingException e) {
           System.out.println("첨부된 파일을 확인하세요.");
           e.printStackTrace();
           return;
        }
        
        try {
           msg.setContent(multi); //Message객체에 multi 내용을 set
           Transport.send(msg);
        } catch (MessagingException e) {
           System.out.println("오류가 발생했습니다.");
           e.printStackTrace();
           return;
        }
        
        System.out.println("메일이 성공적으로 발송되었습니다.");
     }
    
}

