import java.util.ArrayList;
import java.util.function.Function;


public class NQSolve 
{
	public static void main( String args[] )
	{
		simulatedAnnealing();
		genetic();		
		
		/*// Simulated Annealing
		int cnt = 0;
		long start = System.nanoTime();
		for ( int i = 0; i < 1000; ++i )
		{
			if ( simulatedAnnealing() )
				++cnt;
		}
		System.out.println("successes: " + cnt);
		System.out.println("time to complete: " + ( System.nanoTime() - start ) / 1000000000.0f );
		
		// Genetic
		cnt = 0;
		start = System.nanoTime();
		for ( int i = 0; i < 1000; ++i )
		{
			if ( genetic() )
				++cnt;
		}
		System.out.println("successes: " + cnt);
		System.out.println("time to complete: " + ( System.nanoTime() - start ) / 1000000000.0f );*/
	}
	
	// Print a nicely formatted board 
	private static void printState( Node node )
	{
		// Print the array first
		System.out.print( "[" );
		for ( int i = 0; i < node.getState().length; ++i )
			System.out.print( " " + node.getState()[i] );
		System.out.print( " ]\n" );
		
		// Print a graphical representation of the board
		System.out.print( " " + " - ".repeat( node.getState().length )  + "\n" );
		for ( int i = 0; i < node.getState().length; ++i )
		{
			System.out.print( "|" );
			for ( int j = 0; j < node.getState().length; ++j )
			{
				if ( node.getState()[j] == i )
					System.out.print( " 1 ");
				else
					System.out.print( " 0 " );
			}
			System.out.println( "|" );
		}
		System.out.print( " " + " - ".repeat( node.getState().length )  + "\n" );
	}
	
	private static boolean simulatedAnnealing()
	{
		Problem prob = new Problem();
		Function<Integer, Float> sched =  
				parameter -> 1.0f * ( float )Math.pow( 0.999, parameter ); // reduce temp by 20% each iteration from a base T = 1.0f	
		
		Node res = SimulatedAnnealing.solve(prob, sched);
		
		System.out.print( "Mutations: " + res.getMutations() + "\n" );
		System.out.print( "Collisions: " + res.getCost() + "\n" );
		System.out.print( "Board State:\n" );
		printState( res );
		
		return res.getCost() == 0 ? true : false;
	}
	
	private static boolean genetic()
	{
		ArrayList<Node> pop = new ArrayList<Node>();
		Problem prob = new Problem();
		
		// Generate our pop of size n = 20
		for ( int i = 0; i < 20; ++i )
		{
			pop.add( new Node( prob.init() ) ); // Reuse prob.init() for random pop
		}
		
		Node res = Genetic.solve( pop );
		System.out.print( "Offspring: " + res.getOffspring() + "\n" );
		System.out.print( "Collisions: " + res.getCost() + "\n" );
		System.out.print( "Board State:\n" );
		printState( res );
		
		return res.getCost() == 0 ? true : false;
	}
}
