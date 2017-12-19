package control;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

import util.APIGate;

public class ServerController {
	
	private static ServerController instance;
	private String localURL;
	private String gatelURL;
	
	private ServerController () {
		
	}
	
	public void setLocalURL(String localURL) {
		this.localURL = localURL;
	}
	
	public void setGateURL(String gateURL) {
		this.gatelURL = gateURL;
	}
	
	public static ServerController getInstance() {
		if(instance == null)
			instance = new ServerController();
		return instance;
	}
	
	private HashMap<String, String> files = new HashMap<String,String>();
	
	public void mapFile(String fileName, String filePath) {
		files.put(fileName, filePath);
		try {
			APIGate gate = (APIGate) Naming.lookup(gatelURL);
			gate.saveHost(fileName, localURL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File searchFile(String fileName) {
		return new File(files.get(fileName));
	}
	
	public byte[] convertToBytes(File file) {
		
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void remove(String fileName) {
		files.remove(fileName);
		try {
			APIGate gate = (APIGate) Naming.lookup(gatelURL);
			gate.removeHost(fileName);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
