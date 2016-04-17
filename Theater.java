package assignment6;

import java.util.ArrayList;

public class Theater {
	
	private char startRow = 'A';
	private final int rows = 26;
	private final int cols = 28;
	private int seats[][] = new int[rows][cols];
	private int seatsLeft[] = new int [26];
	
	
	public Theater () 
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				seats[i][j] = 0;
			}
		}
		
		for (int j = 0; j < 26; j++)
		{
			seatsLeft[j] = 28;
		}
	}
	
	public synchronized int bestAvailableSeat ()
	{
		System.out.println("THEATER : Inside bestAvailableSeat method!");
		
		int rowIncr = 100;
		
		// Check availibility of seat from best row (A) to worst (Z)
		for (char seatRow = startRow; seatRow <= 'Z'; seatRow++)
		{
			// Check availibility of seat from best to worst columns
			// (middle 7-21; left 0-6; right 21-27)
			for (int midCol = 7; midCol <= 20; midCol++)
			{
				if (seats[seatRow][midCol] == 0)
				{
					return midCol + rowIncr + 1; 
				}
			}
			
			for (int leftCol = 0; leftCol <= 6; leftCol++)
			{
				if (seats[seatRow][leftCol] == 0)
				{
					return leftCol + rowIncr + 1;
				}
			}
			
			for (int rightCol = 21; rightCol <= 27; rightCol++)
			{
				if (seats[seatRow][rightCol] == 0)
				{
					return rightCol + rowIncr + 1;
				}
			}
			rowIncr += 100;
		}
		return -1;
	}
	
	public synchronized void markAvailableSeatTaken (int seat)
	{
		int row = (seat/100);
		int col = (seat % 100) - 1;
		seats[row][col] = 1;
		seatsLeft[rows - 1] -= 1;
		
		if (seatsLeft[rows -1] == 0)
		{
			startRow++;
		}
		
		return;
	}
	
	public void printSeatTicket (char row, int col)
	{
		
	}
}





















