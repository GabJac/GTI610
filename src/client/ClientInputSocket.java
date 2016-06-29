package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientInputSocket implements Runnable {
	
	private final static int MAX_LENGHT = 256;

	private Socket socket;
	private boolean connexionClose;
	
	public ClientInputSocket(Socket socket){
		this.socket = socket;
		connexionClose = false;
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void closeConnexion(){
		connexionClose = true;
	}
	
	@Override
	public void run() {
		
		while(!connexionClose){
			byte[] buffer = new byte[MAX_LENGHT];
			
			try{
				InputStream input = socket.getInputStream();
				input.read(buffer);
			} catch (IOException e){
				connexionClose = true;
				System.out.println(e);
			}

			String stringReceived = new String (buffer);
			System.out.println(stringReceived);
		}
	
	}

}
