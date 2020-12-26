package wg.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.AlertUtil;
import wg.auction.service.IAuctionService;
import wg.notice.service.INoticeService;
import wg.vo.AuctionVO;



public class ServerMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnServerStart;

    @FXML
    private Label lblCount;

    @FXML
    private TextArea taOutputMsg;
    
    // 접속한 소켓을 저장할 list --> (접속자리스트)
    private List<Socket> socketList = Collections.synchronizedList(new ArrayList<Socket>()) ;
    
    private Map<Integer, List<Socket>> roomMap = Collections.synchronizedMap(new HashMap<Integer, List<Socket>>()); 
    private Map<Integer, Integer> priceMap = Collections.synchronizedMap(new HashMap<Integer, Integer>()); 
    
    ServerSocket mainServerSocket= null;
//    ServerSocket mainServerSocket2= null;
    
    
    String price = "1000";
    IAuctionService service;
    
    @FXML
    void serverEnd(ActionEvent event) {
    	if (btnServerStart.getText().equals("Server Stop")) {
    		try {
    			taOutputMsg.appendText("\n서버가 닫혔습니다.\n\n");
                btnServerStart.setText("Server Start");                    
                
                // to send 'SERVER CLOSE' message to Client
                String sendMessage = "FFFF";
                byte[] byteArray = sendMessage.getBytes("UTF-8");
                for (int i = 0; i < socketList.size(); i++) {
                    OutputStream outputStream = socketList.get(i).getOutputStream();
                    outputStream.write(byteArray);
                }
                socketList.clear();
                mainServerSocket.close();
//                mainServerSocket2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
//    String CronScheduleStr = null;
//	String CronScheduleStr2 = null;

    @FXML
    void initialize() throws SchedulerException {
    	serverStart();
    	Registry reg = null;
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IAuctionService) reg.lookup("auctionService");
			
			
			
//				Runnable rn = new Runnable() {
//					@Override
//					public void run() {
//						new ServerMainController().serverStart();
//					}
//				};
//				Thread th = new Thread(rn);
			
			
			
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void serverStart(){
    	if (btnServerStart.getText().equals("Server Start")) {
				try {
					mainServerSocket = null;
					taOutputMsg.setText("ip : " + InetAddress.getLocalHost() + "\n");
					
					// 방법1
					//mainServerSocket = new ServerSocket(port번호);
					
					// 방법2
					mainServerSocket = new ServerSocket();
					mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
					
					taOutputMsg.appendText("서버가 열렸습니다.\n\n");
					btnServerStart.setText("Server Stop");
					lblCount.setText(socketList.size() + " 명");
				
					ConnectThread connectThread = new ConnectThread(mainServerSocket);
					connectThread.setDaemon(true);
					connectThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
    	} else if (btnServerStart.getText().equals("Server Stop")) {
    		try {
    			taOutputMsg.appendText("\n서버가 닫혔습니다.\n\n");
                btnServerStart.setText("Server Start");                    
                
                // to send 'SERVER CLOSE' message to Client
                String sendMessage = "FFFF";
                byte[] byteArray = sendMessage.getBytes("UTF-8");
                for (int i = 0; i < socketList.size(); i++) {
                    OutputStream outputStream = socketList.get(i).getOutputStream();
                    outputStream.write(byteArray);
                }
                socketList.clear();
                mainServerSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    List<Socket> roomSocketList = null;
    //------------------------------------------------------------------------------
    // Connect class
    class ConnectThread extends Thread {
    	ServerSocket mainServerSocket = null;
 
        ConnectThread(ServerSocket mainServerSocket) {
            this.mainServerSocket = mainServerSocket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                    Socket socket = mainServerSocket.accept();
                    
                  
                    
                    
                    
                    //outputField.setText("사용자 접속!");
 
//                    socketList.add(socket);
//                    Platform.runLater(() -> {
//                        lblCount.setText(socketList.size() + " 명");
//                    });
 					
                    // 접속한 사용자가 처음 보낸 메시지 받기 ( 방번호:사용자 이름 받기)
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
 
                    String readMessage = new String(byteArray, 0, size, "UTF-8");
                    String[] strArr = readMessage.split(":");
                    int roomNum = Integer.parseInt(strArr[0].trim());
                    
                    if(roomMap.containsKey(new Integer(roomNum))) {
                    	roomSocketList = roomMap.get(new Integer(roomNum));
                    }else {
                    	roomSocketList = new ArrayList<Socket>();
                    	roomMap.put(roomNum, roomSocketList);
                    	priceMap.put(roomNum, 1000);	// 초기값 1000원
                    }
                    
                    roomSocketList.add(socket);
                    price = String.valueOf(priceMap.get(new Integer(roomNum)));
                    Platform.runLater(() -> {
                        lblCount.setText(roomSocketList.size() + " 명");
                    });
                    
                    String user = strArr[1];
                    Platform.runLater(() -> {
                    	taOutputMsg.appendText(user + "님 접속\n");
                    });
//                    System.out.println(readMessage2);
                    
                    //--
                    
//                    String sendMessage = readMessage +"님이 접속하셨습니다.";
//                    String sendMessage = readMessage +"님이 접속하셨습니다." +"접속자수"+socketList.size() + "가격"+price;
                    
                    String sendMessage = user +"님이 접속하셨습니다." +"접속자수:"+ roomSocketList.size() + " 가격:"+price;
                    byteArray = sendMessage.getBytes("UTF-8");
                    System.out.println("ssssssssss"+socketList.size());
                    for (int i = 0; i < roomSocketList.size(); i++) {
                    	//if (socket != socketList.get(i)){
	                        OutputStream outputStream = roomSocketList.get(i).getOutputStream();
	                        outputStream.write(byteArray);
	                        outputStream.flush();
                    	//}
                    }
                   
                   
                    
                  
                    
                    //--
                    
                    ServerReader serverReader = new ServerReader(socket);
                    serverReader.start();
                }
            } catch (Exception e) {
            	//System.out.println("==> " + mainServerSocket.isClosed());
            	if(!mainServerSocket.isClosed())
            		e.printStackTrace();
            }
        }
    }
    //-------------------------------------------------------------------------------
    // Reader class
    class ServerReader extends Thread {
        Socket socket = null;
 
        ServerReader(Socket socket) {
            this.socket = socket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                    InputStream inputStream = socket.getInputStream();  // 방번호:ID:금액
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
                    //System.out.println("<<<<<============>>>> size = " + size);
                    if (size == -1) { // 클라이언트이 '접속종료'버튼으로 접속을 끊었을 때.
                        for (int i = 0; i < socketList.size(); i++) {
                            if (socket == socketList.get(i)) {
                            	socketList.remove(i);
                            	break;
                            } 
                        }
                        Platform.runLater(() -> {
                        	lblCount.setText(socketList.size() + " 명");
                        	
                        	System.out.println(price);
                        });
                        break;
                    }
                    
                    String readMessage = new String(byteArray, 0, size, "UTF-8");
                    String[] messageArr = readMessage.split(":");
                    
                    int roomNum = Integer.parseInt(messageArr[0]);
                    String user = messageArr[1];
                   	price = messageArr[2];
                   	// 새로작성
                   	priceMap.put(roomNum, Integer.parseInt(price));
                    
                    //System.out.println("li.size() = " + socketList.size());
                   	String sendMessage = user + ":" + price;
                    byteArray = sendMessage.getBytes("UTF-8");
                    //outputField.setText(readMessage);
                    List<Socket> roomSocketList = roomMap.get(new Integer(roomNum));
                    //--------------------------
                    for (int i = 0; i < roomSocketList.size(); i++) {
							OutputStream outputStream = roomSocketList.get(i).getOutputStream();
							outputStream.write(byteArray);
							//System.out.println("byteArray(Server) => " + byteArray);
							outputStream.flush();
                    }
                    //---------------------------
                    
                }
            } catch (Exception e) {
            	// 클라이언트 프로그램이 종료되었을 때.
                for (int i = 0; i < socketList.size(); i++) {
                    if (socket == socketList.get(i)) {
                    	socketList.remove(i);
                    	break;
                    } 
                }
                Platform.runLater(() -> {
                	lblCount.setText(socketList.size() + " 명");
                	
                	System.out.println(price);
                });
            }
        }
    }
    //-----------------------------------------------------------------------------
    
}
