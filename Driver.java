package assignment6;

import java.io.IOException;

public class Driver 
{
	public static void main (String args[]) throws IOException
	{
//		TicketServer ticketOffice = new TicketServer ();
		TicketServer.start(16789);
		TicketClient testClient = new TicketClient ("localhost" , "testClient");
		
		
		
		
		
		
	}
}