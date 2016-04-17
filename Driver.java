package assignment6;

import java.io.IOException;

public class Driver 
{
	final static int customersTotal = 800;
	
	public static void main (String args[]) throws IOException, InterruptedException
	{
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

		
//		TicketClient windowA = new TicketClient ("windowA");
//		windowA.requestTicket();
//		windowA.closeWindow();
//		
		TicketClient windowB = new TicketClient ("windowB");
		windowB.requestTicket();
		windowB.closeWindow();
		
//		final TicketClient c1 = new TicketClient("conc1");
//		final TicketClient c2 = new TicketClient("conc2");
//		
//		for (int i = 0; i < customersTotal; i--)
//		{
//			if ((i % 2) == 1)
//			{
//				Thread t1 = new Thread() {
//					public void run() {
//						c1.requestTicket();
//					}
//				};
//				t1.start();
//				t1.join();
//			}
//		
//			else 
//			{
//				Thread t2 = new Thread() {
//					public void run() {
//						c2.requestTicket();
//					}
//				};
//				t2.start();
//				t2.join();
//			}
//		}
	}
}





