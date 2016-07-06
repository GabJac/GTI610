package server;

public class Launcher {

	private final static int PORT = 40444;
	
	public static void main(String [ ] args)
	{
		ServersSocket socket = new ServersSocket(PORT);
		socket.startServer();
	}
	
}
