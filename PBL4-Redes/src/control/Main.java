package control;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import util.RMIService;
import view.TelaPrincipal;

public class Main {
	
	public static void main (String args[]) {
		
		try {
			
		String IpGate = JOptionPane.showInputDialog("Insira o ip do gate");
		String urlPorteiro = String.format("rmi://%s:%d/porteiro", IpGate,1099);
		String ipLocal = InetAddress.getLocalHost().getHostAddress();
		
		double id = Math.random()*100 + 1;
		String urlLocal = String.format("rmi://%s:%d/%s", IpGate,1099, id);
		
		ClientController.getInstance().setLocalUrl(urlLocal);
		ClientController.getInstance().setGateURL(urlPorteiro);
		ServerController.getInstance().setGateURL(urlPorteiro);
		ServerController.getInstance().setLocalURL(urlLocal);
		
		new TelaPrincipal();
		new RMIService(urlLocal);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
