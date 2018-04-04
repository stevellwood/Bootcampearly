package ssa;

public class SimulatedBowling {

	private static java.util.Random rnd = new java.util.Random((new java.util.Date()).getTime());
	private static final int NUM_FRAMES = 10;
	private static final int NUM_GAMES = 3;
	
	private static final int STRIKE = 15;
	private static final int SPARE = 12;
			
	// 2-D array indexes the number of games and total number of bowls per game, which
	// could potentially be 21
	private int bowlingScores[][] = new int[NUM_GAMES][NUM_FRAMES * 2 + 1];
		
	public void bowl() {
		// For each game
		for(int game = 0; game < bowlingScores.length; game++) {

			// For each frame
			for(int frame = 0; frame < NUM_FRAMES; frame++) {
				//System.out.println("For frame " + (frame+1) + ": ");
				// Bowl first ball
				int firstBowl = getPinsKnockedDown();
				bowlingScores[game][frame*2] = firstBowl;
				
				// Bowl again if there are pins left
				int secondBowl = 0;
				if(firstBowl < 10) {
					secondBowl = getPinsKnockedDown();
					
					if(secondBowl >= (10 - firstBowl)) {
						secondBowl = (10 - firstBowl);
					}
					
					bowlingScores[game][frame*2 + 1] = secondBowl;
				}
				
				// Do we bowl for a third time during the 10th frame?
				if( (frame + 1 == NUM_FRAMES) && (firstBowl + secondBowl == 10)) {
					int thirdBowl = getPinsKnockedDown();
					bowlingScores[game][frame*2 + 2] = thirdBowl;
				}
			}
		}
	
		doScoring();
		printHeader();
		printScores();
	}
	
	private void doScoring() {
		for(int game = 0; game < bowlingScores.length; game++) {
			
			for(int frame = 0; frame < NUM_FRAMES; frame++) {
				if(frame < NUM_FRAMES - 1) {
					// Strike
					if(bowlingScores[game][frame*2] == 10) {
						bowlingScores[game][frame*2] = 0;
						bowlingScores[game][frame*2+1] = STRIKE;						
					} 
					// Spare
					else if(bowlingScores[game][frame*2] + 
							bowlingScores[game][frame*2+1] == 10) {
						bowlingScores[game][frame*2] = 0;
						bowlingScores[game][frame*2+1] = SPARE;
						
						
					} 
				}
			}
		}
	}
			
	private int getPinsKnockedDown() {
		
		int randomInt = rnd.nextInt(11); // zero to ten
		return randomInt;
	}
	
	private void printHeader() {
		System.out.print(String.format("%-14s", "Frames"));
		for(int frame = 1; frame <= NUM_FRAMES; frame++) {
			System.out.print(String.format("%2s", " "));
			System.out.print(String.format("%d", frame));
			System.out.print(String.format("%7s", " "));
		}
		System.out.println(String.format("%8s", "Total"));
		System.out.println();
		
	}

	private void printScores() {
		int totalScoreSeries = 0;
		for(int game = 0; game < bowlingScores.length; game++) {
			int totalScoreGame = 0;
			System.out.print(String.format("%-10s", ("Game " + (game+1))));
			
			for(int frame = 0; frame < NUM_FRAMES; frame++) {
				String frameScoreString = "";
				
				if(frame < NUM_FRAMES - 1) {
					frameScoreString = String.format("[%s][%s]", 
						bowlingScores[game][frame*2], bowlingScores[game][frame*2+1] );
					System.out.print(String.format("%10s", frameScoreString));
				} else {
					frameScoreString = String.format("[%s][%s][%s]", 
						bowlingScores[game][frame*2], bowlingScores[game][frame*2+1],
						bowlingScores[game][frame*2+2]);
					System.out.print(String.format("%12s", frameScoreString));
				}
			}
			System.out.println();
			System.out.print(String.format("%14s", " "));
			
			for(int frame = 0; frame < NUM_FRAMES; frame++) {
				int totalFrame = bowlingScores[game][frame*2] + bowlingScores[game][frame*2+1];
				
				// Add the scores of the two bowls for each frame under 10
				if(frame < NUM_FRAMES - 1) {
					System.out.print(String.format("%3s", " "));
					System.out.print(String.format("%3d", totalFrame));	
					System.out.print(String.format("%4s", " "));
				} else { // Add the scores of the possibly three bowls for frame 10
					totalFrame += bowlingScores[game][frame*2+2];
					System.out.print(String.format("%5s", " "));
					System.out.print(String.format("%3d", totalFrame));
					System.out.print(String.format("%4s", " "));
				}
				totalScoreGame += totalFrame;
			}
			totalScoreSeries += totalScoreGame;
			System.out.println(String.format("%6s\n", totalScoreGame));				
		}
		System.out.println(String.format("%-114s%8d", "Total Series", totalScoreSeries));
	}
}
