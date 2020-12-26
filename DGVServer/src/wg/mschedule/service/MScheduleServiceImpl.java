package wg.mschedule.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.mschedule.dao.IMScheduleDao;
import wg.mschedule.dao.MSheduleDaoImpl;
import wg.vo.MScheduleVO;
import wg.vo.MscheduleViewVO;
import wg.vo.ShowInfoVO;

public class MScheduleServiceImpl extends UnicastRemoteObject implements IMScheduleService {
	private static MScheduleServiceImpl service;
	private IMScheduleDao dao;
	
	
	private MScheduleServiceImpl()  throws RemoteException  {
		dao = MSheduleDaoImpl.getInstance();
	}
	
	public static MScheduleServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new MScheduleServiceImpl();
		return service;
	}

	@Override
	public List<MscheduleViewVO> viewList(Map<String, String> param) throws RemoteException {
		return dao.viewList(param);
	}

	@Override
	public List<MscheduleViewVO> viewMoname(String theater_name) throws RemoteException {
		return dao.viewMoname(theater_name);
	}

	@Override
	public int insertMs(MScheduleVO msvo) throws RemoteException {
		return dao.insertMs(msvo);
	}

	@Override
	public int deletMs(int show_no) throws RemoteException {
		return dao.deletMs(show_no);
	}


	@Override
	public List<ShowInfoVO> getShowInfo(Map<String, String> selectedMap) {
		return dao.getShowInfo(selectedMap);
	}

	@Override
	public int getShow_no(Map<String, String> selectedMap) throws RemoteException {
		return dao.getShow_no(selectedMap);
	}

	@Override
	public String getDis_id(int show_no) throws RemoteException {
		return dao.getDis_id(show_no);
	}

	@Override
	public int deletMsSeat(int show_no) throws RemoteException {
		return dao.deletMsSeat(show_no);
	}

}
