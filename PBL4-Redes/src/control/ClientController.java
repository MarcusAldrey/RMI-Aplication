package control;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import exceptions.HostNotFoundException;
import util.API;
import util.APIGate;

public class ClientController {

	private static ClientController instance;
	private String localURL;
	private Communication communication; 
	
	private ClientController() {

	}
	
	public void criarConexao(String IP, int porta) throws IOException {
		communication = new Communication(IP, porta);
	}
	
	public void setLocalUrl(String localUrl) {
		this.localURL = localUrl;
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
	 * @throws HostNotFoundException 
	 */
	public byte[] searchFile(String fileName) throws MalformedURLException, RemoteException, NotBoundException, HostNotFoundException {
		APIGate aPIGate = communication;
		String ipHost = aPIGate.searchHost(fileName);
		if(ipHost == null)
			throw new HostNotFoundException();
		Registry registry = LocateRegistry.getRegistry(ipHost, 1099);
		API remoteServer = (API) registry.lookup(localURL);
		return remoteServer.download(fileName);
	}
	
	/**
	 * Removes a file reference from the system
	 * @param fileName
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void removeFile(String fileName) throws MalformedURLException, RemoteException, NotBoundException {
		APIGate aPIGate = communication;
		API remoteServer = (API) Naming.lookup(aPIGate.searchHost(fileName));
		remoteServer.remove(fileName);
	}
	
	public String[] getAllFiles() {
		APIGate aPIGate = communication;
		return aPIGate.getAllFiles();
	}
	
}
