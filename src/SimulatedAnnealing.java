
public class SimulatedAnnealing 
{
	// Object to be replaced with state class
	// Peform solve on state for N-Queen n = 25
	public Object solve( Object state )
	{
		// Initialize Temp at 1, decrease it by 20% every it until we reach our minimum value
		float T = 1, delta = 0.8f, minT = 0.000001f;
		int curCost = cost( state ); // Get the cost for our current state
		
		while ( T > minT )
		{
			// i = population size (play with this)
			for ( int i = 0; i < 100; ++i )
			{
				Object nextState = mutate( state ); // Get our new state
				newCost = cost( nextState );
				// Calc acceptance prob and compare to math.rand between 0-1 where a = e^( ( curCost - newCost / ) T )
				
				T *= delta;
			}
		}
		return null;
	}
	
	private Object mutate( Object state )
	{
		// Probably shuffle a random row for this (or maybe random # of rows)?
		Object next = state;
		return next;
	}
	
	private int cost( Object state )
	{
		// Calculate # of collisions
		return 0;
	}
}