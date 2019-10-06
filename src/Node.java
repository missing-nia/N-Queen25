//=============================================================================//
//
// Purpose: Class for storing board states
//
//=============================================================================//

import java.util.Random;

public class Node implements Comparable<Node>
{
	private int[] m_state; // Current state
	private int m_cost; // Cost of current state
	private int m_mutations; // Number of mutations thus far
	private int m_offspring; // Number of offspring
	
	// Default constructor
	public Node()
	{
		// Init cost to invalid value
		this.m_cost = -1;
		this.m_mutations = 0;
		this.m_offspring = 0;
	}
	
	// Constructor given a board state
	public Node( int[] state )
	{
		setState( state );
		this.m_mutations = 0;
		this.m_offspring = 0;
	}
	
	// Copy constructor
	public Node( Node node )
	{
		this.m_state = node.getState();
		this.m_cost = node.getCost();
		this.m_mutations = node.getMutations() + 1;
		this.m_offspring = node.getOffspring() + 1;
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
	
	public  void setOffspring( int offspring )
	{
		this.m_offspring = offspring;
	}
	
	// Get methods
	public int[] getState() { return this.m_state; }	
	public int getCost() { return m_cost; }	
	public int getMutations() { return this.m_mutations; }
	public int getOffspring() { return this.m_offspring; }
	
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
	
	@Override
	// Comparator for genetic population sorting (lower cost = more fitness)
	public int compareTo( Node other ) 
	{
		if 	( this.getCost() < other.getCost() )
			return -1;
		else if ( this.getCost() == other.getCost() )
			return 0;
		else
			return 1;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		Node other = ( Node )obj;
		// Only return true if these nodes share the same state and offspring tally
		if ( this.getState() == other.getState() && this.getOffspring() == other.getOffspring() )
			return true;
		return false;
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
