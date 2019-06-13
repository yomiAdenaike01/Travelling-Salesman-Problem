import java.util.ArrayList;

public class RandomMutationHillClimbing {
	public static final int ITER_MAX = 100;
	
	
	
	/**
	 * 
	 * @param distances
	 * @param ITER_MAX_RMHC
	 * @return
	 */
	public CompleteSolution randomHillClimbing(double[][] distances, int ITER_MAX_RMHC)
	{
		
		//getting the amount of distances
		int n = distances.length;
		//generate a random start point 
		CompleteSolution STARTPOINT = new CompleteSolution(n);
		ArrayList<Double> Fitnesses = new ArrayList<>();
		
		for(int iteration = 0;iteration <= ITER_MAX_RMHC; iteration++) {
			//copy current tour into 
			CompleteSolution oldTour = new CompleteSolution(STARTPOINT.getTour(),n);
			//evaluate the current distance for that tour
			double oldTourFitness = oldTour.calcFitness(distances);
			Fitnesses.add(oldTourFitness);
			//make small changes to the current tour
			STARTPOINT.makeSmallChanges();
			//start a new tour with the changes made
			CompleteSolution newTour = new CompleteSolution(STARTPOINT.getTour(),n);
			//evaluate the distance of the new tour
			double newTourFitness = newTour.calcFitness(distances);
			//check the old fitness to the new fitness
			Fitnesses.add(newTourFitness);
			//checking the oldfitness against the new fitness
		
			if(isBetter(oldTourFitness, newTourFitness)) {
			//if new fitness is worse the old fitness then the old tour becomes the new one
				STARTPOINT = oldTour;
			}
			//System.out.println("iteration "+iteration+" fitness "+oldTourFitness);
			
		}
		return (STARTPOINT);
		
	}
	/**
	 * 
	 * @param oldFitness
	 * @param newFitness
	 * @return boolean 
	 */
	private boolean isBetter(double oldFitness, double newFitness) {
	
		if(newFitness > oldFitness) {
			return true;
		}else {
			return false;
		}
	}

}
