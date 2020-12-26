package wg.notice.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import wg.notice.dao.INoticeDao;
import wg.notice.dao.NoticeDaoImpl;
import wg.vo.NoticeVO;

public class NoticeServiceImpl extends UnicastRemoteObject implements INoticeService {
	private static NoticeServiceImpl service;
	private INoticeDao dao;
	
	
	private NoticeServiceImpl()  throws RemoteException  {
		dao = NoticeDaoImpl.getInstance();
	}
	
	public static NoticeServiceImpl getInstance()  throws RemoteException {
		if(service==null) service = new NoticeServiceImpl();
		return service;
	}

	@Override
	public int insertNotice(NoticeVO noticeVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertNotice(noticeVo);
	}

	@Override
	public int deleteNotice(String not_no) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.deleteNotice(not_no);
	}

	@Override
	public int updateNotice(NoticeVO noticeVo) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.updateNotice(noticeVo);
	}

	@Override
	public List<NoticeVO> getAllNoticeList(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllNoticeList(searchMap);
	}

	@Override
	public int getMaxNot_no()  throws RemoteException{
		// TODO Auto-generated method stub
		return dao.getMaxNot_no();
	}

	@Override
	public int getNoticeCount(Map<String, String> serachMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getNoticeCount(serachMap);
	}
	
	
	

}
