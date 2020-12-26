package wg.preview.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import wg.preview.dao.IPreviewDao;
import wg.preview.dao.PreviewDaoImpl;

public class PreviewServiceImpl extends UnicastRemoteObject implements IPreviewService{
	private static PreviewServiceImpl service;
	
	private IPreviewDao dao;
	
	
	
	private PreviewServiceImpl()  throws RemoteException{
		dao = PreviewDaoImpl.getInstance();
	}
	
	
	public static PreviewServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new PreviewServiceImpl();
		return service;
	}
	
}
