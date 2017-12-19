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
	
	public void setLocalUrl(String localURL) {
		this.localURL = localURL;
	}
	
	public void setGateURL(String gateURL) {
		this.gatelURL = gateURL;
	}
	
	public ClientController getInstance() {
		if(instance == null)
			instance = new ClientController();
		return instance;
	}
	
	public void addFile(File file) throws MalformedURLException, RemoteException, NotBoundException {
		API api = (API) Naming.lookup(localURL);
		api.saveFile(file);
	}
	
	public void searchFile(String fileName) throws MalformedURLException, RemoteException, NotBoundException {
		APIGate aPIGate = (APIGate) Naming.lookup(gatelURL);
		API remoteServer = (API) Naming.lookup(aPIGate.searchHost(fileName));
		byte[] receivedFile = remoteServer.download(fileName);
	}
	
	public void removeFile(String fileName) throws MalformedURLException, RemoteException, NotBoundException {
		APIGate aPIGate = (APIGate) Naming.lookup(gatelURL);
		API remoteServer = (API) Naming.lookup(aPIGate.searchHost(fileName));
		remoteServer.remove(fileName);
	}
	
}
