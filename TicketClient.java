package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;
	static String command = "Reserve";

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run() {
		
		System.out.flush();
		
		try 
		{
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
//			System.out.println("CLIENT : Opened a client socket to the server ...");
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
//			System.out.println("CLIENT : Writing a command, " + command + ", to the server!");
			out.println(command);
//			System.out.println("CLIENT : Wrote a command");
			
			int reservation = Integer.parseInt(in.readLine());
//			System.out.println("CLIENT : Received seat " + reservation + "!");
			echoSocket.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println("CLIENT : Unable to open a socket");
			e.printStackTrace();
		}
	}
}

public class TicketClient {
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";

	TicketClient(String hostname, String threadname) {
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
	}

	TicketClient(String name) {
		this("localhost", name);
	}

	TicketClient() {
		this("localhost", "unnamed client");
	}

	void requestTicket() {
		ThreadedTicketClient.command = this.threadName;
		tc.run();
	}
	
	void closeWindow ()
	{
		tc.command = "Exit";
		tc.run();
	}
	
	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
