
	package wg.payMethod.service;

	import java.rmi.RemoteException;
	import java.rmi.server.UnicastRemoteObject;

	import wg.payMethod.dao.PayMethodDao;
	import wg.payMethod.dao.PayMethodDaoImpl;

	public class PayMethodServiceImpl  extends UnicastRemoteObject implements IpayMethodService{
	   private PayMethodDao dao;
	   
	   private static PayMethodServiceImpl service;
	   
	   
	   private PayMethodServiceImpl() throws RemoteException{
	      dao = PayMethodDaoImpl.getInstance();
	   }
	   
	   
	   public static PayMethodServiceImpl getInstance() throws RemoteException {
	      if(service==null) service = new PayMethodServiceImpl();
	      return service;
	   }
	      
	}

