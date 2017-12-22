package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.APIGate;

public class Communication implements APIGate {

	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public Communication(String IP, int porta) throws IOException {
		socket = new Socket(IP,porta);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void saveHost(String fileName, String url) {
		// TODO Auto-generated method stub
		try {
			output.writeObject("connect gate,add file," + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String searchHost(String fileName) {
		// TODO Auto-generated method stub
		try {
			output.writeObject("connect gate,get host ip," + fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			String ip = null;
			try {
				ip = (String) input.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ip;
		}
	}

	@Override
	public void removeHost(String fileName) {
		// TODO Auto-generated method stub
		try {
			output.writeObject("connect gate,remove file,"+fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String[] getAllFiles() {
		// TODO Auto-generated method stub
		String[] allFiles = new String[0];
		try {
			output.writeObject("connect gate,get all files");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			allFiles = (String[]) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allFiles;
	}
}
