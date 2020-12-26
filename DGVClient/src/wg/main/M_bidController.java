package wg.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import wg.auction.service.IAuctionService;
import wg.bid.service.IBidService;
import wg.vo.BidVO;
import wg.vo.BookMovieViewVO;

public class M_bidController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BidVO> table;

    @FXML
    private TableColumn<?, ?> bidid;

    @FXML
    private TableColumn<?, ?> memid;

    @FXML
    private TableColumn<?, ?> aucno;

    @FXML
    private TableColumn<?, ?> bidtime;

    @FXML
    private TableColumn<?, ?> bidprice;

    @FXML
    void click(MouseEvent event) {

    }
    IBidService service;
    private ObservableList<BidVO> list;
    @FXML
    void initialize() {
    	Registry reg = null;
    	try {
//			reg = LocateRegistry.getRegistry("192.168.31.32",9988);
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IBidService) reg.lookup("bidService");
			
			
			bidid.setCellValueFactory(new PropertyValueFactory<>("bid_id"));
			memid.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			aucno.setCellValueFactory(new PropertyValueFactory<>("auc_no"));
			bidtime.setCellValueFactory(new PropertyValueFactory<>("bid_time"));
			bidprice.setCellValueFactory(new PropertyValueFactory<>("bid_price"));
			
			
			getAllList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

    }
    private void getAllList() {
    	List<BidVO> data;
    	try {
			data = service.getAllList();
			list = FXCollections.observableArrayList(data);
			table.setItems(list);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}

    

