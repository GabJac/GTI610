package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Launcher {
	
	private final static String IP_ADDRESS = "10.196.115.198";
	private final static int PORT = 46876;
	
	public static void main(String [ ] args)
	{
		InetAddress iNet = null;
		
		try {
			iNet = InetAddress.getByName(IP_ADDRESS);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		ClientSocket socket = new ClientSocket(iNet, PORT);
		socket.launchClient();
	}
	
}
