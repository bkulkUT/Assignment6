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
	
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;

	public static void start(int portNumber) throws IOException {
		PORT = portNumber;
		Runnable serverThread = new ThreadedTicketServer();
		Thread t = new Thread(serverThread);
		t.start();
	}
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;

	public void run() {
		// TODO 422C
		ServerSocket serverSocket;
		try 
		{
			serverSocket = new ServerSocket(TicketServer.PORT);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String message = in.readLine();	
			
			int reservedSeat = TicketServer.theater.bestAvailableSeat();
			
			if (reservedSeat != -1)
			{
				TicketServer.theater.markAvailableSeatTaken(reservedSeat);
			}
			
			out.write(reservedSeat);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}