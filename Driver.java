package assignment6;

import java.io.IOException;

public class Driver 
{
	public static void main (String args[]) throws IOException
	{
		TicketServer ticketOffice = new TicketServer ();
		ticketOffice.start(2222);
		TicketClient testClient = new TicketClient ("testClient");
		testClient.requestTicket();
		
		
		
		
		
	}
}