package gov.ssa.assignment2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TwoDiceGame {	
	// Controls how many times we play the game
	public static final int NUM_GAMES_TO_PLAY = 30;
	
	public static final int QUIT_VALUE = 7;
					
	// The positions where the statistic values are stored
	public static final int POS_MIN_SCORE = 0;
	public static final int POS_MAX_SCORE = 1;
	public static final int POS_AVG_SCORE = 2;
	
	public static void main(String[] args) {
		// Create the dice
		Die die1 = new Die(0);
		Die die2 = new Die(1);
						
		// Keep track of the scores for all games played for generating statistics
		int[] allScores = new int[NUM_GAMES_TO_PLAY];
		
		// Play the game for NUM_GAMES_TO_PLAY
		for(int i = 1; i <= NUM_GAMES_TO_PLAY; i++) {
			// Start a new total for each game
			int grandTotal = 0;
			int totalRollRound = 0;
									
			System.out.print("For game #" + i + " the rolls (die1, die2) are:");
						
			do {
				int roll1 = die1.rollDie();
				int roll2 = die2.rollDie();
				totalRollRound = roll1 + roll2;
				
				System.out.print("(" + roll1 + ", " + roll2 + ")\t");
				
				if(totalRollRound != QUIT_VALUE) {
					grandTotal += totalRollRound;
				}
				
			} while (totalRollRound != QUIT_VALUE);
						
			System.out.println("\nThe game has ended with a score of " + grandTotal + "\n");
			
			// Store the score for this game
			allScores[i - 1] = grandTotal;
		}
		
		// Calculate and display the statistics as long as we have played at least one game
		if(allScores.length > 0) {
			printStatistics(calculateStatistics(allScores));
		}
	}
	
	// If we get to this method, we're guaranteed to have at least one score - we've checked
	public static List<Object> calculateStatistics(int[] scores) {
		int min = scores[0];
		int max = scores[0];
		int total = 0;
		double average = 0.0;
		
		for(int i = 0; i < scores.length; i++) {
			if(scores[i] < min) {
				min = scores[i];
			}
			else if(scores[i] > max) {
				max = scores[i];
			}
			
			total += scores[i];
		}
		
		// Cast one of them to an integer so we avoid integer division
		average = total / (double) scores.length;
		
		List<Object> statsList = new ArrayList<Object>();
		statsList.add(new Integer(min));
		statsList.add(new Integer(max));
		statsList.add(new Double(average));
		
		return statsList;
	}
	
	// Utility method to display the statistics
	public static void printStatistics(List<Object> statsList) {
		System.out.println("Minimum score: " + statsList.get(POS_MIN_SCORE));
		System.out.println("Maximum score: " + statsList.get(POS_MAX_SCORE));
		System.out.printf("Average score: %.2f", statsList.get(POS_AVG_SCORE));
	}

}

// A class to represent a die
class Die {
	// Playing with a 6-sided die - this could be changed to represent other sided dies
	public static final int MAX_DIE_VALUE = 6;
	
	private long seed;
	private Random rand;
	
	// Adding dieNo to make sure each seed for the die is different
	public Die(int dieNo) {
		// Initialize the random number generator once for each die
		seed = (new Date()).getTime() + dieNo;
		rand = new Random(seed);
	}
	
	public int rollDie() {
		// Translate the roll of 0-5 into the range 1-6
		return rand.nextInt(MAX_DIE_VALUE) + 1;
	}
}