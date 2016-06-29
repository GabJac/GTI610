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
	
	@Override
	public void run() {
		
		InputStream input = null;
		
		try{
			input = socket.getInputStream();
		} catch (IOException e){
			System.out.println(e);
		}
		
		while(!connexionClose){
			byte[] buffer = new byte[MAX_LENGHT];
			
			try{
				input = socket.getInputStream();
				input.read(buffer);
			} catch (IOException e){
				System.out.println(e);
			}

			String stringReceived = new String (buffer);
			System.out.println(stringReceived);
		}
		
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
