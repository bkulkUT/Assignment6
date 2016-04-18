package assignment6;

import java.io.IOException;

public class Driver 
{
//	final static int customersTotal = 56;
	final static int customersTotal = 728;

	
	public static void main (String args[]) throws IOException, InterruptedException
	{
		/* 
		 * Create a ticket office in a separate thread
		 * Have it wait on the clients to request a ticket (TicketClient)
		 */
		final TicketServer ticketOffice = new TicketServer ();
		Thread t0 = new Thread () 
		{
			public void run ()
			{
				try 
				{
					ticketOffice.start(2222);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t0.start();
		
		/*
		 * Create two concurrent threads for the TicketClients 
		 * (i.e. two "lines" for ticket requests)
		 */
		final TicketClient c1 = new TicketClient("A");
		final TicketClient c2 = new TicketClient("B");
		for (int i = 0; i < customersTotal; i++)
		{
			if ((i % 2) == 1)
			{
				Thread t1 = new Thread() {
					public void run() {
						c1.requestTicket();
					}
				};
				t1.start();
				t1.join();
			}
		
			else 
			{
				Thread t2 = new Thread() {
					public void run() {
						c2.requestTicket();
					}
				};
				t2.start();
				t2.join();
			}
		}
	}
}





