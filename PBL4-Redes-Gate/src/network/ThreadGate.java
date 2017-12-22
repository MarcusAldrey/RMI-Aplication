package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.registry.Registry;

import control.ControllerGate;

public class ThreadGate extends Thread{
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public ThreadGate(Socket socket) throws IOException {
		// TODO Auto-generated constructor stub
		this.setSocket(socket);
		this.input = new ObjectInputStream(socket.getInputStream());
		this.output = new ObjectOutputStream(socket.getOutputStream());
	}

	public void enviarMensagem(Object object) throws IOException{
		output.writeObject(object);
		System.out.println("Enviou " + object + " para " + socket.getLocalAddress());
	}

	public Object receberMensagem() throws ClassNotFoundException, IOException{
		Object mensagem = input.readObject();
		System.out.println("Recebeu " + mensagem + " de " + socket.getLocalAddress());
		return mensagem;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectInputStream getInput() {
		return input;
	}

	public void setInput(ObjectInputStream input) {
		this.input = input;
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

	public void setOutput(ObjectOutputStream output) {
		this.output = output;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				String mensagem = (String) input.readObject();
				System.out.println("Recebeu " + mensagem);
				String[] mensagemDividida = mensagem.split(","); //Divide a mensagem onde tem vírgula

				/*Caso a mensagem venha de um cliente*/
				if(mensagemDividida[0].equals("connect gate")) {
					if(mensagemDividida[1].equals("add file")) {
						ControllerGate.getInstance().saveHost(mensagemDividida[2], socket.getInetAddress().getHostAddress());
					}
					else if(mensagemDividida[1].equals("get host ip")) {
						String ip = ControllerGate.getInstance().searchHost(mensagemDividida[2]);
						output.writeObject(ip);
					}
					else if(mensagemDividida[1].equals("remove file")) {
						ControllerGate.getInstance().removeHost(mensagemDividida[2]);
					}
					else if(mensagemDividida[1].equals("get all files")) {
						String[] allFiles = ControllerGate.getInstance().getAllFiles();
						output.writeObject(allFiles);
					}
				}
			}
		}   catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
