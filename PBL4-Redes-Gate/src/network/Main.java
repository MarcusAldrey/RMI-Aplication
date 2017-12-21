package network;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Main {
	public static void main(String args[]) {
		String ip;
		try {
			ip = InetAddress.getLocalHost().getHostName();
			String url = String.format("rmi://%s:%d/%s",ip,1099,"gate");
			System.out.println("Serviço de porteiro iniciado com url " + url);
			Naming.rebind(url, new GateImplement());
		} catch (UnknownHostException | MalformedURLException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
