package util;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import control.ServerController;

public class APIImplement extends UnicastRemoteObject implements API {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServerController controller = ServerController.getInstance();

	public APIImplement() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveFile(File file) throws RemoteException {
		// TODO Auto-generated method stub
		controller.mapFile(file.getName(), file.getAbsolutePath());
	}

	@Override
	public byte[] download(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return controller.convertToBytes(controller.searchFile(fileName));
	}

	@Override
	public void remove(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
