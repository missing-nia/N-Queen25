import java.util.function.Function;

public class NQSolve 
{
	public static void main( String args[] )
	{
		Problem prob = new Problem();
		Function<Integer, Float> sched =  
				parameter -> 1.0f * ( float )Math.pow( 0.999, parameter ); // reduce temp by 20% each iteration from a base T = 1.0f	
				
		Node res = SimulatedAnnealing.solve(prob, sched);
		
		System.out.print( "Mutations: " + res.getMutations() + "\n" );
		System.out.print( "Collisions: " + res.getCost() + "\n" );
		System.out.print( "Board State:\n" );
		printState( res );
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
}
