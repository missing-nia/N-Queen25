// Class for storing board states
public class Board
{
	public int[] state;
	private int m_fitness;
	
	public Board()
	{
		state = new int[25];
		this.m_fitness = -1;
	}
	
	public Board( int[] state )
	{
		setState( state );
		fitness();
	}	
	
	// Calculate board state cost
	private void fitness()
	{
		int n = 25;
		for ( int i = 0; i < n - 2; ++i )
		{
			for ( int j = i + 1; j < n - 1; ++j )
			{
				int idxDelta = j - 1,
					queenDelta = Math.abs( state[i] - state[j] );
				
				if ( state[i] == state[j] || idxDelta == queenDelta )
				{
					++m_fitness;
				}
				
			}
		}
	}
	
	public void setState( int[] state )
	{
		assert( state.length == 25 ); // This should NEVER be anything other than size 25
		this.state = state;
	}
	
	public int getFitness()
	{
		return m_fitness;
	}
}