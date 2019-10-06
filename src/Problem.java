//=============================================================================//
//
// Purpose: Simple problem class for Simulated Annealing. 		
//
//=============================================================================//

import java.util.Random;

public class Problem 
{
	// Initialize a problem state for a given board size n
	private int m_n;
	
	public Problem()
	{
		this.m_n = 25; // default
	}
	
	public Problem( int n )
	{
		this.m_n = n;
	}
	
	public int[] init()
	{
		int[] state = new int[m_n];
		Random rand = new Random();
		
		for ( int i = 0; i < m_n; ++i )
		{
			// Put each piece in a random row
			state[i] = rand.nextInt( m_n - 1 );
		}
		return state;
	}
}
