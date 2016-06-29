package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class InputOutputThread implements Runnable{
	
	private final static int MAX_LENGHT = 256;
	private Socket socket;
	
	public InputOutputThread(Socket socket){
		this.socket = socket;
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		boolean connexionClose = false;

		while(!connexionClose){
			
			InputStream input = null;
			OutputStream output = null;
			try {
				input = socket.getInputStream();
				output = socket.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			byte[] buffer = new byte[MAX_LENGHT];
			
			try{
				input = socket.getInputStream();
				input.read(buffer);
			} catch (IOException e){
				connexionClose = true;
			}

			String ipPort =  " ipv4: " + socket.getInetAddress().getHostAddress() +
					" port: " + socket.getPort();
			String stringReceived = new String (buffer);
			
			System.out.println(stringReceived.concat(ipPort));

			
			try{
				output.write(stringReceived.toUpperCase().getBytes());
			} catch (IOException e) {
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
