package wg.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;



import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.AlertUtil;
import wg.bid.service.IBidService;
import wg.genre.service.IGenreService;
import wg.vo.AuctionVO;
import wg.vo.LoginVO;
import wg.vo.SnackSetVO;

public class U_auctionRoomController {
   
   public static AuctionVO acvo = new AuctionVO();
   
   
   private AuctionVO vo;

   public AuctionVO getVo() {
      return vo;
   }

   public void setVo(AuctionVO vo) {
      this.vo = vo;
      lbTitle.setText(vo.getAuc_no()+"");
      taOutputMsg.setText(vo.getAuc_title()+"경매를 시작합니다. \n");
   }


   @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbConnectNum;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbTitle;


    @FXML
    private TextArea taOutputMsg;

    @FXML
    private TextField tfMsg;

    @FXML
    private Button btnSend;
    
    @FXML
    private Label memid;
    
    @FXML
    private Label ogPrice;
    
    
    public Label getLbPrice() {
      return lbPrice;
   }

   public void setLbPrice(Label lbPrice) {
      this.lbPrice = lbPrice;
   }

   public Label getMemid() {
      return memid;
   }

   public void setMemid(Label memid) {
      this.memid = memid;
   }


   int i = 0;
    
    
 
    void out() {
       try {
          Stage currenStage = (Stage) lbConnectNum.getScene().getWindow(); 
           
           currenStage.close();// 창 닫기 
         
         clientSocket.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
    }
   
    IBidService service;
    
    private Socket clientSocket= null;
//    private Socket clientSocket2= null;

    @FXML
    void initialize() {
       Registry reg = null;
       try {
         reg = LocateRegistry.getRegistry("localhost",9988);
         service = (IBidService) reg.lookup("bidService");
         ogPrice.setText(service.bidPrice(acvo.getSseat_no())+"");
         
      } catch (RemoteException e) {
         e.printStackTrace();
      } catch (NotBoundException e) {
         e.printStackTrace();
      }
        taOutputMsg.setDisable(false);
        socketConnect();
        tfMsg.setOnKeyPressed(e -> tfMsgSend(e));
        btnSend.setOnAction(e-> btnMsgSend(e) );
        FirstJob.setUa(this);
        
        Runnable r = new Runnable() {
         @Override
         public void run() {
            
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = null;

            try {
               sched = schedFact.getScheduler();
               sched.start();
               

               String year = acvo.getAuc_endTime().substring(0, 4);
               String month = acvo.getAuc_endTime().substring(5, 7);
               String date = acvo.getAuc_endTime().substring(8, 10);
               String hours = acvo.getAuc_endTime().substring(11, 13);
               String minutes = acvo.getAuc_endTime().substring(14, 16);
               String seconds = acvo.getAuc_endTime().substring(17, 19);
               
               
               String CronScheduleStr = seconds+" " + minutes + " " + hours +" * * ? "+year;
               
//               String CronScheduleStr = " 35 15 * * ? 2020";

               JobKey firstJobKey = new JobKey("firstJobKey", "Group");
               JobDetail job = JobBuilder.newJob(FirstJob.class).withIdentity(firstJobKey)
                     .build();
               
               //Cron 설정
               CronTrigger trigger = TriggerBuilder.newTrigger()
                     .withSchedule(CronScheduleBuilder.cronSchedule(CronScheduleStr))
                     .build();
//               
//               CronTrigger trigger2 = TriggerBuilder.newTrigger()
//                     .withSchedule(CronScheduleBuilder.cronSchedule(CronScheduleStr2))
//                     .build();
//            
//               sched.getListenerManager().addJobListener(new FirstListener(), KeyMatcher.keyEquals(firstJobKey));
//               sched.scheduleJob(goOut(event), trigger);
//               
               sched.getListenerManager().addJobListener(new FirstListener(), KeyMatcher.keyEquals(firstJobKey));
               sched.scheduleJob(job, trigger);

               
            } catch (SchedulerException e) {
               e.printStackTrace();
            } catch (Exception e){
               e.printStackTrace();
            }

         }
      };
      
      
      Thread th = new Thread(r);
      th.start();

          }
       
    
    
    public void msgSend(){
       try {
           if(clientSocket==null){
              taOutputMsg.setText("서버에 접속되지 않았습니다.\n");
              return;
           }
           
           // 입력창에서 입력 후 'Enter'키를 누르면 메시지 전송
            String sendMessage = acvo.getAuc_no()+":"+LoginVO.currVo.getMem_id() +":"+ tfMsg.getText();
            String regEx = "[1-9]{1}[0-9]*";
            if(!(Pattern.matches(regEx, tfMsg.getText()))) {
               return;
            }else if(Integer.parseInt(lbPrice.getText())>=Integer.parseInt(tfMsg.getText())) {
               AlertUtil.warnMsg("입력오류","현 경매가 보다 높은 금액을 입력해주세요." );
               tfMsg.clear();
               return;
            }
            else if(Integer.parseInt(tfMsg.getText())>=service.bidPrice(acvo.getSseat_no())) {
               AlertUtil.warnMsg("입력오류","원가 보다 낮은 금액을 입력해주세요." );
               tfMsg.clear();
               return;
            }
            byte[] byteArray = sendMessage.getBytes("UTF-8");
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tfMsg.clear();
    }
    
    public void btnMsgSend(ActionEvent event){
       msgSend();
    }
    
    
    public void tfMsgSend(KeyEvent event){
       if (event.getCode() == KeyCode.ENTER) {
            msgSend();
        }
    }
    
    // 서버에 접속하기
    public void socketConnect(){
       
          try {

             
             // 방법1
             clientSocket = new Socket("localhost", 4040);
             
             /*
             // 방법2
             clientSocket = new Socket();
            //clientSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
             clientSocket.connect(new InetSocketAddress(tfIp.getText(), Integer.parseInt( tfPort.getText())) );
             */
             System.out.println("서버 접속........................................");
             
             taOutputMsg.setDisable(false);
             tfMsg.setDisable(false);
             btnSend.setDisable(false);
             
            taOutputMsg.setText("서버에 접속하였습니다.\n\n");
            
         
            try {
               // 서버 접속후 '이름'을 서버로 전송
               String sendMessage = acvo.getAuc_no()+":"+LoginVO.currVo.getMem_id();
               byte[] byteArray = sendMessage.getBytes("UTF-8");
               OutputStream outputStream = clientSocket.getOutputStream();
               outputStream.write(byteArray);
               outputStream.flush();
                   
            } catch (Exception e) {
                   e.printStackTrace();
            }
            // ClientReader객체 생성
            ClientReader clientReader = new ClientReader(clientSocket);
            clientReader.setDaemon(true);
            clientReader.start();
          }catch (ConnectException e){
             taOutputMsg.setText("서버가 꺼져있습니다.\n\n");
          } catch (Exception e) {
            e.printStackTrace();
         }
       
    }
    
    
    // Reader class
    class ClientReader extends Thread {
        Socket clientSocket = null;
        String readMessage = null;
 
        ClientReader(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                   
                    InputStream inputStream = clientSocket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
                    if (size == -1){
                       break;  // 강제종료일 경우
                    }
                    readMessage = new String(byteArray, 0, size, "UTF-8");
                    if(readMessage.equals("FFFF")) {
                        tfMsg.clear();
                        taOutputMsg.clear();
                        
                        readMessage = "서버가 종료되었습니다";    
                        Platform.runLater(() -> {
                           
                          taOutputMsg.setDisable(false);
                          tfMsg.setDisable(true);
                          btnSend.setDisable(true);
                           
                          
                        });
                        if(!clientSocket.isClosed()){
                           clientSocket.close();
                        }
                    }
                       System.out.println(readMessage);
                       if(readMessage.contains("접속자수")) {
                          Platform.runLater(() -> {
                             lbConnectNum.setText(readMessage.substring(readMessage.indexOf("수")+2,readMessage.indexOf("가")));
                             lbPrice.setText(readMessage.substring(readMessage.indexOf("격")+2, readMessage.length()));
                             taOutputMsg.appendText(readMessage.substring(0,readMessage.indexOf("수")-4) + "\n");
                            });
                       }else {
                          Platform.runLater(() -> {
                             if(Integer.parseInt(readMessage.substring(readMessage.indexOf(":")+1,readMessage.length()))>Integer.parseInt(lbPrice.getText())) {
                                memid.setText(readMessage.substring(0,readMessage.indexOf(":")));
                                lbPrice.setText(readMessage.substring(readMessage.indexOf(":")+1,readMessage.length()));
                             }
                            });
                          taOutputMsg.appendText(readMessage + "\n");
                       }

                  
                    
                    
                    
                }
            } catch (SocketException e){
               //System.out.println("--> " + clientSocket.isClosed());
               if(!clientSocket.isClosed()){
                  try {   
                     clientSocket.close();  
                     taOutputMsg.appendText("서버가 종료되었습니다\n");
                  } catch (IOException e1) {   }
               }
            } catch (Exception e) {
                  e.printStackTrace();
            }
        }
    }


   

}