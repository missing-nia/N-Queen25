//=============================================================================//
//
// Purpose: General Simulated Annealing algorithm implementation		
//
//=============================================================================//

import java.util.function.Function;

public class SimulatedAnnealing 
{
	// Perform solve on state for N-Queen
	public static Node solve( Problem prob, Function<Integer, Float> sched )
	{
		Node cur = new Node( prob.init() );
		
		// Initialize Temp at 1, decrease it by 20% every it until we reach our minimum value
		float T = 1;
		int t = 1, // Counter
			mutations = 0; // Number of mutations
		
		while ( T > 0 )
		{
			T = sched.apply(t);
			Node next = cur.mutate(); // Get our new state
			next.setMutations( ++mutations );
			if ( next.getCost() == 0 )
				return next; // We found a solution
			
			// Flip deltaE since we're calculating cost versus value
			int deltaE = cur.getCost() - next.getCost();
			
			if ( deltaE > 0 )
				cur = next; // next has a lower cost, replace
			else if ( probability( deltaE, T ) > Math.random() )
				cur = next; // next won the probability gamble, replace
			
			++t;
		}
		cur.setMutations( mutations );
		return cur; // Return whatever we have
	}
	
	// Calculate the probability of jumping to new mutation
	private static double probability( int deltaE, float T )
	{
		return Math.pow(Math.E, deltaE / T );
	}
}