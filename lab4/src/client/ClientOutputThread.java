package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientOutputThread implements Runnable {

	private InetAddress ip;
	private int port;
	
	public ClientOutputThread(InetAddress ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void launchClient(){
		Thread socketThread = new Thread(this);
		socketThread.start();
	}
	
	@Override
	public void run() {
		boolean connexionClose = false;
		
		Socket clientSocket = null;
		OutputStream output = null;
		
		Scanner keyboard = new Scanner(System.in);
		
		try{
			clientSocket = new Socket(ip, port);
		} catch (IOException e){
			System.out.println(e);
		}
		
		ClientInputThread inputSocket = new ClientInputThread(clientSocket);
		inputSocket.start();
		
		int i = 0;
		
		while(!connexionClose){
			String data = keyboard.nextLine();
			
			if(data.isEmpty()){
				continue;
			}
			

			if(data.equals("close the connection")){
				connexionClose = true;
				inputSocket.closeConnexion();
			} else {
				try{
					output = clientSocket.getOutputStream();
					output.write(data.getBytes());
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
