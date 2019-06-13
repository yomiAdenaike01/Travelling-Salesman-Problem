import java.util.ArrayList;
import java.util.Collections;

import Defaults.CS2004;

public class CompleteSolution {
	private double CompleteFitness;
	private ArrayList<Integer> Tour;
	/**
	 * 
	 * @param instance
	 * @param numberOfCities
	 */
	public CompleteSolution(ArrayList<Integer> instance, int numberOfCities) {
		if(instance == null) {
			Tour = RandomStart(numberOfCities);
		} else {
			Tour = (ArrayList<Integer>) instance.clone(); 
		}
	}
	//GENERATE RANDOM ARRAY EACH TIME
	/**
	 * 
	 * @param n
	 * @return
	 */
	private ArrayList<Integer> ShuffledNodes(int n) {
		
		Tour = new ArrayList<>();
		
		//minus one because index starts from 0
		for (int i = 0; i <n - 1;i++ )
		{
			//placing the number of cities
			Tour.add(i);
		}
		Collections.shuffle(Tour); 
		return Tour;
		
		
	}

	
	/**
	 * 
	 * @param n
	 */
	public CompleteSolution(int n) {
		Tour  = ShuffledNodes(n);
	}
	
	private ArrayList<Integer> RandomStart(int n) {
		Tour = new ArrayList<Integer>();
		
		for(int i = 0; i < n - 1; i++) {
			Tour.add(i);
		}
		Collections.shuffle(Tour);
		return Tour;
		
	}
	
	
	
	
	/**
	 * @params n number of cities
	 * OBTAIN THE NODES FROM THE READ FILE
	 * LOOP THROUGH ENTIRE NODES
	 * PLACE NODES INSIDE DOUBLE ARRAY
	 * OBTAINING DISTANCE  = S
	 * WHICH IS THE FITNESS
	 */
	
	public double calcFitness(double[][] distances) {
		
		double s = 0;
		 int n = Tour.size();
		
		for(int i = 0; i<n - 1; i++) {
			int x = Tour.get(i);
			int y = Tour.get(i+1);
			//keep adding the distances 
			s = s + distances[x][y];
			
		}
		//first node will be the first index of the array
		Integer StartNode = Tour.get(0);
		//last node will be the last -1 because of ZERO INDEX COUNTING
		Integer EndNode = Tour.get(n - 1);
		//fitness will be the total distances;
		s = s + distances[EndNode][StartNode];
		CompleteFitness = s;
		return CompleteFitness;
		
	}
	
	public void makeSmallChanges() {
		/*
		 * MAKE THE SMALL CHANGES AND THEN EVALUATE THE FITNESS OF THE SOLUTION
		 */
		//SWAPPING THE NODES AROUND 
		ArrayList<Integer> currentTour = new ArrayList<Integer>();
		//MAKE THE CURRENT TOUR EQUAL TO THE PREVIOUS TOUR 
		currentTour = (ArrayList<Integer>)Tour.clone();
		int nodeA = CS2004.UI(0, currentTour.size() - 1);
		int nodeB = CS2004.UI(0, currentTour.size() - 1);
		//SWAPPING THEM
		Collections.swap(Tour, nodeA, nodeB);

		
	}
	
	public ArrayList<Integer> getTour(){
		return Tour;
	}
	
	public double getFitness() {
		return CompleteFitness;
	}

}
