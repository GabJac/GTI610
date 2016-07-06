package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket implements Runnable {

	private InetAddress ip;
	private int port;
	
	public ClientSocket(InetAddress ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void launchClient(){
		Thread socketThread = new Thread(this);
		socketThread.start();
	}
	
	@Override
	public void run() {
		Socket clientSocket = null;
		boolean connexionClose = false;
		
		try{
			clientSocket = new Socket(ip, port);
		} catch (IOException e){
			System.out.println(e);
		}
		
		int i = 0;
		
		while(!connexionClose){
			String data = "test"+i;
			
			try{
				OutputStream output = clientSocket.getOutputStream();
				output.write(data.getBytes());
			} catch (IOException e) {
				System.out.println(e);
			}
			
			i++;
			
			if(i == 100){
				connexionClose = true;
			}
		}
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
