
import java.util.ArrayList;
import java.util.Collections;

import Defaults.CS2004;

/**
 * START AT RANDOM PLACE
 * GET THE FITNESS OF THAT TOUR
 * 
 * SET INIT TEMP
 * SET INIT COOLING RATE
 * SET MIN TEMP
 * @author yomia
 *
 */
public class SimulatedAnnealing {
	
	public static double INIT_TEMP = 999;
	public static double MIN_TEMP = 0.99;
	public static ArrayList<Double> minFitness;
	
	/**
	 * 
	 * @param ITER_MAX_ANNEALING
	 * @param distances
	 * @param temp
	 * @return CompleteSlution
	 */
	public CompleteSolution SimulatedAnnealing(double[][]distances,int ITER_MAX_ANNEALING, double temp, boolean print) {
		int n = distances.length;
		minFitness = new ArrayList<Double>();
		CompleteSolution STARTPOINT = new CompleteSolution(n);//create random solution
		
		double currentTemp = temp;
		for (int i = 1; i <= ITER_MAX_ANNEALING; i++) //creating a for loop, iterates for specified number of iterations
		{
			
			
			CompleteSolution CURRENTTOUR = new CompleteSolution(STARTPOINT.getTour(),n); 
			double CURRENTTOURFITNESS = CURRENTTOUR.calcFitness(distances); 
			
			STARTPOINT.makeSmallChanges();
			
			CompleteSolution NEWTOUR = new CompleteSolution(STARTPOINT.getTour(),n);
			
			double NEWTOURFITNESS = NEWTOUR.calcFitness(distances); //evaluates new fitness to another variable

			//if the new fitness is worse than the old, then keep the old solution
			if (NEWTOURFITNESS > CURRENTTOURFITNESS)
			{
				double p = Acceptance(NEWTOURFITNESS,CURRENTTOURFITNESS,currentTemp);
				
				if(p<CS2004.UR(0, 1)) {
					STARTPOINT=CURRENTTOUR;
				}
				else
					STARTPOINT=NEWTOUR;
			}
			//adding them to an array list and printing the last because it doesn't currently work
			minFitness.add(CURRENTTOURFITNESS);
			if(print == true) {
				
				Collections.sort(minFitness, Collections.reverseOrder());
				for(double fitness : minFitness) {
					System.out.println("Iteration " + i + ": " +" Fitness: " +fitness );
				}
				
			}
			
		//didn't effectivley work fitness will go up and down 
//			currentTemp *= 1- coolRate(temp, i);
			
			//currentTemp *= 1 - 0.003;
			currentTemp *= 1-0.003;
		}
		
		return(STARTPOINT);
		//returing the current 
}

	/**
	 * 
	 * @param CURRENTTOURFITNESS
	 * @param NEWTOURFITNESS
	 * @param temp
	 * @return FORMULA OF COOLING
	 */
	private static double coolRate(double initialTemp, int iter) {
	    return Math.exp((Math.log(Math.pow(10,-100))-Math.log(initialTemp))/iter); //calculating the cooling rate
    }


//probability of acceptance method
private static double Acceptance(double NEWTOURFITNESS, double OLDTOURFITNESS, double currentTemperature) {
	
	return Math.exp(-(Math.abs(NEWTOURFITNESS - OLDTOURFITNESS))/currentTemperature);
	
	
}
/**
 * 
 * @param oldFitness
 * @param newFitness
 * @return boolean, whether the fitness is better or not
 */
private boolean isBetter(double oldFitness, double newFitness) {
	if(oldFitness > newFitness) {
		return false;
	}
	else {
		return true;
	}
}
}
//			temp = temp * Cooling(CURRENTTOURFITNESS,NEWTOURFITNESS,INIT_TEMP);
