import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Defaults.CS2004;

public class StocasticHillClimbing {
	public static List<Double> stochasticFitness;
	/**
	 * 
	 * @param distances
	 * @param ITER_MAX_STOCHASTIC
	 * @return CompleteSolution
	 */
	public CompleteSolution StochasticHillClimbing(double[][] distances, int ITER_MAX_STOCHASTIC, double t, boolean print) {
		//stochasticFitness = new ArrayList<Double>();
		int n = distances.length;
		//starting at a random point in the solution space
		CompleteSolution STARTPOINT = new CompleteSolution(n);
		for(int i = 1; i < ITER_MAX_STOCHASTIC; i++) {
			//iterate a specified number of times
			//genrate a random real number
			//get the current tour 
			CompleteSolution CURRENTTOUR = new CompleteSolution(STARTPOINT.getTour(),n);
			STARTPOINT.makeSmallChanges();
			double CURRENTTOURFITNESS = CURRENTTOUR.calcFitness(distances);
			CompleteSolution NEWTOUR = new CompleteSolution(STARTPOINT.getTour(),n);
			double NEWTOURFITNESS = NEWTOUR.calcFitness(distances);
			//get the fitness of the two tours
			//Accept() running the acceptance method 
		
			if(CS2004.UR(0, 1) > Accept(NEWTOURFITNESS,CURRENTTOURFITNESS,t)) {
				STARTPOINT=CURRENTTOUR;
			}
			
			if(print == true) {
				System.out.println("Iterations "+i+" fitness "+ CURRENTTOURFITNESS);
			}
			//Array list that will return the lowest fitness 
			/*
			if(print == true) {
				System.out.println("Fitnesses " + i +" "+CURRENTTOURFITNESS);
			Collections.sort(stochasticFitness, Collections.reverseOrder());
			for(double fitness : stochasticFitness) {
				System.out.println("Fitness : "+fitness);
			}
			
			}
			*/
			
		}
		return (STARTPOINT);
	}
	
	/**
	 * running the acceptance probability on the fitnesses
	 * @param CURRENTTOURFITNESS
	 * @param NEWTOURFITNESS
	 * @param t
	 * @return threshold
	 */
	private double Accept(double NEWTOURFITNESS , double CURRENTTOURFITNESS, double t) {
		
		
		return 1.0/(1.0+Math.exp((NEWTOURFITNESS - CURRENTTOURFITNESS)/t));
		
}
	//is better method which will return true or false if the old fitness is better or worse than the new one
	/**
	 * 
	 * @param CURRENTTOURFITNESS
	 * @param NEWTOURFITNESS
	 * @return true/false
	 */
	private boolean isBetter(double CURRENTTOURFITNESS, double NEWTOURFITNESS) {
		if(CURRENTTOURFITNESS < NEWTOURFITNESS) {
			return true;
		}else {
			return false;
		}
	}

}
