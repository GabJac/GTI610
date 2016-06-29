package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServersSocket implements Runnable {

	private int port;
	private final static int MAX_LENGHT = 256;
	
	public ServersSocket(int port){
		this.port = port;
	}
	
	public void startServer(){
		Thread serverThread = new Thread(this);
		serverThread.start();
	}
	
	@Override
	public void run() {
			
		boolean connexionClose = false;
		Socket socket = null;
		
		try {
			ServerSocket serveurSocket = new ServerSocket(port);
			socket = serveurSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(!connexionClose){
			
			byte[] buffer = new byte[MAX_LENGHT];
			
			try{
				InputStream input = socket.getInputStream();
				input.read(buffer);
			} catch (IOException e){
				System.out.println(e);
			}

			String received = new String (buffer);
			System.out.println(received);

			if(received.equals(-1)){
				System.out.println("closing!!!!!");
				connexionClose = true;
			}
		}
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
