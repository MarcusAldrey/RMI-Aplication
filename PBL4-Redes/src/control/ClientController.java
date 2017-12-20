package control;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import util.API;
import util.APIGate;

public class ClientController {

	private static ClientController instance;
	private String localURL;
	private String gatelURL;
	
	private ClientController() {
		
	}
	
	/**
	 * Saves the URL from local RMI service
	 * @param localURL
	 */
	public void setLocalUrl(String localURL) {
		this.localURL = localURL;
	}
	
	/**
	 * Saves the URL from the gate
	 * @param gateURL
	 */
	public void setGateURL(String gateURL) {
		this.gatelURL = gateURL;
	}
	
	/**
	 * Returns the Singleton instance from the controller 
	 * @return
	 */
	public static ClientController getInstance() {
		if(instance == null)
			instance = new ClientController();
		return instance;
	}
	
	/**
	 * Share a file from your PC
	 * @param file
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void addFile(File file) throws MalformedURLException, RemoteException, NotBoundException {
		API api = (API) Naming.lookup(localURL);
		api.saveFile(file);
	}
	
	/**
	 * Search for a file in remote PC 
	 * @param fileName
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void searchFile(String fileName) throws MalformedURLException, RemoteException, NotBoundException {
		APIGate aPIGate = (APIGate) Naming.lookup(gatelURL);
		API remoteServer = (API) Naming.lookup(aPIGate.searchHost(fileName));
		byte[] receivedFile = remoteServer.download(fileName);
	}
	
	/**
	 * Removes a file reference from the system
	 * @param fileName
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void removeFile(String fileName) throws MalformedURLException, RemoteException, NotBoundException {
		APIGate aPIGate = (APIGate) Naming.lookup(gatelURL);
		API remoteServer = (API) Naming.lookup(aPIGate.searchHost(fileName));
		remoteServer.remove(fileName);
	}
	
}
