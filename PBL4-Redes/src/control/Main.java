package control;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import util.RMIService;
import view.TelaPrincipal;

public class Main {
	
	public static void main (String args[]) {
		
		try {
			
		String IpGate = JOptionPane.showInputDialog("Insira o IP do gate");
		String urlGate = String.format("rmi://%s:%d/gate", IpGate,1099);
		String ipLocal = InetAddress.getLocalHost().getHostAddress();
		
		int id = ThreadLocalRandom.current().nextInt(1, 100000 + 1);
		String urlLocal = String.format("rmi://%s:%d/%s", ipLocal,1099, "cliente"+id);
		
		
		ClientController.getInstance().setLocalUrl(urlLocal);
		ClientController.getInstance().setGateURL(urlGate);
		ServerController.getInstance().setGateURL(urlGate);
		ServerController.getInstance().setLocalURL(urlLocal);
		TelaPrincipal tela = new TelaPrincipal();
		tela.setVisible(true);
		new RMIService(urlLocal);
		System.out.println("Novo serviço de cliente criado com url " + urlLocal);
		System.out.println("URL do gate " + urlGate);
		

			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
