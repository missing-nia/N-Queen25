//=============================================================================//
//
// Purpose: Class for storing board states
//
//=============================================================================//

import java.util.Random;

public class Node
{
	private int[] m_state; // Current state
	private int m_cost; // Cost of current state
	private int m_mutations; // Number of mutations thus far
	
	// Default constructor
	public Node()
	{
		// Init cost to invalid value
		this.m_cost = -1;
		this.m_mutations = 0;
	}
	
	// Constructor given a board state
	public Node( int[] state )
	{
		setState( state );
		this.m_mutations = 0;
	}
	
	// Copy constructor
	public Node( Node node )
	{
		this.m_state = node.getState();
		this.m_cost = node.getCost();
		this.m_mutations = node.getMutations() + 1;
	}
	
	// Set methods
	public void setState( int[] state )
	{
		this.m_state = state;
		cost(); // Compute cost
	}
	
	public void setMutations( int mutations )
	{
		this.m_mutations = mutations;
	}
	
	// Get methods
	public int[] getState() { return this.m_state; }	
	public int getCost() { return m_cost; }	
	public int getMutations() { return this.m_mutations; }
	
	// Mutate the node by moving random pieces
	public Node mutate()
	{
		Random rand = new Random();
		int[] state = this.m_state.clone(); // mutated state
		
		// Pick a random column to change
		int col = rand.nextInt( state.length );
		
		// Move piece to a random row
		state[col] = rand.nextInt( state.length );
		return new Node( state );
	}
	
	// Calculate board state cost
	private void cost()
	{
		m_cost = 0;
		int n = m_state.length;
		for ( int i = 0; i < n - 1; ++i )
		{
			for ( int j = i + 1; j < n; ++j )
			{
				int deltaColumn = j - i,
					deltaRow = Math.abs( m_state[i] - m_state[j] );
				
				if ( m_state[i] == m_state[j] || deltaColumn == deltaRow )
				{
					++m_cost;
				}
				
			}
		}
	}
}
