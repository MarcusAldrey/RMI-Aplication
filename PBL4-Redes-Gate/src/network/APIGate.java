package network;

import java.rmi.RemoteException;

public interface APIGate {
	
	public void saveHost(String fileName, String url) throws RemoteException;
	public String searchHost(String fileName) throws RemoteException;
	public void removeHost(String fileName) throws RemoteException;

}
