package util;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIService {

	public RMIService(String url) {
		try {
			Naming.rebind(url, new APIImplement());
		} catch (MalformedURLException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
