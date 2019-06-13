import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;

import Defaults.MST;
import Defaults.TSP;

public class TourManager {
	//this is so that by default they will run 1000 times
	public static final int ITER_MAX = 1000;
	public static final int ITER_MIN = 0;
	public static String filename = "D:\\Docs\\Computer Science\\CS2004 TSP Data (2017-2018)\\TSP_70.txt";
	public static String filenameOPT = "D:\\Docs\\Computer Science\\CS2004 TSP Data (2017-2018)\\TSP_70_OPT.txt";
	public static double[][] distances = TSP.ReadArrayFile(filename, " ");
	public static double[][] optimalDistances = TSP.ReadArrayFile(filename, " ");
	public static long startTime;
	//main functions
	public static void main(String[] args) {
		//selecting the file that you want to run it on 
		GlobalOptimalSoltuion();
		LocalOptimalSolution();
		//running the iterations
		//starting the time
		startTime = System.nanoTime();
		for(int i = 0; i<10; i++) {
	
		
			//SimulatedAnnealing(100000);
			//RandomHillClimbing();
			//RandomRestart(100000);
			StocasticHillClimbing(10);
			// LocalOptimalSolution();
			
		}
		//determining the time taken 
		runTime();
	
	}
	


	//-----------------------------------WORKING---------------------------------------------------//
	private static void RandomHillClimbing(int iterations) {
		RandomMutationHillClimbing rand = new RandomMutationHillClimbing();
		CompleteSolution optimalTour = rand.randomHillClimbing(distances, iterations);
		System.out.println(optimalTour.getFitness());
		
	}

	private static void RandomRestart(int iterations) { 
		 //instantiating the class
		RandomRestartHillClimbing randRestart = new RandomRestartHillClimbing();
		CompleteSolution optimalTourRestart = randRestart.RandomRestartHillClimbing(distances, iterations, 1 );
		double fitness = optimalTourRestart.getFitness();
		
		System.out.println( fitness );
	}
	private static void SimulatedAnnealing(int iterations) {
		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
		CompleteSolution simulatedAnnealingTour = simulatedAnnealing.SimulatedAnnealing(distances,iterations, 1000, false);
		double simulatedAnnealingFitness = simulatedAnnealingTour.getFitness();
		System.out.println(simulatedAnnealingFitness);
		
	}
	
	

	
	private static void LocalOptimalSolution() {
		//getting the optimal solutions fitness
		ArrayList<Integer> optimalSolutionArray = TSP.ReadIntegerFile(filenameOPT);
		CompleteSolution optimalSolution = new CompleteSolution(optimalSolutionArray, distances.length);
		double optimalFit = optimalSolution.calcFitness(distances);
		ArrayList<Integer> optimalTour = new ArrayList<Integer>();
		optimalTour.addAll(optimalSolution.getTour());
		System.out.println("LOCAL OPTIMA FITNESS "+optimalFit);
		System.out.println("LOCAL OPTIMA TOUR: "+ optimalSolution.getTour());
		
		
		
	}
	private static void GlobalOptimalSoltuion() {
		//running the minimum spanning tree
		double[][] globalMST = MST.PrimsMST(distances);
		System.out.println("Global MST OPTIMUM FITNESS: "+MST.MSTFitness(globalMST));
		
	}
	
	private static void StocasticHillClimbing(int iterations) {
		StocasticHillClimbing s = new StocasticHillClimbing();
		CompleteSolution sT = s.StochasticHillClimbing(distances, iterations,0.0003,false);
		System.out.println(sT.getFitness());
		
		
	}
	
	private static void runTime() {
		//ending the runtime
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		double convertedToSeconds = (double)(elapsedTime / 1000000000.0);
		System.out.println("Elapsed Time "+ convertedToSeconds);
		// TODO Auto-generated method stub
		
	}
	//-----------------------------------WORKING---------------------------------------------------//
	
}
