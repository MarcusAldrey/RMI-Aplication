package network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import util.APIGate;

public class GateImplement extends UnicastRemoteObject implements APIGate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<String, String> hosts = new HashMap<String, String>();
	
	public GateImplement() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveHost(String fileName, String url) throws RemoteException {
		// TODO Auto-generated method stub
		hosts.put(fileName, url);
	}

	@Override
	public String searchHost(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return hosts.get(fileName);
	}

	@Override
	public void removeHost(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		hosts.remove(fileName);
	}
	
}
