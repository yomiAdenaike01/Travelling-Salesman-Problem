import java.util.ArrayList;
import java.util.Collections;

public class RandomRestartHillClimbing {
	RandomMutationHillClimbing random = new RandomMutationHillClimbing();
	
	
	
	/*
	 * OBTAIN THE LOCAL OPTIMUM FROM RANDOM MUTATION HILL CLIMBING 
	 * GET THE FITNESS OF THAT 
	 * RESTART AGAIN
	 */
	public CompleteSolution RandomRestartHillClimbing(double[][] distances,int ITER_MAX_RMHC, int RESTART_COUNT) {
		//create arraylist to store ALL outputs from random mutation hill climbing
		ArrayList<CompleteSolution> STARTPOINT = new ArrayList<>();
		
		//restarting a number of times and getting the local optimum
		for(int i = 0; i<RESTART_COUNT; i++) {
			STARTPOINT.add(random.randomHillClimbing(distances, ITER_MAX_RMHC));
		}
		//getting 
		return currentSolution(STARTPOINT);
		
	}
	
	public CompleteSolution currentSolution(ArrayList<CompleteSolution> currentSolution){
		ArrayList<Double> currentSolutionFitness = new ArrayList<Double>();
		//double minimumFitness = 0;
		//int minFitness;
		//get the fitness of the product of random hill climbing
		for(int i = 0; i<currentSolution.size(); i++ ) {
			//adding this to an arraylist of fitnesses
			currentSolutionFitness.add(currentSolution.get(i).getFitness());
		}
		//minFitness = currentSolution.indexOf(Collections.min(currentSolutionFitness));
		int minFitness = currentSolutionFitness.indexOf(Collections.min(currentSolutionFitness));
		//casting the double to an int to get a new fitness
		
		//int newMinimumFitness = (int) minimumFitness;
		//adding 
		return currentSolution.get(minFitness);
		
	}

}
