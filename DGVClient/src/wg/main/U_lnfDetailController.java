package wg.main;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import wg.lnf.service.ILnFService;
import wg.vo.LnFVO;
import wg.vo.MemberVO;

public class U_lnfDetailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtField_name;

    @FXML
    private Button btnList;

    @FXML
    private TextField txtField_memId;

    @FXML
    private TextField txtField_writer;

    @FXML
    private TextField txtField_no;

    @FXML
    private TextField txtField_time;

    @FXML
    private TextField txtField_spot;

    @FXML
    private ComboBox<String> cmb_col;

    @FXML
    private ImageView img;

    @FXML
    private TextField txtField_imgPath;

    @FXML
    void btnListClick(MouseEvent event) {

    }

    private ILnFService service;

	private LnFVO vo;

	public LnFVO getVo() {
		return vo;
	}


	public void setVo(LnFVO vo) {
		this.vo = vo;


		txtField_no.setText(String.valueOf(vo.getLnf_no()));
		txtField_writer.setText(vo.getLnf_writer());
		txtField_memId.setText(vo.getMem_id());
		txtField_time.setText(vo.getLnf_time());
		txtField_spot.setText(vo.getLnf_spot());
		txtField_name.setText(vo.getLnf_name());
		txtField_imgPath.setText(vo.getLnf_photo());
		cmb_col.setValue(vo.getLnf_collect());

	}



	private String strWords = "";
	private ObservableList<LnFVO> lnfList;
	MemberVO member = new MemberVO();


	private Map<String, String>searchMap;




	@FXML
	void initialize() throws RemoteException{
		Registry reg = null;

		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			service = (ILnFService) reg.lookup("lnfService");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
