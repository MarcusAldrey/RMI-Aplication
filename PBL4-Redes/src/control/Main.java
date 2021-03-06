package control;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.sound.midi.ControllerEventListener;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import util.API;
import util.APIImplement;
import view.TelaPrincipal;

public class Main {
	
	public static void main (String args[]) {
		
		try {
			
		String IpGate = JOptionPane.showInputDialog("Insira o IP do gate");
		String portaGate = JOptionPane.showInputDialog("Insira a porta do gate");
		
		LocateRegistry.createRegistry(51000);
		
		String localUrl = "cliente";
		
		Registry registry = LocateRegistry.getRegistry("localhost", 51000);
		API stub = (API) UnicastRemoteObject.exportObject(new APIImplement(), 0);
		registry.bind(localUrl, stub);
		
		System.out.println("Novo registro criado em " + localUrl);
		
		ClientController.getInstance().criarConexao(IpGate, Integer.parseInt(portaGate));
		ClientController.getInstance().setLocalUrl(localUrl);
		
		TelaPrincipal tela = new TelaPrincipal();
		tela.setVisible(true);
			
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
