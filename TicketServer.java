package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketServer {
	
	static int PORT;
	static Theater theater;
	static public ServerSocket serverSocket;
	final  int MAXPARALLELTHREADS = 3;

	public void start(int portNumber, Theater myTheater) throws IOException {
		theater = myTheater;
		PORT = portNumber;
		System.out.println("The port number is " + PORT);
		serverSocket = new ServerSocket(TicketServer.PORT);
		
		while (true)
		{
			Socket clientSocket = serverSocket.accept();
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
				
		try 
		{			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = in.readLine();
						
			if (!message.equals("Exit"))
			{				
				synchronized (TicketServer.theater)
				{
					int reservedSeat = TicketServer.theater.bestAvailableSeat();
					
					if (reservedSeat != -1)
					{
						TicketServer.theater.markAvailableSeatTaken(reservedSeat, message);
					}
					out.println(reservedSeat);
				}
			}
			socket.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		catch(Exception e2)
		{
			System.err.println("Another exception");
		}
	}
}




























