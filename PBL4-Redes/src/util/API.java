package util;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface API extends Remote {

	public void saveFile(File file) throws RemoteException;
	public byte[] download(String fileName) throws RemoteException;
	public void remove(String fileName) throws RemoteException;
	
}
