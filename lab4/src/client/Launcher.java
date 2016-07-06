package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Launcher {
	
	private final static String IP_ADDRESS = "10.196.115.166";
	private final static int PORT = 40444;
	
	public static void main(String [ ] args)
	{
		InetAddress iNet = null;
		
		try {
			iNet = InetAddress.getByName(IP_ADDRESS);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		ClientOutputSocket socket = new ClientOutputSocket(iNet, PORT);
		socket.launchClient();
	}
	
}
