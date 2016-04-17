package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketServer {
	
	static int PORT = 2222;
	static Theater theater = new Theater ();
	final static int MAXPARALLELTHREADS = 3;

	public static void start(int portNumber) throws IOException {
		PORT = portNumber;
	
		ServerSocket serverSocket = new ServerSocket(TicketServer.PORT);
		System.out.println("SERVER : Opened a server socket!");
		
		while (true)
		{
			System.out.println("SERVER : Waiting for connection ... ");
			Socket clientSocket = serverSocket.accept();
			System.out.println("SERVER : Connected to a client");
			Runnable serverThread = new ThreadedTicketServer(clientSocket);
			
			Thread t = new Thread(serverThread);
			t.start();
		}
	}
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	Socket socket;

	public ThreadedTicketServer (Socket sock)
	{
		this.socket = sock;
	}
	
	public void run() {
		
		System.out.println("SERVER : Inside run method of ThreadedTicketServer");
		try 
		{			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = in.readLine();
			System.out.println("SERVER : Message received from client: " + message);
			
			if (!message.equals("Exit"))
			{
				System.out.println("SERVER : Calling bestAvailableSeat using TicketServer Theater object");
				int reservedSeat = TicketServer.theater.bestAvailableSeat();
				
				if (reservedSeat != -1)
				{
					TicketServer.theater.markAvailableSeatTaken(reservedSeat);
				}
				out.write(reservedSeat);
			}
			socket.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}












