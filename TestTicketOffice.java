package assignment6;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;

//	 @Test
	public void basicServerTest() {
		
		System.out.println("TEST 1...");
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
		
		final TicketClient c1 = new TicketClient("A");
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		t1.start();
		
		try {
			t1.join();
		} 
		
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		finally 
		{
			System.exit(0);
		}
	}

//	@Test
	public void testServerCachedHardInstance() throws InterruptedException {
		
		System.out.println("TEST 2 ...");
		
		final int customersTotal = 729;
		final int seatsTotal = 728;
		final TicketServer ticketOffice = new TicketServer ();
		
		Thread t0 = new Thread () 
		{
			public void run ()
			{
				try 
				{
					ticketOffice.start(2223);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t0.start();

		Thread[] threadArr = new Thread[seatsTotal];
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
			for(int i = 0; i < seatsTotal; i++)
			{
				threadArr[i].join();
			}
			System.exit(0);
		}
	}


	@Test
	public void twoConcurrentServerTest() {
		try {
			TicketServer.start(16792);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1");
		final TicketClient c2 = new TicketClient("conc2");
		final TicketClient c3 = new TicketClient("conc3");
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
