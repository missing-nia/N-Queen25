//=============================================================================//
//
// Purpose: General Genetic algorithm implementation		
//
//=============================================================================//

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetic 
{
	// Perform solve on N-Queen problem
	// pop = sorted set of nodes 
	// fitness function is implemented in node (cost) so we'll use that for simplicity
	public static Node solve( ArrayList<Node> pop )
	{
		int numGens = 5000, // Max number of generations to generate
			offspring = 0; // Number of offspring
		for ( int i = 0; i < numGens; ++i )
		{
			Collections.sort( pop ); // Sort the pop
			ArrayList<Node> newPop = new ArrayList<Node>( pop.size() );
			for ( int j = 0; j < pop.size(); ++j )
			{
				Node[] parents = randomSelection( pop );
				Node child = reproduce( parents[0], parents[1] );
				
				// Mutate 72% of the time
				if ( Math.random() > 0.28f )
					child = child.mutate();
				child.setOffspring( offspring ); // Current number of offspring
				
				if ( child.getCost() == 0 )
					return child; // Solution found!
				
				newPop.add( child );
				++offspring;
			}
			pop = newPop;
		}
		Node res = pop.get(0); // get the top
		res.setOffspring( offspring ); // Update number of offspring to reflect current count
		return res;
	}
	
	// Return two parents from the fittest in the population
	private static Node[] randomSelection( ArrayList<Node> pop )
	{
		// Don't pick that same parent twice by making a list of indices for top 20% of pop
		ArrayList<Integer> idx = new ArrayList<Integer>();
		for ( int i = 0; i < pop.size() / 5; ++i )
			idx.add( i );
		Collections.shuffle( idx ); // Randomly shuffle our indices
		
		// Get parents
		Node[] parents = { pop.get( idx.get( 0 ) ), pop.get( idx.get( 1 ) ) };	
		return parents;
	}
	
	// Make a child given two individuals
	private static Node reproduce( Node x, Node y )
	{
		Random rand = new Random();
		int[] state = x.getState().clone();
		int n = state.length,
			c = rand.nextInt( n ); // Crossover point
		
		// Replace all genes after n with genes from y
		for ( int i = c; i < state.length; ++i  )
		{
			state[i] = y.getState()[i];
		}
		
		return new Node( state );
	}
	/*
	 * function Genetic-Algorithm(population, FITNESS-FN) returns an individual
	 * 	inputs: population, a set of individuals
	 * 	FITNESS-FN, a function that measures the fitness of an individual
	 * 
	 * 	repeat
	 * 		new_population <-- empty set
	 * 		loop for i from 1 to SIZE(population) do
	 * 			x <-- RANDOM-SELECTION(population, FITNESS-FN)
	 * 			y <-- RANDOM-SELECTION(population, FITNESS-FN)
	 * 			child <-- REPRODUCE(x,y)
	 * 			if(small random probability) then child <-- MUTATE(child)
	 * 			add child to new_population
	 * 		population <-- new_population
	 * 	until some individual is fit enough, or enough time has elapsed
	 * 	return the best individual in the population, according to FITNESS-FN
	 * 
	 * 
	 * function REPRODUCE(x,y) returns an individual
	 * 	inputs: x, y, parent individuals
	 * 
	 * 	n <-- LENGTH(x)
	 * 	c <-- random number from 1 to n
	 * 	return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c + 1, n))
	 */
}
