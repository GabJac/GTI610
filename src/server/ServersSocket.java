package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
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
		ServerSocket serveurSocket = null;
		try {
			serveurSocket = new ServerSocket();
			serveurSocket.setReuseAddress(true);
			serveurSocket.bind(new InetSocketAddress(port));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while(true){
			Socket socket = null;
			
			try {
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
