package assignment6;

import java.io.IOException;

public class Driver 
{
	final static int customersTotal = 729;
	final static int seatsTotal = 728;

	
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

		Thread[] threadArr = new Thread[728];
		
		/*
		 * Create two concurrent threads for the TicketClients 
		 * (i.e. two "lines" for ticket requests)
		 */
		final TicketClient c1 = new TicketClient("A");
		final TicketClient c2 = new TicketClient("B");
		try 
		{
			for (int i = 0; i < seatsTotal; i++)
			{
				if ((i % 2) == 1)
				{
					threadArr[i] = new Thread() {
						public void run() {
							c1.requestTicket();
						}
					};
					threadArr[i].start();
				}
			
				else 
				{
					threadArr[i] = new Thread() {
						public void run() {
							c2.requestTicket();
						}
					};
					threadArr[i].start();
				}
			}
		}
		
		catch (Exception e)
		{
			System.err.println("");
		}
		
		finally 
		{
			for(int i = 0; i < 728; i++)
			{
				threadArr[i].join();
			}
			
			if (customersTotal > seatsTotal)
			{
				System.out.println("Sorry -- House is full!");
			}
			System.exit(0);
		}
	}
}





