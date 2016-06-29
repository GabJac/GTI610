package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServersSocket implements Runnable {

	private int port;
	
	public ServersSocket(int port){
		this.port = port;
	}
	
	public void startServer(){
		Thread serverThread = new Thread(this);
		serverThread.start();
	}
	
	@Override
	public void run() {
		while(true){
			Socket socket = null;
			
			try {
				ServerSocket serveurSocket = new ServerSocket(port);
				socket = serveurSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(socket != null){
				InputOutputThread inputOutputThread = new InputOutputThread(socket);
				inputOutputThread.start();
			}
		}
	}

}
