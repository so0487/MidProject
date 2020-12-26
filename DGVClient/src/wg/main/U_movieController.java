package wg.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.AlertUtil;
import wg.movie.service.IMovieService;
import wg.vo.MovieVO;
import wg.vo.SelectMovieVO;

public class U_movieController {
    private IMovieService service;
	
    @FXML
    private ScrollPane outerBox3;
	
    @FXML
    private StackPane outerBox2;
	
    @FXML
    private BorderPane outerBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBox;

    @FXML
    private Button btnSchedule;

    @FXML
    private VBox vbox;
    
    @FXML
    private TextField tf_movName;

    @FXML
    private Button btnScr;
    
    private G_topController topCtrl;
    public void setTopCtrl(G_topController topCtrl) {
    	this.topCtrl=topCtrl;
    }
    
    @FXML
    void btnBoxClick(ActionEvent event) {
    	 int i = 0;      
         HBox hb = null;
         vbox.getChildren().clear();
         
         
         try {
            List<MovieVO> list = service.getAllMovie();

             for(MovieVO movieVo : list) {
                if(i%3 == 0) {
                   hb = new HBox();
                }
                
                hb.setId("hb"+i);
                if(hb.getId().equals("hb"+i)) {
                   SelectMovieVO.setCurrMovieVO(list.get(i));
                }
                
                FXMLLoader loader1 = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieSet.fxml"));
                Parent center = loader1.load();
                U_movieOneController setOneController = loader1.getController();
                setOneController.setTopCtrl(topCtrl);
                setOneController.setMovieVo(movieVo);
                MovieVO mm = movieVo;
               
               center.setOnMouseClicked(e->{
                  try {
                    FXMLLoader loader = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieInfo.fxml"));
                    Parent selectedMovie = loader.load();
                    
                    
                    outerBox.getChildren().clear();
                    outerBox.setCenter(selectedMovie);
                    
                    U_movieInfoController mi = loader.getController();
                    mi.setVo(mm);
                 } catch (IOException e2) {
                    e2.printStackTrace();
                 }
               });   
               
               
               hb.getChildren().add(center);
               
               if(i%3 == 0) {
                  vbox.getChildren().add(hb);
               }
               
               i++;
             }
        } catch (RemoteException e) {
           e.printStackTrace();
        }
         catch (IOException e) {
           e.printStackTrace();
        }

    }

    @FXML
    void btnScheduleClick(ActionEvent event) {
  	
    	int i = 0;   	
    	HBox hb = null;
    	vbox.getChildren().clear();
    	
    	try {
    		List<MovieVO> list = service.movieSchList();

        	for(MovieVO movieVo : list) {
        		if(i%3 == 0) {
        			hb = new HBox();
        		}
        		
        		hb.setId("hb"+i);
        		if(hb.getId().equals("hb"+i)) {
        			SelectMovieVO.setCurrMovieVO(list.get(i));
        		}
        		
        		FXMLLoader loader1 = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieSet.fxml"));
        		Parent center = loader1.load();
        		U_movieOneController setOneController = loader1.getController();
        		setOneController.setMovieVo(movieVo);
        		setOneController.setBtn();
        		MovieVO mm = movieVo;
    			
    			center.setOnMouseClicked(e->{
    				try {
						FXMLLoader loader = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieInfo.fxml"));
						Parent selectedMovie = loader.load();
						
						outerBox.getChildren().clear();
						outerBox.setCenter(selectedMovie);
						
						U_movieInfoController mi = loader.getController();
						SelectMovieVO.setCurrMovieVO(setOneController.getMvoieVo());
						mi.setVo(mm);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
    			});	
    			
    			
    			hb.getChildren().add(center);
    			
    			if(i%3 == 0) {
    				vbox.getChildren().add(hb);
    			}
    			
    			i++;
        	}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    @FXML
    void btnScrClick(ActionEvent event) {
    	if(tf_movName.getText().isEmpty()) {
    		AlertUtil.warnMsg("검색오류", "영화명을 입력해주십시오.");
    		return;
    	}
    	
    	int i = 0;   	
    	HBox hb = null;
    	String movie_name = tf_movName.getText();
    	vbox.getChildren().clear();
    	
    	
    	try {
    		List<MovieVO> list = service.umovNameList(movie_name);
    		
    		
    		if(list.size() == 0) {
    			AlertUtil.warnMsg("검색결과", "검색한 영화가 없습니다.");
    			tf_movName.requestFocus();
    			return;
    		}

        	for(MovieVO movieVo : list) {
        		if(i%3 == 0) {
        			hb = new HBox();
        		}
        		
        		int sCount = service.umovNameList2(movieVo.getMovie_id());
        		
        		
        		hb.setId("hb"+i);
        		if(hb.getId().equals("hb"+i)) {
        			SelectMovieVO.setCurrMovieVO(list.get(i));
        		}
        		
        		FXMLLoader loader1 = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieSet.fxml"));
        		Parent center = loader1.load();
        		U_movieOneController setOneController = loader1.getController();
        		setOneController.setMovieVo(movieVo);
        		//날짜비교 버튼 바꾸기
        		String rel = movieVo.getMovie_release();
        		String end = movieVo.getMovie_end();
        		Date nowDate = new Date();
        		
        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		String now = format.format(nowDate);
        		
        		Date relDate = format.parse(rel);
        		Date endDate = format.parse(end);
        		Date nowDateFormat = format.parse(now);
        		       		
        		if(relDate.getTime() > nowDateFormat.getTime()) {
        			setOneController.setBtn();
        		}else if(endDate.getTime() < nowDateFormat.getTime()) {
        			setOneController.setBtnClear();
        		}else if(sCount == 0) {
        			setOneController.setBtnNo();
        		}
        			
        		MovieVO mm = movieVo;	
    			
    			center.setOnMouseClicked(e->{
    				try {
						FXMLLoader loader = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieInfo.fxml"));
						Parent selectedMovie = loader.load();
						outerBox.getChildren().clear();
						outerBox.setCenter(selectedMovie);
						U_movieInfoController mi = loader.getController();
						mi.setVo(mm);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
    			});	
    			
    			
    			hb.getChildren().add(center);
    			
    			if(i%3 == 0) {
    				vbox.getChildren().add(hb);
    			}
    			
    			i++;
        	}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

    } 

    @FXML
    void initialize() {
    	Registry reg = null;
    	
    	try {
    		reg = LocateRegistry.getRegistry("localhost", 9988);
    		service = (IMovieService) reg.lookup("movieService");
    		List<MovieVO> list = service.getAllMovie();
    		
    		int i = 0;
    		HBox hb = null;
    		
    		for(MovieVO movieVo : list) {
    			if(i%3 == 0) {
    				hb = new HBox();
    			}
    			hb.setId("hb"+i);
    			if(hb.getId().equals("hb"+i)) {
    				SelectMovieVO.setCurrMovieVO(list.get(i));
    			}
    			FXMLLoader loader1 = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieSet.fxml"));
    			Parent center = loader1.load();
    			U_movieOneController setOneController = loader1.getController();
    			setOneController.setMovieVo(movieVo);
    			final MovieVO mm = movieVo;
    			
    			center.setOnMouseClicked(e->{
    				try {
						FXMLLoader loader = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieInfo.fxml"));
						Parent selectedMovie = loader.load();
						
						outerBox.getChildren().clear();
						
						outerBox.setCenter(selectedMovie);
						
						U_movieInfoController mi = loader.getController();
						mi.setVo(mm);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
    			});
    			
    			hb.getChildren().add(center);
    			
    			if(i%3==0) {
    				vbox.getChildren().add(hb);
    			}
    			i++;
    			
    		}
    		
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void callInfo(MovieVO mvo) {
    	try {
			FXMLLoader loader = new FXMLLoader(U_movieController.class.getResource("../fxml/U_movieInfo.fxml"));
			Parent selectedMovie = loader.load();
			
			outerBox.getChildren().clear();
			outerBox.setCenter(selectedMovie);
			
			U_movieInfoController mi = loader.getController();
			mi.setVo(mvo);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
    }
}
